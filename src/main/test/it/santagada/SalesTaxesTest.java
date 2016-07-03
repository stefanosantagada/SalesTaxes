/**
 *
 */
package it.santagada;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.santagada.entities.GoodType;
import it.santagada.entities.IItem;
import it.santagada.entities.Receipt;
import it.santagada.tools.CashDesk;
import it.santagada.tools.TaxManager;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class
 *
 */
public class SalesTaxesTest {

	private static CashDesk cashDesk;

	/**
	 * Initialize tax rates and applications
	 *
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TaxManager.getInstance().setImportDutyTax(0.05);
		TaxManager.getInstance().addBasicTaxes(GoodType.GENERIC, 0.10);
		cashDesk = new CashDesk();
	}

	/**
	 * Reset {@link CashDesk} for each test method
	 */
	@Before
	public void setUpBeforeMethod() {
		cashDesk.reset();
	}

	@Test
	public void testInput1() {
		cashDesk.addItems(1, GoodType.BOOKS, "book", 12.49, false);
		cashDesk.addItems(1, GoodType.GENERIC, "music CD", 14.99, false);
		cashDesk.addItems(1, GoodType.FOOD, "chocolate bar", 0.85, false);

		Receipt receipt = cashDesk.printReceipt();
		List<IItem> receiptDetails = receipt.getReceiptDetails();
		System.out.println("Output 1:");
		System.out.println(receipt);

		assertTrue(receiptDetails.get(0).getAmount() == 1);
		assertEquals("book", receiptDetails.get(0).getDescription());
		assertTrue(receiptDetails.get(0).getIncludingTaxPrice() == 12.49);
		assertFalse(receiptDetails.get(0).isImported());

		assertTrue(receiptDetails.get(1).getAmount() == 1);
		assertEquals("music CD", receiptDetails.get(1).getDescription());
		assertTrue(receiptDetails.get(1).getIncludingTaxPrice() == 16.49);
		assertFalse(receiptDetails.get(1).isImported());

		assertTrue(receiptDetails.get(2).getAmount() == 1);
		assertEquals("chocolate bar", receiptDetails.get(2).getDescription());
		assertTrue(receiptDetails.get(2).getIncludingTaxPrice() == 0.85);
		assertFalse(receiptDetails.get(2).isImported());

		assertTrue(receipt.getSalesTaxes() == 1.50);
		assertTrue(receipt.getTotal() == 29.83);

	}

	@Test
	public void testInput2() {
		cashDesk.addItems(1, GoodType.FOOD, "box of chocolate", 10.00, true);
		cashDesk.addItems(1, GoodType.GENERIC, "bottle of perfume", 47.50, true);

		Receipt receipt = cashDesk.printReceipt();
		List<IItem> receiptDetails = receipt.getReceiptDetails();
		System.out.println("Output 2:");
		System.out.println(receipt);

		assertTrue(receiptDetails.get(0).getAmount() == 1);
		assertEquals("box of chocolate", receiptDetails.get(0).getDescription());
		assertTrue(receiptDetails.get(0).getIncludingTaxPrice() == 10.50);
		assertTrue(receiptDetails.get(0).isImported());

		assertTrue(receiptDetails.get(1).getAmount() == 1);
		assertEquals("bottle of perfume", receiptDetails.get(1).getDescription());
		assertTrue(receiptDetails.get(1).getIncludingTaxPrice() == 54.65);
		assertTrue(receiptDetails.get(1).isImported());

		assertTrue(receipt.getSalesTaxes() == 7.65);
		assertTrue(receipt.getTotal() == 65.15);

	}

	@Test
	public void testInput3() {
		cashDesk.addItems(1, GoodType.GENERIC, "bottle of perfume", 27.99, true);
		cashDesk.addItems(1, GoodType.GENERIC, "bottle of perfume", 18.99, false);
		cashDesk.addItems(1, GoodType.MEDICAL, "packet of headache pills", 9.75, false);
		cashDesk.addItems(1, GoodType.FOOD, "box of chocolate", 11.25, true);

		Receipt receipt = cashDesk.printReceipt();
		List<IItem> receiptDetails = receipt.getReceiptDetails();
		System.out.println("Output 3:");
		System.out.println(receipt);

		assertTrue(receiptDetails.get(0).getAmount() == 1);
		assertEquals("bottle of perfume", receiptDetails.get(0).getDescription());
		assertTrue(receiptDetails.get(0).getIncludingTaxPrice() == 32.19);
		assertTrue(receiptDetails.get(0).isImported());

		assertTrue(receiptDetails.get(1).getAmount() == 1);
		assertEquals("bottle of perfume", receiptDetails.get(1).getDescription());
		assertTrue(receiptDetails.get(1).getIncludingTaxPrice() == 20.89);
		assertFalse(receiptDetails.get(1).isImported());

		assertTrue(receiptDetails.get(2).getAmount() == 1);
		assertEquals("packet of headache pills", receiptDetails.get(2).getDescription());
		assertTrue(receiptDetails.get(2).getIncludingTaxPrice() == 9.75);
		assertFalse(receiptDetails.get(2).isImported());

		assertTrue(receiptDetails.get(3).getAmount() == 1);
		assertEquals("box of chocolate", receiptDetails.get(3).getDescription());
		assertTrue(receiptDetails.get(3).getIncludingTaxPrice() == 11.85);
		assertTrue(receiptDetails.get(3).isImported());

		assertTrue(receipt.getSalesTaxes() == 6.70);
		assertTrue(receipt.getTotal() == 74.68);

	}

}
