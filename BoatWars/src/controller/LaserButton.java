package controller;

import view.Cell;

/**
 * Represents a laser button.
 * @author Amanda, Joe, Jason, Matt, Erick
 * @version 1.0
 */
public class LaserButton {
	public static int[] laser(Cell c, boolean isVertical) {
		boolean isValid = true;
		Cell[] cells = new Cell[3];
		int[] sunkShips = new int[cells.length];//
		//0 = already hit, 1 = hit, 2 = sunk, 3 = miss
		if(isVertical){
			cells[0] =c.topCell();
			cells[1] =c;
			cells[2] =c.bottomCell();
		}else{
			cells[0] =c.leftCell();
			cells[1] =c;
			cells[2] =c.rightCell();
		}
		for(int i = 0; i < cells.length; i++){
			if(cells[i] == null){
				sunkShips[i] = 0;
				isValid = false;
			}
		}
		if(isValid){
			for(int i = 0; i<cells.length; i++){
				if(!cells[i].shot){
					if (cells[i].takeShot()) {
						if (!(cells[i].getShip().alive())) {
							sunkShips[i] = 2; // sunk ship
						}
						else {
							sunkShips[i] = 1;//hit ship
						}
					}else{
						sunkShips[i] = 3;//miss ship
					}
				}
			}
		}
		return sunkShips;
	}
}
