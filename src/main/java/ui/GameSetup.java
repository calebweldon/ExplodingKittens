package ui;

import domain.*;
import domain.cardcontroller.*;

import java.util.*;

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

		Map<CardType, CardController> cardControllers = new HashMap<>();

		CardController alterController = new AlterFutureCardController(
				new AlterFutureCardView(), deck);
		CardController attackController = new AttackCardController(
				new AttackCardView());
		CardController beardCatController = new BasicCardController(
				new BasicCardView(CardType.BEARD_CAT),
				CardType.BEARD_CAT);
		CardController cattermelonController = new BasicCardController(
				new BasicCardView(CardType.CATTERMELON),
				CardType.CATTERMELON);
		// TODO: Update Embarrass
		CardController embarrassController = new EmbarrassCardController();
		// TODO: Update Exploding Kitten
		CardController explodingkittenController = new ExplodingKittenCardController();
		CardController favorController = new FavorCardController(
				new FavorCardView());
		CardController flipController = new FlipCardController(
				new FlipCardView(), deck);
		CardController implodingDownController = new ImplodingFaceDownCardController(
				new ImplodingFaceDownCardView(), deck);
		CardController implodingUpController = new ImplodingFaceUpCardController(
				new ImplodingFaceUpCardView());
		CardController potatoCatController = new BasicCardController(
				new BasicCardView(CardType.POTATO_CAT),
				CardType.POTATO_CAT);
		CardController rainbowRalphingCatController = new BasicCardController(
				new BasicCardView(CardType.RAINBOW_RALPHING_CAT),
				CardType.RAINBOW_RALPHING_CAT);
		CardController recycleController = new RecycleCardController(
				new RecycleCardView());
		CardController seeController = new SeeFutureCardController(
				new SeeFutureCardView(), deck);
		CardController shuffleController = new ShuffleCardController(
				new ShuffleCardView(), deck);
		CardController skipController = new SkipCardController(
				new SkipCardView());
		CardController swapController = new SwapHandCardController(
				new SwapHandCardView());
		CardController tacoCatController = new BasicCardController(
				new BasicCardView(CardType.TACO_CAT),
				CardType.TACO_CAT);

		Map<CardType, CardController> godCatMap = new HashMap<>();
		godCatMap.put(CardType.ATTACK, attackController);
		godCatMap.put(CardType.SKIP, skipController);
		godCatMap.put(CardType.FAVOR, favorController);
		godCatMap.put(CardType.TACO_CAT, tacoCatController);
		godCatMap.put(CardType.CATTERMELON, cattermelonController);
		godCatMap.put(CardType.POTATO_CAT, potatoCatController);
		godCatMap.put(CardType.BEARD_CAT, beardCatController);
		godCatMap.put(CardType.RAINBOW_RALPHING_CAT, rainbowRalphingCatController);
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
		cardControllers.put(CardType.BEARD_CAT, beardCatController);
		cardControllers.put(CardType.CATTERMELON, cattermelonController);
		cardControllers.put(CardType.POTATO_CAT, potatoCatController);
		cardControllers.put(CardType.RAINBOW_RALPHING_CAT, rainbowRalphingCatController);
		cardControllers.put(CardType.TACO_CAT, tacoCatController);
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


		TurnView turnView = new TurnView(cardControllers);
		TurnController turnController = new TurnController(deck, turnView, cardControllers);

		this.gameController = new GameController(
				players, turnController, this.gameView, cardControllers);
	}

	public void runGame() {
		this.gameController.startGame();
	}

	// For Integration Feature Test - Game Setup
	public LinkedList<PlayerTurn> getPlayerTurns() {
		return this.gameController.getPlayerTurns();
	}

	// For Integration Feature Test - Game Setup
	public int getCardCount(CardType cardType) {
		return this.gameController.getCardCount(cardType);
	}
}
