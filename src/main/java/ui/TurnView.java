package ui;

import domain.CardType;
import domain.Player;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class TurnView {
	private final Scanner scanner;
	private final ResourceBundle labels;

	public TurnView() {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	public void showNoCardsMessage() {
		final String turnViewNoCards = labels.getString("turnViewNoCards");
		System.out.println(turnViewNoCards);
	}

	public void showInvalidCardPlay(String errorMessage) {
		final String turnViewInvalidCard = labels.getString("turnViewInvalidCard");
		String invalidCardMessage = MessageFormat.format(turnViewInvalidCard, errorMessage);
		System.out.println(invalidCardMessage);
	}

	public void showCardDrawn(CardType drawn) {
		final String turnViewShowCardDrawn = labels.getString("turnViewShowCardDrawn");
		String drawnCardMessage = MessageFormat.format(turnViewShowCardDrawn, drawn);
		System.out.println(drawnCardMessage);
	}

	public void showCardCouldNotBeAdded(String errorMessage) {
		final String turnViewCardCouldNotBeAdded =
				labels.getString("turnViewCardCouldNotBeAdded");
		String cardNotAddedMessage =
				MessageFormat.format(turnViewCardCouldNotBeAdded, errorMessage);
		System.out.println(cardNotAddedMessage);
	}

	public void showUnexpectedAction() {
		final String turnViewUnexpectedAction =
				labels.getString("turnViewUnexpectedAction");
		System.out.println(turnViewUnexpectedAction);
	}


	public String promptForInput() {
		while (true) {
			final String turnViewPromptAction =
					labels.getString("turnViewPromptAction");
			System.out.println(turnViewPromptAction);

			String input = scanner.nextLine().trim().toLowerCase();
			if ("play".equals(input) || "draw".equals(input) 
				|| "info".equals(input)) {
				return input;
			}
			final String turnViewInvalidInput =
					labels.getString("turnViewInvalidInput");
			System.out.println(turnViewInvalidInput);
		}
	}

	public void showDefuseUsed() {
		final String turnViewShowDefuse = labels.getString("turnViewShowDefuse");
		System.out.println(turnViewShowDefuse);
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

	public void reinsertExplodia() {
	}
}
