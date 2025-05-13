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
				case "play" -> {
					if (player.viewHand().isEmpty()) {
						System.out.println("You have no cards to play.");
						continue;
					}
					System.out.println("Your hand: " + player.viewHand());
					CardType cardType = promptCardChoice(player);
					try {
						PlayCard(player, cardType);
						turnOver = doCardAction(cardType); // semi-stub for now
					} catch (IllegalArgumentException e) {
						System.out.println("Invalid card play: " + e.getMessage());
					}
				}
				case "draw" -> {
					CardType drawn = drawCard();
					System.out.println("You drew: " + drawn);
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
				}
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

	public void PlayCard(Player player, CardType cardType) {
		player.playCard(cardType);
	}

	public String getCardInfo(CardType type) {
		// Stub: add descriptions for each card type if needed
		return switch (type) {
			case EXPLODING_KITTEN -> "Draw this and you're out—unless you defuse it.";
			case DEFUSE -> "Defuse an Exploding Kitten.";
			default -> "No description available.";
		};
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
			case SKIP -> {
				System.out.println("SKIP card played. Turn ends immediately.");
				return true;
			}
			default -> System.out.println("Played card: " + cardType);
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
			} catch (NumberFormatException ignored) {}
			System.out.println("Invalid index.");
		}
	}
}
