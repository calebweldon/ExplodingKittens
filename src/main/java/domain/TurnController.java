package domain;

import domain.cardcontroller.CardController;
import domain.cardcontroller.DrawCardController;
import domain.cardcontroller.ActionCardController;
import ui.TurnView;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public final class TurnController implements TurnSubject,
		LastPlayedSubject {
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck can be shared")
	private final Deck deck;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
			justification = "Player reference is intentionally stored for game state")
	private Player currPlayer = null;
	private final TurnView turnView;
	private final Map<CardType, CardController> cardControllers;
	private final List<TurnObserver> turnObservers;
	private final List<LastPlayedObserver> lastPlayedObservers;

	public TurnController (Deck deck, TurnView turnView,
				Map<CardType, CardController> cardControllers) {
		if (deck == null) {
			throw new IllegalArgumentException("Deck cannot be null");
		}
		if (turnView == null) {
			throw new IllegalArgumentException("TurnView cannot be null");
		}
		if (cardControllers == null) {
			throw new IllegalArgumentException("CardControllers cannot be null");
		}
		this.deck = deck;
		this.turnView = turnView;
		this.cardControllers = new HashMap<>(cardControllers);

		this.turnObservers = new ArrayList<>();
		this.lastPlayedObservers = new ArrayList<>();
		for (Map.Entry<CardType, CardController> entry : cardControllers.entrySet()) {
			CardController value = entry.getValue();
			if (value instanceof TurnObserver) {
				TurnObserver observer = (TurnObserver) value;
				registerObserver(observer);
			}
			if (value instanceof LastPlayedObserver) {
				LastPlayedObserver observer = (LastPlayedObserver) value;
				registerObserver(observer);
			}
		}
	}

	public TurnResult takeTurn(Player player) {
		if (player == null) {
			throw new IllegalArgumentException("Player cannot be null");
		}
		if (this.currPlayer != player) {
			this.currPlayer = player;
			notifyTurnObservers();
		}

		TurnResult specialAction = TurnResult.CONTINUE;

		int implodingIndex = deck.getImplodingIndex();
		turnView.showImplodingIndex(implodingIndex);
		turnView.displayHand(currPlayer);

		loop:
		while (specialAction == TurnResult.CONTINUE) {
			String input = promptForInput();
			switch (input) {
				case "play": {
					if (player.viewHand().isEmpty()) {
						turnView.showNoCardsMessage();
						continue;
					}
					CardType cardType = promptCardChoice();
					try {
						this.currPlayer.playCard(cardType);
						specialAction = playCardAction(cardType);
						notifyLastPlayedObservers(cardType);
					} catch (IllegalArgumentException e) {
						turnView.showInvalidCardPlay(cardType);
					}
					break;
				}
				case "draw": {
					CardType drawn = drawCardFromDeck();
					turnView.showCardDrawn(drawn);
					specialAction = drawCardAction(drawn);
					break loop;
				}
				case "info": {
					turnView.getCardInfo();
					break;
				}
				default:
					turnView.showUnexpectedAction();
			}
		}
		return specialAction;
	}

	private String promptForInput() {
		return turnView.promptForInput();
	}

	private TurnResult playCardAction(CardType cardType) {
		CardController controller = cardControllers.get(cardType);
		if (controller instanceof ActionCardController) {
			ActionCardController actionController =
					(ActionCardController) controller;
			TurnResult result = actionController.handleCardAction();
			if (result == TurnResult.ELIMINATED) {
				turnView.reinsertExplodia();
				reinsertExplodia();
			}
			return result;
		}
		throw new IllegalArgumentException("Unsupported card type: " + cardType);
	}

	private TurnResult drawCardAction(CardType drawn) {
		CardController controller = cardControllers.get(drawn);
		if (controller instanceof DrawCardController) {
			DrawCardController drawController =
					(DrawCardController) controller;
			TurnResult result = drawController.handleCardDraw();
			updateLastPlayedIfDefused(drawn, result);
			return result;
		}
		currPlayer.addCard(drawn);
		return TurnResult.CONTINUE;
	}

	private CardType drawCardFromDeck() {
		return deck.drawCard();
	}

	private CardType promptCardChoice() {
		return turnView.promptCardChoice(this.currPlayer);
	}

	private void reinsertExplodia() {
		Map<CardType, Integer> hand = currPlayer.viewHand();
		int numExplodia = hand.getOrDefault(CardType.EXPLODIA, 0);
		for (; numExplodia > 0; numExplodia--) {
			deck.insertCardAtRandomIndex(CardType.EXPLODIA);
		}
	}

	private void updateLastPlayedIfDefused(CardType drawn, TurnResult result) {
		if (result == TurnResult.CONTINUE && drawn == CardType.EXPLODING_KITTEN) {
			notifyLastPlayedObservers(CardType.DEFUSE);
		}
	}

	public void registerObserver(TurnObserver controller) {
		turnObservers.add(controller);
	}

	public void unregisterObserver(TurnObserver controller) {
		turnObservers.remove(controller);
	}

	public void notifyTurnObservers() {
		for (TurnObserver observer : turnObservers) {
			observer.updatePlayer(this.currPlayer);
		}
	}

	int getTurnObserverSize() {
		return turnObservers.size();
	}

	public void registerObserver(LastPlayedObserver observer) {
		lastPlayedObservers.add(observer);
	}

	public void unregisterObserver(LastPlayedObserver observer) {
		lastPlayedObservers.remove(observer);
	}

	public void notifyLastPlayedObservers(CardType lastPlayed) {
		for (LastPlayedObserver observer : lastPlayedObservers) {
			observer.updateLastPlayed(lastPlayed);
		}
	}

	int getLastPlayedObserverSize() {
		return lastPlayedObservers.size();
	}

	// For Integration Feature Test - Game Setup
	public int getCardCount(CardType cardType) {
		return this.deck.getCardCount(cardType);
	}
}
