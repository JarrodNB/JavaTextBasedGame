
package Models;

public class RoomItem extends RoomObject{

	private Item item;
	private String containerName;
	
	public RoomItem(Item item, String containerName) {
		super();
		this.item = item;
		this.containerName = containerName;
	}

	public Item getItem() {
		return item;
	}
	
	public String getContainerName() {
		return containerName;
	}

}
