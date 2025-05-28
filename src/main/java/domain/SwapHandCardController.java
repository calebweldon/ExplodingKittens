package domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.GameController;
import ui.SwapHandCardView;

import java.util.List;

@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "currentPlayer must be shared")
public class SwapHandCardController implements CardController, ActionCardController, TurnObserver {
	private final SwapHandCardView view;
	private final GameController gameController;
	private Player currentPlayer;

	public SwapHandCardController(SwapHandCardView view, GameController gameController) {
		this.view = view;
		this.gameController = gameController;
	}

	public TurnResult handleCardAction() {
		List<Player> activePlayers = gameController.getActivePlayers();

		Player playerToSwapWith = view.promptForPlayerToSwapWith(activePlayers);

		currentPlayer.swapHandWith(playerToSwapWith);

		return TurnResult.CONTINUE;
	}

	public void updatePlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
}
