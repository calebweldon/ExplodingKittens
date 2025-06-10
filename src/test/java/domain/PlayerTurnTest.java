package domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.easymock.EasyMock;

public class PlayerTurnTest {

	@Test
	public void testPlayerTurn() {
		Player player = EasyMock.createMock(Player.class);
		EasyMock.replay(player);
		
		PlayerTurn playerTurn = new PlayerTurn(player, 1);
		
		assertEquals(player, playerTurn.player);
		assertEquals(1, playerTurn.remainingTurns);
		EasyMock.verify(player);
	}
}
