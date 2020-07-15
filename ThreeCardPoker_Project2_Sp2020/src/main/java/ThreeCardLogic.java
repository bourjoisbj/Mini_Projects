import java.util.ArrayList;
import java.util.Collections;

public class ThreeCardLogic {
	

	static boolean hasHighCard(ArrayList<Card> hand) {
		// Return false if the hand is empty
		if( hand.isEmpty())
			return false;
		// Check each card if hand is not empty to
		// determine if one card is queen or higher
		for(Card card : hand) {
			if( card.value >= 12)
				return true;
		}
		
		return false;
	}
	
	
	static boolean hasStraightFlush(ArrayList<Card> hand) {
		
		ArrayList<Integer> cards = new ArrayList<Integer>();
		// Collect the values in an array for sorting
		for( Card card : hand) {
			cards.add( card.value);
		}
		Collections.sort( cards);
		
		if( (cards.get(0) - cards.get(1) == -1) && (cards.get(1) - cards.get(2) == -1) 
			&& hand.get(0).suit == hand.get(1).suit && hand.get(1).suit == hand.get(2).suit )
			return true;
		
		return false;
	}
	
	
	static boolean hasStraight(ArrayList<Card> hand) {
		if( hand.isEmpty() || hasStraightFlush( hand) )
			return false;
		
		ArrayList<Integer> cards = new ArrayList<Integer>();
		// Collect the values in an array for sorting
		for (Card card : hand) {
			cards.add(card.value);
		}
		Collections.sort(cards);

		if( (cards.get(0) - cards.get(1) == -1) && (cards.get(1) - cards.get(2) == -1) )
			return true;
		
		return false;
	}
	
	
	static boolean hasThreeOfAKind(ArrayList<Card> hand) {
		
		if( hand.get(0).value == hand.get(1).value && hand.get(0).value == hand.get(2).value)
			return true;
		
		return false;
	}
	
	
	static boolean hasFlush(ArrayList<Card> hand) {
		
		if( hasStraightFlush( hand))
			return false;
			
		if( hand.get(0).suit == hand.get(1).suit && hand.get(0).suit == hand.get(2).suit )
			return true;
			
		return false;		
	}
	
	
	
	static boolean hasPair(ArrayList<Card> hand) {
		
		if( hasThreeOfAKind( hand))
		return false;
		
		if( hand.get(0).value == hand.get(1).value || hand.get(0).value == hand.get(2).value
			|| hand.get(1).value == hand.get(2).value)
			return true;
		
		return false;
	}
	
	
	
	public static int evalHand(ArrayList<Card> hand) {
		/*This class represents the logic in the game.
		 * The method evalHand will return an integer
		 * value representing the value of the hand 
		 * passed in. It will return:
		 * • 0 if the hand just has a high card
		 * • 1 for a straight flush
		 * • 2 for three of a kind
		 * • 3 for a straight
		 * • 4 for a flush
		 * • 5 for a pair
		 */
		
		//
		// Add stuff ****
		//
		
		if(hasHighCard(hand)) {
			return 0;
		}
		else if(hasStraightFlush(hand)) {
			return 1;
		}
		else if(hasThreeOfAKind(hand)) {
			return 2;
		}
		else if(hasStraight(hand)) {
			return 3;
		}
		else if(hasFlush(hand)) {
			return 4;
		}
		else if (hasPair(hand)) {
			return 5;
		}
		
		
		return -1;
	}
	
	
	
	public static int evalPPWinnings(ArrayList<Card>hand, int bet) {
		/*The method evalPPWinnings will return the amount won for the
		 * PairPlus bet. It will evaluate the hand and then evaluate the
		 * winnings and return the amount won. If the player lost the Pair 
		 * Plus bet, it will just return 0. 
		 */
//		if( hand.isEmpty())
//			return 0;
		
		int num = evalHand(hand);

		switch(num) {
			case 1:
				bet *= 40;
				break;
			case 2:
				bet *= 30;
				break;
			case 3:
				bet *= 6;
				break;
			case 4:
				bet *= 3;
				break;
			case 5:
				bet *= 1;
				break;
			default:
				bet = 0;
				break;
		}
		
		return bet;
	}
	
	
	
	public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
		/*The method compareHands will compare the two hands passed 
		 * in and return an integer based on which hand won:
		 * • 0 if neither hand won
		 * • 1 if the dealer hand won
		 * • 2 if the player hand won*/
		int dealerPoint = evalHand(dealer);
		int playerPoint = evalHand(player);
		
		if(dealerPoint == 0) 
			dealerPoint = 6;
		
		if(playerPoint == 0) 
			playerPoint = 6;
		
		//dealer won
		if(dealerPoint < playerPoint) 
			return 1;
		
		//player won
		else if(dealerPoint > playerPoint) {
			return 2;
		}
		else {
			return compareHighCards(dealer, player);
		}
	}
	
	public static int compareHighCards(ArrayList<Card> dealer, ArrayList<Card> player) {
		
		ArrayList<Integer> dealerHighCard = new ArrayList<Integer>() ;
		ArrayList<Integer> playerHighCard = new ArrayList<Integer>() ;
		
		for( int i=0; i < dealer.size(); ++i) {
			playerHighCard.add( player.get(i).value);
			dealerHighCard.add( dealer.get(i).value);
		}
		
		Collections.sort( playerHighCard);
		Collections.sort( dealerHighCard);
		
		if( dealerHighCard.get(2) > playerHighCard.get(2) ) 
			return 1;
		
		else if( dealerHighCard.get(2) < playerHighCard.get(2) ) 
			return 2;
		
		else 
			return 0;
	}
	
}
