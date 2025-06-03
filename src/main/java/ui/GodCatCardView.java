package ui;

import domain.CardType;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class GodCatCardView {
	private final Scanner scanner;

	public GodCatCardView () {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
	}

	public void getInfo() {
		// TODO: add locale
		System.out.println("The God Cat. Playing this card will allow it to " +
				"transform into the card of your choice.");
	}

	public void actionMessage() {
		// TODO: add locale
		System.out.println("The God Cat has been played!");
	}

	public CardType chooseController() {
		// TODO: add locale
		final int FAVOR = 3;
		final int BASIC = 4;
		final int REVERSE = 5;
		final int SHUFFLE = 6;
		final int SWAP = 7;
		final int EMBARRASS = 8;
		final int RECYCLE = 9;
		final int ALTER = 10;
		final int SEE = 11;
		System.out.println("Please choose the card type:");
		while (true) {
			System.out.println("1. Action \t 2. Skip \t 3. Favor \t 4. Basic ...");
			int choice = scanner.nextInt();
			switch (choice) {
				case 1:
					return CardType.ATTACK;
				case 2:
					return CardType.SKIP;
				case FAVOR:
					return CardType.FAVOR;
				case BASIC:
					return CardType.TACO_CAT;
				case REVERSE:
					return CardType.REVERSE;
				case SHUFFLE:
					return CardType.SHUFFLE;
				case SWAP:
					return CardType.SWAP;
				case EMBARRASS:
					return CardType.EMBARRASS;
				case RECYCLE:
					return CardType.RECYCLE;
				case ALTER:
					return CardType.ALTER_THE_FUTURE;
				case SEE:
					return CardType.SEE_THE_FUTURE;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
