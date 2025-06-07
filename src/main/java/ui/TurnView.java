package ui;

import domain.CardType;

import domain.Player;
import domain.cardcontroller.CardController;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.text.MessageFormat;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class TurnView {
	private final Scanner scanner;
	private final ResourceBundle labels;
	private static final int ROWLENGTH = 7;

	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Access getInfo() method")
	private final Map<CardType, CardController> cardControllers;

	public TurnView(Map<CardType, CardController> cardControllers) {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
		this.cardControllers = cardControllers;
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
			System.out.print(turnViewPromptAction);

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

	public CardType promptCardChoice(Player player) {
		Map<CardType, Integer> hand = player.viewHand();
		List<CardType> cards = new ArrayList<> (hand.keySet());
		while (true) {
			System.out.print("Select a card to play:");
			printCards(hand, cards);
			System.out.print("Enter index: ");
			try {
				int idx = Integer.parseInt(scanner.nextLine());
				if (idx >= 0 && idx < cards.size()) {
					return cards.get(idx);
				}
			} catch (NumberFormatException ignored) { }
			System.out.println("Invalid index.");
		}
	}
	
	public void displayHand(Player player) {
		Map<CardType, Integer> hand = player.viewHand();
		List<CardType> cards = new ArrayList<> (hand.keySet());
		System.out.printf("%nYour hand: ");
		printCards(hand, cards);
	}

	private void printCards(Map<CardType, Integer> hand, List<CardType> cards) {
		for (int i = 0; i < cards.size(); i++) {
			CardType card = cards.get(i);
			if (i % ROWLENGTH == 0) {
				System.out.println();
			}
			System.out.printf("[%d] %s (%d)    ", i, card, hand.get(card));
		}
		System.out.println();
	}

	public void getCardInfo() {
		System.out.print("Select card information:");
		CardType[] cards = CardType.values();
		for (int i = 0; i < CardType.values().length; i++) {
			if (i % ROWLENGTH == 0) {
				System.out.println();
			}
			System.out.printf("[%d] %s    ", i, cards[i]);
		}
		try {
			System.out.printf("%nEnter index: ");
			int idx = Integer.parseInt(scanner.nextLine());
			CardType card = cards[idx];
			CardController cardController = cardControllers.get(card);
			cardController.getInfo();
		} catch (NumberFormatException ignored) {
			System.out.println("Invalid index.");
		}
	}

	public void reinsertExplodia() {
		System.out.println("Returning eliminated player's Explodia(s) to the deck");
	}
}
