package model;
/**
 * Represents a ship for this battleship game.
 * @author Amanda, Joe, Jason, Matt, Erick
 * @version 1.0
 */
public class Ship {
	public boolean isVertical = true;
	public int type;
	private int health;
	
	public Ship(int type, boolean vertical) {
		this.isVertical = vertical;
		this.type = type;
		health = type;
	}
	public boolean alive() {
		if(health > 0) {
			return true;
		}else {
			return false;
		}
	}
	public void hit() {
		health--;
	}
}
