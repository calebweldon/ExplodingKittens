package ui;

import domain.Player;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.Locale;

public class GameView {
	private final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

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
		ResourceBundle labels = ResourceBundle.getBundle("labels", locale);

		final String languageSet = labels.getString("languageSet");
		System.out.println(languageSet);
	}

	public int chooseNumPlayers() {
		Locale locale = LocaleContext.getLocale();
		ResourceBundle labels = ResourceBundle.getBundle("labels", locale);

		final String promptForNumPlayers = labels.getString("promptForNumPlayers");
		final String twoPlayersChosen = labels.getString("twoPlayersChosen");
		final String threePlayersChosen = labels.getString("threePlayersChosen");
		final String fourPlayersChosen = labels.getString("fourPlayersChosen");
		final String invalidNumPlayers = labels.getString("invalidNumPlayers");

		System.out.println(promptForNumPlayers);

		final int twoPlayers = 2;
		final int threePlayers = 3;
		final int fourPlayers = 4;
		while (true) {
			String userInput = scanner.nextLine();
			switch (userInput) {
				case "2":
					System.out.println(twoPlayersChosen);
					return twoPlayers;
				case "3":
					System.out.println(threePlayersChosen);
					return threePlayers;
				case "4":
					System.out.println(fourPlayersChosen);
					return fourPlayers;
				default:
					System.out.println(invalidNumPlayers);
			}
		}
	}

	public void announceGameStart() { }

	public void announceGameEnd(Player winner) { }

}
