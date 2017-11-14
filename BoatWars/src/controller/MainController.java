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
	boolean isEnemyTurn = false;
	boolean inRunning = false;
	Board player,enemy;
	int allowedShips = 4;
	 Random rand = new Random();
	boolean enemysTurn = false;
	boolean run = false;
	boolean victory = false;
	TextArea info = new TextArea();//added text area
	
	private String getDefaultMessage(){//added default message in text area
		return "The goal of BoatWars(TM) is to sink all of the enemy ships before the enemy sinks yours. Each player takes turns firing at the other players board. If a boat has all of it's squares hit, then it is sunk. The winner is the last player with ship(s) remaining. " + 
				"To start: \n\n1) Place ships. \nLeft click the board to place a ship vertically or right click the board to place a ship horizontally. Place a total of four ships." +
				"\n\n2) Select Targets. \nSelect squares on the opponents board to \"hit\" that square. If an enemy's boat was there the cell will be marked red, otherwise white if you missed a boat. If you hit a boat then you can fire again." +
				"\n\n3) The first player to sink all of the enemy ships wins the game." +
				"\n-----------------------------------------------------------";
	}
	
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
	
	private void enemysMove() {
		while(enemysTurn) {
			int y = rand.nextInt(10);
			int x = rand.nextInt(10);
			
			Cell c = player.getCell(x, y);
			if(c.shot)
				continue;
			enemysTurn = c.takeShot();
			
			if(player.getNumShips() == 0 && !victory) {
				//print loss screen or images
				info.appendText("You Lose!");
				this.victory = true;
			}
		}
	}
	public Text tutorial(BorderPane root) {
		Text text = new Text("Left Side-Control and Display\n"
				+ "This works now!\n"
				+ "Yayyyy!!!!!!!!!!!!\n");
		return text;
	}
	public void setButtons(BorderPane root) {
		VBox box = new VBox();
		Button laserButton = new Button("Laser");
		Button missileButton = new Button("Missile");
		info.setEditable(false);
		info.setPrefSize(300, 500);
		info.setWrapText(true);
		info.setText(getDefaultMessage());
		box.setSpacing(10);
		box.setPadding(new Insets(10,10,10,10));
		box.getChildren().addAll(tutorial(root),laserButton, missileButton, info);
		root.setLeft(box);
	}
	
	public Parent create() {
		BorderPane root = new BorderPane();
		root.setPrefSize(800,800);
		setButtons(root);
		enemy = new Board( event ->  {
			if(!run)
				return;
			Cell c = (Cell)event.getSource();
			if(c.shot)
				return;
			enemysTurn = !c.takeShot();
			if(enemy.getNumShips() == 0 && !victory) {
				//Win message or picture(s)
				info.appendText("You Win!");
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
			
			VBox vbox = new VBox(50, enemy, player);
	        vbox.setAlignment(Pos.CENTER);

	        root.setCenter(vbox);

	        return root;
	}
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
