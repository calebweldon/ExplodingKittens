package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

public class PlayerTest {
	
	@Test
	public void viewHand_emptyHand_returnsEmpty() {
		Player player = new Player();
		
		List<Card> actual = player.viewHand();

		assertTrue(actual.isEmpty());
	}

	@Test
	public void drawCard_oneCardInDeck_drawsCard() {
		Deck deck = EasyMock.createMock(Deck.class);
		Card tacoCat = EasyMock.createMock(Card.class);
		EasyMock.expect(deck.drawCard()).andReturn(tacoCat);
		EasyMock.replay(deck, tacoCat);

		Player player = new Player();
		player.drawCard(deck);

		List<Card> expected = new ArrayList<Card>();
		expected.add(tacoCat);
		List<Card> actual = player.viewHand();

		assertEquals(expected, actual);
		EasyMock.verify(deck, tacoCat);
	}

	@Test
	public void drawCard_threeCardsInDeck() {
		Deck deck = EasyMock.createMock(Deck.class);
		Card card1 = EasyMock.createMock(Card.class);
		Card card2 = EasyMock.createMock(Card.class);
		Card card3 = EasyMock.createMock(Card.class);
		EasyMock.expect(deck.drawCard()).andReturn(card1);
		EasyMock.expect(deck.drawCard()).andReturn(card2);
		EasyMock.expect(deck.drawCard()).andReturn(card3);
		EasyMock.replay(deck, card1, card2, card3);
		
		Player player = new Player();
		List<Card> expected = new ArrayList<Card>();

		final int numCards = 3;
		Card[] cardOrder = {card1, card2, card3};
		for (int i = 0; i < numCards; i++) {
			player.drawCard(deck);
			expected.add(cardOrder[i]);
			List<Card> actual = player.viewHand();
			assertEquals(expected, actual);
		}

		EasyMock.verify(deck, card1, card2, card3);
	}

	@Test
	public void playCard_oneCardInHand_removesCard() {
		Deck deck = EasyMock.createMock(Deck.class);
		Card tacoCat = EasyMock.createMock(Card.class);
		EasyMock.expect(deck.drawCard()).andReturn(tacoCat);
		EasyMock.replay(deck, tacoCat);

		Player player = new Player();
		player.drawCard(deck);

		Card actual = player.playCard(0);

		assertEquals(tacoCat, actual);
		EasyMock.verify(deck, tacoCat);
	}

	@Test
	public void playCard_threeCardInHand() {
		Deck deck = EasyMock.createMock(Deck.class);
		Card card1 = EasyMock.createMock(Card.class);
		Card card2 = EasyMock.createMock(Card.class);
		Card card3 = EasyMock.createMock(Card.class);
		EasyMock.expect(deck.drawCard()).andReturn(card1);
		EasyMock.expect(deck.drawCard()).andReturn(card2);
		EasyMock.expect(deck.drawCard()).andReturn(card3);
		EasyMock.replay(deck, card1, card2, card3);

		Player player = new Player();
		player.drawCard(deck);
		player.drawCard(deck);
		player.drawCard(deck);

		Card actualMiddleCard = player.playCard(1);
		assertEquals(card2, actualMiddleCard);

		List<Card> actualCardsBetweenPlayCards = player.viewHand();
		List<Card> expectedCardsBetweenPlayCards = new ArrayList<Card>();
		expectedCardsBetweenPlayCards.add(card1);
		expectedCardsBetweenPlayCards.add(card3);
		assertEquals(expectedCardsBetweenPlayCards, actualCardsBetweenPlayCards);

		Card actualEndCard = player.playCard(1);
		assertEquals(card3, actualEndCard);

		List<Card> actualCardsAfterPlayCards = player.viewHand();
		List<Card> expectedCardsAfterPlayCards = new ArrayList<Card>();
		expectedCardsAfterPlayCards.add(card1);
		assertEquals(expectedCardsAfterPlayCards, actualCardsAfterPlayCards);

		EasyMock.verify(deck, card1, card2, card3);
	}
}
