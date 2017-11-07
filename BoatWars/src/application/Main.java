package application;
	
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.geometry.*;
import java.util.*;


public class Main extends Application {
	boolean isEnemyTurn = false;
	boolean inRunning = false;
	Board player,enemy;
	int allowedShips = 4;
	
	public Parent create() {
		BorderPane root = new BorderPane();
		root.setPrefSize(600,800);
		root.setLeft(new Text("Left Side- Controls and Displays"));
		
		
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			Scene scene = new scene(create());
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
