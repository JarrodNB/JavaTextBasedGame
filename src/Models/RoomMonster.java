
package Models;

public class RoomMonster extends RoomObject{

	private Monster monster;
	
	public RoomMonster(Monster monster) {
		super();
		this.monster = monster;
	}

	public Monster getMonster() {
		return monster;
	}
	
}
