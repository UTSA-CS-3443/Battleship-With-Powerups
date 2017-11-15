package controller;
	
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.geometry.*;
import java.util.Random;
import view.Cell;
import view.Board;
import model.Ship;

/**
 * Represents the data and the rules that govern this battleship game.
 * @author Amanda, Joe, Jason, Matt, Erick
 * @version 1.0
 */
public class MainController extends Application {
	/**
	 * A boolean value specifying if this turn is the enemy's turn.
	 */
	boolean isEnemyTurn = false;
	
	/**
	 * If true, the game is currently running. If false, both players are currently placing ships.
	 */
	boolean run = false;

	/**
	 * A reference to the board that the player is using.
	 */
	Board player;
	
	/**
	 * A reference to the board that the enemy is using.
	 */
	Board enemy;

	/**
	 * An integer value specifying the amount of allowed ships.
	 */
	int allowedShips = 4;

	/**
	 * A reference to a pseudo-random number generator.
	 */
	Random rand = new Random();
	
	/**
	 * A boolean value specifying if it is the enemy's turn.
	 */
	boolean enemysTurn = false;
	
	/**
	 * A boolean value specifying if either player has won this game.
	 */
	boolean victory = false;
	
	/**
	 * A reference to the text area that displays the desired information.
	 */
	TextArea info = new TextArea();//added text area
	
	/**
	 * Restart Button ~ STILL UNFINISHED
	 */
	Button restartButton;
	
	/**
	 * An integer value specifying the enemy's turn.
	 */
	int enemyTurnNumber = 0;
	
	/**
	 * An integer value specifying the players turn.
	 */
	int playerTurnNumber = 0;
	
	/**
	 * A label for enemy board.
	 */
	Label enemyLabel = new Label("Enemy - Turn " + enemyTurnNumber);
	
	/**
	 * A label for the player board.
	 */
	Label playerLabel = new Label("Player - Turn " + playerTurnNumber);
	
	
	/**
	 * Sets the default message at the start of this game.
	 * @return A reference to the default message at the start of this game
	 */
	private String getDefaultMessage(){//added default message in text area
		return "The goal of BoatWars(TM) is to sink all of the enemy ships before the enemy sinks yours. Each player takes turns firing at the other players board. If a boat has all of it's squares hit, then it is sunk. The winner is the last player with ship(s) remaining. " + 
				"To start: \n\n1) Place ships. \nLeft click the board to place a ship vertically or right click the board to place a ship horizontally. Place a total of four ships." +
				"\n\n2) Select Targets. \nSelect squares on the opponents board to \"hit\" that square. If an enemy's boat was there the cell will be marked red, otherwise white if you missed a boat. If you hit a boat then you can fire again." +
				"\n\n3) The first player to sink all of the enemy ships wins the game." +
				"\n--------------------------------------------------------";
	}
	
	/**
	 * Starts this game.
	 */
	public void gameStart() {
		int num = 4;
		
		while(num > 0) {
			int y = rand.nextInt(10);
			int x = rand.nextInt(10);
			if(enemy.placeShip(x,y,new Ship(num, Math.random() < 0.5))) {
				num--;
			}
		}
		run = true;
	}
	
	/**
	 * Completes the enemy's move.
	 */
	private void enemysMove() {
		while(enemysTurn) {
			int y = rand.nextInt(10);
			int x = rand.nextInt(10);
			
			Cell c = player.getCell(x, y);
			if(c.shot){
				continue;
			}else{
				if(!victory){
					enemyTurnNumber++;
					enemyLabel.setText("Enemy - Turn " + enemyTurnNumber);
				}
			}
			
			if(c.takeShot() && !victory){
				enemysTurn = true;
				if(!(c.getShip().alive())){
					info.appendText("\nCRITICAL HIT!\n One of your SHIPS have been SUNK! \n\nYou have " + player.getNumShips() + " ship(s) REMAINING!\n");
					
				}else{
					info.appendText("\nOne of your SHIPS have been HIT!");
				}
			}else{
				enemysTurn = false;
			}
			if(player.getNumShips() == 0 && !victory) {
				//print loss screen or images
				info.appendText("\n\n\n\t\t\t\tYou Lose!");
				this.victory = true;
			}
		}
	}
	
