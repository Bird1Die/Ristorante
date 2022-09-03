package employees;
import java.util.ArrayList;
import libs.*;


/**
 * {@code Cook} class is used to handle the events in the panel,
 * manipulate the {@code Command} and create the receipts
 */
public class Cook {
	
	private Command command;
	private Receipt receipts;

	/**
	 * Constructs a {@code Cook} object and initialize
	 * a {@code Command} and a {@code Receipt} object
	 */
	public Cook() {
		command = new Command();
		receipts = new Receipt();
	}
	
	/**
	 * Loads the ordination list from a text file
	 */
	public void loadCommand() {
		command.loadCommand();
		receipts.loadReceipts();
	}
	
	/**
	 * Returns a list of ordinations
	 * @return command
	 */
	public ArrayList<Table> getCommand(){
		return command.getCommand();
	}
	
	public void save() {
		command.saveCommand();
		receipts.saveReceipts();
	}
	
	/**
	 * Removes a table from the ordination list
	 * @param table
	 */
	public void removeTable(Table table) {
		command.removeTable(table);
	}
	
	/**
	 * adds the receipt to the receipts arraylist
	 * @param table
	 */
	public void addReceipt(Table table) {
		receipts.addReceipt(table);
	}
	
	/**
	 * Return the receipt
	 * @return Receipt
	 */
	public Receipt getReceipt() {
		return receipts;
	}
	
	/**
	 * Sets the {@code Receipt} to the one given as parameter
	 * @param receipt
	 */
	public void setReceipt(Receipt receipt) {
		receipts = receipt;
	}
}