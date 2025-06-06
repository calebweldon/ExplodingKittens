package ui;

import java.util.ResourceBundle;

public class ExplodiaCardView {
	private static final int THREE_EXPLODIA = 3;
	private static final int FOUR_EXPLODIA = 4;
	private final ResourceBundle labels;

	ExplodiaCardView() {
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	public void getInfo() {
		final String explodiaInfoFirst = labels.getString("explodiaInfoFirst");
		final String explodiaInfoSecond = labels.getString("explodiaInfoSecond");

		System.out.println(explodiaInfoFirst);
		System.out.println(explodiaInfoSecond);
	}

	public void actionMessage() {
		final String explodiaActionMessage = labels.getString("explodiaActionMessage");
		System.out.println(explodiaActionMessage);
	}

	public void drawMessage(int numExplodia) {
		final String explodiaDrawDefault = labels.getString("explodiaDrawDefault");
		final String explodiaDrawOne = labels.getString("explodiaDrawOne");
		final String explodiaDrawTwo = labels.getString("explodiaDrawTwo");
		final String explodiaDrawThree = labels.getString("explodiaDrawThree");
		final String explodiaDrawFour = labels.getString("explodiaDrawFour");

		switch (numExplodia) {
			case 1:
				System.out.println(explodiaDrawOne);
				break;
			case 2:
				System.out.println(explodiaDrawTwo);
				break;
			case THREE_EXPLODIA:
				System.out.println(explodiaDrawThree);
				break;
			case FOUR_EXPLODIA:
				System.out.println(explodiaDrawFour);
				break;
			default:
				System.out.println(explodiaDrawDefault);
		}
	}

}
