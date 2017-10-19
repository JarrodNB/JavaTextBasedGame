
package Models;

import GameExceptions.ArmorException;
import GameExceptions.ItemException;

public class Armor extends Item {

	private int defense;
	
	public Armor(int id, String name, String description, int buyValue, int quantity, int defense) throws ItemException, ArmorException {
		super(id, name, description, buyValue, quantity);
		setDefense(defense);
	}

	public int getDefence() {
		return defense;
	}

	private void setDefense(int defense) throws ArmorException {
		if (defense < 1) throw new ArmorException("defense must be atleast one" + getClass().getSimpleName());
		this.defense = defense;
	}
	
	@Override
	public String toString() {
		String result = super.toString();
		result += " Defense: " + this.defense;
		return result;
	}

}
