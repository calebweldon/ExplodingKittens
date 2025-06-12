package domain.cardcontroller;

import domain.CardType;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ui.GodCatCardView;

import java.util.*;

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
		EasyMock.expect(controller.handleCardAction()).andReturn(null);

		EasyMock.replay(cv, controller);
		godCatCardController.handleCardAction();
		EasyMock.verify(cv, controller);
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
