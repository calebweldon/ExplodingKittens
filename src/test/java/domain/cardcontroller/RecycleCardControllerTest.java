package domain.cardcontroller;

import domain.Deck;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.CardType;
import domain.Player;
import domain.TurnResult;
import ui.AlterFutureCardView;
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

	@Test
	public void IntegrationTest_recycleCard_RecycleCard() {
		Deck deck = new Deck();

		CardType firstCard = deck.getCardAtIndex(0);
		CardType secondCard = deck.getCardAtIndex(1);
		CardType thirdCard = deck.getCardAtIndex(2);
		CardType[] topCards = {firstCard, secondCard, thirdCard};

		AlterFutureCardView view = EasyMock.createMock(AlterFutureCardView.class);
		CardType[] reorderedCards = {firstCard, thirdCard, secondCard};
		view.actionMessage();
		view.displayTopCards(topCards);
		EasyMock.expect(view.promptForNewOrder(topCards)).andReturn(reorderedCards);

		EasyMock.replay(view);

		AlterFutureCardController controller = new AlterFutureCardController(view, deck);

		TurnResult expectedTurnResult = TurnResult.CONTINUE;
		TurnResult actualTurnResult = controller.handleCardAction();

		assertEquals(expectedTurnResult, actualTurnResult);

		CardType expectedFirstCard = firstCard;
		CardType expectedSecondCard = thirdCard;
		CardType expectedThirdCard = secondCard;

		CardType actualFirstCard = deck.getCardAtIndex(0);
		CardType actualSecondCard = deck.getCardAtIndex(1);
		CardType actualThirdCard = deck.getCardAtIndex(2);

		assertEquals(expectedFirstCard, actualFirstCard);
		assertEquals(expectedSecondCard, actualSecondCard);
		assertEquals(expectedThirdCard, actualThirdCard);

		EasyMock.verify(view);
	}
}
