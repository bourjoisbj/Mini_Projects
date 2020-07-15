import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.DisplayName;
//
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;

class ThreeCardTest {
	
	ArrayList<Card> hand;
	ArrayList<Card> hand2;
	Player playerOne;
	Player playerTwo;
	Dealer theDealer;
	
	@BeforeEach
	void init() {
		hand = new ArrayList<>();
		hand2 = new ArrayList<>();
		
		playerOne = new Player();
		playerOne.player = 1;
		
		playerTwo = new Player();
		playerTwo.player = 2;
		
		theDealer = new Dealer();
	}
	
	//===========================
	@Test 
	void testStraightFlush1() {
		hand.add(new Card('C', 2));
		hand.add(new Card('C', 3));
		hand.add(new Card('C', 4));
		assertTrue(ThreeCardLogic.hasStraightFlush(hand), "This is a straight flush. You said it wasn't");
	
	}
	
	@Test 
	void testStraightFlush2() {
		hand.add(new Card('C', 2));
		hand.add(new Card('B', 3));
		hand.add(new Card('C', 4));
		assertFalse(ThreeCardLogic.hasStraightFlush(hand), "This is not a straigt flush. You said it was.");
	
	}
	
	@Test 
	void testStraightFlush3() {
		hand.add(new Card('C', 2));
		hand.add(new Card('C', 5));
		hand.add(new Card('C', 4));
		assertFalse(ThreeCardLogic.hasStraightFlush(hand), "This is not a straigt flush. You said it was.");
	}
	
	@Test 
	void testStraightFlush4() {
		hand.add(new Card('C', 2));
		hand.add(new Card('B', 5));
		hand.add(new Card('C', 4));
		assertFalse(ThreeCardLogic.hasStraightFlush(hand), "This is not a straigt flush. You said it was.");
	}
	
	//===========================
	@Test
	void testThreeOfAKind1() {
		hand.add(new Card('C', 4));
		hand.add(new Card('B', 4));
		hand.add(new Card('H', 4));
		assertTrue(ThreeCardLogic.hasThreeOfAKind(hand), "This is 3 of a kind. You said it was wasn't.");
	}
	
	@Test
	void testThreeOfAKind2() {
		hand.add(new Card('D', 4));
		hand.add(new Card('B', 4));
		hand.add(new Card('H', 4));
		assertTrue(ThreeCardLogic.hasThreeOfAKind(hand), "This is 3 of a kind. You said it was wasn't.");
	}
	
	@Test
	void testThreeOfAKind3() {
		hand.add(new Card('C', 5));
		hand.add(new Card('B', 4));
		hand.add(new Card('H', 4));
		assertFalse(ThreeCardLogic.hasThreeOfAKind(hand), "This is not 3 of a kind. You said it was was");
	}
	
	//===========================
	@Test 
	void testStraight1() {
		hand.add(new Card('C', 5));
		hand.add(new Card('B', 4));
		hand.add(new Card('H', 3));
		assertTrue(ThreeCardLogic.hasStraight(hand), "This is a straight. You said it wasn't");
	}
	
	@Test 
	void testStraight2() {
		hand.add(new Card('C', 6));
		hand.add(new Card('B', 4));
		hand.add(new Card('H', 3));
		assertFalse(ThreeCardLogic.hasStraight(hand), "This is not a straight. You said it was");
	}
	
	//===========================
	@Test
	void testFlush1() {
		hand.add(new Card('C', 6));
		hand.add(new Card('C', 4));
		hand.add(new Card('C', 3));
		assertTrue(ThreeCardLogic.hasFlush(hand), "This is a flush. You said it wasn't");
	}
	
	@Test
	void testFlush2() {
		hand.add(new Card('B', 2));
		hand.add(new Card('B', 4));
		hand.add(new Card('B', 3));
		assertTrue(ThreeCardLogic.hasFlush(hand), "This is a flush. You said it wasn't");
	}
	
	@Test
	void testFlush3() {
		hand.add(new Card('B', 2));
		hand.add(new Card('S', 4));
		hand.add(new Card('B', 3));
		assertFalse(ThreeCardLogic.hasFlush(hand), "This is not a flush. You said it was");
	}
	
	//===========================
	@Test
	void testPair1() {
		hand.add(new Card('B', 2));
		hand.add(new Card('B', 4));
		hand.add(new Card('C', 2));
		assertTrue(ThreeCardLogic.hasPair(hand), "This is a pair. You said it wasn't");
	}
	
