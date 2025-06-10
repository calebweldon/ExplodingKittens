package domain.cardcontroller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.List;

import domain.ActivePlayersExcludingCurrentObserver;
import domain.Player;
import domain.TurnObserver;
import domain.TurnResult;
import domain.CardType;
import ui.BasicCardView;

public class BasicCardController implements CardController, ActionCardController,
		TurnObserver, ActivePlayersExcludingCurrentObserver {
	private final BasicCardView view;
	private final CardType basicCardType;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
			justification = "currentPlayer must be shared")
	private Player currentPlayer;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
			justification = "active players must be shared")
	private List<Player> activePlayersExcludingCurrent;

	public BasicCardController(BasicCardView view, CardType basicCardType) {
		this.view = view;
		this.basicCardType = basicCardType;
	}

	public TurnResult handleCardAction() {
		view.actionMessage();

		Player otherPlayer = view.promptForPlayer
				(activePlayersExcludingCurrent);

		if (otherPlayer.getHandSize() == 0) {
			view.displayOtherPlayerNoCards();
			this.currentPlayer.addCard(basicCardType);
			this.currentPlayer.addCard(basicCardType);
			return TurnResult.CONTINUE;
		}

		CardType otherPlayerCard = otherPlayer.takeRandomCard();
		currentPlayer.addCard(otherPlayerCard);

		view.displayCardTaken(otherPlayerCard);

		return TurnResult.CONTINUE;
	}

	public void getInfo() {
		this.view.getInfo();
	}

	public void updatePlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void updateActivePlayersExcludingCurrent
			(List<Player> activePlayersExcludingCurrent) {
		this.activePlayersExcludingCurrent = activePlayersExcludingCurrent;
	}
}
