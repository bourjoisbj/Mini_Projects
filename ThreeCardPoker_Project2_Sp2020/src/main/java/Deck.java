import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {
	
	// Deck constructor
	Deck(){
		// This constructor will create a new deck of 52
		// cards that have been sorted in random order.
		for(int i = 2; i <= 14; i++) {
			this.add(new Card('C', i));	//Clubs
			this.add(new Card('D', i));	//Diamonds
			this.add(new Card('S', i));	//Spades
			this.add(new Card('H', i));	//Hearts
		}
		//Sort in random order
		Collections.shuffle(this);
		
	}
	
	//TODO: Find out method of creating new deck
	public Deck newDeck(){
		// The method will clear all the cards and create a 
		// brand new deck of 52 cards sorted in random order.
		this.clear();
		//new Deck(); 
		return new Deck();
	}
	

}
