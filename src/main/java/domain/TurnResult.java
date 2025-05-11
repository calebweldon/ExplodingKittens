package domain;

public class TurnResult {
	public int extraTurnsForNextPlayer;
	public boolean playerEliminated;
	public boolean playerWon;

	TurnResult(int extraTurnsForNextPlayer, boolean playerEliminated, boolean playerWon) {
		this.extraTurnsForNextPlayer = extraTurnsForNextPlayer;
		this.playerEliminated = playerEliminated;
		this.playerWon = playerWon;
	}
}