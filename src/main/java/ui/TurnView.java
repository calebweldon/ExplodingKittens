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

	public void showInvalidCardPlay(CardType playedCard) {
		final String turnViewInvalidCard = labels.getString("turnViewInvalidCard");
		String invalidCardMessage = MessageFormat.format(turnViewInvalidCard, playedCard);
		System.out.println(invalidCardMessage);
	}

	public void showCardDrawn(CardType drawn) {
		final String turnViewShowCardDrawn = labels.getString("turnViewShowCardDrawn");
		String drawnCardMessage = MessageFormat.format(turnViewShowCardDrawn, drawn);
		System.out.println(drawnCardMessage);
	}

	public void showCardCouldNotBeAdded(CardType drawn) {
		final String turnViewCardCouldNotBeAdded =
				labels.getString("turnViewCardCouldNotBeAdded");
		String cardNotAddedMessage =
				MessageFormat.format(turnViewCardCouldNotBeAdded, drawn);
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
				System.out.println();
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

		final String turnViewPromptForCard =
				labels.getString("turnViewPromptForCard");
		final String turnViewPromptForIndex =
				labels.getString("turnViewPromptForIndex");
		final String turnViewInvalidIndex =
				labels.getString("turnViewInvalidIndex");

		while (true) {
			System.out.print(turnViewPromptForCard);
			printCards(hand, cards);
			System.out.print(turnViewPromptForIndex);
			try {
				int idx = Integer.parseInt(scanner.nextLine());
				if (idx >= 0 && idx < cards.size()) {
					return cards.get(idx);
				}
			} catch (NumberFormatException ignored) { }
			System.out.println(turnViewInvalidIndex);
		}
	}
	
	public void displayHand(Player player) {
		Map<CardType, Integer> hand = player.viewHand();
		List<CardType> cards = new ArrayList<> (hand.keySet());

		final String turnViewYourHand =
				labels.getString("turnViewYourHand");
		System.out.print(turnViewYourHand);
		printCards(hand, cards);
	}

	private void printCards(Map<CardType, Integer> hand, List<CardType> cards) {
		for (int i = 0; i < cards.size(); i++) {
			CardType card = cards.get(i);
			if (i % ROWLENGTH == 0) {
				System.out.println();
			}
			final String cardInfo = MessageFormat.format
					("[{0}] {1} ({2})", i, card, hand.get(card));
			System.out.println(cardInfo);
		}
		System.out.println();
	}

	public void getCardInfo() {
		final String turnViewSelectCardInfo =
				labels.getString("turnViewSelectCardInfo");
		System.out.print(turnViewSelectCardInfo);

		final String turnViewPromptForIndex =
				labels.getString("turnViewPromptForIndex");
		final String turnViewInvalidIndex =
				labels.getString("turnViewInvalidIndex");

		CardType[] cards = CardType.values();
		for (int i = 0; i < CardType.values().length; i++) {
			if (i % ROWLENGTH == 0) {
				System.out.println();
			}
			final String cardInfo = MessageFormat.format("[{0}] {1}    ", i, cards[i]);
			System.out.print(cardInfo);
		}
		while (true) {
			try {
				System.out.println();
				System.out.print(turnViewPromptForIndex);
				int idx = Integer.parseInt(scanner.nextLine());
				if (idx < 0 || idx >= cards.length) {
					throw new NumberFormatException();
				}
				CardType card = cards[idx];
				CardController cardController = cardControllers.get(card);
				cardController.getInfo();
				return;
			} catch (NumberFormatException ignored) {
				System.out.print(turnViewInvalidIndex);
			}
		}
	}

	public void reinsertExplodia() {
		final String turnViewReinsertExplodia =
				labels.getString("turnViewReinsertExplodia");
		System.out.println(turnViewReinsertExplodia);
	}

	public void showImplodingIndex(int implodingIndex) {
		if (implodingIndex != -1) {
			final String turnViewShowImplodingIndex =
					labels.getString("turnViewShowImplodingIndex");
			final String implodingIndexMsg = MessageFormat.format
					(turnViewShowImplodingIndex, implodingIndex);
			System.out.println(implodingIndexMsg);
		}
	}
}
