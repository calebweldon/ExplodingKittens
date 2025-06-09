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

	public GameSetup(GameView view) {
		this.gameView = view;
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
		Map<CardType, CardController> cardControllers = new HashMap<>();

		CardController alterController = new AlterFutureCardController(
				new AlterFutureCardView(), deck);
		CardController attackController = new AttackCardController(
				new AttackCardView());
		// TODO: Update Basic
		CardController basicController = new BasicCardController();
		// TODO: Update Embarrass
		CardController embarrassController = new EmbarrassCardController();
		// TODO: Update Exploding Kitten
		CardController explodingkittenController = new ExplodingKittenCardController();
		// TODO: Update Favor
		CardController favorController = new FavorCardController();
		CardController flipController = new FlipCardController(
				new FlipCardView(), deck);
		CardController implodingDownController = new ImplodingFaceDownCardController(
				new ImplodingFaceDownCardView(), deck);
		CardController implodingUpController = new ImplodingFaceUpCardController(
				new ImplodingFaceUpCardView());
		// TODO: Update Recycle
		CardController recycleController = new RecycleCardController();
		CardController seeController = new SeeFutureCardController(
				new SeeFutureCardView(), deck);
		CardController shuffleController = new ShuffleCardController(
				new ShuffleCardView(), deck);
		CardController skipController = new SkipCardController(
				new SkipCardView());
		CardController swapController = new SwapHandCardController(
				new SwapHandCardView());

		Map<CardType, CardController> godCatMap = new HashMap<>();
		godCatMap.put(CardType.ATTACK, attackController);
		godCatMap.put(CardType.SKIP, skipController);
		godCatMap.put(CardType.FAVOR, favorController);
		godCatMap.put(CardType.TACO_CAT, basicController);
		godCatMap.put(CardType.FLIP, flipController);
		godCatMap.put(CardType.SHUFFLE, shuffleController);
		godCatMap.put(CardType.SWAP, swapController);
		godCatMap.put(CardType.EMBARRASS, embarrassController);
		godCatMap.put(CardType.RECYCLE, recycleController);
		godCatMap.put(CardType.ALTER_THE_FUTURE, alterController);
		godCatMap.put(CardType.SEE_THE_FUTURE, seeController);

		CardController godCatController = new GodCatCardController(
				new GodCatCardView(), godCatMap);
		CardController explodiaController = new ExplodiaCardController(
				new ExplodiaCardView(), new ArrayList<>(godCatMap.values()));


		cardControllers.put(CardType.ALTER_THE_FUTURE, alterController);
		cardControllers.put(CardType.ATTACK, attackController);
		cardControllers.put(CardType.BEARD_CAT, basicController);
		cardControllers.put(CardType.CATTERMELON, basicController);
		cardControllers.put(CardType.POTATO_CAT, basicController);
		cardControllers.put(CardType.RAINBOW_RALPHING_CAT, basicController);
		cardControllers.put(CardType.TACO_CAT, basicController);
		cardControllers.put(CardType.EMBARRASS, embarrassController);
		cardControllers.put(CardType.EXPLODING_KITTEN, explodingkittenController);
		cardControllers.put(CardType.EXPLODIA, explodiaController);
		cardControllers.put(CardType.FAVOR, favorController);
		cardControllers.put(CardType.FLIP, flipController);
		cardControllers.put(CardType.GOD_CAT, godCatController);
		cardControllers.put(CardType.IMPLODING_FACEDOWN, implodingDownController);
		cardControllers.put(CardType.IMPLODING_FACEUP, implodingUpController);
		cardControllers.put(CardType.RECYCLE, recycleController);
		cardControllers.put(CardType.SEE_THE_FUTURE, seeController);
		cardControllers.put(CardType.SHUFFLE, shuffleController);
		cardControllers.put(CardType.SKIP, skipController);
		cardControllers.put(CardType.SWAP, swapController);


		TurnController turnController = new TurnController(deck, turnView, cardControllers);

		this.gameController = new GameController(
				players, turnController, this.gameView, cardControllers);
	}

	public void runGame() {
		this.gameController.startGame();
	}
}
