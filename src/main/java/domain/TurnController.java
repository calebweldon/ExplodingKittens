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

public final class TurnController implements TurnSubject {
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck can be shared")
	private final Deck deck;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
			justification = "Player reference is intentionally stored for game state")
	private Player currPlayer = null;
	private final TurnView turnView;
	private final Map<CardType, CardController> cardControllers;
	private final List<TurnObserver> observers;

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

		this.observers = new ArrayList<>();
		for (Map.Entry<CardType, CardController> entry : cardControllers.entrySet()) {
			CardController value = entry.getValue();
			if (value instanceof TurnObserver) {
				TurnObserver observer = (TurnObserver) value;
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
			notifyObservers();
		}

		TurnResult specialAction = TurnResult.CONTINUE;

		int implodingIndex = deck.getImplodingIndex();
		turnView.showImplodingIndex(implodingIndex);
		turnView.displayHand(currPlayer);
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
					} catch (IllegalArgumentException e) {
						turnView.showInvalidCardPlay(cardType);
					}
					break;
				}
				case "draw": {
					CardType drawn = drawCardFromDeck();
					turnView.showCardDrawn(drawn);
					specialAction = drawCardAction(drawn);
					return specialAction;
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
			// TODO: Handle adding to player hand if needed
			return drawController.handleCardDraw();
		}
		try {
			currPlayer.addCard(drawn);
		} catch (IllegalArgumentException e) {
			turnView.showCardCouldNotBeAdded(drawn);
		}
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

	public void registerObserver(TurnObserver controller) {
		observers.add(controller);
	}

	public void unregisterObserver(TurnObserver controller) {
		observers.remove(controller);
	}

	public void notifyObservers() {
		for (TurnObserver observer : observers) {
			observer.updatePlayer(this.currPlayer);
		}
	}

	int getObserverSize() {
		return observers.size();
	}

	// For Integration Feature Test - Game Setup
	public int getCardCount(CardType cardType) {
		return this.deck.getCardCount(cardType);
	}
}
