
package Controllers;

import java.util.Scanner;

import GameExceptions.ItemException;
import GameExceptions.PlayerIsDeadException;
import GameExceptions.YouDontHaveThatException;
import Models.Armor;
import Models.Item;
import Models.Player;
import Models.Weapon;

public class OpenInventory {
// add character stats gold etc
	// add redisplay inv
	// Make exception string prettier
	private static final String HELP = "your possible commands are use itemName, examine itemName, equip itemName, unequip weapon/armor, or exit.";

	public static void openInventory(Player player) {
		System.out.println(player.getInventory().toString());
		System.out.println(HELP);
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String input = scanner.nextLine();
			if (input.equals("exit")) {
				//scanner.close();
				return;
			}
			try {
				inventoryInput(player, input);
			} catch (YouDontHaveThatException | ItemException | PlayerIsDeadException e) {
				System.out.println(e.getMessage());
			}
		}

	}
	
	private static void inventoryInput(Player player, String input) throws YouDontHaveThatException, PlayerIsDeadException, ItemException {
		
		String[] inputArray = input.split(" ");

		if (inputArray[0].equalsIgnoreCase("use")) {
			if (inputArray.length == 3) {
				String itemName = inputArray[1] + " " + inputArray[2];
				UseItem.useItem(player, itemName);
			} else if (inputArray.length == 4){
				String itemName = inputArray[1] + " " + inputArray[2] + " " + inputArray[3];
				UseItem.useItem(player, itemName);
			} else {
				String itemName = inputArray[1];
				UseItem.useItem(player, itemName);
			}
		}
		
		else if (inputArray[0].equalsIgnoreCase("equip")) {
			if (inputArray.length == 3) {
				String itemName = inputArray[1] + " " + inputArray[2];
				equip(player, itemName);
			} else if (inputArray.length == 4){
				String itemName = inputArray[1] + " " + inputArray[2] + " " + inputArray[3];
				equip(player, itemName);
			} else {
				String itemName = inputArray[1];
				equip(player, itemName);
			}
		}
		
		else if (inputArray[0].equalsIgnoreCase("unequip")) {
			if (inputArray[1].equals("weapon")) {
				player.unequipWeapon();
				System.out.println("Your weapon has been unequipped.");
			}
			else if (inputArray[1].equals("armor")) {
				player.unequipArmor();
				System.out.println("Your armor has been unequipped");
			}
		}
		
		else if (inputArray[0].equalsIgnoreCase("examine")) {
			if (inputArray.length == 3) {
				String itemName = inputArray[1] + " " + inputArray[2];
				System.out.println(player.getInventory().getItemNoRemoval(itemName).getDescription());
			} else if (inputArray.length == 4){
				String itemName = inputArray[1] + " " + inputArray[2] + " " + inputArray[3];
				System.out.println(player.getInventory().getItemNoRemoval(itemName).getDescription());
			} else {
				String itemName = inputArray[1];
				System.out.println(player.getInventory().getItemNoRemoval(itemName).getDescription());
			}
		}
		
		else if (inputArray[0].equalsIgnoreCase("help")) {
			System.out.println(HELP);
		}
		
		else {
			System.out.println(GameEngine.UNRECOGNIZED_COMMAND);
		}
	}
	
	private static void equip(Player player, String itemName) throws YouDontHaveThatException, ItemException {
		System.out.println(itemName);
		if (player.getInventory().hasItem(itemName)) {
			Item item = player.getInventory().getItemNoRemoval(itemName);
			if (item instanceof Weapon) {
				player.equipWeapon(itemName);
			} else if (item instanceof Armor) {
				player.equipArmor(itemName);
			} else {
				System.out.println("That can not be equipped!");
			} 
		}
		else {
			System.out.println("Thats not an item you have.");
		}
	}
	
}
