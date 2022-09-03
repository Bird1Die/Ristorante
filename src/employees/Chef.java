package employees;

import libs.*;

/**
 * Chef class is used to handle the events in the panel
 * and manipulate the menu
 */
public class Chef {
	
	private Menu menu;
	
	/**
	 * Constructs a new {@code Chef} and initialize 
	 * the {@code Menu} object
	 */
	public Chef() {
		menu = new Menu();
	}
	
	/**
	 * Calls the function to load the menu
	 */
	public void loadMenu(){	
		menu.loadMenu();
	}
	
	/**
	 *  Calls the function to save the menu
	 */
	public void saveMenu() {
		menu.saveMenu();
	}
	
	/**
	 * Returns the {@code Menu} object
	 * @return {@code Menu}
	 */
	public Menu getMenu(){
		return menu;
	}
	
	/**
	 * Adds dish to a specific course in the menu
	 * @param course
	 * @param dish
	 */
	public void addDish(Course course, Dish dish) {
		menu.addDish(course, dish);
		saveMenu();
	}
	
	/**
	 * Removes dish from a specific course in the menu
	 * @param {@code course}
	 * @param {@code dish}
	 */
	public void removeDish(Course course, Dish dish) {
		menu.removeDish(course, dish);
		saveMenu();
	}
	
	/**
	 * Modifies name and price of the dish in the specific course
	 * @param course
	 * @param name
	 * @param price
	 * @param dish
	 */
	public void modifyDish(Course course, String name, String price, Dish dish) {
		menu.modifyDish(course, name, price, dish);
		saveMenu();
	}
	
	/**
	 * Removes the course from the menu
	 * @param portata
	 */
	public void removeCourse(String course) {
		menu.removeCourse(course);
		saveMenu();
	}
	
	/**
	 * Adds the course in the menu
	 * @param portata
	 */
	public void addCourse(String course) {
		menu.addCourse(new Course(course));
		saveMenu();
	}
		
}