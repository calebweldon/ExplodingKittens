package domain.cardcontroller;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.BasicCardView;

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
        EasyMock.expect(view.promptForPlayer().andReturn(playerOne));
        EasyMock.replay(view, playerOne, playerTwo, currentPlayer);

        BasicCardController basicCardController = new BasicCardController(view);
        basicCardController.updatePlayer(currentPlayer);
        basicCardController.updateActivePlayersExcludingCurrent(activePlayers);

        TurnResult expected = TurnResult.CONTINUE;
        TurnResult actual = basicCardController.handleCardAction();

        assertEquals(expected, actual);
        EasyMock.verify(view, playerOne, playerTwo, currentPlayer);
    }
}
