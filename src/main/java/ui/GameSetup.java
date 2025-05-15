package ui;

import domain.CardType;
import domain.Deck;
import domain.Player;
import domain.TurnController;

import java.util.ArrayList;

public class GameSetup {
	private GameController gameController;
	private GameView gameView;
	public static final int HAND_SIZE = 7;

	public GameSetup() {
		this.gameView = new GameView();
		this.gameView.chooseLanguage();
		int numPlayers = this.gameView.chooseNumPlayers();
		Deck deck = new Deck();

		ArrayList<Player> players = new ArrayList<>(numPlayers);
		for (int i = 0; i < numPlayers; i++) {
			Player player = new Player();
			for (int j = 0; j < HAND_SIZE; j++) {
				CardType card = deck.drawCard();
				try {
					player.addCard(card);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				player.addCard(CardType.DEFUSE);
			} catch (Exception e) {
					e.printStackTrace();
			}
			players.add(new Player());
		}
		deck.addSpecialCards();

		TurnController turnController = new TurnController(deck);

		this.gameController = new GameController(players, turnController, this.gameView);
	}

	public void setupGame() {
		this.gameController.startGame();
	}
}
