package domain;

import java.util.List;
import java.util.ArrayList;

public class Player {
	private List<Card> hand;

	public Player(List<Card> hand) {
		this.hand = hand;
	}

	public void drawCard(Deck deck) {
		Card drawnCard = deck.drawCard();
		hand.add(drawnCard);
	}

	public List<Card> viewHand() {
		List<Card> copy = List.copyOf(hand);
		return copy;
	}

	public Card playCard(int index) {
		if (index < 0 || index >= hand.size()) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}

		return hand.remove(index);
	}
}
