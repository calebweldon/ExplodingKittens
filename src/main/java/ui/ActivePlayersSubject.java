package ui;

import domain.ActivePlayersExcludingCurrentObserver;

public interface ActivePlayersSubject {
	public void notifyObservers();

	public void registerObserver(ActivePlayersExcludingCurrentObserver observer);

	public void unregisterObserver(ActivePlayersExcludingCurrentObserver observer);
}
