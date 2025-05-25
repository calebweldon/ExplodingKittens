package domain;

import java.security.SecureRandom;
import ui.TurnView;

// suppress deck warning
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public final class TurnController {
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck can be shared")
	private final Deck deck;
	private final TurnView turnView;

	public TurnController(Deck deck, TurnView turnView) {
		if (deck == null) {
			throw new IllegalArgumentException("Deck cannot be null");
		}
		if (turnView == null) {
			throw new IllegalArgumentException("TurnView cannot be null");
		}
		this.deck = deck;
		this.turnView = turnView;
	}

	public TurnResult takeTurn(Player player) {
		boolean turnOver = false;
		boolean eliminated = false;
		boolean playerWon = false;
		TurnResult specialAction = TurnResult.CONTINUE;

		while (!turnOver) {
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
						playCard(player, cardType);
						// Get action based on card played
						specialAction = getCardAction(cardType);
						// Playing any card ends the turn
						turnOver = true;
					} catch (IllegalArgumentException e) {
						turnView.showInvalidCardPlay(e.getMessage());
					}
					break;
				}
				case "draw": {
					CardType drawn = drawCard();
					turnView.showCardDrawn(drawn);
					if (drawn == CardType.EXPLODING_KITTEN) {
						eliminated = !handleExplodingKitten(player);
					} else {
						try {
							player.addCard(drawn);
						} catch (IllegalArgumentException e) {
							turnView.showCardCouldNotBeAdded(
								e.getMessage()
							);
						}
					}
					endPlayerTurn();
					turnOver = true;
					break;
				}
				default:
					turnView.showUnexpectedAction();
			}
		}

		if (eliminated) {
			return TurnResult.ELIMINATED;
		} else if (playerWon) {
			return TurnResult.WON;
		} else {
			return specialAction;
		}
	}

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

	public void playCard(Player player, CardType cardType) {
		player.playCard(cardType);
	}

	public String getCardInfo(CardType type) {
		switch (type) {
			case EXPLODING_KITTEN:
				return "Draw this and you're out—unless you defuse it.";
			case DEFUSE:
				return "Defuse an Exploding Kitten.";
			default:
				return "No description available.";
		}
	}

	public void endPlayerTurn() {
		// placeholder for cleanup logic
	}

	public void endPrematurely() {
		turnView.showTurnEndedPrematurely();
	}
	
	public CardType drawCard() {
		return deck.drawCard();
	}

	public boolean handleExplodingKitten(Player player) {
		if (player.viewHand().getOrDefault(CardType.DEFUSE, 0) > 0) {
			turnView.showDefuseUsed();
			player.removeCard(CardType.DEFUSE);
			deck.insertCardAtRandomIndex(CardType.EXPLODING_KITTEN);
			return true;
		}
		turnView.showNoDefuseFound();
		return false;
	}

	private CardType promptCardChoice(Player player) {
		return turnView.promptCardChoice(player);
	}
}
