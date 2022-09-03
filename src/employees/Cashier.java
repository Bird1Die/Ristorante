package employees;

import libs.*;

/**
 * Cashier class is used to handle the events in the panel
 * and manipulate the Receipts
 */
public class Cashier {

	private Receipt receipts;
	
	/**
	 * Constructs a new {@code Cashier} and 
	 * load the receipts
	 */
	public Cashier() {
		receipts = new Receipt();
		receipts.loadReceipts();
	}
	
	/**
	 * Returns the {@code Receipt} object
	 * @return {@code Receipt}
	 */
	public Receipt getReceipt() {
		return receipts;
	}
	
	/**
	 * Calls the {@code Receipt} load
	 */
	public void loadReceipts() {
		receipts.loadReceipts();
	}
	
	/**
	 * Calls the {@code Receipt} save
	 */
	public void saveReceipts() {
		receipts.saveReceipts();
	}
	
	/**
	 * Calls for the {@code Receipt} to print a specific {@code Table} 
	 * with the missing param
	 * @param table
	 * @param total
	 * @param rest
	 * @param cash
	 */
	public void printReceipt(Table table, String total, String rest, String cash) {
		receipts.makeReceipt(table, total, rest, cash);
	}
	
	/**
	 * Sets the {@code Receipt} to the one passed as param
	 * @param a
	 */
	public void setReceipt(Receipt receipt) {
		receipts = receipt;
	}
}
