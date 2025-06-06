package ui;

import domain.CardType;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GodCatCardView {
	private final Scanner scanner;
	private final ResourceBundle labels;

	public GodCatCardView () {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	public void getInfo() {
		final String godCatInfo = labels.getString("godCatInfo");
		System.out.println(godCatInfo);
	}

	public void actionMessage() {
		final String godCatActionMessage = labels.getString("godCatActionMessage");
		System.out.println(godCatActionMessage);
	}

	public CardType chooseController() {
		final int FAVOR = 3;
		final int BASIC = 4;
		final int REVERSE = 5;
		final int SHUFFLE = 6;
		final int SWAP = 7;
		final int EMBARRASS = 8;
		final int RECYCLE = 9;
		final int ALTER = 10;
		final int SEE = 11;

		final String godCatPromptForCardType = labels.getString("godCatPromptForCardType");
		System.out.println(godCatPromptForCardType);

		// TODO : May need to add/remove types depending on final amount
		final String godCatCardSelection = labels.getString("godCatCardSelection");
		final String godCatInvalidSelection = labels.getString("godCatInvalidSelection");

		while (true) {
			System.out.println(godCatCardSelection);
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
					System.out.println(godCatInvalidSelection);
			}
		}
	}
}
