package controller;
	
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.geometry.*;
import java.util.Random;
import view.Cell;
import view.Board;
import model.Ship;


public class MainController extends Application {
	boolean isEnemyTurn = false;
	boolean inRunning = false;
	Board player,enemy;
	int allowedShips = 4;
	 Random rand = new Random();
	boolean enemysTurn = false;
	boolean run = false;
	
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
			
			if(player.getNumShips() == 0) {
				//print loss screen or images
			}
		}
	}
	public Parent create() {
		BorderPane root = new BorderPane();
		root.setPrefSize(1000,1000);
		root.setLeft(new Text("Left Side- Controls and Displays"));
		enemy = new Board( event ->  {
			if(!run)
				return;
			Cell c = (Cell)event.getSource();
			if(c.shot)
				return;
			enemysTurn = !c.takeShot();
			if(enemy.getNumShips() == 0) {
				//Win message or picture(s)
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
