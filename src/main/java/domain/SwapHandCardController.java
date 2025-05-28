package domain;

import ui.GameController;
import ui.SwapHandCardView;

import java.util.List;

public class SwapHandCardController implements CardController, ActionCardController {
	private final SwapHandCardView view;
	private final GameController gameController;

	public SwapHandCardController(SwapHandCardView view, GameController gameController) {
		this.view = view;
		this.gameController = gameController;
	}

	public TurnResult handleCardAction() {
		List<Player> activePlayers = gameController.getActivePlayers();

		Player playerToSwapWith = view.promptForPlayerToSwapWith(activePlayers);

		// somehow get current player
		// currPlayer.swapHandWith(playerToSwapWith)

		return TurnResult.CONTINUE;
	}
}
