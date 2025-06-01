package domain;

public interface LastPlayedObserver extends TurnObserver {
	public void updateLastPlayed(CardType lastPlayed);
}
