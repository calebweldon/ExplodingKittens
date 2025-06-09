package ui;

import domain.*;
import domain.cardcontroller.CardController;

import java.util.*;

public class GameController implements ActivePlayersSubject {
	private final LinkedList<PlayerTurn> playerTurns;
	private final TurnController turnController;
	private final GameView gameView;
	private final List<Player> activePlayersExcludingCurrent;
	private final List<ActivePlayersExcludingCurrentObserver> observers;

	GameController(List<Player> players, TurnController turnController, GameView gameView,
				Map<CardType, CardController> cardControllers) {
		this.playerTurns = new LinkedList<>();
		for (Player p : players) {
			playerTurns.add(new PlayerTurn(p, 1));
		}
		Collections.shuffle(playerTurns);
		this.turnController = turnController;
		this.gameView = gameView;
		this.activePlayersExcludingCurrent = players;

		this.observers = new ArrayList<>();
		for (Map.Entry<CardType, CardController> entry : cardControllers.entrySet()) {
			CardController value = entry.getValue();
			if (value instanceof ActivePlayersExcludingCurrentObserver) {
				ActivePlayersExcludingCurrentObserver observer =
						(ActivePlayersExcludingCurrentObserver) value;
				registerObserver(observer);
			}
		}
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

			updateActivePlayersExcludingCurrent();
			notifyObservers();

			while (remainingTurns > 0) {
				TurnResult result = playTurn(currPlayer);
				remainingTurns--;

				if (result == TurnResult.ELIMINATED) {
					if (onlyOnePlayerLeft()) {
						return getNextPlayerTurn().player;
					}
					break;
				}
				if (result == TurnResult.WON) {
					return currPlayer;
				}
				if (result == TurnResult.ATTACK) {
					changeNumTurns(2 + remainingTurns + 1);
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

	private void updateActivePlayersExcludingCurrent() {
		this.activePlayersExcludingCurrent.clear();
		for (PlayerTurn playerTurn : playerTurns) {
			this.activePlayersExcludingCurrent.add(playerTurn.player);
		}
	}

	public void registerObserver(ActivePlayersExcludingCurrentObserver controller) {
		observers.add(controller);
	}

	public void unregisterObserver(ActivePlayersExcludingCurrentObserver controller) {
		observers.remove(controller);
	}

	public void notifyObservers() {
		for (ActivePlayersExcludingCurrentObserver observer : observers) {
			observer.updateActivePlayersExcludingCurrent(activePlayersExcludingCurrent);
		}
	}

	// For Integration Feature Test - Game Setup
	public LinkedList<PlayerTurn> getPlayerTurns() {
		return new LinkedList<>(this.playerTurns);
	}

	// For Integration Feature Test - Game Setup
	public Integer getNumExplodingKittens() {
		return turnController.getNumExplodingKittens();
	}

}
