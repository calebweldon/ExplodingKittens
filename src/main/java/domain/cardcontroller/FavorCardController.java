package domain.cardcontroller;

import domain.ActivePlayersExcludingCurrentObserver;
import domain.CardType;
import domain.Player;
import domain.TurnObserver;
import domain.TurnResult;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.FavorCardView;

import java.util.List;
import java.util.stream.Collectors;

public class FavorCardController implements CardController, ActionCardController,
		TurnObserver, ActivePlayersExcludingCurrentObserver {
	private final FavorCardView view;
	@SuppressFBWarnings(
		value = "EI_EXPOSE_REP2",
		justification = "Trusted internal game context; Player reference is safe to share"
	)
	private Player currentPlayer;

	@SuppressFBWarnings(
		value = "EI_EXPOSE_REP2",
		justification = "Trusted internal game context."
	)
	private List<Player> activePlayersExcludingCurrent;

	public FavorCardController(FavorCardView view) {
		this.view = view;
	}

	@Override
	public TurnResult handleCardAction() {
		view.actionMessage();

		List<Player> playersWithCards = activePlayersExcludingCurrent.stream()
				.filter(player -> player.getHandSize() > 0)
				.collect(Collectors.toList());

		if (playersWithCards.isEmpty()) {
			view.printNoCardsToGive();
			return TurnResult.CONTINUE;
		}

		Player targetPlayer = view.promptForTargetPlayer(playersWithCards);

		CardType cardToGive = view.promptTargetPlayerForCard(targetPlayer);

		if (cardToGive == null) {
			view.printNoCardGivenMessage();
			return TurnResult.CONTINUE;
		}

		targetPlayer.removeCard(cardToGive);
		currentPlayer.addCard(cardToGive);

		return TurnResult.CONTINUE;
	}

	@Override
	@SuppressFBWarnings(
		value = "EI_EXPOSE_REP2",
		justification = "Trusted internal game context; Player reference is safe to share"
	)
	public void updatePlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	@Override
	@SuppressFBWarnings(
		value = "EI_EXPOSE_REP2",
		justification = "Trusted internal game context."
	)
	public void updateActivePlayersExcludingCurrent(
			List<Player> activePlayersExcludingCurrent) {
		this.activePlayersExcludingCurrent = activePlayersExcludingCurrent;
	}
}
