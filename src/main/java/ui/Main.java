package ui;

public class Main {
	public static void main(String[] args) {
		GameView view = new GameView();
		GameSetup gameSetup = new GameSetup(view);
		gameSetup.runGame();
	}
}
