
package Controllers;

import java.util.Scanner;

import GameExceptions.CharacterException;
import Models.Inventory;
import Models.Player;
import Models.Universe;

public class NewGame {
	// select name and difficulty and create new player and universe
	public static Universe newGame() {
		System.out.println("Select a name for your player.");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		Inventory inventory = new Inventory();
		try {
			Player player = new Player(name, 0, 1, 100, 50, inventory);
			//scanner.close();
			return new Universe(player);
		} catch (CharacterException e) {
			e.printStackTrace();
			//scanner.close();
		}
		return null;
	}
}
