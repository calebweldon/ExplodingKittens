package domain;

public interface ActivePlayersSubject {
	public void notifyObservers();

	public void registerObserver(ActivePlayersObserver observer);

	public void unregisterObserver(ActivePlayersObserver observer);
}
