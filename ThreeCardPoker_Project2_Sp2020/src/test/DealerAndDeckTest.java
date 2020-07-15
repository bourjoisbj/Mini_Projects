import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DealerAndDeckTest {
	Dealer theDealer;
	@BeforeEach
	void init() {
		theDealer = new Dealer();
	}
	
	
	@Test
	void testIfDealerClassWasMade(){
		assertEquals("Dealer", theDealer.getClass().getName(), "did not initiate proper object");
	}
	
	@Test 
	void testDeckClass() {
		assertEquals("Deck", theDealer.theDeck.getClass().getName(), "did not initiate proper object");
	}
	
	@Test
	void testDealersHand() {
		assertEquals("java.util.ArrayList", theDealer.dealersHand.getClass().getName(), "did not use ArrayList for dealer's hand");
	}
	
	@Test
	void testDealerHandSize() {
		assertEquals(0, theDealer.dealersHand.size(), "You should have no cards");
	}
	
	@Test
	void testDealerHandSize2() {
		theDealer.dealersHand = theDealer.dealHand();
		assertEquals(3, theDealer.dealersHand.size(), "You have more or less than 3 cards.");
	}
	
	@Test
	void testDeckValues() {
		for(int i = 0; i < theDealer.theDeck.size(); i++) {
			assertTrue( (theDealer.theDeck.get(i).value >=2 || theDealer.dealersHand.get(i).value <=14),
						"You have cards with values greater than 14 or less than 2.");
		}
	}
	
	@Test
	void testDeckSuits() {
		for(int i = 0; i < theDealer.theDeck.size(); i++) {
			assertTrue( (theDealer.theDeck.get(i).suit == 'C' || theDealer.theDeck.get(i).suit == 'D' ||
						 theDealer.theDeck.get(i).suit == 'S' || theDealer.theDeck.get(i).suit == 'H'), 
						"You have cards with values that are not Club, Diamond, Heart, or Spade.");
		}
	}
	
	@Test
	void testDeckSize1() {
		assertEquals(52, theDealer.theDeck.size(), "You don't have the right amount of cards in the deck");
	}
	
	@Test
	void testSDeckize2() {
		for(int i = 0; i < 6; i++) {
			theDealer.dealHand();
		}
		assertEquals(theDealer.theDeck.size(), 34,  "You don't know how to deal cards");
	}
	
	@Test
	void testDeckSize3() {
		for(int i = 0; i < 6; i++) {
			theDealer.dealHand();
			theDealer.checkCardSize = false;
		}
		theDealer.dealHand();
		assertEquals(theDealer.theDeck.size(), 49,  "You don't know how to deal cards");
	}

}
