package controller;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import view.Board;
import view.Cell;

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
