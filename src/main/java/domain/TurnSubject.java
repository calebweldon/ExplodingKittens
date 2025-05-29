package domain;

public interface TurnSubject {
	public void notifyObservers();

	public void registerObserver(TurnObserver observer);

	public void unregisterObserver(TurnObserver observer);
}
