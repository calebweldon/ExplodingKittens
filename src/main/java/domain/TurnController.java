package domain;

import java.util.ArrayList;
import java.util.List;

public class TurnController {
	private final List<ObserverCardController> observers = new ArrayList<>();
	private Player currPlayer;

	// TODO: Replace dummy method
	public TurnController(Deck deck) {
	}

	// TODO: Replace dummy method
	public TurnResult takeTurn(Player player) {
		this.currPlayer = player;
		notifyObservers();
		return TurnResult.CONTINUE;
	}

	void registerObserver(ObserverCardController controller) {
		observers.add((ObserverCardController) controller);
	}

	void unregisterObserver(ObserverCardController controller) {
		observers.remove((ObserverCardController) controller);
	}

	private void notifyObservers() {
		for (ObserverCardController observer : observers) {
			observer.updatePlayer(currPlayer);
		}
	}

}
