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

public class Board  extends Parent{
	VBox rows = new VBox();
	private int numShips = 5;
	boolean enemy = false;
	
	public Board(EventHandler<? super MouseEvent> handler, boolean isEnemy) {
		this.enemy = isEnemy;
		for( int i = 0; i < 10; i++) {
			HBox row = new HBox();
			for(int j = 0; j < 10; j++) {
				Cell cell = new Cell(this,j, i);
				cell.setOnMouseClicked(handler);
				row.getChildren().add(cell);
			}
			rows.getChildren().add(row);
		}
		getChildren().add(rows);
	}
	
	public Cell getCell(int x, int y) {
		return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
	}
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
	
	public boolean placeShip(int x, int y, Ship ship) {
		if(allowPlaceShip(ship,y,x)) {
			int len = ship.type;
			if(ship.isVertical) {
				for(int a = y; a < y+len; a++) {
					Cell cell = getCell(x, a);
					cell.ship = ship;
					if(!enemy) {
						cell.setFill(Color.GREEN);
						cell.setStroke(Color.BLACK);
					}
				}
			}else {
				for(int a = x; a < x+len;a++) {
					Cell cell = getCell(a, y);
                    cell.ship = ship;
                    if (!enemy) {
                        cell.setFill(Color.GREEN);
                        cell.setStroke(Color.BLACK);
				}
			}
		}
			return true;
	}
		return false;
}
	private boolean ValidPoint(double x, double y) {
		if(x >= 0 && x < 10 && y >= 0 && y < 10) {
			return true;
		}else {
			return false;
		}
	}
	private boolean ValidPoint(Point2D point) {
        return ValidPoint(point.getX(), point.getY());
    }

	public int getNumShips() {
		return numShips;
	}

	public void setNumShips(int numShips) {
		this.numShips = numShips;
	}
}
