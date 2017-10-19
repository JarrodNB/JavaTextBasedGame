
package Controllers;

import java.util.Map;
import java.util.Scanner;

import GameExceptions.PlayerIsDeadException;
import Models.Room;
import Models.RoomItem;

public class RoomHandler {

	public static void enter(Room room) throws PlayerIsDeadException {
		Scanner scanner = new Scanner(System.in);
		System.out.println(room.getDescription());
		
		// while loop
		String input = scanner.nextLine().toLowerCase();
		String destination = roomCommands(room, input);
		if (destination != null) {
			System.out.println("You are traveling to " + destination);
			scanner.close();
			RoomHandler.enter(room.getUniverse().getRoom(destination));
		}
		
	}
	
	private static String roomCommands(Room room, String input) throws PlayerIsDeadException {
		if (input.equalsIgnoreCase("open inventory")) {
			OpenInventory.openInventory(room.getUniverse().getPlayer());
			return null;
		} else if (input.startsWith("fight")) {
			if (room.getRoomMonster().isInRoom()) {
				Arena.fight(room.getUniverse().getPlayer(), room.getRoomMonster());
			}
		}
		//String[] inputArray = input.split(" ");
		// open container name
		// fight monster
		// solve puzzle 
		// go to exit return exit string
		
		
		
		
		
		
		
		
		
		
		return null; // name of room
	}
	
	
	
	
	
	
	
	public static void displayRoomObjects(Room room) {
		
		if (room.getRoomGold() != null) {
			System.out.println("You see a " + room.getRoomGold().getContainerName() + ". Maybe something valueable is inside.");
		}
		
		if (room.getRoomItems() != null) {
			for (RoomItem roomItem : room.getRoomItems()) {
				if (roomItem.isInRoom()) {
					System.out.println("You see a " + roomItem.getContainerName() + ". Maybe something is inside?");
				}
			} 
		}
		
		
		if (room.getRoomMonster() != null) {
			if (room.getRoomMonster().isInRoom()) {
				System.out.println(
						"You see a " + room.getRoomMonster().getMonster().getName() + ". Perhaps you should kill it");
			} 
		}
		
		
		if (room.getRoomPuzzle() != null) {
			if (room.getRoomPuzzle().isInRoom()) {
				System.out.println("You see a puzzle waiting to be solved!");
			} 
		}
		
		for (Map.Entry<String, Boolean> entry : room.getExits().entrySet()) {
			if (entry.getValue() == true) {
				System.out.println("In the distance you can see a portal to " + entry.getKey() + ".");
			} else {
				System.out.println("You can see an entrance to " + entry.getKey() + ".");
			}
			
		}
	}
	
	
	
	
	
	
	
	
	
	
}
