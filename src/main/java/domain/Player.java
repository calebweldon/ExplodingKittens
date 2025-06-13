package domain;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.security.SecureRandom;


public class Player {
	private int id;
	private Map<CardType, Integer> hand;
	private SecureRandom random;

	public Player(int id) {
		this.id = id;
		this.hand = new HashMap<>();
	}

	// Package private for testing
	Player(Map<CardType, Integer> hand) {
		this.hand = hand;
	}

	Player(Map<CardType, Integer> hand, SecureRandom random) {
		this.hand = hand;
		this.random = random;
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
		if (isBasicCard(cardType)) {
			removeCount = 2;
		}
		int count = this.hand.getOrDefault(cardType, 0);
		if (count < removeCount) {
			String message = "You do not have enough cards to play.";
			throw new IllegalArgumentException(message);
		}
		if (count - removeCount == 0) {
			this.hand.remove(cardType);
		} else {
			this.hand.put(cardType, count - removeCount);
		}
	}

	private boolean isBasicCard(CardType cardType) {
		return cardType == CardType.TACO_CAT || cardType == CardType.CATTERMELON
				|| cardType == CardType.POTATO_CAT
				|| cardType == CardType.BEARD_CAT
				|| cardType == CardType.RAINBOW_RALPHING_CAT;
	}

	public void removeCard(CardType cardType) {
		int count = this.hand.getOrDefault(cardType, 0);
		if (count < 1) {
			throw new IllegalArgumentException("Not enough cards to remove.");
		}
		if (count == 1) {
			this.hand.remove(cardType);
		} else {
			this.hand.put(cardType, count - 1);
		}
	}

	public int getId() {
		return id;
	}

	public int getHandSize() {
		int handSize = 0;
		for (int numberPerCard : hand.values()) {
			handSize += numberPerCard;
		}
		return handSize;
	}

	public void swapHandWith(Player playerToSwapWith) {
		Map<CardType, Integer> temp = new HashMap<>(this.hand);
		this.hand = new HashMap<>(playerToSwapWith.hand);
		playerToSwapWith.hand = temp;
	}

	public CardType takeRandomCard() {
		if (this.hand.isEmpty()) {
			throw new IllegalStateException("no cards");
		}
		Set<CardType> keys = this.hand.keySet();
		CardType[] keysArray = keys.toArray(new CardType[0]);
		int randomIndex = random.nextInt(keysArray.length);

		CardType randomCard = keysArray[randomIndex];
		removeCard(randomCard);
		return randomCard;
	}

	public Map<CardType, Integer> viewHand() {
		return new HashMap<>(this.hand);
	}
}
