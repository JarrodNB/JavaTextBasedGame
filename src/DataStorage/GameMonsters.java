
package DataStorage;

import Models.Monster;

public class GameMonsters {

	// add inventories as variable
	public static Monster getSpacePirate(int goldAmount) {
		try {
			return new Monster("Space Pirate", "Infamous space pirates known for causing havoc system wide. They are despised by all colonies. They are armored with light weaponry and armor.", 2, 10, goldAmount, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Monster getSpacePirateCaptian(int goldAmount) {
		try {
			return new Monster("Space Pirate Captian", "Commander of all space pirates in the solar System. He is heavily armored and has powerful weaponry.", 4, 50, goldAmount, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Monster getDefenseDrone(int goldAmount) {
		try {
			return new Monster("Defense Drone", "These defense drones were created to defend research stations from space pirates, however an evil scientist has hacked them and turned them against the player! They attack with a deadly laser but are somewhat weak.", 1, 5, goldAmount, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Monster getMadScientist(int goldAmount) {
		try {
			return new Monster("Mad Scientist", "This evil scientist has been hired by the pace pirates to steal technology from research stations. He has hacked defense drones to do his bidding", 3, 30, goldAmount, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Monster getKraken(int goldAmount) {
		try {
			return new Monster("Kraken", "Huge green sea creature that resides in an ocean moon of Neptune. Frequently terrorizes colonists with its spiky tentacles", 3, 40, goldAmount, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Monster getFireGiant(int goldAmount) {
		try {
			return new Monster("Fire Giant", "Fire resistant monsters native to volcanic moons.", 3, 35, goldAmount, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Monster getWornoutRobot(int goldAmount) {
		try {
			return new Monster("Worn-out Robot", "Old robot that you find on the surface of earth. It’s not very strong.", 1, 5, goldAmount, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Monster getRuffians(int goldAmount) {
		try {
			return new Monster("Ruffians", "Thieves that disrupt and steal from colonists", 1, 6, goldAmount, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
}
