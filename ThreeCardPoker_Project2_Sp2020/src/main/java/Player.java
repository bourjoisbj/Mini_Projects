import java.util.ArrayList;

public class Player {
	
	ArrayList<Card> hand;
	int player;	 //  Added this to signal different player numbers
	int anteBet;
	int playBet;
	int pairPlusBet;
	int totalWinnings;
	
	// Constructor for Player
	Player(int player){
		this.player = player;
	}

}
