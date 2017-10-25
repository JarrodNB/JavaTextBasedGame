
package Models;

import GameExceptions.CharacterException;

public class Monster extends Character {

	// what to put here?
	private String description;
	
	public Monster(String name,String description, int attack, int maxHealth, int gold, Inventory inventory)
			throws CharacterException {
		super(name, 0, attack, maxHealth, gold, inventory);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
