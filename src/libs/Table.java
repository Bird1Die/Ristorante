package libs;

import java.util.ArrayList;

/**
 * A {@code Table} class has a list of {@code DishOrdered} and 
 * is represented by a unique id
 */
public class Table {

	private ArrayList<DishOrdered> table;
	private int id;
	
	/**
	 * Constructs a {@code Table} with a given id
	 * and initialize a list of {@code DishOrdered}
	 * @param id
	 */
	public Table(int id){
		this.id = id;
		table = new ArrayList<DishOrdered>();
	}
	
	/**
	 * Adds a {@code DishOrdered} to the list
	 * @param dish
	 */
	public void addDish(DishOrdered dish) {
		table.add(dish);
	}
	
	/**
	 * Removes a specific {@code DishOrdered} from the list
	 * @param dish
	 */
	public void removeDish(Dish dish) {
		table.remove(dish);
	}
	
	/**
	 * Return the list of {@code DishOrdered}
	 * @return {@code ArrayList<DishOrdered>}
	 */
	public ArrayList<DishOrdered> getTable(){
		return table;
	}
	
	/**
	 * Sets the list to the one given
	 * @param {@code tableList}
	 */
	public void setTable(ArrayList<DishOrdered> tableList) {
		table = tableList;
	}
	
	/**
	 * Returns the id of the table
	 * @return {@code id}
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id of the table to the one provided
	 * @param {@code id}
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Returns a {@code String} used to print the content of this {@code Table}
	 * on a text file
	 * @return toPrint
	 */
	public String toPrint() {
		String toPrint = String.format("%d:\n", id);
		for(DishOrdered b: table) {
			toPrint += String.format("%s\n", b.toSave());
		}
		return toPrint;
	}
	
	/**
	 * Returns the representation of the table with his id
	 */
	public String toString() {
		return String.format("Tavolo - %s", id);
	}
}
