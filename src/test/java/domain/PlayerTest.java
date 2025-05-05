package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PlayerTest {
	
	@Test
	public void viewHand_emptyHand_returnsEmpty() {
		Player player = new Player();
		
		List<Card> expected = player.viewHand();

		assertTrue(expected.isEmpty());
	}
}
