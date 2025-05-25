package ui;

import domain.CardType;
import domain.Player;
import org.junit.jupiter.api.*;
import org.easymock.EasyMock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TurnViewTest {
	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	void setUpStreams() {
		System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
	}

	@AfterEach
	void restoreStreams() {
		System.setOut(originalOut);
	}

	@Test
	void showNoCardsMessage_printsCorrectMessage() {
		TurnView view = new TurnView();
		view.showNoCardsMessage();
		assertEquals("You have no cards to play.\n", outputStream.toString(StandardCharsets.UTF_8));
	}

	@Test
	void showPlayerHand_printsHand() {
		TurnView view = new TurnView();
		Map<CardType, Integer> hand = new HashMap<>();
		hand.put(CardType.DEFUSE, 2);
		hand.put(CardType.SKIP, 1);
		
		view.showPlayerHand(hand);
		String output = outputStream.toString(StandardCharsets.UTF_8);
		assertTrue(output.contains("Your hand:"));
		assertTrue(output.contains("DEFUSE"));
		assertTrue(output.contains("SKIP"));
	}

	@Test
	void showCardDrawn_printsCard() {
		TurnView view = new TurnView();
		view.showCardDrawn(CardType.DEFUSE);
		assertEquals("You drew: DEFUSE\n", outputStream.toString(StandardCharsets.UTF_8));
	}

	@Test
	void showInvalidCardPlay_printsError() {
		TurnView view = new TurnView();
		view.showInvalidCardPlay("Test error message");
		assertEquals("Invalid card play: Test error message\n", outputStream.toString(StandardCharsets.UTF_8));
	}

	@Test
	void showCardCouldNotBeAdded_printsError() {
		TurnView view = new TurnView();
		view.showCardCouldNotBeAdded("Add error");
		assertEquals("Card could not be added: Add error\n", outputStream.toString(StandardCharsets.UTF_8));
	}

	@Test
	void showUnexpectedAction_printsMessage() {
		TurnView view = new TurnView();
		view.showUnexpectedAction();
		assertEquals("Should not happen.\n", outputStream.toString(StandardCharsets.UTF_8));
	}

	@Test
	void showCardPlayed_skipCard_printsSkipMessage() {
		TurnView view = new TurnView();
		view.showCardPlayed(CardType.SKIP);
		assertEquals("SKIP card played. Turn ends immediately.\n", outputStream.toString(StandardCharsets.UTF_8));
	}

	@Test
	void showCardPlayed_attackCard_printsAttackMessage() {
		TurnView view = new TurnView();
		view.showCardPlayed(CardType.ATTACK);
		assertEquals("ATTACK card played\n", outputStream.toString(StandardCharsets.UTF_8));
	}

	@Test
	void showCardPlayed_regularCard_printsGenericMessage() {
		TurnView view = new TurnView();
		view.showCardPlayed(CardType.DEFUSE);
		assertEquals("Played card: DEFUSE\n", outputStream.toString(StandardCharsets.UTF_8));
	}

	@Test
	void showTurnEndedPrematurely_printsMessage() {
		TurnView view = new TurnView();
		view.showTurnEndedPrematurely();
		assertEquals("Turn ended prematurely.\n", outputStream.toString(StandardCharsets.UTF_8));
	}

	@Test
	void showDefuseUsed_printsMessage() {
		TurnView view = new TurnView();
		view.showDefuseUsed();
		assertEquals("Defuse used. You're safe.\n", outputStream.toString(StandardCharsets.UTF_8));
	}

	@Test
	void showNoDefuseFound_printsMessage() {
		TurnView view = new TurnView();
		view.showNoDefuseFound();
		assertEquals("No defuse found. You're eliminated.\n", outputStream.toString(StandardCharsets.UTF_8));
	}

	@Test
	void constructor_createsInstance() {
		TurnView view = new TurnView();
		assertNotNull(view);
	}

	@Test
	void showTopCards_doesNotThrow() {
		TurnView view = new TurnView();
		CardType[] topCards = {CardType.DEFUSE, CardType.SKIP};
		// This method is currently empty but should not throw
		assertDoesNotThrow(() -> view.showTopCards(topCards));
	}
} 