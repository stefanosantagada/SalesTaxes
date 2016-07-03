/**
 *
 */
package it.santagada.entities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Identify a receipt
 *
 *
 */
public class Receipt {

	DecimalFormat priceFormat = new DecimalFormat("#.00");
	private List<IItem> receiptDetails = new ArrayList<IItem>();
	private double salesTaxes;
	private double total;

	public List<IItem> getReceiptDetails() {
		return receiptDetails;
	}

	public void setReceiptDetails(List<IItem> receiptDetails) {
		this.receiptDetails = new ArrayList<IItem>();
		this.receiptDetails.addAll(receiptDetails);
	}

	public double getSalesTaxes() {
		return salesTaxes;
	}

	public void setSalesTaxes(double salesTaxes) {
		this.salesTaxes = salesTaxes;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		StringBuffer toBuffer = new StringBuffer();
		for (IItem receiptDetail : receiptDetails) {
			toBuffer.append(receiptDetail.getAmount() + " ");
			toBuffer.append(receiptDetail.isImported() ? "imported " : "");
			toBuffer.append(receiptDetail.getDescription() + ": ");
			toBuffer.append(priceFormat.format(receiptDetail.getIncludingTaxPrice()) + System.lineSeparator());
		}
		toBuffer.append("Sales Taxes: " + priceFormat.format(getSalesTaxes()) + System.lineSeparator());
		toBuffer.append("Total: " + priceFormat.format(getTotal()) + System.lineSeparator());
		return toBuffer.toString();
	}

}
