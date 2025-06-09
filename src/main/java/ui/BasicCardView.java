package ui;

import java.util.ResourceBundle;
import java.util.List;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

import domain.CardType;
import domain.Player;

public class BasicCardView implements CardView {
	private final ResourceBundle labels;
	private final CardType basicCardType;
	private final Scanner scanner;

	public BasicCardView(CardType basicCardType) {
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
		this.basicCardType = basicCardType;
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
	}

	public void getInfo() {
		final String basicCardInfo = labels.getString("basicInfo");
		System.out.println(basicCardInfo);
	}

	public void actionMessage() {
		final String basicCardActionMessage = labels.getString("basicActionMessage");
		System.out.println(basicCardActionMessage);
	}

	public Player promptForPlayer(List<Player> activePlayersExcludingCurrent) {
		for (Player player : activePlayersExcludingCurrent) {
			int playerId = player.getId();
			String playerListing = MessageFormat.format(
				labels.getString("basicPlayerList"), playerId);
			System.out.println(playerListing);
		}

		final String invalidPlayer = labels.getString("basicPlayerInvalidPlayer");
		while (true) {
			try {
				int userInput = Integer.parseInt(scanner.nextLine());
				for (Player player : activePlayersExcludingCurrent) {
					if (player.getId() == userInput) {
						return player;
					}
				}
				System.out.println(invalidPlayer);
			} catch (NumberFormatException e) {
				System.out.println(invalidPlayer);
			}
		}
	}

	public void displayOtherPlayerNoCards() {
		final String otherPlayerNoCardsMessage = labels.getString
			("basicOtherPlayerNoCards");
		System.out.println(otherPlayerNoCardsMessage);
	}

	public void displayCardTaken(CardType card) {
		final String cardTakenMessage = labels.getString("basicCardTakenMessage");
		System.out.printf(cardTakenMessage, card);
	}
}
