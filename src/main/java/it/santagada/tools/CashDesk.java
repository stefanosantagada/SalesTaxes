/**
 *
 */
package it.santagada.tools;

import it.santagada.entities.GoodType;
import it.santagada.entities.IItem;
import it.santagada.entities.Receipt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a CashDesk.
 *
 */
public class CashDesk {

	private List<IItem> cart = new ArrayList<IItem>();
	private Double subTotal = 0.0;
	private Double subSalesTaxes = 0.0;

	/**
	 * Add items to shopping cart
	 *
	 * @param amount
	 * @param goodType
	 * @param description
	 * @param unitPrice
	 * @param imported
	 */
	public void addItems(int amount, GoodType goodType, String description, double unitPrice, boolean imported) {
		IItem item = Scanner.scan(amount, goodType, description, unitPrice, imported);
		subSalesTaxes += item.getSalesTax();
		subTotal += item.getIncludingTaxPrice();
		cart.add(item);
	}

	/**
	 * Generate a {@link Receipt} which contains all items added to the shopping
	 * cart
	 *
	 * @return {@link Receipt}
	 */
	public Receipt printReceipt() {
		Receipt receipt = new Receipt();
		receipt.setReceiptDetails(cart);
		receipt.setSalesTaxes(new BigDecimal(subSalesTaxes).setScale(2, RoundingMode.HALF_UP).doubleValue());
		receipt.setTotal(new BigDecimal(subTotal).setScale(2, RoundingMode.HALF_UP).doubleValue());
		return receipt;
	}

	/**
	 * reset CashDesk to start a new payment
	 */
	public void reset() {
		this.cart = new ArrayList<IItem>();
		this.subTotal = 0.0;
		this.subSalesTaxes = 0.0;
	}

}
