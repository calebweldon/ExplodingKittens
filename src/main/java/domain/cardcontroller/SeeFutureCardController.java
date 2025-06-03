package domain.cardcontroller;

import domain.CardType;
import domain.Deck;
import domain.TurnResult;
import ui.SeeFutureCardView;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class SeeFutureCardController implements CardController, ActionCardController {
	private static final int NUM_CARDS_FROM_TOP = 3;

	private final SeeFutureCardView view;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck must be shared")
	private final Deck deck;

	public SeeFutureCardController(SeeFutureCardView view, Deck deck) {
		this.view = view;
		this.deck = deck;
	}

	public TurnResult handleCardAction() {
		int numCardsViewed = getNumCardsViewed();
		CardType[] topCards = new CardType[numCardsViewed];
		
		for (int i = 0; i < numCardsViewed; i++) {
			topCards[i] = deck.getCardAtIndex(i);
		}

		view.actionMessage();
		view.displayTopCards(topCards);
		return TurnResult.CONTINUE;
	}

	private int getNumCardsViewed() {
		int deckSize = deck.getSize();
		return (deckSize < NUM_CARDS_FROM_TOP) ? deckSize : NUM_CARDS_FROM_TOP;
	}
}
