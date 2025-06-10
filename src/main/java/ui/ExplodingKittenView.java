package ui;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ExplodingKittenView implements CardView {
	private final Scanner scanner;
	private final ResourceBundle labels;

	public ExplodingKittenView() {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	@Override
	public void getInfo() {
		final String explodingKittenInfo = labels.getString("explodingKittenInfo");
		System.out.println(explodingKittenInfo);
	}

	@Override
	public void actionMessage() {
		final String explodingKittenActionMessage =
				labels.getString("explodingKittenActionMessage");
		System.out.println(explodingKittenActionMessage);
	}

	public void showDefuseUsed() {
		final String explodingKittenDefuseUsed =
				labels.getString("explodingKittenDefuseUsed");
		System.out.println(explodingKittenDefuseUsed);
	}

	public void showNoDefuseFound() {
		final String explodingKittenNoDefuse = labels.getString("explodingKittenNoDefuse");
		System.out.println(explodingKittenNoDefuse);
	}

	public int promptExplodingKittenIndex(int deckSize) {
		final String explodingKittenGetIndex = labels.getString("explodingKittenGetIndex");
		final String explodingKittenInvalidIndex =
				labels.getString("explodingKittenInvalidIndex");
		final String explodingKittenInvalidInput =
				labels.getString("explodingKittenInvalidInput");
		while (true) {
			System.out.printf(explodingKittenGetIndex, deckSize);
			try {
				int index = Integer.parseInt(scanner.nextLine());
				if (index >= 0 && index <= deckSize) {
					return index;
				}
				System.out.println(explodingKittenInvalidIndex);
			} catch (NumberFormatException e) {
				System.out.println(explodingKittenInvalidInput);
			}
		}
	}
} 