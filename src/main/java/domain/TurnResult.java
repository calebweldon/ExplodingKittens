package domain;

public class TurnResult {
	public int extraTurns;
	public boolean playerEliminated;
	public boolean playerWon;

	TurnResult(int extraTurnsForNextPlayer, boolean playerEliminated, boolean playerWon) {
		this.extraTurns = extraTurnsForNextPlayer;
		this.playerEliminated = playerEliminated;
		this.playerWon = playerWon;
	}
}