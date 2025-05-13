package domain;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Scanner;

public final class TurnController {
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
		int extraTurns = 0;

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
						// semi-stub for now (below)
						turnOver = doCardAction(cardType);
					} catch (IllegalArgumentException e) {
						System.out.println("Invalid card play: " + e.getMessage());
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
							System.out.println("Card could not be added: " + e.getMessage());
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

		return new TurnResult(extraTurns, eliminated, playerWon);
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

	private boolean doCardAction(CardType cardType) {
		switch (cardType) {
			case SKIP:
				System.out.println("SKIP card played. Turn ends immediately.");
				return true;
			default:
				System.out.printf("Played card: %s%n", cardType);
		}
		return false;
}


	private CardType promptCardChoice(Player player) {
		CardType[] hand = player.viewHand().keySet().toArray(new CardType[0]);
		while (true) {
			System.out.println("Select a card to play:");
			for (int i = 0; i < hand.length; i++) {
				System.out.printf("[%d] %s (%d)%n", i, hand[i], player.viewHand().get(hand[i]));
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
