package domain;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.easymock.EasyMock;
import ui.GameSetup;
import ui.GameView;
import ui.LocaleContext;

import java.util.Locale;

public class GameSetupSteps {

	private GameSetup gameSetup;
	private GameView view;


	@Given("a new game is started with {int} players")
	public void a_new_game_is_started_with(Integer numberOfPlayers) {
		LocaleContext.setLocale(Locale.ENGLISH);

		view = EasyMock.createMock(GameView.class);
		view.chooseLanguage();
		EasyMock.expect(view.chooseNumPlayers()).andReturn(numberOfPlayers);

		EasyMock.replay(view);

		gameSetup = new GameSetup(view);
		EasyMock.verify(view);
	}

	@Then("each player starts with the correct hand")
	public void each_player_starts_with_the_correct_hand() {

	}
}
