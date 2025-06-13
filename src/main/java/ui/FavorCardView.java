package ui;


import domain.CardType;
import domain.Player;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class FavorCardView implements CardView {
	private final Scanner scanner;
	private final ResourceBundle labels;

	public FavorCardView() {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	public void getInfo() {
		final String favorInfo = labels.getString("favorInfo");
		System.out.println(favorInfo);
	}

	public void actionMessage() {
		final String favorActionMessage = labels.getString("favorActionMessage");
		System.out.println(favorActionMessage);
	}

	public void printNoCardsToGive() {
		final String favorNoCardsToGive = labels.getString("favorNoCardsToGive");
		System.out.println(favorNoCardsToGive);
	}

	public void printNoCardGivenMessage() {
		final String favorNoCardGiven = labels.getString("favorNoCardGiven");
		System.out.println(favorNoCardGiven);
	}

	public Player promptForTargetPlayer(List<Player> activePlayersExceptCurrent) {
		final String favorPromptForPlayer = labels.getString("favorPromptForPlayer");
		System.out.println(favorPromptForPlayer);

		final String favorPlayerHandSizeMessage = labels.getString
				("favorPlayerHandSizeMessage");

		for (Player player : activePlayersExceptCurrent) {
			int playerId = player.getId();
			int handSize = player.getHandSize();
			String playerAndHandSizeMessage = MessageFormat.format(
					favorPlayerHandSizeMessage, playerId, handSize);
			System.out.println(playerAndHandSizeMessage);
		}

		final String favorEnterId = labels.getString("favorEnterId");
		final String favorPlayerChosen = labels.getString("favorPlayerChosen");
		final String favorInvalidId = labels.getString("favorInvalidId");
		final String favorPromptForNum = labels.getString("favorPromptForNum");

		while (true) {
			System.out.print(favorEnterId);
			try {
				int userInput = Integer.parseInt(scanner.nextLine());

				for (Player player : activePlayersExceptCurrent) {
					if (player.getId() == userInput) {
						String playerChosenMessage = MessageFormat.format(
								favorPlayerChosen, userInput);
						System.out.println(playerChosenMessage);
						return player;
					}
				}

				System.out.println(favorInvalidId);
			} catch (NumberFormatException e) {
				System.out.println(favorPromptForNum);
			}
		}
	}

	public CardType promptTargetPlayerForCard(Player targetPlayer) {
		Map<CardType, Integer> hand = targetPlayer.viewHand();

		final String favorPlayerNoCardsToGive = labels.getString
				("favorPlayerNoCardsToGive");
		final String favorPlayerChooseCardToGive = labels.getString
				("favorPlayerChooseCardToGive");
		final String favorDisplayCards = labels.getString("favorDisplayCards");

		if (hand.isEmpty()) {
			System.out.println(MessageFormat.format(
					favorPlayerNoCardsToGive, targetPlayer.getId()));
			return null;
		}

		System.out.println(MessageFormat.format(
				favorPlayerChooseCardToGive, targetPlayer.getId()));

		int index = 1;
		for (Map.Entry<CardType, Integer> entry : hand.entrySet()) {
			if (entry.getValue() > 0) {
				System.out.println(MessageFormat.format(
						favorDisplayCards,
						index, entry.getKey(), entry.getValue()));
				index++;
			}
		}

		final String favorPromptNumCardsToGive = labels.getString
				("favorPromptNumCardsToGive");
		final String favorInvalidChoice = labels.getString("favorInvalidChoice");
		final String favorPlayerGives = labels.getString("favorPlayerGives");
		final String favorPromptForNum = labels.getString("favorPromptForNum");


		while (true) {
			System.out.print(favorPromptNumCardsToGive);
			try {
				int choice = Integer.parseInt(scanner.nextLine());
				
				if (choice < 1 || choice > hand.size()) {
					System.out.println(favorInvalidChoice);
					continue;
				}

				int currentIndex = 1;
				for (Map.Entry<CardType, Integer> entry : hand.entrySet()) {
					if (entry.getValue() > 0) {
						if (currentIndex == choice) {
							System.out.println(MessageFormat.format(
									favorPlayerGives,
									targetPlayer.getId(), 
									entry.getKey()));
							return entry.getKey();
						}
						currentIndex++;
					}
				}
			} catch (NumberFormatException e) {
				System.out.println(favorPromptForNum);
			}
		}
	}
} 