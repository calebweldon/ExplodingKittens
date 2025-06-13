package domain.cardcontroller;

import domain.CardType;
import domain.Deck;
import domain.TurnResult;
import ui.AlterFutureCardView;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class AlterFutureCardController implements CardController, ActionCardController {
	private static final int NUM_CARDS_FROM_TOP = 3;

	private final AlterFutureCardView view;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck must be shared")
	private final Deck deck;

	public AlterFutureCardController(AlterFutureCardView view, Deck deck) {
		this.view = view;
		this.deck = deck;
	}

	public TurnResult handleCardAction() {
		int numCardsViewed = getNumCardsViewed();
		CardType[] topCards = new CardType[numCardsViewed];
		
		for (int i = 0; i < numCardsViewed; i++) {
			topCards[i] = deck.drawCard();
		}

		view.actionMessage();
		view.displayTopCards(topCards);
		CardType[] reorderedCards = view.promptForNewOrder(topCards);

		for (int i = 0; i < numCardsViewed; i++) {
			deck.insertCardAtIndex(reorderedCards[i], i);
		}

		return TurnResult.CONTINUE;
	}

	private int getNumCardsViewed() {
		int deckSize = deck.getSize();
		return Math.min(deckSize, NUM_CARDS_FROM_TOP);
	}

	public void getInfo() {
		this.view.getInfo();
	}
}
