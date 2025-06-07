package domain.cardcontroller;

import domain.ActivePlayersExcludingCurrentObserver;
import domain.Player;
import domain.TurnObserver;
import domain.TurnResult;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.SwapHandCardView;

import java.util.List;

public class SwapHandCardController implements CardController, ActionCardController,
		TurnObserver, ActivePlayersExcludingCurrentObserver {
	private final SwapHandCardView view;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
			justification = "currentPlayer must be shared")
	private Player currentPlayer;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
			justification = "active players must be shared")
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

	public void getInfo() {
		this.view.getInfo();
	}
}
