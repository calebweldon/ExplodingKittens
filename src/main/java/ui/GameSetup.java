package ui;

import domain.CardType;
import domain.Deck;
import domain.Player;
import domain.TurnController;

import java.util.ArrayList;

public class GameSetup {
	private final GameController gameController;
	private final GameView gameView;
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
				player.addCard(card);
			}
			player.addCard(CardType.DEFUSE);
			players.add(new Player());
		}
		deck.addSpecialCards(numPlayers - 1);

		TurnController turnController = new TurnController(deck);

		this.gameController = new GameController(players, turnController, this.gameView);
	}

	public void runGame() {
		this.gameController.startGame();
	}
}
