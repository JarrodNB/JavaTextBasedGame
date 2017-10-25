
package DataStorage;

import Models.Inventory;
import Models.Item;
import Models.Monster;

public class GameMonsters {

	public static Monster getSpacePirate(Item item, int goldAmount) {
		Inventory inventory = null;
		try {
			if (item != null) {
				inventory = new Inventory();
				inventory.addItem(item);
			}
			return new Monster("Space Pirate", "Infamous space pirates known for causing havoc system wide. They are despised by all colonies. They are armored with light weaponry and armor.", 2, 10, goldAmount, inventory);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Monster getSpacePirateCaptian(Item item, int goldAmount) {
		Inventory inventory = null;
		try {
			if (item != null) {
				inventory = new Inventory();
				inventory.addItem(item);
			}
			return new Monster("Space Pirate Captian", "Commander of all space pirates in the solar System. He is heavily armored and has powerful weaponry.", 4, 50, goldAmount, inventory);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Monster getDefenseDrone(Item item, int goldAmount) {
		Inventory inventory = null;
		try {
			if (item != null) {
				inventory = new Inventory();
				inventory.addItem(item);
			}
			return new Monster("Defense Drone", "These defense drones were created to defend research stations from space pirates, however an evil scientist has hacked them and turned them against the player! They attack with a deadly laser but are somewhat weak.", 2, 7, goldAmount, inventory);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Monster getMadScientist(Item item, int goldAmount) {
		Inventory inventory = null;
		try {
			if (item != null) {
				inventory = new Inventory();
				inventory.addItem(item);
			}
			return new Monster("Mad Scientist", "This evil scientist has been hired by the pace pirates to steal technology from research stations. He has hacked defense drones to do his bidding", 3, 30, goldAmount, inventory);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Monster getKraken(Item item, int goldAmount) {
		Inventory inventory = null;
		try {
			if (item != null) {
				inventory = new Inventory();
				inventory.addItem(item);
			}
			return new Monster("Kraken", "Huge green sea creature that resides in an ocean moon of Neptune. Frequently terrorizes colonists with its spiky tentacles", 3, 40, goldAmount, inventory);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Monster getFireGiant(Item item, int goldAmount) {
		Inventory inventory = null;
		try {
			if (item != null) {
				inventory = new Inventory();
				inventory.addItem(item);
			}
			return new Monster("Fire Giant", "Fire resistant monsters native to volcanic moons.", 3, 35, goldAmount, inventory);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Monster getWornoutRobot(Item item, int goldAmount) {
		Inventory inventory = null;
		try {
			if (item != null) {
				inventory = new Inventory();
				inventory.addItem(item);
			}
			return new Monster("Worn-out Robot", "Old robot that you find on the surface of earth. It’s not very strong.", 2, 5, goldAmount, inventory);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Monster getRuffians(Item item, int goldAmount) {
		Inventory inventory = null;
		try {
			if (item != null) {
				inventory = new Inventory();
				inventory.addItem(item);
			}
			return new Monster("Ruffians", "Thieves that disrupt and steal from colonists", 2, 6, goldAmount, inventory);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
}
