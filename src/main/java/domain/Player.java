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
		int count = this.hand.getOrDefault(cardType, 0);
		this.hand.put(cardType, count + 1);
	}
}
