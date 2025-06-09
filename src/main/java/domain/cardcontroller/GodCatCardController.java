package domain.cardcontroller;

import domain.CardType;
import domain.TurnResult;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.GodCatCardView;

import java.util.Map;

public class GodCatCardController implements CardController, ActionCardController {
	private final GodCatCardView view;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
			justification = "Card Controllers must be shared")
	private final Map<CardType, CardController> cardControllers;

	public GodCatCardController (GodCatCardView view, Map<CardType,
			CardController> cardControllers) {
		this.view = view;
		this.cardControllers = cardControllers;
	}

	@Override
	public TurnResult handleCardAction() {
		view.actionMessage();
		CardType card = view.chooseController();
		ActionCardController controller = (ActionCardController) cardControllers.get(card);
		return controller.handleCardAction();
	}

	@Override
	public void getInfo() {
		this.view.getInfo();
	}
}
