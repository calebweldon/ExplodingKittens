package domain;

import java.util.List;

public interface ActivePlayersExcludingCurrentObserver {
	public void updateActivePlayersExcludingCurrent(List<Player> activePlayers);
}
