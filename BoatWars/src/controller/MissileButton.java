package controller;

import view.Cell;

public class MissileButton {
	public static int[] missile(int x ,int y, Cell c) {
		boolean isValid = true;
		Cell[] cells = {c.topCell(),c.rightCell(), c, c.leftCell(), c.bottomCell()};
		int[] sunkShips = new int[cells.length];//
		//0 = already hit, 1 = hit, 2 = sunk, 3 = miss
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
