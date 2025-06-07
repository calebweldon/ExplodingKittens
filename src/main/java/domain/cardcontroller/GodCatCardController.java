package domain.cardcontroller;

import domain.CardType;
import domain.TurnResult;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.GodCatCardView;

import java.util.Map;

public class GodCatCardController implements CardController, ActionCardController {
	private final GodCatCardView cv;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
			justification = "Card Controllers must be shared")
	private final Map<CardType, CardController> cardControllers;

	public GodCatCardController (GodCatCardView cv, Map<CardType,
			CardController> cardControllers) {
		this.cv = cv;
		this.cardControllers = cardControllers;
	}

	@Override
	public TurnResult handleCardAction() {
		cv.actionMessage();
		CardType card = cv.chooseController();
		ActionCardController controller = (ActionCardController) cardControllers.get(card);
		return controller.handleCardAction();
	}
}
