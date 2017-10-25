
package Controllers;

import java.io.File;
import java.util.Scanner;

import GameExceptions.CharacterException;
import Models.Inventory;
import Models.Player;
import Models.Universe;

public class NewGame {

	public static Universe newGame() {
		new File("C:\\Voyager\\").mkdir();
		System.out.println("Select a name for your player.");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		while(!validName(name)) {
			System.out.println("Your name may only contain letters and may not be blank. Enter another name.");
			name = scanner.nextLine();
		}
		Inventory inventory = new Inventory();
		try {
			Player player = new Player(name, 0, 1, 30, 50, inventory);
			//scanner.close();
			return new Universe(player);
		} catch (CharacterException e) {
			e.printStackTrace();
			//scanner.close();
		}
		return null;
	}
	
	private static boolean validName(String name) {
		if (name.equals("")) {
			return false;
		}
		else if (name.matches("[a-zA-Z]+")) {
			return true;
		}
		else {
			return false;
		}
	}
}
