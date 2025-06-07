package domain.cardcontroller;

import domain.CardType;
import domain.Player;
import domain.TurnResult;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
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

	@Test
	public void UnitTest_OneActivePlayerExcludingCurrentWithZeroCards_handleSwapHandCardAction() {
		Player playerOne = EasyMock.createMock(Player.class);
		EasyMock.expect(playerOne.getId()).andReturn(1).anyTimes();;
		EasyMock.expect(playerOne.getHandSize()).andReturn(0).anyTimes();;

		List<Player> activePlayers = new ArrayList<>(List.of(playerOne));

		Player currentPlayer = EasyMock.createMock(Player.class);
		currentPlayer.swapHandWith(playerOne);

		SwapHandCardView view = EasyMock.createMock(SwapHandCardView.class);
		view.actionMessage();
		EasyMock.expect(view.promptForPlayerToSwapWith(activePlayers)).andReturn(playerOne);

		EasyMock.replay(playerOne, currentPlayer, view);

		SwapHandCardController swapHandCardController = new SwapHandCardController(view);
		swapHandCardController.updatePlayer(currentPlayer);
		swapHandCardController.updateActivePlayersExcludingCurrent(activePlayers);

		TurnResult actual = swapHandCardController.handleCardAction();
		TurnResult expected = TurnResult.CONTINUE;

		assertEquals(expected, actual);

		EasyMock.verify(playerOne, currentPlayer, view);
	}

	@Test
	public void IntegrationTest_TwoActivePlayersExcludingCurrentWithDiffHandSizes_handSwapped() {

		Player playerOne = new Player(1);
		Player playerTwo = new Player(2);
		Player currentPlayer = new Player(3);

		playerTwo.addCard(CardType.ATTACK);
		currentPlayer.addCard(CardType.SKIP);
		currentPlayer.addCard(CardType.SKIP);

		List<Player> activePlayers = new ArrayList<>(List.of(playerOne, playerTwo));

		SwapHandCardView view = EasyMock.createMock(SwapHandCardView.class);
		view.actionMessage();
		EasyMock.expect(view.promptForPlayerToSwapWith(activePlayers)).andReturn(playerOne);

		EasyMock.replay(view);

		SwapHandCardController controller = new SwapHandCardController(view);
		controller.updatePlayer(currentPlayer);
		controller.updateActivePlayersExcludingCurrent(activePlayers);

		assertEquals(2, currentPlayer.getHandSize());
		assertEquals(0, playerOne.getHandSize());

		TurnResult actual = controller.handleCardAction();
		TurnResult expected = TurnResult.CONTINUE;

		assertEquals(actual, expected);

		assertEquals(0, currentPlayer.getHandSize());
		assertEquals(2, playerOne.getHandSize());

		EasyMock.verify(view);
	}
}