	/**
	 * Displays tutorial text.
	 * @param root A reference to the pane that this component is placed within
	 * @return The text A reference to the tutorial text
	 */
	public Text tutorial(BorderPane root) {
		Text text = new Text("Left Side-Control and Display\n"
				+ "This works now!\n"
				+ "Yayyyy!!!!!!!!!!!!\n");
		return text;
	}
	
	/**
	 * Sets up the buttons
	 * @param root A reference to the pane that this component is placed within
	 */
	public void setButtons(BorderPane root) {
		VBox box = new VBox();
		Label label = new Label("Power-Ups:");
		Label label2 = new Label("Display:");
		Label label3 = new Label("Menu:");
		Button scatterBombButton = new Button("Scatter Bomb");
		Button laserButton = new Button("Laser");
		Button missileButton = new Button("Missile");
		Button scoreboardButton = new Button("View Scoreboard");
		restartButton = new Button("Restart");
		Button exitButton = new Button("Exit");
		restartButton.setOnAction( e -> {
			this.restart();
		});
		scoreboardButton.setOnAction( e -> {
			this.showScoreboard();
		});
		exitButton.setOnAction(e ->{
			this.exit();
		});
		
		info = new TextArea();
		info.setEditable(false);
		info.setPrefSize(300, 500);
		info.setWrapText(true);
		info.setText(getDefaultMessage());
		box.setSpacing(10);
		box.setPadding(new Insets(10,10,10,10));
		box.getChildren().addAll(label, laserButton, missileButton, scatterBombButton, label2, info, label3, scoreboardButton, restartButton, exitButton);
		root.setLeft(box);
	}
	
	/**
	 * Restarts this application.
	 */
	public void restart(){
		//TODO this method
	}
	
	/**
	 * Displays scoreboard.
	 */
	private void showScoreboard(){
		//TODO this method
	}
	
	/**
	 * Exits this application.
	 */
	private void exit(){
		//TODO this method
	}
	
	/**
	 * Creates the BoatWars game.
	 * @return Returns the pane that this game is component is placed within
	 */
	public Parent create() {
		BorderPane root = new BorderPane();
		root.setPrefSize(800,800);
		setButtons(root);
		enemy = new Board( event ->  {
			if(!run)
				return;
			Cell c = (Cell)event.getSource();
			if(c.shot){
				return;
			}else{
				if(!victory){
					playerTurnNumber++;
					playerLabel.setText("Player - Turn " + playerTurnNumber);
				}
			}
			if(c.takeShot() && !victory){
				enemysTurn = false;
				
				if(!(c.getShip().alive())){
					info.appendText("\nCRITICAL HIT!\n You SUNK one of the enemy's SHIPS! \n\nThe enemy has " + enemy.getNumShips() + " ship(s) REMAINING!\n");
					
				}else{
					info.appendText("\nYou HIT one of the enemy's SHIPS!");
				}
				
			}else{
				enemysTurn = true;
			}
			if(enemy.getNumShips() == 0 && !victory) {
				//Win message or picture(s)
				info.appendText("\n\n\n\t\t\t\tYou Win!");
				this.victory = true;
			}
			if(enemysTurn)
				enemysMove();
		}, true);
		player = new Board(event ->  {
			if(run)
				return;
			
			Cell c = (Cell)event.getSource();
			if (player.placeShip(c.x, c.y, new Ship(allowedShips, event.getButton() == MouseButton.PRIMARY)))
					if(--allowedShips == 0) {
						gameStart();
					}
		}, false);
		
			
			
			VBox vbox = new VBox(50, enemyLabel, enemy, playerLabel, player);
	        vbox.setAlignment(Pos.CENTER);

	        root.setCenter(vbox);

	        return root;
	}
	
	/**
	 * Starts this BoatWars application.
	 * @param primaryStage A reference to the stage for this BoatWars application
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			 Scene scene = new Scene(create());
			primaryStage.setTitle("Boat Wars");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Runs this BoatWars application.
	 * @param args A reference to command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
