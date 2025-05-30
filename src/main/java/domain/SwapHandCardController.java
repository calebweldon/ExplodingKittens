package domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.SwapHandCardView;

import java.util.List;

@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
		justification = "currentPlayer and activePlayers must be shared")
public class SwapHandCardController implements CardController, ActionCardController,
		TurnObserver, ActivePlayersExcludingCurrentObserver {
	private final SwapHandCardView view;
	private Player currentPlayer;
	private List<Player> activePlayersExcludingCurrent;

	public SwapHandCardController(SwapHandCardView view) {
		this.view = view;
	}

	public TurnResult handleCardAction() {
		view.actionMessage();

		Player playerToSwapWith = view.promptForPlayerToSwapWith
				(activePlayersExcludingCurrent);

		currentPlayer.swapHandWith(playerToSwapWith);

		return TurnResult.CONTINUE;
	}

	public void updatePlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void updateActivePlayersExcludingCurrent
			(List<Player> activePlayersExcludingCurrent) {
		this.activePlayersExcludingCurrent = activePlayersExcludingCurrent;
	}
}
