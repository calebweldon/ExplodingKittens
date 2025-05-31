package ui;

import domain.CardType;
import domain.Player;
import java.util.Map;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class TurnView {
	private final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

	public TurnView() { }

	public void showTopCards(CardType[] topCards) { }

	public void showNoCardsMessage() {
		System.out.println("You have no cards to play.");
	}

	public void showInvalidCardPlay(String errorMessage) {
		System.out.printf("Invalid card play: %s%n", errorMessage);
	}

	public void showCardDrawn(CardType drawn) {
		System.out.printf("You drew: %s%n", drawn);
	}

	public void showCardCouldNotBeAdded(String errorMessage) {
		System.out.printf("Card could not be added: %s%n", errorMessage);
	}

	public void showUnexpectedAction() {
		System.out.println("Should not happen.");
	}


	public String promptForInput() {
		while (true) {
			System.out.print("Enter [play], [draw], or [info]: ");
			String input = scanner.nextLine().trim().toLowerCase();
			if ("play".equals(input) || "draw".equals(input) 
				|| "info".equals(input)) {
				return input;
			}
			System.out.println("Invalid input.");
		}
	}

	public void showDefuseUsed() {
		System.out.println("Defuse used. You're safe.");
	}

	public void showNoDefuseFound() {
		System.out.println("No defuse found. You're eliminated.");
	}

	public CardType promptCardChoice(Player player) {
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

	public int promptExplodingKittenIndex(int deckSize) {
		while (true) {
			System.out.printf(
				"Where do you want to put the Exploding Kitten back? (0-%d): ", 
				deckSize
			);
			try {
				int index = Integer.parseInt(scanner.nextLine());
				if (index >= 0 && index <= deckSize) {
					return index;
				}
				System.out.println("Invalid index. Try again.");
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			}
		}
	}

	public void getInputForCardInfo(Player currPlayer) {
	}
}
