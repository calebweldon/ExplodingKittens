package ui;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AttackCardView implements CardView {
	private final Scanner scanner;
	private ResourceBundle labels;

	public AttackCardView() {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
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
