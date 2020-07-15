
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ThreeCardPokerGame extends Application {
	//All the players in the game.
	private Player playerOne;
	private Player playerTwo;
	private Dealer theDealer;

	//The variables needed for the window.
	private Stage window;
	private static BorderPane layout;
	private static VBox topLayout;
	private VBox centerLayout1;
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Let's Play Three Card Poker!!!");
		
		layout = new BorderPane();
		layout.setStyle("-fx-background-color: lightblue");
		
		centerLayout1 = new VBox();
		Text opening_text = new Text("LET'S PLAY 3-CARD POKER.");
		Button start_button = new Button("Start");	
		opening_text.setFont(new Font("Arial", 30));
		start_button.setFont(new Font("Arial", 22));
		
		start_button.setOnAction(e -> {
			//Initialize all the players
			playerOne = new Player(1);
			playerTwo = new Player(2);
			theDealer = new Dealer();
			
			//Start with code that has player one pick their bets
			placeBets(playerOne, window);
		});
		
		centerLayout1.getChildren().addAll(opening_text, start_button);
		centerLayout1.setAlignment(Pos.CENTER);
		layout.setCenter(centerLayout1);
		
		Scene scene = new Scene(layout,600,600);
		window.setScene(scene);
		window.show();
		
	}//End of start()...
	

	public Player placeBets(Player player, Stage window) {
		//Make new BorderPane
		layout = new BorderPane();
		
		//Make new TopLayout
		topLayout = new VBox();
		Text playerNameText = new Text("Player " + player.player);
		playerNameText.setFont(new Font("Arial", 24));
		topLayout.getChildren().add(playerNameText);
		
		//Make new CenterLayout
		GridPane centerLayout2 = new GridPane();
//		centerLayout2.setPadding(new Insets(100, 100, 50, 50));
		centerLayout2.setVgap(10);
		centerLayout2.setHgap(10);
		
		//Making the text and text field for the ante wager
		Text anteWagerText = new Text("Ante Wager: ");
		TextField anteWager = new TextField();
		anteWager.setOnKeyPressed(e -> {
//			KeyCode key = KeyCode.getKeyCode(e.getCode().getName());
//			if(key.isDigitKey() || key.getCode() == KeyEvent.VK_BACK_SPACE) {
//				anteWager.setEditable(true);
//			}
//			else {
//				e.consume();
//				anteWager.setEditable(false);
//			}
		});
		//Set the text and text field in the right coordinates
		GridPane.setConstraints(anteWagerText, 0, 0);
		GridPane.setConstraints(anteWager, 1, 0);
		
		
		//Making the text and text field for the pair plus wager
		Text pairPlusWagerText = new Text("Pair Plus Wager (OPTIONAL): ");
		TextField pairPlusWager = new TextField();
		pairPlusWager.setOnKeyPressed(e -> {
//			KeyCode key = KeyCode.getKeyCode(e.getCode().getName());
//			if(key.isDigitKey() || key.getCode() == KeyEvent.VK_BACK_SPACE) {
//				pairPlusWager.setEditable(true);
//			}
//			else {
//				e.consume();
//				pairPlusWager.setEditable(false);
//			}
		}); 
		//Set the text and text field in the right coordinates
		GridPane.setConstraints(pairPlusWagerText, 0, 1);
		GridPane.setConstraints(pairPlusWager, 1, 1);
		
		
		//Creating the button for next
		Button nextButton = new Button();
		if(player.player == 1) {
			nextButton.setText("Next");
		}
		else {
			nextButton.setText("Play");
		}
		
		nextButton.setOnAction(e -> {
			//get ante wager
			if(anteWager.getText().equals("")) {
				System.out.println("Cannot place this ante bet");
				placeBets(player, window);
			}
			else if(Integer.parseInt(anteWager.getText()) >= 5 && 
					Integer.parseInt(anteWager.getText()) <= 25) {
				player.anteBet = Integer.parseInt(anteWager.getText());
			}
			else {
				//They cannot place this bet
				System.out.println("Cannot place this ante bet");
				placeBets(player, window);
			}
			
			//get pair plus wager
			if(pairPlusWager.getText().equals("")) {
				player.pairPlusBet = 0;
			}
			else if(Integer.parseInt(pairPlusWager.getText()) >= 5 &&
					Integer.parseInt(pairPlusWager.getText()) <= 25) {
				player.pairPlusBet = Integer.parseInt(pairPlusWager.getText());
			}
			else {
				//They cannot place this bet
				System.out.println("Cannot place this pair plus bet");
				placeBets(player, window);
			}
			
			//Assign the player to the right bets
			if(player.player == 1) {
				playerOne = player;
				placeBets(playerTwo, window);
			}
			else {
				playerTwo = player;
				//Move on to next phase of game;
				foldOrBet(playerOne, window);
			}
			
			
		});//End of nextButton.setOnAction()..
		
		//Creating the button for back
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> {
			if(player.player == 1) {
				try {
					start(window);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
			else {
				placeBets(playerOne, window);
			}
		});
		//Set coordinates for next and back
		GridPane.setConstraints(nextButton, 1, 3);
		GridPane.setConstraints(backButton, 0, 3);
		
		centerLayout2.getChildren().addAll(anteWagerText, anteWager, pairPlusWagerText,
											pairPlusWager, nextButton, backButton);
		
		layout.setCenter(centerLayout2);
		layout.setTop(topLayout);
		
		Scene scene = new Scene(layout, 600, 600);
		window.setScene(scene);
		window.show();
		return player;
		
	}//End of placeBets()...

	public void foldOrBet(Player player, Stage window) {
		
		//Have dealer hand player card
		player.hand = theDealer.dealHand();

		layout = new BorderPane();
		
		//Make new TopLayout
		topLayout = new VBox();
		Text playerNameText = new Text("Player" + player.player + "'s Cards");
		playerNameText.setFont(new Font("Arial", 30));
		topLayout.getChildren().add(playerNameText);
		
		VBox centerLayout = new VBox();
		Text card1 = new Text(Character.toString(player.hand.get(0).suit) + " " + player.hand.get(0).value);
		Text card2 = new Text(Character.toString(player.hand.get(1).suit) + " " + player.hand.get(1).value);
		Text card3 = new Text(Character.toString(player.hand.get(2).suit) + " " + player.hand.get(2).value);
		
		centerLayout.getChildren().addAll(card1, card2, card3);
		
		layout.setCenter(centerLayout);
		layout.setTop(topLayout);
		
		Scene scene = new Scene(layout, 600, 600);
		window.setScene(scene);
		window.show();
	}
	
}
