package controller;

import view.Cell;

/**
 * Represents a laser button.
 * @author Miguel Perez
 * @author Jasmin
 * @author Jason McDonald
 * @author Matthew Weigel
 * @author Erick Flores
 * @version 1.0
 */
public class LaserButton {
	/**
	 * Shoots the board with a laser.
	 * @param c A reference to the initial cell to be shot
	 * @param isVertical A boolean value specifying if this laser is vertical or horizontal
	 * @return A reference to an integer array specifying which cells were already hit, hit, sunk, or missed.
	 */
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
			sunkShips[i] = 0;
			if(cells[i] == null){
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
