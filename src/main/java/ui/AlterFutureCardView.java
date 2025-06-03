package ui;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Scanner;

import domain.CardType;

public class AlterFutureCardView implements CardView {
	private final Scanner scanner;

	public AlterFutureCardView() {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
	}

	public void getInfo() {
		System.out.println("Pick the order of the top three cards of the deck.");
	}

	public void actionMessage() {
		System.out.println("You peek at the top of the deck in order.");
	}

	public void displayTopCards(CardType[] topCards) {
		int count = 1;
		for (CardType card : topCards) {
			String cardString = MessageFormat.format("{0}: {1}", count, card);
			System.out.println(card);
			count++;
		}
	}

	public CardType[] promptForNewOrder(CardType[] topCards) {
		int numCards = topCards.length;
		CardType[] reorderedCards = new CardType[numCards];

		String prompt = MessageFormat.format(
				"Please enter the new order of cards 1 to {0}, spaces-separated: ",
				numCards
				);
		System.out.println(prompt);

		String invalidCountMessage = MessageFormat.format(
				"You must enter exactly {0} values. Please try again.",
				numCards
				);
		String invalidCardMessage = MessageFormat.format(
				"Invalid card index. Please enter values between 1 and {0}.",
				numCards
				);

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
