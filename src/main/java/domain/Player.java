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

	public void addCard(CardType cardType) throws IllegalArgumentException {
		if (cardType == CardType.EXPLODING_KITTEN) {
			String message = "You cannot add an Exploding Kitten to your hand.";
			throw new IllegalArgumentException(message);
		}
		int count = this.hand.getOrDefault(cardType, 0);
		this.hand.put(cardType, count + 1);
	}

	public void playCard(CardType cardType) {
		int count = this.hand.getOrDefault(cardType, 0);
		this.hand.put(cardType, count - 1);
	}
}
