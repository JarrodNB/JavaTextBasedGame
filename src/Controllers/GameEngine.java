
package Controllers;

import java.util.Scanner;

import DataStorage.GameRooms;
import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import GameExceptions.PlayerIsDeadException;
import Models.Universe;

public class GameEngine {

	public static void main(String[] args) throws ItemException, CharacterException {
		GameEngine engine = new GameEngine();
		engine.start();
	}
	// fix strings
	// remove sysout from model
	// save, load
	public static final String UNRECOGNIZED_COMMAND = "Command is not recognized.";
	
	public void start() throws ItemException, CharacterException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Welcome. New game or load game?");
			
			String input = scanner.nextLine();
			Universe universe = null;
			if (input.equalsIgnoreCase("new game")) {
				universe = NewGame.newGame();
			} else if (input.equalsIgnoreCase("load game")) {
				universe = LoadGame.load();
			} else {
				System.out.println(UNRECOGNIZED_COMMAND);
				//scanner.close();
				continue;
			}
			try {
				//scanner.close();
				RoomHandler.enter(universe.getCurrentRoom());
			} catch (PlayerIsDeadException dead) {
				universe.getPlayer().setGold(universe.getPlayer().getGold() / 2);
				universe.setCurrentRoom(universe.getRoom("Home Base"));
				System.out.println("You have died! You lost half your gold and respawned at Home Base. Your game has been saved.");
				SaveGame.save(universe, universe.getCurrentRoom());
				start();
			} 
		}
	}
}

