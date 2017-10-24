new
package Models;

import java.util.Map;

import DataStorage.GameRooms;

public class Universe {

	private Map<String, Room> rooms;
	private Player player;
	private  Room currentRoom;
	
	public Universe(Player player) {
		this.player = player;
		rooms = GameRooms.getRooms();
		for (Map.Entry<String, Room> entry : rooms.entrySet()) {
			entry.getValue().setUniverse(this);
		}
		currentRoom = rooms.get("Crash Site");
	}
	
	
	public Room getRoom(String roomName) {
		return this.rooms.get(roomName);
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Map<String, Room> getRooms() {
		return rooms;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}
	
}
