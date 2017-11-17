package controller;

import javafx.scene.control.Button;
import view.Board;
import view.Cell;

/**
 * Represents a laser button.
 * @author Amanda, Joe, Jason, Matt, Erick
 * @version 1.0
 */
public class LaserButton {
	public static int laser(int x ,int y, Cell c) {
		int sunkShip = 0;
		for(int i = 0; i < 3; i++) {
			c = c.topCell(c);
			if (c.takeShot()) {
				if (!(c.getShip().alive())) {
					sunkShip=2; // sunk ship
				}
				else {
					sunkShip=1;
				}
			}
		}
		return sunkShip;
	}
}
