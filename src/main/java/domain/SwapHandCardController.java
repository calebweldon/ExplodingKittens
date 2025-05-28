package domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.SwapHandCardView;

import java.util.List;

@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
		justification = "currentPlayer and activePlayers must be shared")
public class SwapHandCardController implements CardController, ActionCardController,
		TurnObserver, ActivePlayersObserver {
	private final SwapHandCardView view;
	private Player currentPlayer;
	private List<Player> activePlayers;

	public SwapHandCardController(SwapHandCardView view) {
		this.view = view;
	}

	public TurnResult handleCardAction() {
		Player playerToSwapWith = view.promptForPlayerToSwapWith(activePlayers);

		currentPlayer.swapHandWith(playerToSwapWith);

		return TurnResult.CONTINUE;
	}

	public void updatePlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void updateActivePlayers(List<Player> activePlayers) {
		this.activePlayers = activePlayers;
	}
}
