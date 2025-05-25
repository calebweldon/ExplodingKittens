package ui;

import domain.Player;

public class GameView {
	private Language language;

	public GameView() {
		language = Language.ENGLISH;
	}

	public void chooseLanguage() { }

	public Language getLanguage() {
		return this.language;
	}

	public int chooseNumPlayers() {
		return 0;
	}

	public void announceGameStart() {
		System.out.println("Game starting!");
	}

	public void announceGameEnd(Player winner) {
		System.out.printf("Game over! Winner: %s%n", winner);
	}
} 