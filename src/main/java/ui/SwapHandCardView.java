package ui;

import domain.Player;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;

public class SwapHandCardView implements CardView {
	private final Scanner scanner;

	public SwapHandCardView() {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
	}

	public void getInfo() {
		// TODO: add locale
		System.out.println("Lets you choose a player to swap hands with");
	}

	public void actionMessage() {
		// TODO: add locale
		System.out.print("Enter the ID of the player you want to swap with: ");
	}

	public Player promptForPlayerToSwapWith(List<Player> activePlayersExceptCurrent) {
		// TODO: add locale

		for (Player player : activePlayersExceptCurrent) {
			int playerId = player.getId();
			int handSize = player.getHandSize();
			String playerAndHandSizeMessage = MessageFormat.format(
					"Player {0} has {1} card(s)", playerId, handSize);
			System.out.println(playerAndHandSizeMessage);
		}

		while (true) {
			try {
				int userInput = Integer.parseInt(scanner.nextLine());

				for (Player player : activePlayersExceptCurrent) {
					if (player.getId() == userInput) {
						String playerChosenMessage = MessageFormat.format(
								"Player {0} chosen", userInput);
						System.out.println(playerChosenMessage);
						return player;
					}
				}

				System.out.println("Invalid ID. Please try again.");
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			}
		}
	}
}
