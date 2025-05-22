package domain;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Scanner;

// suppress deck warning
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public final class TurnController {
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck can be shared")
	private final Deck deck;
	private final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

	public TurnController(Deck deck) {
		if (deck == null) {
			throw new IllegalArgumentException("Deck cannot be null");
		}
		this.deck = deck;
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
						System.out.println("You have no cards to play.");
						continue;
					}
					System.out.printf("Your hand: %s%n", player.viewHand());
					CardType cardType = promptCardChoice(player);
					try {
						playCard(player, cardType);
						// Get the appropriate action based on the card played
						specialAction = getCardAction(cardType);
						turnOver = specialAction != TurnResult.CONTINUE;
					} catch (IllegalArgumentException e) {
						System.out.printf(
							"Invalid card play: %s%n",
							e.getMessage()
						);
					}
					break;
				}
				case "draw": {
					CardType drawn = drawCard();
					System.out.printf("You drew: %s%n", drawn);
					if (drawn == CardType.EXPLODING_KITTEN) {
						eliminated = !handleExplodingKitten(player);
					} else {
						try {
							player.addCard(drawn);
						} catch (IllegalArgumentException e) {
							System.out.printf(
								"Card could not be added: %s%n",
								e.getMessage()
							);
						}
					}
					endPlayerTurn();
					turnOver = true;
					break;
				}
				default:
					System.out.println("Should not happen.");
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
		switch (cardType) {
			case SKIP:
				System.out.println("SKIP card played. Turn ends immediately.");
				return TurnResult.SKIP;
			case ATTACK:
				System.out.println("ATTACK card played");
				return TurnResult.ATTACK;
			default:
				System.out.printf("Played card: %s%n", cardType);
				return TurnResult.CONTINUE;
		}
	}

	public String promptForInput() {
		while (true) {
			System.out.print("Enter [play] or [draw]: ");
			String input = scanner.nextLine().trim().toLowerCase();
			if ("play".equals(input) || "draw".equals(input)) {
				return input;
			}
			System.out.println("Invalid input.");
		}
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
		System.out.println("Turn ended prematurely.");
	}
	
	public CardType drawCard() {
		return deck.drawCard();
	}

	public boolean handleExplodingKitten(Player player) {
		if (player.viewHand().getOrDefault(CardType.DEFUSE, 0) > 0) {
			System.out.println("Defuse used. You're safe.");
			player.removeCard(CardType.DEFUSE);
			deck.insertCardAtRandomIndex(CardType.EXPLODING_KITTEN);
			return true;
		}
		System.out.println("No defuse found. You're eliminated.");
		return false;
	}

	private CardType promptCardChoice(Player player) {
		CardType[] hand = player.viewHand().keySet().toArray(new CardType[0]);
		while (true) {
			System.out.println("Select a card to play:");
			for (int i = 0; i < hand.length; i++) {
				System.out.printf(
					"[%d] %s (%d)%n",
					i,
					hand[i],
					player.viewHand().get(hand[i])
				);
			}
			System.out.print("Enter index: ");
			try {
				int idx = Integer.parseInt(scanner.nextLine());
				if (idx >= 0 && idx < hand.length) {
					return hand[idx];
				}
			} catch (NumberFormatException ignored) { }
			System.out.println("Invalid index.");
		}
	}
}
