
package Models;

public class RoomGold extends RoomObject{

	private int gold;
	private String containerName;
	
	public RoomGold(int gold, String containerName) {
		super();
		this.gold = gold;
		this.containerName = containerName;
	}
	
	public int getGold() {
		return this.gold;
	}
	
	public String getContainerName() {
		return containerName;
	}
}
