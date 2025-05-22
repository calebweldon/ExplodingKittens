package ui;

import domain.Player;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.Locale;

public class GameView {

	public GameView() { }

	public void chooseLanguage() {
		Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
		final String promptForLanguage = "Enter the number of your choice";
		final String languageOptions  = "1) English\n2) French\n";
		final String invalidChoice = "Invalid input. Please enter 1 or 2";

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

		final String languageSet = labels.getString("language");
		System.out.println(languageSet);
	}

	public int chooseNumPlayers() {
		return 0;
	}

	public void announceGameStart() { }

	public void announceGameEnd(Player winner) { }

}
