package domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.ArrayList;
import java.util.List;

@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Player must be shared")
public class TurnController implements SubjectDomain {
	private final List<TurnObserver> observers = new ArrayList<>();
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

	public void registerObserver(TurnObserver controller) {
		observers.add(controller);
	}

	public void unregisterObserver(TurnObserver controller) {
		observers.remove(controller);
	}

	public void notifyObservers() {
		for (TurnObserver observer : observers) {
			observer.updatePlayer(currPlayer);
		}
	}

}
