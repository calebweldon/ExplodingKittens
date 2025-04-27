package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;


public class CardTest {
	@ParameterizedTest
	@EnumSource(CardType.class)
	public void getCardType_AllCardTypes_returnCardType(CardType cardType) {
		Card card = new Card(cardType);

		CardType expected = cardType;
		CardType actual = card.getCardType();
		assertEquals(expected, actual);
	}
}
