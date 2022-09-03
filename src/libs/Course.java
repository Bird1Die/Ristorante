package libs;

import java.util.ArrayList;

/**
 * {@code Course} is used to instantiate a course with
 * a list of {@code Dish}
 */
public class Course {

	private String name;
	private ArrayList<Dish> dishes;
	
	/**
	 * Constructs a {@code Course} with a name
	 * and a list of {@code Dish}
	 * @param name
	 */
	public Course(String name) {
		this.name = name;
		dishes = new ArrayList<Dish>();
	}
	
	/**
	 * Adds dish to the list
	 * @param dish
	 */
	public void addDish(Dish dish) {
		dishes.add(dish);
	}
	
	/**
	 * Removes dish from the list
	 * @param dish
	 */
	public void removeDish(Dish dish) {
		dishes.remove(dish);
	}
	
	/**
	 * Modifies name and price of the given dish
	 * @param dish
	 * @param name
	 * @param price
	 */
	public void modifyDish(Dish dish, String name, double price) {
		for(Dish a: dishes) {
			if(a.getNome().equals(dish.getNome())) {
				a.setName(name);
				a.setPrice(price);
			}
		}
	}
	
	/**
	 * Returns a string with a name of the dish
	 * @return name
	 */
	public String toString() {
		return name;
	}
	
	/**
	 * Returns the layout used to print the course on 
	 * a txt
	 * @return
	 */
	public String toPrint() {
		String a = "";
		for(Dish b: dishes) {a += String.format("%s; %s\n", b.getNome(), b.getPrice());}
		return name + ":\n" + a;
	}
	
	
	/**
	 * Returns the name of the course
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the list of {@code Dish}
	 * @return dishs
	 */
	public ArrayList<Dish> getDishs(){
		return dishes;
	}
	
	/**
	 * Sets the name of the course with the given name
	 * @param a
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean exist(String name) {
		for (Dish d: dishes) {
			if (d.getNome().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
