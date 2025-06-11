package domain.cardcontroller;

import domain.CardType;
import domain.Player;
import domain.TurnResult;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ui.ExplodiaCardView;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplodiaCardControllerTest {
	private ExplodiaCardView cv;
	private SecureRandom rand;
	private List<CardController> cardControllers;

	@BeforeEach
	public void setUp() {
		this.cv = EasyMock.createMock(ExplodiaCardView.class);
		this.rand = EasyMock.createMock(SecureRandom.class);

		AttackCardController c1 = EasyMock.createMock(AttackCardController.class);
		SkipCardController c2 = EasyMock.createMock(SkipCardController.class);
		FavorCardController c3 = EasyMock.createMock(FavorCardController.class);
		BasicCardController c4 = EasyMock.createMock(BasicCardController.class);
		FlipCardController c5 = EasyMock.createMock(FlipCardController.class);
		ShuffleCardController c6 = EasyMock.createMock(ShuffleCardController.class);
		SwapHandCardController c7 = EasyMock.createMock(SwapHandCardController.class);
		RecycleCardController c8 = EasyMock.createMock(RecycleCardController.class);
		SeeFutureCardController c9 = EasyMock.createMock(SeeFutureCardController.class);
		AlterFutureCardController c10 = EasyMock.createMock(AlterFutureCardController.class);
		this.cardControllers = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3})
	public void handleExplodiaCardDraw_Continue(int numExplodia) {
		Player player = EasyMock.createMock(Player.class);
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		ExplodiaCardController explodiaCardController = new ExplodiaCardController(cv, cardControllers, rand);

		explodiaCardController.updatePlayer(player);
		player.addCard(CardType.EXPLODIA);
		EasyMock.expect(player.viewHand()).andReturn(hand);
		EasyMock.expect(hand.getOrDefault(CardType.EXPLODIA,0)).andReturn(numExplodia);

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
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		ExplodiaCardController explodiaCardController = new ExplodiaCardController(cv, cardControllers, rand);

		explodiaCardController.updatePlayer(player);
		player.addCard(CardType.EXPLODIA);
		EasyMock.expect(player.viewHand()).andReturn(hand);
		EasyMock.expect(hand.getOrDefault(CardType.EXPLODIA, 0)).andReturn(numExplodia);

		cv.drawMessage(numExplodia);
		EasyMock.replay(cv, player, hand);

		TurnResult result = explodiaCardController.handleCardDraw();

		assertEquals(TurnResult.WON, result);
		EasyMock.verify(cv, player, hand);
	}

	@ParameterizedTest
	@ValueSource(ints = {0,1,2,3,4,5,6,7,8,9})
	public void handleExplodiaCardAction_BecomesOtherCard(int index) {
		int NUM_CONTROLLERS = 10;

		ExplodiaCardController explodiaCardController = new ExplodiaCardController(cv, cardControllers, rand);

		cv.actionMessage();
		EasyMock.expect(rand.nextInt(NUM_CONTROLLERS)).andReturn(index);
		ActionCardController controller = (ActionCardController) cardControllers.get(index);
		EasyMock.expect(controller.handleCardAction()).andReturn(null);

		EasyMock.replay(rand, cv, controller);
		explodiaCardController.handleCardAction();
		EasyMock.verify(rand, cv, controller);
	}
}
