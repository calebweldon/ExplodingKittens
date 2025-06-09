package ui;

import java.util.ResourceBundle;
import java.util.List;

import domain.CardType;
import domain.Player;

public class BasicCardView implements CardView {
	private final ResourceBundle labels;
	private final CardType basicCardType;

	public BasicCardView(CardType basicCardType) {
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
		this.basicCardType = basicCardType;
	}

	public void getInfo() {
		final String basicCardInfo = labels.getString("basicCardInfo");
		System.out.println(basicCardInfo);
	}

	public void actionMessage() {
		final String basicCardActionMessage = labels.getString("basicCardActionMessage");
		System.out.println(basicCardActionMessage);
	}

	public Player promptForPlayer(List<Player> activePlayersExcludingCurrent) {
		// TODO
		return activePlayersExcludingCurrent.get(0);
	}

	public void displayOtherPlayerNoCards() {
		final String otherPlayerNoCardsMessage = labels.getString
			("otherPlayerNoCardsMessage");
		System.out.println(otherPlayerNoCardsMessage);
	}

	public void displayCardTaken(CardType card) {
		final String cardTakenMessage = labels.getString("cardTakenMessage");
		System.out.printf(cardTakenMessage, card);
	}
}
