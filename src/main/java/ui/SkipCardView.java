package ui;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SkipCardView implements CardView {
	private final ResourceBundle labels;

	public SkipCardView() {
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	public void getInfo() {
		final String skipInfo = labels.getString("skipInfo");
		System.out.println(skipInfo);
	}

	public void actionMessage() {
		final String skipActionMessage = labels.getString("skipActionMessage");
		System.out.println(skipActionMessage);
	}
}
