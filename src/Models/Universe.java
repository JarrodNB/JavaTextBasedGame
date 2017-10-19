
package Models;

import java.util.Map;

public class Universe {

	private Map<String, Room> rooms;
	private Player player;
	private  Room currentRoom;
	
	public Universe(Map<String, Room> rooms) {
		this.rooms = rooms;
	}
	
	public Universe() {
		
	}
	
	public Room getRoom(String roomName) {
		return this.rooms.get(roomName);
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void bigBang() {
		// go through rooms and set this as universe
	}

	public Map<String, Room> getRooms() {
		return rooms;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}
	
}
