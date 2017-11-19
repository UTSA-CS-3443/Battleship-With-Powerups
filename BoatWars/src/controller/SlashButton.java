package controller;

import view.Cell;

public class SlashButton {
	public static int[] slash(Cell c, boolean isVertical) {
		boolean isValid = true;
		Cell[] cells = new Cell[3];
		int[] sunkShips = new int[cells.length];//
		//0 = already hit, 1 = hit, 2 = sunk, 3 = miss
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
