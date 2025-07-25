package domain.cardcontroller;

import domain.CardType;
import domain.Player;
import domain.TurnResult;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.FavorCardView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FavorCardControllerTest {

	// Method 1: handleCardAction() BVA Test Case 1
	@Test
	public void handleCardAction_CurrentPlayerWithZeroCards_OneOtherPlayerWithCards_ReceivesCard() {
		Player currentPlayer = EasyMock.createMock(Player.class);
		Player otherPlayer = EasyMock.createMock(Player.class);
		FavorCardView view = EasyMock.createMock(FavorCardView.class);
		
		List<Player> activePlayersExcludingCurrent = new ArrayList<>();
		activePlayersExcludingCurrent.add(otherPlayer);
		
		List<Player> playersWithCards = new ArrayList<>();
		playersWithCards.add(otherPlayer);

		view.actionMessage();
		EasyMock.expect(otherPlayer.getHandSize()).andReturn(5);
		EasyMock.expect(view.promptForTargetPlayer(playersWithCards)).andReturn(otherPlayer);
		EasyMock.expect(view.promptTargetPlayerForCard(otherPlayer)).andReturn(CardType.ATTACK);
		otherPlayer.removeCard(CardType.ATTACK);
		currentPlayer.addCard(CardType.ATTACK);
		
		EasyMock.replay(currentPlayer, otherPlayer, view);

		FavorCardController controller = new FavorCardController(view);
		controller.updatePlayer(currentPlayer);
		controller.updateActivePlayersExcludingCurrent(activePlayersExcludingCurrent);
		
		TurnResult actual = controller.handleCardAction();
		TurnResult expected = TurnResult.CONTINUE;
		
		assertEquals(expected, actual);
		EasyMock.verify(currentPlayer, otherPlayer, view);
	}

	@Test
	public void handleCardAction_NoPlayersWithCards_ReturnsContinue() {
		Player currentPlayer = EasyMock.createMock(Player.class);
		Player otherPlayer = EasyMock.createMock(Player.class);
		FavorCardView view = EasyMock.createMock(FavorCardView.class);
		
		List<Player> activePlayersExcludingCurrent = new ArrayList<>();
		activePlayersExcludingCurrent.add(otherPlayer);

		view.actionMessage();
		view.printNoCardsToGive();
		EasyMock.expect(otherPlayer.getHandSize()).andReturn(0);
		
		EasyMock.replay(currentPlayer, otherPlayer, view);

		FavorCardController controller = new FavorCardController(view);
		controller.updatePlayer(currentPlayer);
		controller.updateActivePlayersExcludingCurrent(activePlayersExcludingCurrent);
		
		TurnResult actual = controller.handleCardAction();
		TurnResult expected = TurnResult.CONTINUE;
		
		assertEquals(expected, actual);
		EasyMock.verify(currentPlayer, otherPlayer, view);
	}

	@Test
	public void handleCardAction_TargetPlayerReturnsNullCard_ReturnsContinue() {
		Player currentPlayer = EasyMock.createMock(Player.class);
		Player otherPlayer = EasyMock.createMock(Player.class);
		FavorCardView view = EasyMock.createMock(FavorCardView.class);
		
		List<Player> activePlayersExcludingCurrent = new ArrayList<>();
		activePlayersExcludingCurrent.add(otherPlayer);
		
		List<Player> playersWithCards = new ArrayList<>();
		playersWithCards.add(otherPlayer);

		view.actionMessage();
		view.printNoCardGivenMessage();
		EasyMock.expect(otherPlayer.getHandSize()).andReturn(3);
		EasyMock.expect(view.promptForTargetPlayer(playersWithCards)).andReturn(otherPlayer);
		EasyMock.expect(view.promptTargetPlayerForCard(otherPlayer)).andReturn(null);
		
		EasyMock.replay(currentPlayer, otherPlayer, view);

		FavorCardController controller = new FavorCardController(view);
		controller.updatePlayer(currentPlayer);
		controller.updateActivePlayersExcludingCurrent(activePlayersExcludingCurrent);
		
		TurnResult actual = controller.handleCardAction();
		TurnResult expected = TurnResult.CONTINUE;
		
		assertEquals(expected, actual);
		EasyMock.verify(currentPlayer, otherPlayer, view);
	}

	@Test
	public void getInfo_FavorCardController() {
		FavorCardView view = EasyMock.createMock(FavorCardView.class);
		view.getInfo();
		EasyMock.replay(view);

		FavorCardController controller = new FavorCardController(view);
		controller.getInfo();

		EasyMock.verify(view);
	}

	@Test
	public void integrationTest_FavorCardFlow_TransfersCardBetweenPlayers() {
		Player currentPlayer = new Player(1);
		Player otherPlayer = new Player(2);

		otherPlayer.addCard(CardType.SKIP);
		otherPlayer.addCard(CardType.ATTACK);

		List<Player> activePlayersExcludingCurrent = new ArrayList<>();
		activePlayersExcludingCurrent.add(otherPlayer);

		FavorCardView view = EasyMock.createMock(FavorCardView.class);
		view.actionMessage();
		EasyMock.expect(view.promptForTargetPlayer(EasyMock.anyObject())).andReturn(otherPlayer);
		EasyMock.expect(view.promptTargetPlayerForCard(otherPlayer)).andReturn(CardType.SKIP);

		EasyMock.replay(view);

		FavorCardController controller = new FavorCardController(view);
		controller.updatePlayer(currentPlayer);
		controller.updateActivePlayersExcludingCurrent(activePlayersExcludingCurrent);

		Map<CardType, Integer> expectedCurrentPlayerHandAfterTransfer = new HashMap<>();
		expectedCurrentPlayerHandAfterTransfer.put(CardType.SKIP, 1);

		Map<CardType, Integer> expectedOtherPlayerHandAfterTransfer = new HashMap<>();
		expectedOtherPlayerHandAfterTransfer.put(CardType.ATTACK, 1);

		TurnResult actualTurnResult = controller.handleCardAction();
		TurnResult expectedTurnResult = TurnResult.CONTINUE;

		assertEquals(actualTurnResult, expectedTurnResult);

		Map<CardType, Integer> actualCurrentPlayerHandAfterTransfer = currentPlayer.viewHand();
		Map<CardType, Integer> actualOtherPlayerHandAfterTransfer = otherPlayer.viewHand();

		assertEquals(actualCurrentPlayerHandAfterTransfer, expectedCurrentPlayerHandAfterTransfer);
		assertEquals(actualOtherPlayerHandAfterTransfer, expectedOtherPlayerHandAfterTransfer);

		EasyMock.verify(view);
	}
} 
