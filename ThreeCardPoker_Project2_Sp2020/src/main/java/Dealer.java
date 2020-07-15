import java.util.ArrayList;

public class Dealer {
	
	private Deck theDeck;
	private ArrayList<Card> dealersHand;
	
	// Constructor for Dealer
	Dealer() {
		this.theDeck = new Deck();
		this.dealersHand = new ArrayList<>();
	}
	
	public ArrayList<Card> dealHand(){
		//The method dealHand() will return an ArrayList<Card> of
		//the three cards removed from the Deck. Before each game 
		//starts, the Dealer class must check to see if there are more
		//than 34 cards left in the deck. If not, the Deck must be 
		//re-shuffled with a new set of 52 cards in a random order
		
		if( theDeck.size() <= 34) {
			//re-shuffle deck with a new set
			// of 52 cards in a random order
			theDeck.newDeck();
			theDeck = theDeck.newDeck();
			//
			// check****
			//
		}
		else {
			//remove 3 cards from the top of the deck
			for(int i = 0; i < 3; i++) {
				dealersHand.add(theDeck.remove(i));
			}
		}
		
		return dealersHand;
	}

}
