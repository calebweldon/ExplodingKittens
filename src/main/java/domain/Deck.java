package domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.security.SecureRandom;

public class Deck {
	private List<Card> deck;
	private SecureRandom rand;

	public Deck() {
		this.deck = new LinkedList<>();
		this.rand = new SecureRandom();
	}

	Deck(SecureRandom rand, List<Card> deck) {
		this.deck = deck;
		this.rand = rand;
	}

	public void insertCardAtIndex(Card card, int index) {
		checkIndexOutOfBounds(index);
		deck.add(index, card);
	}

	public void insertCardAtRandomIndex(Card card) {
		int index = this.rand.nextInt(getSize() + 1);
		insertCardAtIndex(card, index);
	}

	public Card drawCard() {
		return deck.remove(0);
	}

	public Card drawCardFromBottom() {
		return deck.remove(getSize() - 1);
	}

	public void shuffleDeck() {
		Collections.shuffle(deck);
	}

	public void flipDeck() {
		Collections.reverse(deck);
	}

	public Card getCardAtIndex(int index) {
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
