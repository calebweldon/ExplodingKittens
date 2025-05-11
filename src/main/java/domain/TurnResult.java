package domain;

public class TurnResult {
	public int extraTurnsForNextPlayer; // if attack card played
	public boolean playerEliminated;
	public boolean playerWon; // if all Explodia Card's are drawn

	TurnResult(int extraTurnsForNextPlayer, boolean playerEliminated, boolean playerWon) {
		this.extraTurnsForNextPlayer = extraTurnsForNextPlayer;
		this.playerEliminated = playerEliminated;
		this.playerWon = playerWon;
	}
}