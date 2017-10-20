
package Models;

import DataStorage.GameItems;
import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import GameExceptions.YouDontHaveThatException;

public class Player extends Character {

	private Weapon currentWeapon;
	private Armor currentArmor;
	
	public Player(String name, int defense, int attack, int maxHealth, int gold, Inventory inventory) throws CharacterException {
		super(name, defense, attack, maxHealth, gold, inventory);
		this.currentWeapon = GameItems.getPlasmaKnife(1);
		this.currentArmor = GameItems.getPilotSuit(1);
	}
	
	public int getCalcAttack() {
		if (this.currentWeapon == null) return getBaseAttack();
		return this.getBaseAttack() + this.currentWeapon.getAttack();
	}

	public int getCalcDefense() {
		if (this.currentArmor == null) return getBaseDefense();
		return this.getBaseDefense() + this.currentArmor.getDefence();
	}
	
	public void equipWeapon(String weaponName) throws ItemException, YouDontHaveThatException {
		unequipWeapon();
		Weapon weapon = (Weapon) this.getInventory().getItem(weaponName);
		this.currentWeapon = weapon;
	}
	
	public void unequipWeapon() throws ItemException  {
		Weapon weapon  = currentWeapon;
		this.currentWeapon = null;
		this.getInventory().addItem(weapon);
	}
	
	public void equipArmor(String armorName) throws ItemException, YouDontHaveThatException  {
		unequipArmor();
		Armor armor = (Armor) this.getInventory().getItem(armorName);
		this.currentArmor = armor;
		
	}
	
	public void unequipArmor() throws ItemException {
		Armor armor = currentArmor;
		this.currentArmor = null;
		this.getInventory().addItem(armor);
	}
	
}
