package it.santagada.entities;

import it.santagada.tools.TaxManager;

import java.math.BigDecimal;

public class Item implements IItem {

	private GoodType goodType;
	private String description;
	private double unitPrice;
	private boolean imported;
	private int amount;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.santagada.beans.IItem#getGoodType()
	 */
	@Override
	public GoodType getGoodType() {
		return goodType;
	}

	public void setGoodType(GoodType goodType) {
		this.goodType = goodType;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.santagada.beans.IItem#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.santagada.beans.IItem#getPrice()
	 */
	@Override
	public double getUnitPrice() {
		return unitPrice;
	}

	public void setPrice(double price) {
		this.unitPrice = price;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.santagada.beans.IItem#isImported()
	 */
	@Override
	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.santagada.beans.IItem#getAmount()
	 */
	@Override
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.santagada.beans.IItem#getIncludingTaxPrice()
	 */
	@Override
	public double getIncludingTaxPrice() {
		return new BigDecimal((getUnitPrice() * amount) + getSalesTax()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.santagada.beans.IItem#getSalesTax()
	 */
	@Override
	public double getSalesTax() {
		return TaxManager.getInstance().calculateTaxes(this.goodType, this.unitPrice * this.amount, this.imported);
	}
}
