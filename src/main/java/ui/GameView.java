package ui;

import domain.Player;

import java.text.MessageFormat;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.Locale;

public class GameView {
	private final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
	private ResourceBundle labels;

	public GameView() { }

	public void chooseLanguage() {
		final String promptForLanguage = "Enter the number of your choice";
		final String languageOptions  = "1) English\n2) French\n";
		final String invalidChoice = "Invalid input. Please enter 1 or 2";

		System.out.println(promptForLanguage);
		System.out.println(languageOptions);
		while (true) {
			String userInput = scanner.nextLine();
			switch (userInput) {
				case "1":
					LocaleContext.setLocale(new Locale("en"));
					break;
				case "2":
					LocaleContext.setLocale(new Locale("fr"));
					break;
				default:
					System.out.println(invalidChoice);
					continue;
			}
			break;
		}

		Locale locale = LocaleContext.getLocale();
		labels = ResourceBundle.getBundle("labels", locale);

		final String languageSet = labels.getString("languageSet");
		System.out.println(languageSet);
	}

	public int chooseNumPlayers() {

		final String promptForNumPlayers = labels.getString("promptForNumPlayers");
		final String playersSelected = labels.getString("playersSelected");
		final String invalidNumPlayers = labels.getString("invalidNumPlayers");
		String playersSelectedMessage;

		System.out.println(promptForNumPlayers);

		final int twoPlayers = 2;
		final int threePlayers = 3;
		final int fourPlayers = 4;
		final int fivePlayers = 5;
		while (true) {
			String userInput = scanner.nextLine();
			switch (userInput) {
				case "2":
					playersSelectedMessage = MessageFormat.format(
							"{0} {1}", twoPlayers, playersSelected);
					System.out.println(playersSelectedMessage);
					return twoPlayers;
				case "3":
					playersSelectedMessage = MessageFormat.format(
							"{0} {1}", threePlayers, playersSelected);
					System.out.println(playersSelectedMessage);
					return threePlayers;
				case "4":
					playersSelectedMessage = MessageFormat.format(
							"{0} {1}", fourPlayers, playersSelected);
					System.out.println(playersSelectedMessage);
					return fourPlayers;
				case "5":
					playersSelectedMessage = MessageFormat.format(
							"{0} {1}", fivePlayers, playersSelected);
					System.out.println(playersSelectedMessage);
					return fivePlayers;
				default:
					System.out.println(invalidNumPlayers);
			}
		}
	}

	public void announceGameStart() {
		final String gameStarting = labels.getString("gameStarting");
		System.out.println(gameStarting);
	}

	public void announceGameEnd(Player winner) {
		final String announceWinner = labels.getString("announceWinner");
		String gameOverMessage = MessageFormat.format("{0} {1}", announceWinner, winner);
		System.out.println(gameOverMessage);
	}

}
