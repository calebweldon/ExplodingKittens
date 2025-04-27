package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class CardTest {
	@ParameterizedTest
	@EnumSource(CardType.class)
	public void getCardType_allCardTypes_returnsCardType(CardType cardType) {
		Card card = new Card(cardType);

		CardType expected = cardType;
		CardType actual = card.getCardType();
		assertEquals(expected, actual);
	}

	@Test
	public void checkIfFaceUp_faceDown_returnsFalse() {
		Card card = new Card(CardType.ATTACK);

		assertFalse(card.checkIfFaceUp());
	}
}
