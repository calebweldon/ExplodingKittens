package ui;

import domain.CardType;

public class AlterFutureCardView implements CardView {
	public void getInfo() {
		System.out.println("Pick the order of the top three cards of the deck.");
	}

	public void actionMessage() {
		System.out.println("You peek at the top of the deck in order.");
	}

	public void displayTopCards(CardType[] topCards) {
		int count = 1;
		for (CardType card : topCards) {
			String cardString = String.format("{0}: {1}", count, card);
			System.out.println(card);
			count++;
		}
	}
}
