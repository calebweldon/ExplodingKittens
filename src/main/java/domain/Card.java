package domain;

public class Card {
	private final CardType cardType;
	private boolean isFaceUp;

	public Card(CardType cardType) {
		this.cardType = cardType;
		this.isFaceUp = false;
	}

	public CardType getCardType() {
		return cardType;
	}

	public boolean checkIfFaceUp() {
		return isFaceUp;
	}

	public void setToFaceUp() {
		this.isFaceUp = true;
	}

	public void setToFaceDown() {
		this.isFaceUp = false;
	}
}
