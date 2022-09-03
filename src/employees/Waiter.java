package employees;

import java.util.ArrayList;

import libs.*;

/**
 * {@code Waiter} class is used to handle the events 
 * in the panel and creating the orders
 *
 */
public class Waiter {
	
	private Menu menu;
	private Order orders;
	private Order completedOrders;
	private Receipt receipt;
	
	/**
	 * Constructs a {@code Waiter} object and initialize a
	 * {@code Menu}, {@code Receipt} and two {@code Order} objects
	 */
	public Waiter() {
		menu = new Menu();
		orders = new Order("database/Orders.txt");
		completedOrders = new Order("database/CompletedOrders.txt");
		receipt = new Receipt();
	}
	/**
	 * Adds the dish to the table
	 * @param id
	 * @param quantity
	 * @param dish
	 */
	public void addDish(int id, int quantity, Dish dish) {
		DishOrdered a = new DishOrdered(dish.getNome(), dish.getPrice(), quantity);
		orders.addDish(a, id);
	}
	
	/**
	 * Remove the dish from the table
	 * @param dish
	 * @param id
	 */
	public void removeDish(DishOrdered dish, int id) {
		orders.removeDish(dish, id);
	}
	
	/**
	 * Adds a table keeping count of the one already existing
	 */
	public void addTable() {
		orders.addTable(completedOrders, receipt);
		orders.saveOrders();
	}
	
	/**
	 * Closes a {@code Table} and if there are some {@code Dish} inside
	 * the {@code Table} is added to the {@code Order}
	 * @param id
	 */
	public void closeOrder(int id) {
		for (Table a: orders.getOrders()) {
			if (a.getId() == id) {
				if(a.getTable().size() > 0) {
					completedOrders.addTable(a);
				}
			}
		}
		orders.removeTable(id);
		completedOrders.saveOrders();
	}
	
	/**
	 * Sets the {@code Order} object to the one given as parameter
	 * @param a
	 */
	public void setOrdini(Order a) {
		orders = a;
	}
	
	/**
	 * Return the list of {@code Table} as an ArrayList
	 * @return {@code ArrayList<Table>}
	 */
	public ArrayList<Table>  getOrdini() {
		return orders.getOrders();
	}
	
	/**
	 * Return the {@code Order} object 
	 * @return {@code Order}
	 */
	public Order getOrder() {
		return orders;
	}
	
	/**
	 * Sets the {@code Menu} object to the one given as parameter
	 * @param {{@code Menu}
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	/**
	 * Returns the {@code Menu} object
	 * @return {@code Menu}
	 */
	public Menu getMenu(){
		return menu;
	}
	
	/**
	 * Sets the {@code Receipt} to the one given as parameter
	 * @param {{@code Receipt}
	 */
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	
	/**
	 * Calls for the {@code Order} objects to save their data
	 */
	public void saveOrders() {
		orders.saveOrders();
		completedOrders.saveOrders();
	}
	
	/**
	 * Calls for the {@code Order} objects to load their data
	 */
	public void loadOrders() {
		orders.loadOrders();
		completedOrders.loadOrders();
	}
}
