package domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.security.SecureRandom;

public class Deck {
	private List<CardType> deck;
	private SecureRandom rand;

	public final static int NUMBER_OF_GOD = 1;
	public final static int NUMBER_OF_DEFUSE = 2;
	public final static int NUMBER_OF_BASIC = 4;
	public final static int NUMBER_OF_SKIP = 4;
	public final static int NUMBER_OF_ATTACK = 4;
	public final static int NUMBER_OF_SHUFFLE = 4;
	public final static int NUMBER_OF_REVERSE = 4;
	public final static int NUMBER_OF_DRAW_BOTTOM = 4;
	public final static int NUMBER_OF_EMBARRASS = 4;
	public final static int NUMBER_OF_RECYCLE = 4;
	public final static int NUMBER_OF_SEE = 5;
	public final static int NUMBER_OF_ALTER = 5;
	public final static int NUMBER_OF_EXPLODIA = 5;
	public final static int NUMBER_OF_FAVOR = 6;

	public Deck() {
		this.deck = new LinkedList<>();
		this.rand = new SecureRandom();
		fillDeck();
		shuffleDeck();
	}

	Deck(SecureRandom rand, boolean fill) {
		this.deck = new LinkedList<>();
		this.rand = rand;
		if (fill) {
			fillDeck();
			shuffleDeck();
		}
	}

	private void fillDeck() {
		for (int i = 0; i < NUMBER_OF_GOD; i++) {
			deck.add(CardType.GOD_CAT);
		}
		for (int i = 0; i < NUMBER_OF_BASIC; i++) {
			deck.add(CardType.TACO_CAT);
			deck.add(CardType.BEARD_CAT);
			deck.add(CardType.CATTERMELON);
			deck.add(CardType.POTATO_CAT);
			deck.add(CardType.RAINBOW_RALPHING_CAT);
		}
		for (int i = 0; i < NUMBER_OF_SKIP; i++) {
			deck.add(CardType.SKIP);
		}
		for (int i = 0; i < NUMBER_OF_ATTACK; i++) {
			deck.add(CardType.ATTACK);
		}
		for (int i = 0; i < NUMBER_OF_SHUFFLE; i++) {
			deck.add(CardType.SHUFFLE);
		}
		for (int i = 0; i < NUMBER_OF_REVERSE; i++) {
			deck.add(CardType.REVERSE);
		}
		for (int i = 0; i < NUMBER_OF_DRAW_BOTTOM; i++) {
			deck.add(CardType.DRAW_FROM_BOTTOM);
		}
		for (int i = 0; i < NUMBER_OF_EMBARRASS; i++) {
			deck.add(CardType.EMBARRASS);
		}
		for (int i = 0; i < NUMBER_OF_RECYCLE; i++) {
			deck.add(CardType.RECYCLE);
		}
		for (int i = 0; i < NUMBER_OF_SEE; i++) {
			deck.add(CardType.SEE_THE_FUTURE);
		}
		for (int i = 0; i < NUMBER_OF_ALTER; i++) {
			deck.add(CardType.ALTER_THE_FUTURE);
		}
		for (int i = 0; i < NUMBER_OF_FAVOR; i++) {
			deck.add(CardType.FAVOR);
		}
	}

	public void addSpecialCards(int numExplodingCards) {
		for (int i = 0; i < numExplodingCards; i++) {
			insertCardAtIndex(CardType.EXPLODING_KITTEN, 0);
		}
		for (int i = 0; i < NUMBER_OF_DEFUSE; i++) {
			insertCardAtIndex(CardType.DEFUSE, 0);
		}
		for (int i = 0; i < NUMBER_OF_EXPLODIA; i++) {
			insertCardAtIndex(CardType.EXPLODIA, 0);
		}
		insertCardAtIndex(CardType.IMPLODING_FACEDOWN, 0);

		shuffleDeck();
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

	int getCardCount(CardType cardType) {
		return Collections.frequency(deck, cardType);
	}

	private void checkIndexOutOfBounds(int index) {
		if (index < 0 || index > getSize()) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
	}

}
