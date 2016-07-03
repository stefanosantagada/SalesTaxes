package it.santagada;

import static org.junit.Assert.assertTrue;
import it.santagada.entities.GoodType;
import it.santagada.tools.TaxManager;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for taxes calculator
 *
 */
public class TaxManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TaxManager.getInstance().setImportDutyTax(0.05);
		TaxManager.getInstance().addBasicTaxes(GoodType.GENERIC, 0.10);
	}

	@Test
	public void testCalculateTaxes() {
		assertTrue(TaxManager.getInstance().calculateTaxes(GoodType.BOOKS, 12.49, false) == 0.0);
		assertTrue(TaxManager.getInstance().calculateTaxes(GoodType.GENERIC, 14.99, false) == 1.50);
		assertTrue(TaxManager.getInstance().calculateTaxes(GoodType.FOOD, 0.85, false) == 0.0);

		assertTrue(TaxManager.getInstance().calculateTaxes(GoodType.FOOD, 10.00, true) == 0.50);
		assertTrue(TaxManager.getInstance().calculateTaxes(GoodType.GENERIC, 47.50, true) == 7.15);

		assertTrue(TaxManager.getInstance().calculateTaxes(GoodType.GENERIC, 27.99, true) == 4.20);
		assertTrue(TaxManager.getInstance().calculateTaxes(GoodType.GENERIC, 18.99, false) == 1.9);
		assertTrue(TaxManager.getInstance().calculateTaxes(GoodType.MEDICAL, 9.75, false) == 0.0);
		assertTrue(TaxManager.getInstance().calculateTaxes(GoodType.FOOD, 11.25, true) == 0.60);

	}

}
