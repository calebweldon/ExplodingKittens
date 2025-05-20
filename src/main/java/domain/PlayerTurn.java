package domain;

public class PlayerTurn {
	public Player player;
	public int remainingTurns;

	public PlayerTurn(Player player, int remainingTurns) {
		this.player = player;
		this.remainingTurns = remainingTurns;
	}
}