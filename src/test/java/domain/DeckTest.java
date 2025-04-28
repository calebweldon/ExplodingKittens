package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.easymock.EasyMock;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
	public static final int INT_MAX = 2147483647;

	@ParameterizedTest
	@ValueSource(ints = {-1, INT_MAX})
	public void insertCard_invalidIndex_throwException(int invalidIndex) {
		Card card = EasyMock.createMock(Card.class);
		Deck deck = new Deck();

		String expectedMessage = "Invalid index: index out of range";
		Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
			deck.insertCardAtIndex(card, invalidIndex);
		});

		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void insertCard_validIndex() {
		Card firstCard = EasyMock.createMock(Card.class);
		Card secondCard = EasyMock.createMock(Card.class);

		Deck deck = new Deck();

		deck.insertCardAtIndex(firstCard, 0);
		assertEquals(1, deck.getSize());
		assertEquals(firstCard, deck.getCardAtIndex(0));

		deck.insertCardAtIndex(secondCard, 1);
		assertEquals(2, deck.getSize());
		assertEquals(secondCard, deck.getCardAtIndex(1));
	}

	@Test
	public void insertCardAtRandomIndex_EmptyDeck() {
		Card card = EasyMock.createMock(Card.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand);

		EasyMock.expect(rand.nextInt(1)).andReturn(0);
		EasyMock.replay(rand);
		deck.insertCardAtRandomIndex(card);

		assertEquals(1, deck.getSize());
		assertEquals(card, deck.getCardAtIndex(0));

		EasyMock.verify(rand);
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2})
	public void insertCardAtRandomIndex_NormalCase(int index) {
		Card firstcard = EasyMock.createMock(Card.class);
		Card secondcard = EasyMock.createMock(Card.class);
		Card thirdcard = EasyMock.createMock(Card.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand);
		final int expectedSize = 3;

		deck.insertCardAtIndex(firstcard, 0);
		deck.insertCardAtIndex(secondcard, 1);

		EasyMock.expect(rand.nextInt(deck.getSize() + 1)).andReturn(index);
		EasyMock.replay(rand);
		deck.insertCardAtRandomIndex(thirdcard);

		assertEquals(expectedSize, deck.getSize());
		assertEquals(thirdcard, deck.getCardAtIndex(index));

		EasyMock.verify(rand);
	}

	@Test
	public void drawCard_NormalCase() {
		Card firstcard = EasyMock.createMock(Card.class);
		Card secondcard = EasyMock.createMock(Card.class);
		Card thirdcard = EasyMock.createMock(Card.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		final int expectedSize = 3;

		Deck deck = new Deck(rand);

		deck.insertCardAtIndex(firstcard, 0);
		deck.insertCardAtIndex(secondcard, 1);
		deck.insertCardAtIndex(thirdcard, 2);

		assertEquals(expectedSize, deck.getSize());
		assertEquals(firstcard, deck.drawCard());
		assertEquals(secondcard, deck.drawCard());
		assertEquals(thirdcard, deck.drawCard());
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, INT_MAX})
	public void getCardAtIndex_invalidIndex_throwException(int invalidIndex) {
		Deck deck = new Deck();

		String expectedMessage = "Invalid index: index out of range";
		Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
			Card card = deck.getCardAtIndex(invalidIndex);
		});

		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void getCardAtIndex_validIndex() {
		Card firstCard = EasyMock.createMock(Card.class);
		Card secondCard = EasyMock.createMock(Card.class);

		Deck deck = new Deck();

		deck.insertCardAtIndex(firstCard, 0);
		deck.insertCardAtIndex(secondCard, 1);
		assertEquals(2, deck.getSize());
		assertEquals(firstCard, deck.getCardAtIndex(0));
		assertEquals(secondCard, deck.getCardAtIndex(1));
	}
}
