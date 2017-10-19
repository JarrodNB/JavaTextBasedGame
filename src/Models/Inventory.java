
package Models;

import java.util.ArrayList;
import java.util.List;

import GameExceptions.ItemException;
import GameExceptions.YouDontHaveThatException;

public class Inventory {

	private List<Item> inventory;
	
	public Inventory() {
		inventory = new ArrayList<Item>();
	}
	
	private List<Item> getInventory(){
		return this.inventory;
	}
	
	public boolean hasItem(String itemName) {
		for (Item inventoryItem : inventory) {
			if (inventoryItem.getName().equals(itemName)) {
				return true;
			}
		}
		return false;
	}
	
	public void addItem(Item item) throws ItemException  {
		for (Item inventoryItem : inventory) {
			if (item.getName().equals(inventoryItem.getName())) {
				inventoryItem.setQuantity(inventoryItem.getQuantity() + item.getQuantity());
				return;
			}
		}
		inventory.add(item);
	}
	
	public void removeItem(Item item, int quantity) throws YouDontHaveThatException, ItemException {
		for (Item inventoryItem : inventory) {
			if (inventoryItem.getName().equals(item.getName())) {
				if (inventoryItem.getQuantity() - quantity >= 0) {
					inventoryItem.setQuantity(inventoryItem.getQuantity() - quantity);
					return;
				} else {
					throw new YouDontHaveThatException("can not remove more items then inventory has" + getClass().getSimpleName());
				}
			}
		}
		throw new YouDontHaveThatException("player does not have item" + getClass().getSimpleName());
	}
	
	public Item getItem(String name) throws YouDontHaveThatException, ItemException {
		for (Item item : inventory) {
			if (item.getName().equals(name)) {
				Item itemResult = item;
				removeItem(item, 1);
				return itemResult;
			}
		}
		throw new YouDontHaveThatException("player can not get item he doesnt have" + getClass().getSimpleName());
	}
	
	public Item getItemNoRemoval(String itemName) throws YouDontHaveThatException {
		for (Item item : inventory) {
			if (item.getName().equals(itemName)) {
				Item itemResult = item;
				return itemResult;
			}
		}
		throw new YouDontHaveThatException("player can not get item he doesnt have" + getClass().getSimpleName());
	}
	
	public void addInventory(Inventory addInventory) throws ItemException  {
		for (Item item : addInventory.getInventory()) {
			addItem(item);
		}
	}
	
	@Override
	public String toString() {
		String result = "";
		for (Item item : inventory) {
			result += item.toString() + ", ";
		}
		return result.trim().substring(0, result.length()-1);
	}
}
