package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.easymock.EasyMock;

import java.security.SecureRandom;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
	public static final int INT_MAX = 2147483647;

	@ParameterizedTest
	@ValueSource(ints = {-1, INT_MAX})
	public void insertCard_invalidIndex_throwException(int invalidIndex) {
		Card card = EasyMock.createMock(Card.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		LinkedList<Card> list = EasyMock.createMock(LinkedList.class);
		Deck deck = new Deck(rand, list);

		EasyMock.expect(list.size()).andStubReturn(0);
		EasyMock.replay(rand, list);

		String expectedMessage = "Invalid index: " + invalidIndex;
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
		LinkedList<Card> list = EasyMock.createMock(LinkedList.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand, list);

		EasyMock.expect(rand.nextInt(1)).andReturn(0);

		//TODO: Double check if this is correct with Prof. Yiji.
		EasyMock.expect(list.size()).andReturn(0);
		EasyMock.expect(list.size()).andReturn(0);
		list.add(0, card);
		EasyMock.expect(list.size()).andReturn(1);
		EasyMock.expect(list.size()).andReturn(1);
		EasyMock.expect(list.get(0)).andReturn(card);

		EasyMock.replay(rand, list);
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
		LinkedList<Card> list = EasyMock.createMock(LinkedList.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand, list);
		final int expectedSize = 3;

		EasyMock.expect(list.size()).andReturn(0);
		list.add(0, firstcard);
		EasyMock.expect(list.size()).andReturn(1);
		list.add(1, secondcard);
		EasyMock.expect(list.size()).andReturn(2);
		EasyMock.expect(list.size()).andReturn(2);
		EasyMock.expect(rand.nextInt(expectedSize)).andReturn(index);
		list.add(index, thirdcard);

		EasyMock.expect(list.size()).andReturn(expectedSize);
		EasyMock.expect(list.size()).andReturn(expectedSize);
		EasyMock.expect(list.get(index)).andReturn(thirdcard);
		EasyMock.replay(rand, list);

		deck.insertCardAtIndex(firstcard, 0);
		deck.insertCardAtIndex(secondcard, 1);
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
		LinkedList<Card> list = EasyMock.createMock(LinkedList.class);
		final int expectedSize = 3;

		EasyMock.expect(list.size()).andReturn(0);
		list.add(0, firstcard);
		EasyMock.expect(list.size()).andReturn(1);
		list.add(1, secondcard);
		EasyMock.expect(list.size()).andReturn(2);
		list.add(2, thirdcard);
		EasyMock.expect(list.size()).andReturn(expectedSize);
		EasyMock.expect(list.remove(0)).andReturn(firstcard);
		EasyMock.expect(list.remove(0)).andReturn(secondcard);
		EasyMock.expect(list.remove(0)).andReturn(thirdcard);
		EasyMock.replay(list);

		Deck deck = new Deck(rand, list);

		deck.insertCardAtIndex(firstcard, 0);
		deck.insertCardAtIndex(secondcard, 1);
		deck.insertCardAtIndex(thirdcard, 2);

		assertEquals(expectedSize, deck.getSize());
		assertEquals(firstcard, deck.drawCard());
		assertEquals(secondcard, deck.drawCard());
		assertEquals(thirdcard, deck.drawCard());
		EasyMock.verify(list);
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, INT_MAX})
	public void getCardAtIndex_invalidIndex_throwException(int invalidIndex) {
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		LinkedList<Card> list = EasyMock.createMock(LinkedList.class);
		Deck deck = new Deck(rand, list);

		EasyMock.expect(list.size()).andStubReturn(0);
		EasyMock.replay(rand, list);

		String expectedMessage = "Invalid index: " + invalidIndex;
		Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
			Card card = deck.getCardAtIndex(invalidIndex);
		});

		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void drawCardFromBottom_NormalCase() {
		Card firstcard = EasyMock.createMock(Card.class);
		Card secondcard = EasyMock.createMock(Card.class);
		Card thirdcard = EasyMock.createMock(Card.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		LinkedList<Card> list = EasyMock.createMock(LinkedList.class);
		final int expectedSize = 3;

		EasyMock.expect(list.size()).andReturn(0);
		list.add(0, firstcard);
		EasyMock.expect(list.size()).andReturn(1);
		list.add(1, secondcard);
		EasyMock.expect(list.size()).andReturn(2);
		list.add(2, thirdcard);
		EasyMock.expect(list.size()).andReturn(expectedSize);
		EasyMock.expect(list.remove(0)).andReturn(thirdcard);
		EasyMock.expect(list.remove(0)).andReturn(secondcard);
		EasyMock.expect(list.remove(0)).andReturn(firstcard);
		EasyMock.replay(list);

		Deck deck = new Deck(rand, list);

		deck.insertCardAtIndex(firstcard, 0);
		deck.insertCardAtIndex(secondcard, 1);
		deck.insertCardAtIndex(thirdcard, 2);

		assertEquals(expectedSize, deck.getSize());
		assertEquals(thirdcard, deck.drawCard());
		assertEquals(secondcard, deck.drawCard());
		assertEquals(firstcard, deck.drawCard());
		EasyMock.verify(list);
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
