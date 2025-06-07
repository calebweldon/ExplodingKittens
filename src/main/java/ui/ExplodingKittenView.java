package ui;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ExplodingKittenView implements CardView {
	private final Scanner scanner;

	public ExplodingKittenView() {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
	}

	@Override
	public void getInfo() {
		// TODO: add locale
		System.out.println("EXPLODING KITTEN!!!");
		System.out.println("If you draw this card, you explode and are out of the game.");
		System.out.println("Unless you have a Defuse card!");
	}

	@Override
	public void actionMessage() {
		// TODO: add locale
		System.out.println("You drew an Exploding Kitten!");
	}

	public void showDefuseUsed() {
		// TODO: add locale
		System.out.println("Defuse used. You're safe.");
	}

	public void showNoDefuseFound() {
		// TODO: add locale
		System.out.println("No defuse found. Eliminated.");
	}

	public int promptExplodingKittenIndex(int deckSize) {
		// TODO: add locale
		while (true) {
			System.out.printf(
				"Where do you want to put the Exploding Kitten back? (0-%d): ", 
				deckSize
			);
			try {
				int index = Integer.parseInt(scanner.nextLine());
				if (index >= 0 && index <= deckSize) {
					return index;
				}
				System.out.println("Invalid index. Try again.");
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			}
		}
	}
} 