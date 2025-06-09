package ui;

import java.util.ResourceBundle;

public class FlipCardView implements CardView {
	private final ResourceBundle labels;

	public FlipCardView() {
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	public void getInfo() {
		final String flipInfo = labels.getString("flipInfo");
		System.out.println(flipInfo);
	}

	public void actionMessage() {
		final String flipActionMessage = labels.getString("flipActionMessage");
		System.out.println(flipActionMessage);
	}
}
