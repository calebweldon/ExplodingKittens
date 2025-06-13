package domain.cardcontroller;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.CardType;
import domain.Player;
import domain.TurnResult;
import ui.RecycleCardView;

public class RecycleCardControllerTest {
	@Test
	public void handleRecycleCardAction_AttackCard() {
		RecycleCardView view = EasyMock.createMock(RecycleCardView.class);
		view.actionMessage();
		Player player = EasyMock.createMock(Player.class);
		player.addCard(CardType.ATTACK);
		EasyMock.replay(view, player);

		RecycleCardController recycleCardController = new RecycleCardController(view);
		recycleCardController.updatePlayer(player);
		recycleCardController.updateLastPlayed(CardType.ATTACK);

		TurnResult expected = TurnResult.CONTINUE;
		TurnResult actual = recycleCardController.handleCardAction();

		assertEquals(expected, actual);
		EasyMock.verify(view);
	}

	@Test
	public void handleRecycleCardAction_DefuseCard() {
		RecycleCardView view = EasyMock.createMock(RecycleCardView.class);
		view.actionMessage();
		Player player = EasyMock.createMock(Player.class);
		player.addCard(CardType.DEFUSE);
		EasyMock.replay(view, player);

		RecycleCardController recycleCardController = new RecycleCardController(view);
		recycleCardController.updatePlayer(player);
		recycleCardController.updateLastPlayed(CardType.DEFUSE);

		TurnResult expected = TurnResult.CONTINUE;
		TurnResult actual = recycleCardController.handleCardAction();

		assertEquals(expected, actual);
		EasyMock.verify(view);
	}

	@Test
	public void handleRecycleCardAction_RecycleCard() {
		RecycleCardView view = EasyMock.createMock(RecycleCardView.class);
		view.actionMessage();
		Player player = EasyMock.createMock(Player.class);
		player.addCard(CardType.RECYCLE);
		EasyMock.replay(view, player);

		RecycleCardController recycleCardController = new RecycleCardController(view);
		recycleCardController.updatePlayer(player);
		recycleCardController.updateLastPlayed(CardType.RECYCLE);

		TurnResult expected = TurnResult.CONTINUE;
		TurnResult actual = recycleCardController.handleCardAction();

		assertEquals(expected, actual);
		EasyMock.verify(view, player);
	}

	@Test
	public void getInfo_recycleCardController() {
		RecycleCardView view = EasyMock.createMock(RecycleCardView.class);
		view.getInfo();
		EasyMock.replay(view);

		RecycleCardController controller = new RecycleCardController(view);
		controller.getInfo();

		EasyMock.verify(view);
	}
}
