package domain;

public interface TurnSubject {
	public void notifyTurnObservers();

	public void registerObserver(TurnObserver observer);

	public void unregisterObserver(TurnObserver observer);
} 
