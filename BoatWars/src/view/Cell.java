package view;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Board;
import model.Ship;
public class Cell  extends Rectangle{
	public Ship ship = null;
	public boolean shot = false;
	public int x = 0;
	public int y = 0;
	private Board board;
	
	public Cell(Board b, int x, int y) {
		super(30,30);
		this.board = b;
		this.x = x;
		this.y = y;
		setFill(Color.CYAN);
		setStroke(Color.BLACK);
	}
	
	public boolean takeShot() {
		shot = true;
		setFill(Color.WHITE);
		
		if(ship != null ) {
			ship.hit();
			setFill(Color.DARKRED);
			if(!ship.alive()) {
				board.setNumShips(board.getNumShips() - 1);
			}
			return true;
		}
		return false;
	}
}
