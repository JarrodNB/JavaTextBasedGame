
package Controllers;

import java.util.Scanner;

import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import GameExceptions.YouDontHaveThatException;
import Models.Item;
import Models.Player;
import Models.Shop;

public class Shopping {

	private static final String HELP = "Your possible commands are buy itemName, sell itemName, examine itenName, and open inventory.";
	private static Shop shop = new Shop();
	
	public static void shop(Player player) {
		System.out.println("Weclome to my shop! Take a look at my wares");
		System.out.println(shop.displayStock());
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Would you like to buy, sell, examine an item, or leave?");
			String input = scanner.nextLine().toLowerCase();
			if (input.startsWith("leave")) {
				scanner.close();
				return;
			}
			try {
				shopInput(input, player);
			} catch (CharacterException | ItemException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static void shopInput(String input, Player player) throws CharacterException, ItemException {
		String[] inputArray = input.split(" ");

		if (inputArray[0].equalsIgnoreCase("buy")) {
			if (inputArray.length == 3) {
				String itemName = inputArray[1] + " " + inputArray[2];
				Item item = shop.getStockItem(itemName);
				buy(player, item);
			} else if (inputArray.length == 4){
				String itemName = inputArray[1] + " " + inputArray[2] + " " + inputArray[3];
				Item item = shop.getStockItem(itemName);
				buy(player, item);
			} else {
				String itemName = inputArray[1];
				Item item = shop.getStockItem(itemName);
				buy(player, item);
			}
		}
		
		else if (inputArray[0].equals("sell")) {
			if (inputArray.length == 3) {
				String itemName = inputArray[1] + " " + inputArray[2];
				Item item = shop.getStockItem(itemName);
				sell(player, item);
			} else if (inputArray.length == 4){
				String itemName = inputArray[1] + " " + inputArray[2] + " " + inputArray[3];
				Item item = shop.getStockItem(itemName);
				sell(player, item);
			} else {
				String itemName = inputArray[1];
				Item item = shop.getStockItem(itemName);
				sell(player, item);
			}
		}
		
		else if (inputArray[0].equals("examine")) {
			if (inputArray.length == 2) {
				String itemName = inputArray[1] + " " + inputArray[2];
				System.out.println(shop.examineItem(itemName));
			} else if (inputArray.length == 3){
				String itemName = inputArray[1] + " " + inputArray[2] + " " + inputArray[3];
				System.out.println(shop.examineItem(itemName));
			} else {
				String itemName = inputArray[1];
				System.out.println(shop.examineItem(itemName));
			}
		}
		
		else if (inputArray[0].equals("open")) {
			OpenInventory.openInventory(player);
		}
		
		else if (inputArray[0].equals("help")) {
			System.out.println(HELP);
		}
		
		else {
			System.out.println(GameEngine.UNRECOGNIZED_COMMAND);
		}

	}
	
	private static void buy(Player player, Item item) throws CharacterException, ItemException {
		if (player.getGold() < item.getBuyValue()) {
			System.out.println("You can not afford that.");
			return;
		} else {
			player.setGold(player.getGold() - item.getBuyValue());
			player.getInventory().addItem(item);
			System.out.println("You have bought " + item.getName() + " for " + item.getBuyValue() + " gold.");
		}
	}
	
	private static void sell(Player player, Item item) throws CharacterException {
		try {
			player.getInventory().removeItem(item, 1);
		} catch (YouDontHaveThatException | ItemException e) {
			System.out.println("It appears that you dont have that item!");
			return;
		}
		player.setGold(player.getGold() + item.getSellValue());
	}
	
}
