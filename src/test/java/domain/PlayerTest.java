package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class PlayerTest {
	
	@Test
	public void addCard_emptyHand_succeeds() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		EasyMock.expect(hand.getOrDefault(CardType.TACO_CAT, 0)).andReturn(0);
		EasyMock.expect(hand.put(CardType.TACO_CAT, 1)).andReturn(null);
		EasyMock.replay(hand);

		Player player = new Player(hand);
		player.addCard(CardType.TACO_CAT);

		EasyMock.verify(hand);
	}
}
