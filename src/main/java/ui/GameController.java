package ui;

import domain.*;

import java.util.*;

public class GameController {
	private LinkedList<PlayerTurn> playerTurns;
	private TurnController turnController;
	private GameView gameView;

	GameController(List<Player> players, TurnController turnController, GameView gameView) {
		this.playerTurns = new LinkedList<>();
		for (Player p : players) {
			playerTurns.add(new PlayerTurn(p, 1));
		}
		Collections.shuffle(playerTurns);
		this.turnController = turnController;
		this.gameView = gameView;
	}

	public void startGame() {
		this.gameView.announceGameStart();
		Player winner = runGameLoop();
		endGame(winner);
	}

	private Player runGameLoop() {
		while (true) {
			PlayerTurn playerTurn = getNextPlayerTurn();
			int remainingTurns = playerTurn.remainingTurns;
			Player currPlayer = playerTurn.player;

			while (remainingTurns > 0) {
				TurnResult result = playTurn(currPlayer);
				remainingTurns--;

				if (result.playerEliminated) {
					if (onlyOnePlayerLeft()) {
						return getNextPlayerTurn().player;
					}
					break;
				}
				if (result.playerWon) {
					return currPlayer;
				}

				if (result.extraTurns > 0) {
					changeNumTurns(result.extraTurns + remainingTurns + 1);
					remainingTurns = 0;
				}
				if (remainingTurns == 0) {
					reinsertPlayer(currPlayer);
				}
			}
		}
	}

	private TurnResult playTurn(Player player) {
		return this.turnController.takeTurn(player);
	}

	private void changeNumTurns(int extraTurns) {
		if (!playerTurns.isEmpty()) {
			PlayerTurn next = playerTurns.poll();
			next.remainingTurns += extraTurns;
			playerTurns.addFirst(next);
		}
	}

	private PlayerTurn getNextPlayerTurn() {
		return playerTurns.poll();
	}

	private void reinsertPlayer(Player player) {
		playerTurns.offer(new PlayerTurn(player, 1));
	}

	private boolean onlyOnePlayerLeft() {
		return 1 == playerTurns.size();
	}

	public void endGame(Player winner) {
		this.gameView.announceGameEnd(winner);
	}
}
