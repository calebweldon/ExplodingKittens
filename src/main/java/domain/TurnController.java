package domain;

import java.security.SecureRandom;
import ui.TurnView;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

// TODO: Go through and restrict access modifiers - most methods should be private
// TODO: Consider having one SuppressFBWarnings before class
public final class TurnController implements TurnSubject {
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck can be shared")
	private final Deck deck;
	private final TurnView turnView;
	private final Map<CardType, CardController> cardControllers;
	private final List<TurnObserver> observers = new ArrayList<>();
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
		justification = "Player reference is intentionally stored for game state")
	private Player currPlayer;

	// TODO: Update GameSetup and Remove this Constructor
	public TurnController(Deck deck, TurnView turnView) {
		this(deck, turnView, new HashMap<>());
	}

	public TurnController(Deck deck, TurnView turnView,
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
		// TODO: Initialize list of observers
	}

	@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
		justification = "Player reference is intentionally stored for game state")
	public TurnResult takeTurn(Player player) {
		this.currPlayer = player;
		// TODO: notify observers only if there is a change in currPlayer?
		notifyObservers();

		TurnResult specialAction = TurnResult.CONTINUE;

		while (specialAction == TurnResult.CONTINUE) {
			// TODO: Print hand immediately
			String input = promptForInput();
			switch (input) {
				case "play": {
					if (player.viewHand().isEmpty()) {
						turnView.showNoCardsMessage();
						continue;
					}
					turnView.showPlayerHand(player.viewHand());
					CardType cardType = promptCardChoice(player);
					try {
						specialAction = playCard(player, cardType);
						// TODO: remove unnecessary comments
						// Loop will exit if specialAction != CONTINUE
					} catch (IllegalArgumentException e) {
						turnView.showInvalidCardPlay(e.getMessage());
					}
					break;
				}
				case "draw": {
					CardType drawn = drawCard();
					turnView.showCardDrawn(drawn);

					// Use controller pattern for special draw cards
					CardController controller = cardControllers.get(drawn);
					if (controller instanceof DrawCardController) {
						DrawCardController drawController =
							(DrawCardController) controller;
						return drawController.handleCardDraw();
					}

					// TODO: Refactor logic
					//  - Exploding Kitten DOES have a DrawCardController
					// Handle cards without DrawCardController
					// (like EXPLODING_KITTEN)
					if (drawn == CardType.EXPLODING_KITTEN) {
						if (!handleExplodingKitten(player)) {
							return TurnResult.ELIMINATED;
						}
					} else {
						try {
							player.addCard(drawn);
						} catch (IllegalArgumentException e) {
							turnView.showCardCouldNotBeAdded(
								e.getMessage()
							);
						}
					}
					return specialAction;
				}
				case "info": {
					// TODO: Remove prints and call TurnView
					System.out.println("=== CARD INFO ===");
					System.out.println("Cxards in your hand:");
					for (CardType cardType : player.viewHand().keySet()) {
						int count = player.viewHand().get(cardType);
						if (count > 0) {
							System.out.printf(
								"%s (%d): ",
								cardType,
								count
							);
							turnView.showCardInfo(cardType);
						}
					}
					System.out.println("=================");
					break;
				}
				default:
					turnView.showUnexpectedAction();
			}
		}

		return specialAction;
	}

	// TODO: Remove - temporary fallback until all cards have controllers
	// (SkipCardController, AttackCardController)
	private TurnResult getCardAction(CardType cardType) {
		turnView.showCardPlayed(cardType);
		switch (cardType) {
			case SKIP:
				return TurnResult.SKIP;
			case ATTACK:
				return TurnResult.ATTACK;
			default:
				return TurnResult.CONTINUE;
		}
	}

	public String promptForInput() {
		return turnView.promptForInput();
	}

	public TurnResult playCard(Player player, CardType cardType) {
		// First, remove the card from player's hand
		player.playCard(cardType);

		// Then, execute the card's special action if it has a controller
		CardController controller = cardControllers.get(cardType);
		if (controller instanceof ActionCardController) {
			ActionCardController actionController =
				(ActionCardController) controller;
			return actionController.handleCardAction();
		}

		// TODO: All cards should have controllers - remove this fallback
		// once SkipCardController and AttackCardController are added
		// For cards without controllers (like SKIP, ATTACK), use legacy method temporarily
		return getCardAction(cardType);
	}

	// TODO: Add Corresponding CardController handleDraw()
	public CardType drawCard() {
		return deck.drawCard();
	}

	// TODO: Remove - handle in CardController handleDraw()
	public boolean handleExplodingKitten(Player player) {
		if (player.viewHand().getOrDefault(CardType.DEFUSE, 0) > 0) {
			turnView.showDefuseUsed();
			player.removeCard(CardType.DEFUSE);

			// Let player choose where to put the exploding kitten back
			int index = turnView.promptExplodingKittenIndex(deck.getSize());
			deck.insertCardAtIndex(CardType.EXPLODING_KITTEN, index);

			return true;
		}
		turnView.showNoDefuseFound();
		return false;
	}

	private CardType promptCardChoice(Player player) {
		return turnView.promptCardChoice(player);
	}

	// TODO: Add BVA Analysis and unit tests for observer pattern methods
	// Observer pattern methods
	public void registerObserver(TurnObserver controller) {
		observers.add(controller);
	}

	public void unregisterObserver(TurnObserver controller) {
		observers.remove(controller);
	}

	public void notifyObservers() {
		for (TurnObserver observer : observers) {
			observer.updatePlayer(currPlayer);
		}
	}
}
