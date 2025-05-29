package ui;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SkipCardView implements CardView {
	private final Scanner scanner;

	public SkipCardView() { this.scanner = new Scanner(System.in, StandardCharsets.UTF_8); }

	public void getInfo() {
		// TODO: add locale
		System.out.println("Immediately ends one remaining turn of the player" +
				"without drawing a card");
	}

	public void actionMessage() {
		// TODO: add locale
		System.out.println("Skip card played!");
	}
}
