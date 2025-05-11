package domain;

public class TurnController {
	private Deck deck;
	public TurnController(Deck deck){
		this.deck = deck;
	}

	public TurnResult takeTurn(Player player){
		return new TurnResult(0, false, false);
	}

}
