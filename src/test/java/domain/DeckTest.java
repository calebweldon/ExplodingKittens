package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.easymock.EasyMock;

import java.security.SecureRandom;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
	public static final int INT_MAX = 2147483647;
	public static final int MAX_DECK_SIZE = 61;

	@ParameterizedTest
	@ValueSource(ints = {-1, INT_MAX})
	public void insertCard_invalidIndex_throwException(int invalidIndex) {
		CardType card = CardType.ATTACK;
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand, false);

		String expectedMessage = String.format("Invalid index: %d", invalidIndex);
		Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
			deck.insertCardAtIndex(card, invalidIndex);
		});

		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void insertCard_validIndex() {
		CardType firstCard = CardType.EXPLODING_KITTEN;
		CardType secondCard = CardType.DEFUSE;
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);

		Deck deck = new Deck(rand, false);

		deck.insertCardAtIndex(firstCard, 0);
		assertEquals(1, deck.getSize());
		assertEquals(firstCard, deck.getCardAtIndex(0));

		deck.insertCardAtIndex(secondCard, 1);
		assertEquals(2, deck.getSize());
		assertEquals(secondCard, deck.getCardAtIndex(1));
	}

	@Test
	public void insertCardAtRandomIndex_EmptyDeck() {
		CardType card = CardType.DEFUSE;
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand, false);

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
		CardType firstcard = CardType.SHUFFLE;
		CardType secondcard = CardType.SEE_THE_FUTURE;
		CardType thirdcard = CardType.FAVOR;
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand, false);
		final int expectedSize = 3;

		EasyMock.expect(rand.nextInt(expectedSize)).andReturn(index);

		EasyMock.replay(rand);

		deck.insertCardAtIndex(firstcard, 0);
		deck.insertCardAtIndex(secondcard, 1);
		deck.insertCardAtRandomIndex(thirdcard);

		assertEquals(expectedSize, deck.getSize());
		assertEquals(thirdcard, deck.getCardAtIndex(index));

		EasyMock.verify(rand);
	}

	@Test
	public void drawCard_NormalCase() {
		CardType firstcard = CardType.SHUFFLE;
		CardType secondcard = CardType.SKIP;
		CardType thirdcard = CardType.EXPLODING_KITTEN;
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		final int expectedSize = 3;

		Deck deck = new Deck(rand, false);

		deck.insertCardAtIndex(firstcard, 0);
		deck.insertCardAtIndex(secondcard, 1);
		deck.insertCardAtIndex(thirdcard, 2);

		assertEquals(expectedSize, deck.getSize());
		assertEquals(firstcard, deck.drawCard());
		assertEquals(secondcard, deck.drawCard());
		assertEquals(thirdcard, deck.drawCard());
	}

	@Test
	public void drawCardFromBottom_NormalCase() {
		CardType firstcard = CardType.ATTACK;
		CardType secondcard = CardType.TACO_CAT;
		CardType thirdcard = CardType.SKIP;
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		final int expectedSize = 3;

		Deck deck = new Deck(rand, false);

		deck.insertCardAtIndex(firstcard, 0);
		deck.insertCardAtIndex(secondcard, 1);
		deck.insertCardAtIndex(thirdcard, 2);

		assertEquals(expectedSize, deck.getSize());
		assertEquals(thirdcard, deck.drawCardFromBottom());
		assertEquals(secondcard, deck.drawCardFromBottom());
		assertEquals(firstcard, deck.drawCardFromBottom());
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, INT_MAX})
	public void getCardAtIndex_invalidIndex_throwException(int invalidIndex) {
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand, false);

		String expectedMessage = String.format("Invalid index: %d", invalidIndex);
		Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
			CardType card = deck.getCardAtIndex(invalidIndex);
		});

		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void getCardAtIndex_validIndex() {
		CardType firstCard = CardType.SKIP;
		CardType secondCard = CardType.EXPLODING_KITTEN;
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);

		Deck deck = new Deck(rand, false);

		deck.insertCardAtIndex(firstCard, 0);
		deck.insertCardAtIndex(secondCard, 1);
		assertEquals(2, deck.getSize());
		assertEquals(firstCard, deck.getCardAtIndex(0));
		assertEquals(secondCard, deck.getCardAtIndex(1));
	}

	@SuppressWarnings("checkstyle:MagicNumber")
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4})
	public void addSpecialCards_FilledDeck(int numExplodingCards) {
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand, true);
		final int num_explodia = 5;

		deck.addSpecialCards(numExplodingCards);

		assertEquals(numExplodingCards, deck.getCardCount(CardType.EXPLODING_KITTEN));
		assertEquals(2, deck.getCardCount(CardType.DEFUSE));
		assertEquals(num_explodia, deck.getCardCount(CardType.EXPLODIA));
		assertEquals(1, deck.getCardCount(CardType.IMPLODING_FACEDOWN));
	}

	@Test
	public void getImplodingIndex_WithoutImploding() {
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand, false);

		assertEquals(-1, deck.getImplodingIndex());
	}

	@Test
	public void getImplodingIndex_WithImploding() {
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand, false);
		CardType imploding = CardType.IMPLODING_FACEUP;

		deck.insertCardAtIndex(imploding, 0);

		assertEquals(0, deck.getImplodingIndex());
	}

	@Test
	public void getDeckSize_OneCard() {
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand, false);
		CardType imploding = CardType.IMPLODING_FACEUP;

		deck.insertCardAtIndex(imploding, 0);

		assertEquals(1, deck.getSize());
	}

	@Test
	public void getDeckSize_AllCards() {
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand, true);

		assertEquals(MAX_DECK_SIZE, deck.getSize());
	}

	// integration test, since flipDeck is not unit testable
	@Test
	public void flipDeck() {
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand, true);
		int originalSize = deck.getSize();
		Deck flippedDeck = new Deck(deck);

		flippedDeck.flipDeck();

		int flippedSize = deck.getSize();
		assertEquals(originalSize, flippedSize);
		for (int i = 0; i < flippedSize; i++) {
			assertEquals(flippedDeck.getCardAtIndex(flippedSize - i - 1), deck.getCardAtIndex(i));
		}
	}

	@Test
	public void shuffleDeckIntegrationTest() {
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck = new Deck(rand, true);
		int originalSize = deck.getSize();
		Deck shuffledDeck = new Deck(deck);

		shuffledDeck.shuffleDeck();

		int shuffledSize = deck.getSize();
		assertEquals(originalSize, shuffledSize);

		CardType[] shuffled = new CardType[shuffledSize];
		CardType[] original = new CardType[originalSize];
		for (int i = 0; i < shuffledSize; i++) {
			shuffled[i] = shuffledDeck.getCardAtIndex(i);
			original[i] = deck.getCardAtIndex(i);
		}
		assertNotEquals(0,Arrays.compare(original,shuffled));
	}

	@Test
	public void publicConstructor_doesntCrash() {
		Deck deck = new Deck();
		assertNotEquals(0, deck.getSize());
		assertNotNull(deck);
	}

	@Test
	public void ShuffleDeck_MutationTest() {
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Deck deck1 = new Deck();
		Deck deck2 = new Deck(rand, true);

		//Assumes God-Cat is added first
		assertNotEquals(CardType.GOD_CAT,deck1.getCardAtIndex(0));
		assertNotEquals(CardType.GOD_CAT,deck2.getCardAtIndex(0));

		CardType firstCard = deck1.getCardAtIndex(deck1.getSize() - 1);
		deck1.addSpecialCards(1);
		assertNotEquals(firstCard,deck1.getCardAtIndex(deck1.getSize() - 1));
	}
}
