
package Controllers;

import java.util.Map;
import java.util.Scanner;

import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import GameExceptions.PlayerIsDeadException;
import Models.Player;
import Models.Room;
import Models.RoomItem;

public class RoomHandler {
	
	// add save add monster examine
	private static final String HELP = "Your possible commands are look, check containerName, fight, solve puzzle, open inventory, and go to RoomName";

	public static void enter(Room room) throws PlayerIsDeadException, ItemException, CharacterException {
		Scanner scanner = new Scanner(System.in);
		System.out.println(room.getDescription());
		while (true) {
			String input = scanner.nextLine().toLowerCase();
			String destination = roomCommands(room, input);
			if (destination != null && room.isExitInRoom(destination)) {
				System.out.println("You are traveling to " + destination);
				scanner.close();
				RoomHandler.enter(room.getUniverse().getRoom(destination));
			} else if (destination != null) {
				System.out.println("There is no connection to that room!");
			}
		}
	}
	
	private static String roomCommands(Room room, String input) throws PlayerIsDeadException, ItemException, CharacterException {
		
		if (input.equalsIgnoreCase("look")) {
			displayRoomObjects(room);
			return null;
		}
		
		if (input.equalsIgnoreCase("open inventory")) {
			OpenInventory.openInventory(room.getUniverse().getPlayer());
			return null;
		} else if (input.startsWith("fight")) {
			if (room.getRoomMonster() != null && room.getRoomMonster().isInRoom()) {
				Arena.fight(room.getUniverse().getPlayer(), room.getRoomMonster());
				return null;
			} else {
				System.out.println("There is no monster to fight.");
				return null;
			}
		}
		
		if (input.equalsIgnoreCase("solve puzzle")) {
			if (room.getRoomPuzzle() == null || !room.getRoomPuzzle().isInRoom()) {
				System.out.println("There is no puzzle to be solved.");
				return null;
			}
			if (room.getRoomPuzzle().isInRoom()) {
				SolvePuzzle.solvePuzzle(room.getRoomPuzzle(), room.getUniverse().getPlayer());
			}
			return null;
		}
		
		String[] inputArray = input.split(" ");
		int length = inputArray.length;

		if (inputArray[0].equalsIgnoreCase("check")) {
			if (length == 3) {
				String containerName = inputArray[1] + " " + inputArray[2];
				getItemFromContainer(room, containerName);
				return null;
			} else {
				getItemFromContainer(room, inputArray[1]);
				return null;
			}
		}
		
		if (input.startsWith("go to")) {
			if (length == 4) {
				return inputArray[2] + " " + inputArray[3];
			} else if (length == 5) {
				return inputArray[2] + " " + inputArray[3] + " " + inputArray[4];
			} else {
				return inputArray[2];
			}
		}
		
		if (input.equalsIgnoreCase("shop")) {
			if (room.containsShop()) {
				Shopping.shop(room.getUniverse().getPlayer());
				return null;
			} else {
				System.out.println("There is no shop here. Are you blind?");
				return null;
			}
		}
		
		if (input.equalsIgnoreCase("help")) {
			System.out.println(HELP);
			return null;
		}
		System.out.println(GameEngine.UNRECOGNIZED_COMMAND);
		
		return null;
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
	
	private static void getItemFromContainer(Room room, String containerName) throws ItemException, CharacterException {
		if (room.getRoomGold().getContainerName().equalsIgnoreCase(containerName)) {
			Player player = room.getUniverse().getPlayer();
			player.setGold(player.getGold() + room.getRoomGold().getGold());
			room.getRoomGold().setIsInRoom(false);
			System.out.println("You found " + room.getRoomGold().getGold() + " gold!");
			return;
		}
		for (RoomItem roomItem : room.getRoomItems()) {
			if (roomItem.getContainerName().equalsIgnoreCase(containerName) && roomItem.isInRoom()) {
				room.getUniverse().getPlayer().getInventory().addItem(roomItem.getItem());
				roomItem.setIsInRoom(false);
				System.out.println("You found " + roomItem.getItem().getQuantity() + " " + roomItem.getItem().getName()+ ".");
				return;
			}
		}
		System.out.println("There is nothing to check with that name.");
	}
	
}
