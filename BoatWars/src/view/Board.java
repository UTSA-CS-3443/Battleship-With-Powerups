package view;


import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Parent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Point2D;
import javafx.scene.control.Cell;
import javafx.scene.control.cell.*;

public class Board  extends Parent{
	VBox rows = new VBox();
	int ships = 5;
	boolean enemy = false;
	
	public Board(EventHandler<? super MouseEvent> handler, boolean isEnemy) {
		this.enemy = isEnemy;
		for( int i = 0; i < 10; i++) {
			HBox row = new HBox();
			for(int j = 0; j < 10; j++) {
				Cell cell = new Cell(j, i, this);
				cell.setOnMouseClicked(handler);
				row.getChildren().add(cell);
			}
			rows.getChildren().add(row);
		}
		getChildren().add(rows);
	}
	
	public boolean placeShip(Ship ship, int x, int y) {
		
}
	public Cell getCell(int x, int y) {
		return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
	}
}
