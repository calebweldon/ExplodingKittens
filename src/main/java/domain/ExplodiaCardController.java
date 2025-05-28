package domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.ExplodiaCardView;

import java.util.Map;

@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Player must be shared")
public class ExplodiaCardController implements CardController,
		ActionCardController, DrawCardController, TurnObserver {
	private ExplodiaCardView explodiaCardView;
	private Player player;
	private static final int TOTAL_EXPLODIA = 5;

	public ExplodiaCardController(ExplodiaCardView explodiaCardView) {
		this.explodiaCardView = explodiaCardView;
	}

	@Override
	public void updatePlayer(Player currentPlayer) {
		this.player = currentPlayer;
	}

	@Override
	public TurnResult handleCardDraw() {
		if (player == null) {
			throw new IllegalStateException("Player not set");
		}
		Map<CardType, Integer> hand = player.viewHand();
		int numExplodia = hand.get(CardType.EXPLODIA);

		explodiaCardView.drawMessage(numExplodia);
		return numExplodia == TOTAL_EXPLODIA ? TurnResult.WON : TurnResult.CONTINUE;
	}

	@Override
	public TurnResult handleCardAction() {
		// TODO
		return TurnResult.CONTINUE;
	}

}
