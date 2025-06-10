package ui;

import java.util.ResourceBundle;

public class ShuffleCardView implements CardView {
	private final ResourceBundle labels;

	public ShuffleCardView() {
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	public void getInfo() {
		final String shuffleInfo = labels.getString("shuffleInfo");
		System.out.println(shuffleInfo);
	}

	public void actionMessage() {
		final String shuffleActionMessage = labels.getString("shuffleActionMessage");
		System.out.println(shuffleActionMessage);
	}
}
