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
		
		List<Card> expected = player.viewHand();

		assertTrue(expected.isEmpty());
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
}
