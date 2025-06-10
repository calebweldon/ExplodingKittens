package ui;

import domain.Player;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SwapHandCardView implements CardView {
	private final Scanner scanner;
	private final ResourceBundle labels;

	public SwapHandCardView() {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	public void getInfo() {
		final String swapHandInfo = labels.getString("swapHandInfo");
		System.out.println(swapHandInfo);
	}

	public void actionMessage() {
		final String swapHandActionMessage = labels.getString("swapHandActionMessage");
		System.out.println(swapHandActionMessage);
	}

	public Player promptForPlayerToSwapWith(List<Player> activePlayersExceptCurrent) {
		final String swapHandPlayerHandSize = labels.getString("swapHandPlayerHandSize");

		for (Player player : activePlayersExceptCurrent) {
			int playerId = player.getId();
			int handSize = player.getHandSize();
			String playerAndHandSizeMessage = MessageFormat.format(
					swapHandPlayerHandSize, playerId, handSize);
			System.out.println(playerAndHandSizeMessage);
		}

		final String swapHandPlayerChosen = labels.getString("swapHandPlayerChosen");
		final String swapHandInvalidId = labels.getString("swapHandInvalidId");
		final String swapHandInvalidPlayerChosen =
				labels.getString("swapHandInvalidPlayerChosen");

		while (true) {
			try {
				int userInput = Integer.parseInt(scanner.nextLine());

				for (Player player : activePlayersExceptCurrent) {
					if (player.getId() == userInput) {
						String playerChosenMessage = MessageFormat.format(
								swapHandPlayerChosen, userInput);
						System.out.println(playerChosenMessage);
						return player;
					}
				}

				System.out.println(swapHandInvalidId);
			} catch (NumberFormatException e) {
				System.out.println(swapHandInvalidPlayerChosen);
			}
		}
	}
}
