package it.santagada.entities;

public interface IItem {

	/**
	 * @return the good type of the items
	 */
	public GoodType getGoodType();

	/**
	 * @return item description
	 */
	public String getDescription();

	/**
	 * @return the unit price
	 */
	public double getUnitPrice();

	/**
	 * @return if item is imported
	 */
	public boolean isImported();

	/**
	 * @return the items amount
	 */
	public int getAmount();

	/**
	 * @return amount of items sales taxes
	 */
	public double getSalesTax();

	/**
	 * @return total price including sales taxes
	 */
	public double getIncludingTaxPrice();

}