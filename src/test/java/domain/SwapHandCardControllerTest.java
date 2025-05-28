package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.GameController;
import ui.SwapHandCardView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwapHandCardControllerTest {
	@Test
	public void UnitTest_TwoActivePlayersExcludingCurrentWithSixCards_handleSwapHandCardAction() {
		Player playerOne = EasyMock.createMock(Player.class);
		EasyMock.expect(playerOne.getId()).andReturn(1).anyTimes();;
		EasyMock.expect(playerOne.getHandSize()).andReturn(6).anyTimes();;

		Player playerTwo = EasyMock.createMock(Player.class);
		EasyMock.expect(playerTwo.getId()).andReturn(2).anyTimes();;
		EasyMock.expect(playerTwo.getHandSize()).andReturn(6).anyTimes();;

		List<Player> activePlayers = new ArrayList<>(List.of(playerOne, playerTwo));

		Player currentPlayer = EasyMock.createMock(Player.class);
		currentPlayer.swapHandWith(playerOne);

		SwapHandCardView view = EasyMock.createMock(SwapHandCardView.class);
		view.actionMessage();
		EasyMock.expect(view.promptForPlayerToSwapWith(activePlayers)).andReturn(playerOne);

		EasyMock.replay(playerOne, playerTwo, currentPlayer, view);

		SwapHandCardController swapHandCardController = new SwapHandCardController(view);
		swapHandCardController.updatePlayer(currentPlayer);
		swapHandCardController.updateActivePlayersExcludingCurrent(activePlayers);

		TurnResult actual = swapHandCardController.handleCardAction();
		TurnResult expected = TurnResult.CONTINUE;

		assertEquals(expected, actual);

		EasyMock.verify(playerOne, playerTwo, currentPlayer, view);
	}
}
