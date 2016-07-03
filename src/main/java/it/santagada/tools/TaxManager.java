/**
 *
 */
package it.santagada.tools;

import it.santagada.entities.GoodType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton manager containing tax rates and calculates amount of sales tax
 *
 */
public class TaxManager {

	private static TaxManager instance = new TaxManager();

	private double importDutyTax;
	private Map<GoodType, Double> basicTaxes = new HashMap<GoodType, Double>();

	private TaxManager() {
	}

	public static TaxManager getInstance() {
		return instance;
	}

	public void setImportDutyTax(double importDutyTax) {
		this.importDutyTax = importDutyTax;
	}

	public void addBasicTaxes(GoodType goodType, double taxRate) {
		this.basicTaxes.put(goodType, taxRate);
	}

	/**
	 * calculates tax amount considering tax rates rules and if goods are
	 * imported
	 *
	 * @param goodType
	 * @param price
	 * @param imported
	 * @return tax amount
	 */
	public double calculateTaxes(GoodType goodType, double price, boolean imported) {
		double taxes = 0.0;
		if (imported) {
			taxes += price * importDutyTax;
		}
		final Double basicTaxToApply = this.basicTaxes.get(goodType);
		if ((basicTaxToApply != null) && (basicTaxToApply > 0.0)) {
			taxes += price * basicTaxToApply;
		}
		return roundTo005(taxes);
	}

	private double roundTo005(double value) {
		return new BigDecimal(Math.ceil(value * 20) / 20).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}
