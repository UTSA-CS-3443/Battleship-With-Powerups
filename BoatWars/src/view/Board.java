package view;


import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.Parent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Point2D;
import view.Cell;
import java.util.*;
import model.Ship;
/**
 * Represents the board for this battleship game.
 * @author Miguel Perez
 * @author Jasmin
 * @author Joe
 * @author Jason McDonald
 * @author Matthew Weigel
 * @author Erick Flores
 * @version 1.0
 */
public class Board  extends Parent{
	/**
	 * Represents the rows on this board
	 */
	VBox rows = new VBox();
	
	/**
	 * An integer value specifying the amount of allowed ships.
	 */
	private int numShips = 4;

	/**
	 * An integer value specifying if this board belongs to the enemy, if false it belongs to the player.
	 */
	boolean enemy = false;

	/**
	 * Constructs a board with an event handler, and a boolean value specifying if this board belongs to the enemy.
	 * @param handler A reference to the mouse handler for this board
	 * @param isEnemy A boolean value specifying if this board belongs to the enemy
	 */
	public Board(EventHandler<? super MouseEvent> handler, boolean isEnemy) {
		this.enemy = isEnemy;
		for( int i = 0; i < 10; i++) {//10 is the standard height for battleship
			HBox row = new HBox();
			for(int j = 0; j < 10; j++) {//10 is the standard length for battleship
				Cell cell = new Cell(this,j, i);
				cell.setOnMouseClicked(handler);
				row.getChildren().add(cell);
			}
			rows.getChildren().add(row);
		}
		getChildren().add(rows);
	}

	/**
	 * Returns the desired cell.
	 * @param x An integer value specifying the x coordinate of this cell.
	 * @param y An integer value specifying the y coordinate of this cell.
	 * @return A reference to the desired cell
	 */
	public Cell getCell(int x, int y) {
		return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
	}

	/**
	 * Returns a boolean value specifying if a ship is allowed to be placed here.
	 * @param ship A reference to the ship that is to be placed
	 * @param x An integer value specifying the x coordinate of a cell
	 * @param y An integer value specifying the y coordinate of a cell
	 * @return A boolean value specifying if a ship is allowed to be placed here
	 */
	public boolean allowPlaceShip(Ship ship, int x, int y) {
		int len = ship.type;
		if(ship.isVertical) {
			for(int a = y; a < y+len; a++) {
				if(!ValidPoint(x, a))
					return false;
				
				Cell cell = getCell(x, a);
				if(cell.ship != null)
					return false;
				for (Cell neigh: getNeigh(x, a)) {
					if (!ValidPoint(x, a))
						return false;
					
					if (neigh.ship != null)
						return false;
				}
			}
		} else {
			for( int a = x; a < x + len; a++ ) {
				if(!ValidPoint(a, y))
					return false;
				
				Cell cell = getCell(a, y);
				if(cell.ship != null)
					return false;
				for(Cell neigh: getNeigh(a,y)) {
					if(!ValidPoint(a, y)) 
						return false;
					if(neigh.ship != null)
						return false;
					}
				}
			}
		return true;
		}

	/**
	 * Returns an array of cells containing the neighboring cells of the desired x and y coordinate.
	 * @param x An integer value specifying the x coordinate of a cell
	 * @param y An integer value specifying the y coordinate of a cell
	 * @return An array of cells containing the neighborings of the desired x and y coordinate
	 */
	private Cell[] getNeigh(int x, int y) {
		Point2D[] points = new Point2D[] {
					new Point2D(x - 1, y),
	                new Point2D(x + 1, y),
	                new Point2D(x, y - 1),
	                new Point2D(x, y + 1)
		};
		ArrayList<Cell>  neigh = new ArrayList<Cell>();
		for(Point2D point: points) {
			if(ValidPoint(point)) {
				neigh.add(getCell((int)point.getX(),(int) point.getY()));
			}
		}
		return neigh.toArray(new Cell[0]);
	}

	/**
	 * Places a ship at the desired location if it is a valid location.
	 * @param x An integer value specifying the x coordinate of a cell
	 * @param y An integer value specifying the y coordinate of a cell
	 * @param ship A reference to the ship that is to be placed
	 * @return A boolean value specifying if a ship was placed
	 */
	public boolean placeShip(int x, int y, Ship ship) {
		if(allowPlaceShip(ship,x,y)) {
			int len = ship.type;
			if(ship.isVertical) {
				for(int a = y; a < y+len; a++) {
					Cell cell = getCell(x, a);
					cell.ship = ship;
					if(!enemy) {
						cell.setFill(Color.BLACK);
						cell.setStroke(Color.DARKSLATEGRAY);
					}
				}
			}else {
				for(int a = x; a < x+len;a++) {
					Cell cell = getCell(a, y);
                    cell.ship = ship;
                    if (!enemy) {
                        cell.setFill(Color.BLACK);
                        cell.setStroke(Color.DARKSLATEGRAY);
				}
			}
		}
			return true;
	}
		return false;
}
	/**
	 * Returns a boolean value specifying if this is a valid point.
	 * @param x A double value specifying the x coordinate of a cell
	 * @param y A double value specifying the y coordinate of a cell
	 * @return A boolean value specifying if this is a valid point
	 */
	private boolean ValidPoint(double x, double y) {
		if(x >= 0 && x < 10 && y >= 0 && y < 10) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Returns a boolean value specifying if this cell is valid.
	 * @param x An integer value specifying the x coordinate of this cell
	 * @param y An integer value specifying the y coordinate of this cell
	 * @return A boolean value specifying if this cell is valid
	 */
	public boolean isValid(int x, int y){
		if(x >= 0 && x < 10 && y >= 0 && y < 10) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Returns a boolean value specifying if this is a valid 2d point.
	 * @param point A reference to a 2d point
	 * @return A boolean value specifying if this is a valid 2d point
	 */
	private boolean ValidPoint(Point2D point) {
        return ValidPoint(point.getX(), point.getY());
    }
	
	/**
	 * Returns an integer value specifying the number of ships allowed.
	 * @return An integer value specifying the number of ships allowed
	 */
	public int getNumShips() {
		return numShips;
	}
	
	/**
	 * Changes the number of allowable ships.
	 * @param numShips An integer value specifying the new number of ships allowed
	 */
	public void setNumShips(int numShips) {
		this.numShips = numShips;
	}
}
