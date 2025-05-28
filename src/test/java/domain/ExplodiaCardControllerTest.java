package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ui.ExplodiaCardView;
import ui.ImplodingFaceDownCardView;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplodiaCardControllerTest {
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3})
	public void handleExplodiaCardDraw_Continue(int numExplodia) {
		Player player = EasyMock.createMock(Player.class);
		ExplodiaCardView cv = EasyMock.createMock(ExplodiaCardView.class);
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		ExplodiaCardController explodiaCardController = new ExplodiaCardController(cv);

		explodiaCardController.updatePlayer(player);
		EasyMock.expect(player.viewHand()).andReturn(hand);
		EasyMock.expect(hand.get(CardType.EXPLODIA)).andReturn(numExplodia);

		cv.drawMessage(numExplodia);
		EasyMock.replay(cv, player, hand);

		TurnResult result = explodiaCardController.handleCardDraw();

		assertEquals(TurnResult.CONTINUE, result);
		EasyMock.verify(cv, player, hand);
	}

	@Test
	public void handleExplodiaCardDraw_Won() {
		int numExplodia = 5;
		Player player = EasyMock.createMock(Player.class);
		ExplodiaCardView cv = EasyMock.createMock(ExplodiaCardView.class);
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		ExplodiaCardController explodiaCardController = new ExplodiaCardController(cv);

		explodiaCardController.updatePlayer(player);
		EasyMock.expect(player.viewHand()).andReturn(hand);
		EasyMock.expect(hand.get(CardType.EXPLODIA)).andReturn(numExplodia);

		cv.drawMessage(numExplodia);
		EasyMock.replay(cv, player, hand);

		TurnResult result = explodiaCardController.handleCardDraw();

		assertEquals(TurnResult.WON, result);
		EasyMock.verify(cv, player, hand);
	}
}
