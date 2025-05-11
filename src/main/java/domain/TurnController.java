package domain;

public class TurnController {

	public TurnController(Deck deck) {
	}

	public TurnResult takeTurn(Player player) {
		return new TurnResult(0, false, false);
	}

}
