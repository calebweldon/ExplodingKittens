package ui;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import domain.CardType;

public class AlterFutureCardView implements CardView {
	private final Scanner scanner;
	private final ResourceBundle labels;

	public AlterFutureCardView() {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	public void getInfo() {
		final String alterFutureInfo = labels.getString("alterFutureInfo");
		System.out.println(alterFutureInfo);
	}

	public void actionMessage() {
		final String alterFutureActionMessage =
				labels.getString("alterFutureActionMessage");
		System.out.println(alterFutureActionMessage);
	}

	public void displayTopCards(CardType[] topCards) {
		int count = 1;
		for (CardType card : topCards) {
			String cardString = MessageFormat.format("{0}: {1}", count, card);
			System.out.println(cardString);
			count++;
		}
	}

	public CardType[] promptForNewOrder(CardType[] topCards) {
		int numCards = topCards.length;
		CardType[] reorderedCards = new CardType[numCards];

		// TODO : Come back to embedded format
		final String alterFuturePrompt = labels.getString("alterFuturePrompt");
		String prompt = MessageFormat.format(alterFuturePrompt, numCards);
		System.out.println(prompt);

		final String alterFutureInvalidCount = labels.getString("alterFutureInvalidCount");
		String invalidCountMessage = MessageFormat.format(alterFutureInvalidCount,
				numCards);

		final String alterFutureInvalidCard = labels.getString("alterFutureInvalidCard");
		String invalidCardMessage = MessageFormat.format(alterFutureInvalidCard, numCards);

		loop:
		while (true) {
			String input = scanner.nextLine();

			String[] values = input.split(" ");
			if (values.length != numCards) {
				System.out.println(invalidCountMessage);
				continue;
			}

			for (int i = 0; i < numCards; i++) {
				try {
					int index = Integer.parseInt(values[i]);
					if (index < 1 || index > numCards) {
						System.out.println(invalidCardMessage);
						continue loop;
					}
					reorderedCards[i] = topCards[index - 1];
				} catch (NumberFormatException e) {
					System.out.println(invalidCardMessage);
					continue loop;
				}
			}

			return reorderedCards;
		}
	}
}
