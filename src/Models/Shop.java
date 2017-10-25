
package Models;

import java.util.Map;

import DataStorage.GameItems;

public class Shop {

	// add ignore case to all equals
	// check models for interaction with controller
	
	private Map<String, Item> stock;
	
	public Shop() {
		stock = GameItems.getShopInventory();
	}

	public String displayStock() {
		String result = "";
		for (Map.Entry<String, Item> entry : stock.entrySet()) {
			Item item = entry.getValue();
			result += item.getName() + " " + item.getBuyValue() + "\n";
		}
		return result;
	}
	

	public String examineItem(String name) {
		String description;
		try {
			description = stock.get(name).getDescription();
		} catch (NullPointerException e) {
			description = null;
		}
		if (description == null) {
			return "I don't carry that item.";
		} 
		return description;
	}
	
	public Item getStockItem(String item) {
		return stock.get(item);
	}
	
}
