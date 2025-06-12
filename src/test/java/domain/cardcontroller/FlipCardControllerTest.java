package domain.cardcontroller;

import domain.Deck;
import domain.TurnResult;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.AttackCardView;
import ui.FlipCardView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlipCardControllerTest {
	@Test
	public void handleFlipCardAction() {
		FlipCardView view = EasyMock.createMock(FlipCardView.class);
		view.actionMessage();

		Deck deck = EasyMock.createMock(Deck.class);
		deck.flipDeck();

		EasyMock.replay(view, deck);

		FlipCardController flipCardController = new FlipCardController(view, deck);
		TurnResult expected = TurnResult.CONTINUE;
		TurnResult actual = flipCardController.handleCardAction();

		assertEquals(expected, actual);
		EasyMock.verify(view, deck);
	}

	@Test
	public void getInfo_flipCardController() {
		FlipCardView view = EasyMock.createMock(FlipCardView.class);
		view.getInfo();
		EasyMock.replay(view);

		FlipCardController flipCardController = new FlipCardController(view, null);
		flipCardController.getInfo();

		EasyMock.verify(view);
	}
}
