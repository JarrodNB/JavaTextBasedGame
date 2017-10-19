
package Models;

public class RoomPuzzle extends RoomObject{

	private Puzzle puzzle;
	
	public RoomPuzzle(Puzzle puzzle) {
		super();
		this.puzzle = puzzle;
	}

	public Puzzle getPuzzle() {
		return puzzle;
	}
	
}
