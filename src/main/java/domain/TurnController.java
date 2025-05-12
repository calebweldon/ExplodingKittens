package domain;

import java.security.SecureRandom;
import java.util.Scanner;

import java.nio.charset.StandardCharsets;


public final class TurnController {
	private final Deck deck;
	private final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

	public TurnController(Deck deck) {
		if (deck == null) {
			throw new IllegalArgumentException("Deck cannot be null");
		}
		// spotbugs wants me to make a defensive copy...
		this.deck = deck;
	}

	/**
	 * Execute one turn for the given player.
	 * Called by Game Controller
	 *
	 * @param player the player whose turn it is
	 * @return true if player survives, false if they explode
	 */
	public boolean takeTurn(Player player) {
		boolean turnOver = false;

		while (!turnOver) {
			String cmd = "";
			// Must enter a valid command, prompt if invalid
			while (true) {
				// System.out.println("\n" + player.getName() + "'s turn.
				// [play] a card or [end] turn?");
				// need player.getName (above 2 lines are one)
				cmd = scanner.nextLine().trim().toLowerCase();

				if ("play".equals(cmd) || "end".equals(cmd)) {
					break;
				} else {
					System.out.println(
							"Invalid input. Please enter 'play' or 'end'.");
				}
			}

			// choose a card to play, if "play" selected
			if ("play".equals(cmd)) {
				if (player.viewHand().isEmpty()) {
					System.out.println("You have no cards to play.");
					continue;
				}
				System.out.println(new StringBuilder()
					.append("Your hand: ")
					.append(player.viewHand())
					.toString());
				int idx = promptCardIndex(player);
				Card c = player.viewHand().remove(idx);
				// player.playCard(c);
				// playCard(c);
			}
			// end the turn (draw card), if "end" selected
			else {
				Card drawn = deck.drawCard();
				System.out.println(new StringBuilder()
					.append("You drew: ")
					.append(drawn.getCardType())
					.toString());
				if (drawn.getCardType() == CardType.EXPLODING_KITTEN) {
					return handleExplodingKitten(player);
				} else {
					player.viewHand().add(drawn);
					turnOver = true;
				}
			}
		}
		return true;
	}


	/**
	 * @return false if player is eliminated, true if they defuse and stay in game
	 */
	private boolean handleExplodingKitten(Player player) {
		for (Card c : player.viewHand()) {
			if (c.getCardType() == CardType.DEFUSE) {
				System.out.println("Defuse! You stay in.");
				player.viewHand().remove(c);
				deck.insertCardAtRandomIndex(new Card(CardType.EXPLODING_KITTEN));
				return true;
			}
		}

		System.out.println("No defuse—you're out!");
		return false;
	}

	/**
	 * Prompts user to select a valid card index from their hand.
	 * Keeps prompting until a valid integer within bounds is entered.
	 */
	private int promptCardIndex(Player player) {
		int idx = -1;
		while (true) {
			System.out.print("Which index to play? ");
			String input = scanner.nextLine();
			try {
				idx = Integer.parseInt(input);
				if (idx >= 0 && idx < player.viewHand().size()) {
					return idx;
				} else {
					System.out.println(new StringBuilder()
						.append("Invalid index. Enter a num b/w 0 and ")
						.append(player.viewHand().size() - 1)
						.append(".")
						.toString());
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			}
		}
	}
}
