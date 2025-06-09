package domain;

public interface LastPlayedSubject {
	public void notifyLastPlayedObservers(CardType lastPlayed);

	public void registerObserver(LastPlayedObserver observer);

	public void unregisterObserver(LastPlayedObserver observer);
} 