	@Test
	void testPair2() {
		hand.add(new Card('D', 3));
		hand.add(new Card('B', 5));
		hand.add(new Card('C', 2));
		assertFalse(ThreeCardLogic.hasPair(hand), "This is not a pair. You said it was");
	}
	
	@Test
	void testStraightFlushVSThreeOfAKind() {
		playerOne.hand.add(new Card('C', 2));
		playerOne.hand.add(new Card('C', 3));
		playerOne.hand.add(new Card('C', 4));
		
		theDealer.dealersHand.add(new Card('C', 14));
		theDealer.dealersHand.add(new Card('B', 14));
		theDealer.dealersHand.add(new Card('H', 14));
	
		int answer = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
		
		assertEquals(answer, 2, "The Straight Flush beats Three of a Kind. You said it didn't");
	}
	
	@Test
	void testStraightFlushVSStraightFlush() {
		playerOne.hand.add(new Card('C', 2));
		playerOne.hand.add(new Card('C', 3));
		playerOne.hand.add(new Card('C', 4));
		
		theDealer.dealersHand.add(new Card('B', 14));
		theDealer.dealersHand.add(new Card('B', 13));
		theDealer.dealersHand.add(new Card('B', 12));
	
		int answer = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
		
		assertEquals(answer, 1, "The Dealer's Straight Flush beats The Player's. You said it didn't");
	}
	
	@Test
	void testThreeOfAKindVSStraight() {
		playerOne.hand.add(new Card('C', 2));
		playerOne.hand.add(new Card('B', 2));
		playerOne.hand.add(new Card('D', 2));
		
		theDealer.dealersHand.add(new Card('D', 14));
		theDealer.dealersHand.add(new Card('B', 13));
		theDealer.dealersHand.add(new Card('B', 12));
		
		int answer = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
		assertEquals(answer, 2, "Three of a Kind beats Straight. You said it didn't");
	}
	
	@Test
	void testStraightVSFlush() {
		playerOne.hand.add(new Card('C', 2));
		playerOne.hand.add(new Card('B', 3));
		playerOne.hand.add(new Card('D', 4));
		
		theDealer.dealersHand.add(new Card('D', 14));
		theDealer.dealersHand.add(new Card('D', 11));
		theDealer.dealersHand.add(new Card('D', 12));
		
		int answer = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
		assertEquals(answer, 2, "Staight beats Flush. You said it didn't");
	}
	
	@Test
	void testFlushVSPair() {
		playerOne.hand.add(new Card('C', 2));
		playerOne.hand.add(new Card('C', 5));
		playerOne.hand.add(new Card('C', 4));
		
		theDealer.dealersHand.add(new Card('D', 14));
		theDealer.dealersHand.add(new Card('H', 14));
		theDealer.dealersHand.add(new Card('D', 12));
		
		int answer = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
		assertEquals(answer, 2, "Flush beats Pair. You said it didn't");
	}
	
	@Test
	void testCompareHighHands() {
		int answer = 0;
		
		//Player win
		playerOne.hand.add(new Card('C', 14));
		playerOne.hand.add(new Card('D', 5));
		playerOne.hand.add(new Card('H', 4));
		
		theDealer.dealersHand.add(new Card('S', 5));
		theDealer.dealersHand.add(new Card('B', 4));
		theDealer.dealersHand.add(new Card('D', 2));
		
		answer = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
		assertEquals(answer, 2, "The player's hand was bigger. You said it wasn't");
		playerOne.hand.clear();
		theDealer.dealersHand.clear();
		
		//Dealer win
		playerOne.hand.add(new Card('C', 2));
		playerOne.hand.add(new Card('D', 5));
		playerOne.hand.add(new Card('H', 4));
		
		theDealer.dealersHand.add(new Card('S', 6));
		theDealer.dealersHand.add(new Card('B', 4));
		theDealer.dealersHand.add(new Card('D', 2));
		
		answer = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
		assertEquals(answer, 1, "The dealer's hand was bigger. You said it wasn't");
		playerOne.hand.clear();
		theDealer.dealersHand.clear();
		
		//No win
		playerOne.hand.add(new Card('C', 2));
		playerOne.hand.add(new Card('D', 5));
		playerOne.hand.add(new Card('H', 4));
		
		theDealer.dealersHand.add(new Card('S', 5));
		theDealer.dealersHand.add(new Card('B', 4));
		theDealer.dealersHand.add(new Card('D', 2));
		
		answer = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
		assertEquals(answer, 0, "This round was a draw. You said it wasn't");
	}
	
	
	


}
