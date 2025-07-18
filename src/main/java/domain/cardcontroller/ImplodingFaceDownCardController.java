package domain.cardcontroller;

import domain.CardType;
import domain.Deck;
import domain.TurnResult;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.ImplodingFaceDownCardView;

public class ImplodingFaceDownCardController implements CardController, DrawCardController {
	private final ImplodingFaceDownCardView view;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck must be shared")
	private final Deck deck;

	public ImplodingFaceDownCardController(ImplodingFaceDownCardView view, Deck deck) {
		this.view = view;
		this.deck = deck;
	}

	public TurnResult handleCardDraw() {
		view.actionMessage();
		int index = view.getIndex(this.deck.getSize());
		deck.insertCardAtIndex(CardType.IMPLODING_FACEUP, index);
		return TurnResult.CONTINUE;
	}

	public void getInfo() {
		this.view.getInfo();
	}
}
