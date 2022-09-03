package libs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class {@code Order} is used to manage a list 
 * of Table.
 * It creates, loads and saves the list.
 */
public class Order {
	
	private ArrayList<Table> orders;
	private File fileOrders;
	
	/**
	 * Constructs a {@code Menu} and loads a menu
	 * from a text file specified by the given path
	 * @param path
	 */
	public Order(String path) {
		orders = new ArrayList<Table>();
		fileOrders = new File(path);
	}
	
	/**
	 * Adds a {@code Table} to the list giving it an id
	 * considering all the existing one
	 * @param completedOrders
	 * @param receipts
	 */
	public void addTable(Order completedOrders, Receipt receipts) {
		int a = 0;
		boolean validId = false;
		while(!validId) {
			validId = true;
			for(Table b: orders) {
				if(b.getId() == a) {
					validId = false;
				}
			}
			for(Table b: completedOrders.getOrders()) {
				if(b.getId() == a) {
					validId = false;
				}
			}
			for(Table b: receipts.getReceipts()) {
				if(b.getId() == a) {
					validId = false;
				}
			}
			if(validId) {
				orders.add(new Table(a));
			}
			a += 1;
		}
	}
	
	public void addTable(Table table) {
		orders.add(table);
	}
	
	/**
	 * Removes the {@code Table} with the specified id
	 * @param id
	 */
	public void removeTable(int id) {
		Table b = null;
		for (Table a: orders) {
			if(a.getId() == id) {
				b = a;
			}
		}
		if(b != null) {
			orders.remove(b);
		}
	}
	
	/**
	 * Adds a {@code DishOrdered} to the {@code Table} with the specified id
	 * @param e
	 * @param id
	 */
	public void addDish(DishOrdered e, int id) {
		for(Table a: orders) {
			if (a.getId() == id) {
				for(DishOrdered b: a.getTable()) {
					if(b.getNome().equals(e.getNome())) {
						int quantity = b.getQuantity() + e.getQuantity();
						b.setQuantity(quantity);
						return;
					}
				}
				a.addDish(e);
			}
		}
	}
	
	/**
	 * Removes a {@code DishOrdered} to the {@code Table} with the specified id
	 * @param dish
	 * @param id
	 */
	public void removeDish(Dish dish, int id) {
		for (Table a: orders) {
			if (id == a.getId()) {
				a.removeDish(dish);
			}
		}
	}
	
	/**
	 * Saves the list of {@code Table} on a text file
	 */
	public void saveOrders() {
		try(PrintWriter sc = new PrintWriter(fileOrders)){
			for(Table a: orders) {
				sc.println(a.toPrint());
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the list of {@code Table} from a text file
	 */
	public void loadOrders() {
		orders.clear();
		try(Scanner sc = new Scanner(fileOrders)){
			Table b = null;
			while(sc.hasNextLine()) {
				String a = sc.nextLine();
				if(a.contains(":")) {
					String c = a.replace(":", "");
					if(b != null) {
						orders.add(b);
						b = new Table(Integer.parseInt(c));
					}
					else {
						b = new Table(Integer.parseInt(c));
					}
				}
				else if(a.contains(";")) {
					String[] d = a.split(";");
					DishOrdered e = new DishOrdered(d[0], Double.parseDouble(d[1].replace(",", ".")), Integer.parseInt(d[2].replace(" ", "")));
					b.addDish(e);
				}
			}
			if(b != null) {
				orders.add(b);
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the list of orders
	 * @return
	 */
	public ArrayList<Table> getOrders(){
		return orders;
	}
}