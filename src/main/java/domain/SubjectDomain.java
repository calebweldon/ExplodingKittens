package domain;

public interface SubjectDomain {
	public void notifyObservers();

	public void registerObserver(TurnObserver observer);

	public void unregisterObserver(TurnObserver observer);
} 