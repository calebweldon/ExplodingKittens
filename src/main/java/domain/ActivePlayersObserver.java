package domain;

import java.util.List;

public interface ActivePlayersObserver {
	public void updateActivePlayers(List<Player> activePlayers);
}
