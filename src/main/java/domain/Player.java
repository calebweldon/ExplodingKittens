package domain;

import java.util.Map;
import java.util.HashMap;


public class Player {
	private Map<CardType, Integer> hand;

	public Player() {
		this.hand = new HashMap<>();
	}

	// Package private for testing
	Player(Map<CardType, Integer> hand) {
		this.hand = hand;
	}

	public void addCard(CardType cardType) {
		if (cardType == CardType.EXPLODING_KITTEN) {
			String message = "You cannot add an Exploding Kitten to your hand.";
			throw new IllegalArgumentException(message);
		}
		int count = this.hand.getOrDefault(cardType, 0);
		this.hand.put(cardType, count + 1);
	}

	public void playCard(CardType cardType) {
		int removeCount = 1;
		if (false
				|| cardType == CardType.TACO_CAT
				|| cardType == CardType.CATTERMELON
				|| cardType == CardType.POTATO_CAT
				|| cardType == CardType.BEARD_CAT
				|| cardType == CardType.RAINBOW_RALPHING_CAT) {
			removeCount = 2;
		}
		int count = this.hand.getOrDefault(cardType, 0);
		if (count < removeCount) {
			String message = "You do not have enough cards to play.";
			throw new IllegalArgumentException(message);
		}
		this.hand.put(cardType, count - removeCount);
	}

	public void removeCard(CardType cardType) {
		int count = this.hand.getOrDefault(cardType, 0);
		if (count < 1) {
			throw new IllegalArgumentException("Not enough cards to remove.");
		}
		this.hand.put(cardType, count - 1);
	}

	public Map<CardType, Integer> viewHand() {
		return new HashMap<>(this.hand);
	}
}
