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

	public void showPlayerHand(Map<CardType, Integer> hand) {
		System.out.printf("Your hand: %s%n", hand);
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

	public void showCardPlayed(CardType cardType) {
		switch (cardType) {
			case SKIP:
				System.out.println("SKIP card played. Turn ends immediately.");
				break;
			case ATTACK:
				System.out.println("ATTACK card played");
				break;
			default:
				System.out.printf("Played card: %s%n", cardType);
				break;
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

	public void showTurnEndedPrematurely() {
		System.out.println("Turn ended prematurely.");
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
}
