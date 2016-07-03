## PROBLEM: SALES TAXES

I started by defining the SalesTaxesTest class, in which I created three methods for the three Input/Output Expected results. Furthermore, in SalesTaxesTest class, I initialized application with tax rates and rules.

Then, step by step, I created:
	- entities: **Item** and **Receipt**
	- **CashDesk** for adding items, calculating subtotals and then generating Receipt
	- **CashScanner** that provides factory method to build an item
	- **TaxManager** that is a singleton that maintains tax rates and exemptions and provides calculating	 method for taxes amount.
