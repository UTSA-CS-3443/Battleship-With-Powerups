package controller;

import view.Cell;

public class SlashButton {
	public static int[] slash(int x ,int y, Cell c, boolean isVertical) {
		boolean isValid = true;
		int[] sunkShips = {0, 0, 0};//if all 3 cells were already hit
		//0 = already hit, 1 = hit, 2 = sunk, 3 = miss
		Cell[] cells = new Cell[3];
		if(isVertical){
			cells[0] =c.topLeftCell();
			cells[1] =c;
			cells[2] =c.bottomRightCell();
		}else{
			cells[0] =c.topRightCell();
			cells[1] =c;
			cells[2] =c.bottomLeftCell();
		}
		for(int i = 0; i < cells.length; i++){
			if(cells[i] == null){
				isValid = false;
			}
		}
		if(isValid){
			for(int i = 0; i<cells.length; i++){
				if(!cells[i].shot){
					if (cells[i].takeShot()) {
						if (!(cells[i].getShip().alive())) {
							sunkShips[0] = 2; // sunk ship
						}
						else {
							sunkShips[0] = 1;//hit ship
						}
					}else{
						sunkShips[0] = 3;//miss ship
					}
				}
			}
		}
		
		return sunkShips;	
	}
}