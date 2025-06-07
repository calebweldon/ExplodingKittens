package ui;

import domain.*;
import domain.cardcontroller.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		// TODO: Finish adding CardControllers
		Map<CardType, CardController> cardControllers = new HashMap<>();
		cardControllers.put(CardType.IMPLODING_FACEDOWN,
				new ImplodingFaceDownCardController(
						new ImplodingFaceDownCardView(), deck));
		cardControllers.put(CardType.IMPLODING_FACEUP,
				new ImplodingFaceUpCardController(new ImplodingFaceUpCardView()));

		TurnController turnController = new TurnController(deck, turnView, cardControllers);

		this.gameController = new GameController(players, turnController, this.gameView);
	}

	public void runGame() {
		this.gameController.startGame();
	}
}
