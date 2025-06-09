package domain.cardcontroller;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.ArrayList;

import ui.BasicCardView;

import domain.Player;
import domain.TurnResult;
import domain.CardType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicCardControllerTest {
	@Test
	public void handleBasicCardAction_canPlay() {
		Player currentPlayer = EasyMock.createMock(Player.class);
		Player playerOne = EasyMock.createMock(Player.class);
		Player playerTwo = EasyMock.createMock(Player.class);
		List<Player> activePlayers = new ArrayList<>(List.of(playerOne, playerTwo));
		BasicCardView view = EasyMock.createMock(BasicCardView.class);

		view.actionMessage();
		EasyMock.expect(view.promptForPlayer(activePlayers)).andReturn(playerOne);
		EasyMock.expect(playerOne.getHandSize()).andReturn(1);
		EasyMock.expect(playerOne.takeRandomCard()).andReturn(CardType.ATTACK);
		currentPlayer.addCard(CardType.ATTACK);
		view.displayCardTaken(CardType.ATTACK);

		EasyMock.replay(view, playerOne, playerTwo, currentPlayer);

		BasicCardController basicCardController = new BasicCardController(view, CardType.TACO_CAT);
		basicCardController.updatePlayer(currentPlayer);
		basicCardController.updateActivePlayersExcludingCurrent(activePlayers);

		TurnResult expected = TurnResult.CONTINUE;
		TurnResult actual = basicCardController.handleCardAction();

		assertEquals(expected, actual);
		EasyMock.verify(view, playerOne, playerTwo, currentPlayer);
	}
}
