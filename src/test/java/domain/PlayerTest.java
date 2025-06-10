package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;
import java.security.SecureRandom;

public class PlayerTest {
	
	@Test
	public void addCard_emptyHand_succeeds() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		EasyMock.expect(hand.getOrDefault(CardType.TACO_CAT, 0)).andReturn(0);
		EasyMock.expect(hand.put(CardType.TACO_CAT, 1)).andReturn(null);
		EasyMock.replay(hand);

		Player player = new Player(hand);
		try {
			player.addCard(CardType.TACO_CAT);
		} catch (IllegalArgumentException e) {
			fail("Should not have thrown an exception");
		}

		EasyMock.verify(hand);
	}

	@Test
	public void addCard_manyCards_succeeds() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		EasyMock.expect(hand.getOrDefault(CardType.TACO_CAT, 0)).andReturn(0);
		EasyMock.expect(hand.put(CardType.TACO_CAT, 1)).andReturn(null);
		EasyMock.expect(hand.getOrDefault(CardType.POTATO_CAT, 0)).andReturn(0);
		EasyMock.expect(hand.put(CardType.POTATO_CAT, 1)).andReturn(null);
		EasyMock.expect(hand.getOrDefault(CardType.TACO_CAT, 0)).andReturn(1);
		EasyMock.expect(hand.put(CardType.TACO_CAT, 2)).andReturn(1);
		EasyMock.replay(hand);

		Player player = new Player(hand);
		try {
			player.addCard(CardType.TACO_CAT);
			player.addCard(CardType.POTATO_CAT);
			player.addCard(CardType.TACO_CAT);
		} catch (IllegalArgumentException e) {
			fail("Should not have thrown an exception");
		}

		EasyMock.verify(hand);
	}

	@Test
	public void addCard_explodingKitten_throwsException() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		EasyMock.replay(hand);

		Player player = new Player(hand);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			player.addCard(CardType.EXPLODING_KITTEN);
		});

		String expectedMessage = "You cannot add an Exploding Kitten to your hand.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

		EasyMock.verify(hand);
	}

	@Test
	public void playCard_oneCard_succeeds() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		EasyMock.expect(hand.getOrDefault(CardType.ATTACK, 0)).andReturn(1);
		EasyMock.expect(hand.remove(CardType.ATTACK)).andReturn(1);
		EasyMock.replay(hand);

		Player player = new Player(hand);
		player.playCard(CardType.ATTACK);

		EasyMock.verify(hand);
	}

	@Test
	public void playCard_manyCard_succeeds() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		EasyMock.expect(hand.getOrDefault(CardType.ATTACK, 0)).andReturn(2);
		EasyMock.expect(hand.put(CardType.ATTACK, 1)).andReturn(2);
		EasyMock.expect(hand.getOrDefault(CardType.ATTACK, 0)).andReturn(1);
		EasyMock.expect(hand.remove(CardType.ATTACK)).andReturn(1);
		EasyMock.replay(hand);

		Player player = new Player(hand);
		player.playCard(CardType.ATTACK);
		player.playCard(CardType.ATTACK);

		EasyMock.verify(hand);
	}

	@Test
	public void playCard_multipleBasicKitten_succeeds() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		EasyMock.expect(hand.getOrDefault(CardType.TACO_CAT, 0)).andReturn(2);
		EasyMock.expect(hand.remove(CardType.TACO_CAT)).andReturn(1);
		EasyMock.replay(hand);

		Player player = new Player(hand);
		player.playCard(CardType.TACO_CAT);

		EasyMock.verify(hand);
	}

	@Test
	public void playCard_emptyHand_throwsException() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		EasyMock.expect(hand.getOrDefault(CardType.ATTACK, 0)).andReturn(0);
		EasyMock.replay(hand);

		Player player = new Player(hand);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			player.playCard(CardType.ATTACK);
		});

		String expectedMessage = "You do not have enough cards to play.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

		EasyMock.verify(hand);
	}

	@ParameterizedTest
	@EnumSource(value = CardType.class, names = {"BEARD_CAT", "CATTERMELON", "POTATO_CAT", "RAINBOW_RALPHING_CAT", "TACO_CAT"})
	public void playCard_oneBasicKitten_throwsException(CardType cardType) {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		EasyMock.expect(hand.getOrDefault(cardType, 0)).andReturn(1);
		EasyMock.replay(hand);

		Player player = new Player(hand);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			player.playCard(cardType);
		});

		String expectedMessage = "You do not have enough cards to play.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

		EasyMock.verify(hand);
	}

	@Test
	public void removeCard_oneCard_succeeds() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		EasyMock.expect(hand.getOrDefault(CardType.ATTACK, 0)).andReturn(1);
		EasyMock.expect(hand.remove(CardType.ATTACK)).andReturn(0);
		EasyMock.replay(hand);

		Player player = new Player(hand);
		player.removeCard(CardType.ATTACK);

		EasyMock.verify(hand);
	}

	@Test
	public void removeCard_multipleBasicKittens_succeeds() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		EasyMock.expect(hand.getOrDefault(CardType.TACO_CAT, 0)).andReturn(2);
		EasyMock.expect(hand.put(CardType.TACO_CAT, 1)).andReturn(2);
		EasyMock.expect(hand.getOrDefault(CardType.TACO_CAT, 0)).andReturn(1);
		EasyMock.expect(hand.remove(CardType.TACO_CAT)).andReturn(0);
		EasyMock.replay(hand);

		Player player = new Player(hand);
		player.removeCard(CardType.TACO_CAT);
		player.removeCard(CardType.TACO_CAT);

		EasyMock.verify(hand);
	}

	@Test
	public void removeCard_multipleCards_succeeds() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		EasyMock.expect(hand.getOrDefault(CardType.ATTACK, 0)).andReturn(2);
		EasyMock.expect(hand.put(CardType.ATTACK, 1)).andReturn(2);
		EasyMock.expect(hand.getOrDefault(CardType.ATTACK, 0)).andReturn(1);
		EasyMock.expect(hand.remove(CardType.ATTACK)).andReturn(0);
		EasyMock.replay(hand);

		Player player = new Player(hand);
		player.removeCard(CardType.ATTACK);
		player.removeCard(CardType.ATTACK);

		EasyMock.verify(hand);
	}

	@Test
	public void removeCard_emptyHand_throwsException() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		EasyMock.expect(hand.getOrDefault(CardType.ATTACK, 0)).andReturn(0);
		EasyMock.replay(hand);

		Player player = new Player(hand);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			player.removeCard(CardType.ATTACK);
		});

		String expectedMessage = "Not enough cards to remove.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

		EasyMock.verify(hand);
	}

	@Test
	public void OnePlayerWithIdZero_getId_returnZero() {
		Player player = new Player(1);

		Integer expected = 1;
		Integer actual = player.getId();

		assertEquals(expected, actual);
	}

	@Test
	public void OnePlayerWithHandSizeOfZero_getHandSize_returnZero() {
		Player player = new Player(0);

		Integer expected = 0;
		Integer actual = player.getHandSize();

		assertEquals(expected, actual);
	}

	@Test
	public void OnePlayerWithHandSizeOfThree_getHandSize_returnThree() {
		Player player = new Player(0);
		player.addCard(CardType.ATTACK);
		player.addCard(CardType.ATTACK);
		player.addCard(CardType.SKIP);

		Integer expected = 3;
		Integer actual = player.getHandSize();

		assertEquals(expected, actual);
	}

	@Test
	public void TwoPlayers_swapHandWith_swapsHandAndChecksSizes() {
		Player playerOne = new Player(0);
		playerOne.addCard(CardType.ATTACK);
		playerOne.addCard(CardType.ATTACK);
		playerOne.addCard(CardType.SKIP);

		Player playerTwo = new Player(1);

		playerOne.swapHandWith(playerTwo);

		Integer actualPlayerOneHandSize = playerOne.getHandSize();
		Integer expectedPlayerOneHandSize = 0;

		Integer actualPlayerTwoHandSize = playerTwo.getHandSize();
		Integer expectedPlayerTwoHandSize = 3;

		assertEquals(expectedPlayerOneHandSize, actualPlayerOneHandSize);
		assertEquals(expectedPlayerTwoHandSize, actualPlayerTwoHandSize);
	}

	@Test
	public void takeRandomCard_oneCard_returnsThatCard() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Set<CardType> keys = EasyMock.createMock(Set.class);
		CardType[] keysArray = {CardType.ATTACK};

		EasyMock.expect(hand.isEmpty()).andReturn(false);
		EasyMock.expect(hand.keySet()).andReturn(keys);
		EasyMock.expect(keys.toArray(new CardType[0])).andReturn(keysArray);
		EasyMock.expect(keys.size()).andReturn(1);
		EasyMock.expect(rand.nextInt(1)).andReturn(0);
		EasyMock.expect(hand.getOrDefault(CardType.ATTACK, 0)).andReturn(1);
		EasyMock.expect(hand.remove(CardType.ATTACK)).andReturn(0);
		EasyMock.replay(hand, keys, rand);

		Player player = new Player(hand, rand);
		CardType card = player.takeRandomCard();

		assertEquals(CardType.ATTACK, card);
		EasyMock.verify(hand);
	}

	@Test
	public void takeRandomCard_twoCards_returnsRandomCard() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		Set<CardType> keys = EasyMock.createMock(Set.class);
		CardType[] keysArray = {CardType.ATTACK, CardType.SKIP};

		EasyMock.expect(hand.isEmpty()).andReturn(false);
		EasyMock.expect(hand.keySet()).andReturn(keys);
		EasyMock.expect(keys.toArray(new CardType[0])).andReturn(keysArray);
		EasyMock.expect(keys.size()).andReturn(2);
		EasyMock.expect(rand.nextInt(2)).andReturn(0);
		EasyMock.expect(hand.getOrDefault(CardType.ATTACK, 0)).andReturn(1);
		EasyMock.expect(hand.remove(CardType.ATTACK)).andReturn(0);
		EasyMock.replay(hand, keys, rand);

		Player player = new Player(hand, rand);
		CardType card = player.takeRandomCard();

		assertEquals(CardType.ATTACK, card);
		EasyMock.verify(hand);
	}

	@Test
	public void takeRandomCard_emptyHand_throwsException() {
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);
		SecureRandom rand = EasyMock.createMock(SecureRandom.class);
		EasyMock.expect(hand.isEmpty()).andReturn(true);
		EasyMock.replay(hand, rand);

		Player player = new Player(hand, rand);

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			player.takeRandomCard();
		});

		String expectedMessage = "no cards";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

		EasyMock.verify(hand);
	}
}
