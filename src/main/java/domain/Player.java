package domain;

import java.util.List;
import java.util.ArrayList;

public class Player {
	private List<Card> hand;

	public Player() {
		this.hand = new ArrayList<Card>();
	}

	public List<Card> viewHand() {
		List<Card> copy = new ArrayList<Card>(hand);
		return copy;
	}
}
