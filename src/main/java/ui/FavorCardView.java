package ui;

import domain.CardType;
import domain.Player;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FavorCardView implements CardView {
	private final Scanner scanner;

	public FavorCardView() {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
	}

	public void getInfo() {
		// TODO: add locale
		System.out.println("Choose a player to give you one of their cards - their choice");
	}

	public void actionMessage() {
		// TODO: add locale
		System.out.println("Favor card has been played!");
	}

	public void printNoCardsToGive() {
		System.out.println("No other players have cards to give!");
	}

	public void printNoCardGivenMessage() {
		System.out.println("No card was given.");
	}

	public Player promptForTargetPlayer(List<Player> activePlayersExceptCurrent) {
		// TODO: add locale
		System.out.println("Choose a player to give you a card:");

		for (Player player : activePlayersExceptCurrent) {
			int playerId = player.getId();
			int handSize = player.getHandSize();
			String playerAndHandSizeMessage = MessageFormat.format(
					"Player {0} has {1} card(s)", playerId, handSize);
			System.out.println(playerAndHandSizeMessage);
		}

		while (true) {
			System.out.print("Enter the ID of the player: ");
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

	public CardType promptTargetPlayerForCard(Player targetPlayer) {
		// TODO: add locale
		Map<CardType, Integer> hand = targetPlayer.viewHand();
		
		if (hand.isEmpty()) {
			System.out.println(MessageFormat.format(
					"Player {0} has no cards to give!", targetPlayer.getId()));
			return null;
		}

		System.out.println(MessageFormat.format(
				"Player {0}, choose a card to give:", targetPlayer.getId()));

		// Display available cards
		int index = 1;
		for (Map.Entry<CardType, Integer> entry : hand.entrySet()) {
			if (entry.getValue() > 0) {
				System.out.println(MessageFormat.format(
						"{0}. {1} (you have {2})", 
						index, entry.getKey(), entry.getValue()));
				index++;
			}
		}

		while (true) {
			System.out.print("Enter the number of the card to give: ");
			try {
				int choice = Integer.parseInt(scanner.nextLine());
				
				if (choice < 1 || choice > hand.size()) {
					System.out.println("Invalid choice. Please try again.");
					continue;
				}

				int currentIndex = 1;
				for (Map.Entry<CardType, Integer> entry : hand.entrySet()) {
					if (entry.getValue() > 0) {
						if (currentIndex == choice) {
							System.out.println(MessageFormat.format(
									"Player {0} gives {1}", 
									targetPlayer.getId(), 
									entry.getKey()));
							return entry.getKey();
						}
						currentIndex++;
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			}
		}
	}
} 