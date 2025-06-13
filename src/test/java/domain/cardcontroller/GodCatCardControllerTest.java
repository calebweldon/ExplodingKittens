package domain.cardcontroller;

import domain.CardType;
import domain.Player;
import domain.TurnResult;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ui.AlterFutureCardView;
import ui.GodCatCardView;
import ui.RecycleCardView;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GodCatCardControllerTest {
	@ParameterizedTest
	@EnumSource(value = CardType.class, names = {"ATTACK", "SKIP", "FAVOR", "TACO_CAT", "FLIP", "SHUFFLE", "SWAP",
			"RECYCLE", "ALTER_THE_FUTURE", "SEE_THE_FUTURE"})
	public void handleGodCatCardAction_BecomesOtherCard(CardType card) {
		GodCatCardView cv = EasyMock.createMock(GodCatCardView.class);

		Map<CardType, CardController> cardControllers = new HashMap<>();
		cardControllers.put(CardType.ATTACK, EasyMock.createMock(AttackCardController.class));
		cardControllers.put(CardType.SKIP, EasyMock.createMock(SkipCardController.class));
		cardControllers.put(CardType.FAVOR, EasyMock.createMock(FavorCardController.class));
		cardControllers.put(CardType.TACO_CAT, EasyMock.createMock(BasicCardController.class));
		cardControllers.put(CardType.FLIP, EasyMock.createMock(FlipCardController.class));
		cardControllers.put(CardType.SHUFFLE, EasyMock.createMock(ShuffleCardController.class));
		cardControllers.put(CardType.SWAP, EasyMock.createMock(SwapHandCardController.class));
		cardControllers.put(CardType.RECYCLE, EasyMock.createMock(RecycleCardController.class));
		cardControllers.put(CardType.ALTER_THE_FUTURE, EasyMock.createMock(SeeFutureCardController.class));
		cardControllers.put(CardType.SEE_THE_FUTURE, EasyMock.createMock(AlterFutureCardController.class));

		GodCatCardController godCatCardController = new GodCatCardController(cv, cardControllers);

		cv.actionMessage();
		EasyMock.expect(cv.chooseController()).andReturn(card);
		ActionCardController controller = (ActionCardController) cardControllers.get(card);
		EasyMock.expect(controller.handleCardAction()).andReturn(TurnResult.CONTINUE);

		EasyMock.replay(cv, controller);
		assertEquals(TurnResult.CONTINUE, godCatCardController.handleCardAction());
		EasyMock.verify(cv, controller);
	}

	@Test
	public void integrationTest_godCatRecycle_godCatPlaysAsRecycleAddLastPlayedCard() {
		Player currentPlayer = new Player(1);

		RecycleCardView recycleCardView = EasyMock.createMock(RecycleCardView.class);
		recycleCardView.actionMessage();

		RecycleCardController recycleCardController = new RecycleCardController(recycleCardView);
		recycleCardController.updatePlayer(currentPlayer);
		recycleCardController.updateLastPlayed(CardType.RECYCLE);

		GodCatCardView godCatView = EasyMock.createMock(GodCatCardView.class);
		godCatView.actionMessage();

		Map<CardType, CardController> cardControllers = new HashMap<>();
		cardControllers.put(CardType.ATTACK, EasyMock.createMock(AttackCardController.class));
		cardControllers.put(CardType.SKIP, EasyMock.createMock(SkipCardController.class));
		cardControllers.put(CardType.FAVOR, EasyMock.createMock(FavorCardController.class));
		cardControllers.put(CardType.TACO_CAT, EasyMock.createMock(BasicCardController.class));
		cardControllers.put(CardType.FLIP, EasyMock.createMock(FlipCardController.class));
		cardControllers.put(CardType.SHUFFLE, EasyMock.createMock(ShuffleCardController.class));
		cardControllers.put(CardType.SWAP, EasyMock.createMock(SwapHandCardController.class));
		cardControllers.put(CardType.RECYCLE, recycleCardController);
		cardControllers.put(CardType.ALTER_THE_FUTURE, EasyMock.createMock(SeeFutureCardController.class));
		cardControllers.put(CardType.SEE_THE_FUTURE, EasyMock.createMock(AlterFutureCardController.class));

		EasyMock.expect(godCatView.chooseController()).andReturn(CardType.RECYCLE);

		EasyMock.replay(godCatView, recycleCardView);

		GodCatCardController godCatCardController = new GodCatCardController(godCatView, cardControllers);
		TurnResult actualTurnResult =  godCatCardController.handleCardAction();
		TurnResult expectedTurnResult = TurnResult.CONTINUE;

		assertEquals(expectedTurnResult, actualTurnResult);

		Map<CardType, Integer> expectedCurrentPlayerHandAfterRecycle = new HashMap<>();
		expectedCurrentPlayerHandAfterRecycle.put(CardType.RECYCLE, 1);

		Map<CardType, Integer> actualCurrentPlayerHandAfterRecycle = currentPlayer.viewHand();

		assertEquals(expectedCurrentPlayerHandAfterRecycle, actualCurrentPlayerHandAfterRecycle);

		EasyMock.verify(godCatView, recycleCardView);
	}

	@Test
	public void getInfo_godCatCardController() {
		GodCatCardView cv = EasyMock.createMock(GodCatCardView.class);
		cv.getInfo();
		EasyMock.replay(cv);

		GodCatCardController godCatCardController = new GodCatCardController(cv, null);
		godCatCardController.getInfo();

		EasyMock.verify(cv);
	}
}
