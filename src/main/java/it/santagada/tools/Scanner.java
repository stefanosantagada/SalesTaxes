/**
 *
 */
package it.santagada.tools;

import it.santagada.entities.GoodType;
import it.santagada.entities.IItem;
import it.santagada.entities.Item;

/**
 * Represents a cash scanner. Is the {@link Item} factory
 *
 */
public class Scanner {

	/**
	 * Build a {@link IItem}
	 *
	 * @param amount
	 * @param goodType
	 * @param description
	 * @param unitPrice
	 * @param imported
	 * @return
	 */
	public static IItem scan(int amount, GoodType goodType, String description, double unitPrice, boolean imported) {
		Item item = new Item();
		item.setAmount(amount);
		item.setGoodType(goodType);
		item.setDescription(description);
		item.setPrice(unitPrice);
		item.setImported(imported);
		return item;
	}
}
