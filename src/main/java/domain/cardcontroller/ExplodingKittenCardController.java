package domain.cardcontroller;

import domain.CardType;
import domain.Deck;
import domain.Player;
import domain.TurnObserver;
import domain.TurnResult;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.ExplodingKittenView;

import java.util.Map;

public class ExplodingKittenCardController implements CardController, 
DrawCardController, 
TurnObserver {
	private final ExplodingKittenView view;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck must be shared")
	private final Deck deck;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Safe to share")
	private Player currentPlayer;

	public ExplodingKittenCardController(ExplodingKittenView view, Deck deck) {
		this.view = view;
		this.deck = deck;
	}

	@Override
	public TurnResult handleCardDraw() {
		if (currentPlayer == null) {
			throw new IllegalStateException("Player not set");
		}

		view.actionMessage();
		try{
			currentPlayer.removeCard(CardType.DEFUSE);
			view.showDefuseUsed();

			int insertIndex = view.promptExplodingKittenIndex(deck.getSize());
			deck.insertCardAtIndex(CardType.EXPLODING_KITTEN, insertIndex);

			return TurnResult.CONTINUE;
		} catch (IllegalArgumentException e) {
			view.showNoDefuseFound();
			return TurnResult.ELIMINATED;
		}
	}

	@Override
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Safe to share")
	public void updatePlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
} 