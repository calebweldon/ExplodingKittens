package ui;

import domain.CardType;
import domain.Deck;
import domain.Player;
import domain.TurnController;

import java.util.ArrayList;
import java.util.List;

public class GameSetup {
	private final GameController gameController;
	private final GameView gameView;
	public static final int HAND_SIZE = 8;

	public GameSetup() {
		this.gameView = new GameView();
		this.gameView.chooseLanguage();
		int numPlayers = this.gameView.chooseNumPlayers();
		Deck deck = new Deck();

		List<Player> players = new ArrayList<>(numPlayers);
		for (int i = 0; i < numPlayers; i++) {
			int id = i + 1;
			Player player = new Player(id);
			for (int j = 0; j < HAND_SIZE - 1; j++) {
				CardType card = deck.drawCard();
				player.addCard(card);
			}
			player.addCard(CardType.DEFUSE);
			players.add(player);
		}
		deck.addSpecialCards(numPlayers - 1);

		TurnView turnView = new TurnView();
		TurnController turnController = new TurnController(deck, turnView);

		this.gameController = new GameController(players, turnController, this.gameView);
	}

	public void runGame() {
		this.gameController.startGame();
	}
}
