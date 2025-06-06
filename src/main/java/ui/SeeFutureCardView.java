package ui;

import domain.CardType;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class SeeFutureCardView implements CardView {
	private final ResourceBundle labels;

	public SeeFutureCardView() {
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	public void getInfo() {
		final String seeFutureInfo = labels.getString("seeFutureInfo");
		System.out.println(seeFutureInfo);
	}

	public void actionMessage() {
		final String seeFutureActionMessage = labels.getString("seeFutureActionMessage");
		System.out.println(seeFutureActionMessage);
	}

	public void displayTopCards(CardType[] topCards) {
		int count = 1;
		for (CardType card : topCards) {
			String cardString = MessageFormat.format("{0}: {1}", count, card);
			System.out.println(cardString);
			count++;
		}
	}
}
