package ui;

import java.util.ResourceBundle;

public class AttackCardView implements CardView {
	private final ResourceBundle labels;

	public AttackCardView() {
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	public void getInfo() {
		final String attackInfo = labels.getString("attackInfo");
		System.out.println(attackInfo);
	}

	public void actionMessage() {
		final String attackActionMessage = labels.getString("attackActionMessage");
		System.out.println(attackActionMessage);
	}
}
