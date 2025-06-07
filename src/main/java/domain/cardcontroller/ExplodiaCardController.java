package domain.cardcontroller;

import domain.CardType;
import domain.Player;
import domain.TurnObserver;
import domain.TurnResult;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.ExplodiaCardView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ExplodiaCardController implements CardController,
		ActionCardController, DrawCardController, TurnObserver {
	private final ExplodiaCardView view;
	private final List<CardController> cardControllers;
	private final SecureRandom rand;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Player must be shared")
	private Player player;
	private static final int TOTAL_EXPLODIA = 5;

	public ExplodiaCardController(ExplodiaCardView view,
				List<CardController> cardControllers) {
		this(view, cardControllers, new SecureRandom());
	}

	ExplodiaCardController(ExplodiaCardView view,
				List<CardController> cardControllers,
				SecureRandom rand) {
		this.view = view;
		this.cardControllers = cardControllers;
		this.rand = rand;
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
		player.addCard(CardType.EXPLODIA);
		Map<CardType, Integer> hand = player.viewHand();
		int numExplodia = hand.get(CardType.EXPLODIA);

		view.drawMessage(numExplodia);
		return numExplodia == TOTAL_EXPLODIA ? TurnResult.WON : TurnResult.CONTINUE;
	}

	@Override
	public TurnResult handleCardAction() {
		view.actionMessage();
		int index = rand.nextInt(this.cardControllers.size());
		ActionCardController cardController =
				(ActionCardController) cardControllers.get(index);
		return cardController.handleCardAction();
	}

	@Override
	public void getInfo() {
		this.view.getInfo();
	}
}
