package domain.cardcontroller;

import domain.CardType;
import domain.Player;
import domain.TurnResult;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ui.ExplodiaCardView;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplodiaCardControllerTest {
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3})
	public void handleExplodiaCardDraw_Continue(int numExplodia) {
		Player player = EasyMock.createMock(Player.class);
		ExplodiaCardView cv = EasyMock.createMock(ExplodiaCardView.class);
		ArrayList<CardController> cardControllers = EasyMock.createMock(ArrayList.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		ExplodiaCardController explodiaCardController = new ExplodiaCardController(cv, cardControllers, rand);

		explodiaCardController.updatePlayer(player);
		player.addCard(CardType.EXPLODIA);
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
		ArrayList<CardController> cardControllers = EasyMock.createMock(ArrayList.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		ExplodiaCardController explodiaCardController = new ExplodiaCardController(cv, cardControllers, rand);

		explodiaCardController.updatePlayer(player);
		player.addCard(CardType.EXPLODIA);
		EasyMock.expect(player.viewHand()).andReturn(hand);
		EasyMock.expect(hand.get(CardType.EXPLODIA)).andReturn(numExplodia);

		cv.drawMessage(numExplodia);
		EasyMock.replay(cv, player, hand);

		TurnResult result = explodiaCardController.handleCardDraw();

		assertEquals(TurnResult.WON, result);
		EasyMock.verify(cv, player, hand);
	}

	@Test
	public void handleExplodiaCardAction_BecomesAttack() {
		int index = 0;
		int NUM_CONTROLLERS = 10;
		ArrayList<CardController> cardControllers = EasyMock.createMock(ArrayList.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		ExplodiaCardView cv = EasyMock.createMock(ExplodiaCardView.class);
		AttackCardController controller = EasyMock.createMock(AttackCardController.class);
		ExplodiaCardController explodiaCardController = new ExplodiaCardController(cv, cardControllers, rand);

		EasyMock.expect(cardControllers.size()).andReturn(NUM_CONTROLLERS);
		EasyMock.expect(rand.nextInt(NUM_CONTROLLERS)).andReturn(index);
		EasyMock.expect(cardControllers.get(index)).andReturn(controller);
		EasyMock.expect(controller.handleCardAction()).andReturn(TurnResult.ATTACK);

		EasyMock.replay(cardControllers, rand, cv, controller);
		explodiaCardController.handleCardAction();
		EasyMock.verify(cardControllers, rand, cv, controller);
	}

	@Test
	public void handleExplodiaCardAction_BecomesSkip() {
		int index = 0;
		int NUM_CONTROLLERS = 10;
		ArrayList<CardController> cardControllers = EasyMock.createMock(ArrayList.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		ExplodiaCardView cv = EasyMock.createMock(ExplodiaCardView.class);
		SkipCardController controller = EasyMock.createMock(SkipCardController.class);
		ExplodiaCardController explodiaCardController = new ExplodiaCardController(cv, cardControllers, rand);

		EasyMock.expect(cardControllers.size()).andReturn(NUM_CONTROLLERS);
		EasyMock.expect(rand.nextInt(NUM_CONTROLLERS)).andReturn(index);
		EasyMock.expect(cardControllers.get(index)).andReturn(controller);
		EasyMock.expect(controller.handleCardAction()).andReturn(TurnResult.SKIP);

		EasyMock.replay(cardControllers, rand, cv, controller);
		explodiaCardController.handleCardAction();
		EasyMock.verify(cardControllers, rand, cv, controller);
	}

	@Test
	public void handleExplodiaCardAction_BecomesFavor() {
		int index = 0;
		int NUM_CONTROLLERS = 10;
		ArrayList<CardController> cardControllers = EasyMock.createMock(ArrayList.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		ExplodiaCardView cv = EasyMock.createMock(ExplodiaCardView.class);
		FavorCardController controller = EasyMock.createMock(FavorCardController.class);
		ExplodiaCardController explodiaCardController = new ExplodiaCardController(cv, cardControllers, rand);

		EasyMock.expect(cardControllers.size()).andReturn(NUM_CONTROLLERS);
		EasyMock.expect(rand.nextInt(NUM_CONTROLLERS)).andReturn(index);
		EasyMock.expect(cardControllers.get(index)).andReturn(controller);
		EasyMock.expect(controller.handleCardAction()).andReturn(TurnResult.CONTINUE);

		EasyMock.replay(cardControllers, rand, cv, controller);
		explodiaCardController.handleCardAction();
		EasyMock.verify(cardControllers, rand, cv, controller);
	}

	@Test
	public void handleExplodiaCardAction_BecomesBasic() {
		int index = 0;
		int NUM_CONTROLLERS = 10;
		ArrayList<CardController> cardControllers = EasyMock.createMock(ArrayList.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		ExplodiaCardView cv = EasyMock.createMock(ExplodiaCardView.class);
		BasicCardController controller = EasyMock.createMock(BasicCardController.class);
		ExplodiaCardController explodiaCardController = new ExplodiaCardController(cv, cardControllers, rand);

		EasyMock.expect(cardControllers.size()).andReturn(NUM_CONTROLLERS);
		EasyMock.expect(rand.nextInt(NUM_CONTROLLERS)).andReturn(index);
		EasyMock.expect(cardControllers.get(index)).andReturn(controller);
		EasyMock.expect(controller.handleCardAction()).andReturn(TurnResult.CONTINUE);

		EasyMock.replay(cardControllers, rand, cv, controller);
		explodiaCardController.handleCardAction();
		EasyMock.verify(cardControllers, rand, cv, controller);
	}

	@Test
	public void handleExplodiaCardAction_BecomesShuffle() {
		int index = 0;
		int NUM_CONTROLLERS = 10;
		ArrayList<CardController> cardControllers = EasyMock.createMock(ArrayList.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		ExplodiaCardView cv = EasyMock.createMock(ExplodiaCardView.class);
		ShuffleCardController controller = EasyMock.createMock(ShuffleCardController.class);
		ExplodiaCardController explodiaCardController = new ExplodiaCardController(cv, cardControllers, rand);

		EasyMock.expect(cardControllers.size()).andReturn(NUM_CONTROLLERS);
		EasyMock.expect(rand.nextInt(NUM_CONTROLLERS)).andReturn(index);
		EasyMock.expect(cardControllers.get(index)).andReturn(controller);
		EasyMock.expect(controller.handleCardAction()).andReturn(TurnResult.CONTINUE);

		EasyMock.replay(cardControllers, rand, cv, controller);
		explodiaCardController.handleCardAction();
		EasyMock.verify(cardControllers, rand, cv, controller);
	}
}
