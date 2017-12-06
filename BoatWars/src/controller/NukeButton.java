package controller;

import view.Cell;
/**
 * Represents a slash button.
 * @author Miguel Perez
 * @author Jasmin
 * @author Jason McDonald
 * @author Matthew Weigel
 * @author Erick Flores
 * @version 1.0
 */
public class NukeButton {
	/**
	 * Shoots the board with a nuke.
	 * @param c A reference to the initial cell to be shot
	 * @return A reference to an integer array specifying which cells were already hit, hit, sunk, or missed.
	 */
	public static int[] nuke(Cell c) {
		boolean isValid = true;
		Cell[] cells = {c, c.topCell(),c.rightCell(), c.leftCell(), c.bottomCell(), c.topRightCell(), c.topLeftCell(), c.bottomLeftCell(), c.bottomRightCell()};
		int[] sunkShips = new int[cells.length];//
		//0 = already hit, 1 = hit, 2 = sunk, 3 = miss
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