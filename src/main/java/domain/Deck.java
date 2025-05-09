package domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.security.SecureRandom;

public class Deck {
	private List<CardType> deck;
	private SecureRandom rand;

	public Deck() {
		this.deck = new LinkedList<>();
		this.rand = new SecureRandom();
	}

	Deck(SecureRandom rand) {
		this.deck = new LinkedList<>();
		this.rand = rand;
	}

	public void insertCardAtIndex(CardType card, int index) {
		checkIndexOutOfBounds(index);
		deck.add(index, card);
	}

	public void insertCardAtRandomIndex(CardType card) {
		int index = this.rand.nextInt(getSize() + 1);
		insertCardAtIndex(card, index);
	}

	public CardType drawCard() {
		return deck.remove(0);
	}

	public CardType drawCardFromBottom() {
		return deck.remove(getSize() - 1);
	}

	public void shuffleDeck() {
		Collections.shuffle(deck);
	}

	public void flipDeck() {
		Collections.reverse(deck);
	}

	public CardType getCardAtIndex(int index) {
		checkIndexOutOfBounds(index);
		return deck.get(index);
	}

	int getSize() {
		return deck.size();
	}

	private void checkIndexOutOfBounds(int index) {
		if (index < 0 || index > getSize()) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
	}

}
