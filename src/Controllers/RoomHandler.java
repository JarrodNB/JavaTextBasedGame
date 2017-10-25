
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
	// add ship mechanic to give quests
	private static final String HELP = "Your possible commands are look, check containerName,"
			+ " fight, examine monster, solve puzzle, open inventory, save, and go to RoomName";

	public static void enter(Room room) throws PlayerIsDeadException, ItemException, CharacterException {
		Scanner scanner = new Scanner(System.in);
		System.out.println(room.getDescription());
		while (true) {
			String input = scanner.nextLine();//testing.toLowerCase();
			String destination = roomCommands(room, input);
			if (destination != null && room.isExitInRoom(destination)) {
				System.out.println("You are traveling to " + destination);
				//scanner.close();
				//System.out.println(destination); // testing
				//System.out.println(room.getUniverse().getRoom(destination));// testing
				try {
					RoomHandler.enter(room.getUniverse().getRoom(destination));
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (destination != null) {
				System.out.println("There is no connection to that room!");
			}
		}
	}
	
	private static String roomCommands(Room room, String input) throws PlayerIsDeadException, ItemException, CharacterException {
		if (input.equals("")) {
			return null;
		}
		if (input.equalsIgnoreCase("save")) {
			SaveGame.save(room.getUniverse(), room);
			System.out.println("The game has been saved.");
			return null;
		}
		
		if (input.equalsIgnoreCase("look")) {
			displayRoomObjects(room);
			return null;
		}
		
		if (input.equalsIgnoreCase("open inventory")) {
			OpenInventory.openInventory(room.getUniverse().getPlayer());
			return null;
		} 
		
		 if (input.startsWith("fight")) {
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
		
		if (input.equalsIgnoreCase("examine monster")) {
			if (room.getRoomMonster() != null && room.getRoomMonster().isInRoom()) {
				System.out.println(room.getRoomMonster().getMonster().getDescription());
				return null;
			} else {
				System.out.println("There is no monster to examine");
				return null;
			}
		}
		
		String[] inputArray = input.split(" ");
		int length = inputArray.length;
		for (int index = 0; index < inputArray.length; index++) {
			inputArray[index] = inputArray[index].substring(0, 1).toUpperCase() + inputArray[index].substring(1, inputArray[index].length());
		}

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
		
		if (room.getRoomGold() != null && room.getRoomGold().isInRoom()) {
			System.out.println("You see a " + room.getRoomGold().getContainerName() + ". Maybe something valueable is inside.");
		}
		
		if (room.getRoomItems() != null) {
			for (RoomItem roomItem : room.getRoomItems()) {
				if (roomItem.isInRoom()) {
					System.out.println("You see a " + roomItem.getContainerName() + ". Maybe something is inside?");
				}
			} 
		}
		
		if (room.getRoomMonster() != null && room.getRoomMonster().isInRoom()) {
				System.out.println("You see a " + room.getRoomMonster().getMonster().getName() + ". Perhaps you should kill it"); 
		}
		
		if (room.getRoomPuzzle() != null && room.getRoomPuzzle().isInRoom()) {
				System.out.println("You see a puzzle waiting to be solved!");
		}
		
		for (Map.Entry<String, Boolean> entry : room.getExits().entrySet()) {
			if (entry.getValue() == true) {
				System.out.println("You can see a portal to " + entry.getKey() + ".");
			} else {
				System.out.println("You can see an entrance to " + entry.getKey() + " in the distance.");
			}
			
		}
	}
	
	private static void getItemFromContainer(Room room, String containerName) throws ItemException, CharacterException {
		if (room.getRoomGold() != null && room.getRoomGold().isInRoom()) {
			if (room.getRoomGold().getContainerName().equalsIgnoreCase(containerName)) {
				Player player = room.getUniverse().getPlayer();
				player.setGold(player.getGold() + room.getRoomGold().getGold());
				room.getRoomGold().setIsInRoom(false);
				System.out.println("You found " + room.getRoomGold().getGold() + " gold!");
				return;
			} 
		}
		if (room.getRoomItems() != null) {
			for (RoomItem roomItem : room.getRoomItems()) {
				if (roomItem.getContainerName().equalsIgnoreCase(containerName) && roomItem.isInRoom()) {
					room.getUniverse().getPlayer().getInventory().addItem(roomItem.getItem());
					roomItem.setIsInRoom(false);
					System.out.println(
							"You found " + roomItem.getItem().getQuantity() + " " + roomItem.getItem().getName() + ".");
					return;
				}
			} 
		}
		System.out.println("There is nothing to check with that name.");
	}
	
}
