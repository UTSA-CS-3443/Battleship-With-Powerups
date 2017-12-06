package controller;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import view.Board;
import view.Cell;
/**
 * Represents a slash button.
 * @author Miguel Perez
 * @author Jasmin Ramirez
 * @author Jason McDonald
 * @author Matthew Weigel
 * @author Erick Flores
 * @version 1.0
 */
public class SingleShotButton{
	public static int singleShot(Cell c) {
		int sunkShip = 0;
		if (c.takeShot()) {
			if (!(c.getShip().alive())) {
				sunkShip=2; // sunk ship
			}
			else {
				sunkShip=1;
			}
		}
		return sunkShip;
	}
	
}
