package domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.RecycleCardView;

public class RecycleCardController implements CardController, ActionCardController,
		TurnObserver, LastPlayedObserver {
	private final RecycleCardView view;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "player must be shared")
	private Player currentPlayer;
	private CardType lastCard;

	public RecycleCardController(RecycleCardView view) {
		this.view = view;
	}

	public TurnResult handleCardAction() {
		currentPlayer.addCard(lastCard);
		view.actionMessage();
		return TurnResult.CONTINUE;
	}

	public void updatePlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void updateLastPlayed(CardType lastPlayed) {
		this.lastCard = lastPlayed;
	}
}
