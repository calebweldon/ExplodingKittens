package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.*;


public class DeckTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 2147483647})
    public void insertCard_invalidIndex_throwException(int invalidIndex) {
        Card card = EasyMock.createMock(Card.class);
        Deck deck = new Deck();

        String expectedMessage = "Invalid index: index out of range";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            deck.insertCardAtIndex(card, invalidIndex);
        });

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
