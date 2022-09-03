package libs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * A {@code Menu} is used to instantiate a menu with 
 * a list of {@code Course} and to save the menu on a 
 * text file
 */
public class Menu {
	private ArrayList<Course> menu;
	private File fileMenu = new File("database/Menu.txt");
	
	/**
	 * Constructs a {@code Menu} and loads a menu
	 * from a text file
	 */
	public Menu() {
		menu = new ArrayList<Course>();
		loadMenu();
	}
	
	/**
	 * Reads an encode text file with a pattern for distinguish 
	 * the name of the {@code Course}  
	 * and its {@code Dish} which compose the menu 
	 */
	public void loadMenu() {
		menu = new ArrayList<Course>();
		Course port = null;
		try(Scanner sc = new Scanner(fileMenu)){
			while(sc.hasNextLine()) {
				String a = sc.nextLine();
				if(a.contains(":")) {
					if(port == null) {
						port = new Course(a.replace(":", ""));
					}
					else {
						menu.add(port);
						port = new Course(a.replace(":", ""));
					}
				}
				else {
					String[] dish = a.split(";");
					port.addDish(new Dish(dish[0], Double.parseDouble(dish[1])));
				}
			}
			menu.add(port);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves the menu on a text file 
	 */
	public void saveMenu() {
		try(PrintWriter sc = new PrintWriter(fileMenu)){
			for(Course a: menu) {
				sc.print(a.toPrint());
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a {@code Course} in the list of 
	 * course in the menu
	 * @param course
	 */
	public void addCourse(Course course) {
		menu.add(course);
	}
	
	/**
	 * Removes a {@code Course} from the menu
	 * by its name
	 * @param name
	 */
	public void removeCourse(String name) {
		ListIterator<Course> iter = menu.listIterator();
		while(iter.hasNext()) {
			if(name.equals(iter.next().getName())) {
				iter.remove();			
			}
		}
	}
	
	/**
	 * Adds a {@code Dish} in the given 
	 * {@code Course}
	 * @param course
	 * @param dish
	 */
	public void addDish(Course course, Dish dish) {
			course.addDish(dish);
	}
	
	/**
	 * Modifies price and name of the {@code Dish}
	 * of the given {@code Course}
	 * @param course
	 * @param name
	 * @param price
	 * @param dish
	 */
	public void modifyDish(Course course, String name, String price, Dish dish) {
		for(Dish a: course.getDishs()) {
			if(a.getNome().equals(dish.getNome())) {
				a.setName(name);
				a.setPrice(Double.parseDouble(price));
			}
		}
		saveMenu();
		loadMenu();
	}
	
	/**
	 * Removes {@code Dish} from the {@code Course}
	 * @param course
	 * @param dish
	 */
	public void removeDish(Course course, Dish dish) {
		course.removeDish(dish);
	}
	
	/**
	 * Returns a list of {@code Course}
	 * @return list of {@code Course}
	 */
	public ArrayList<Course> getMenu(){
		return menu;
	}
	
	/**
	 * Sets the list of {@code Course} to the 
	 * specified {@code course}
	 * @param course
	 */
	public void setMenu(ArrayList<Course> course) {
		menu = course;
	}

	/**
	 * Returns {@code true} if the {@code Course}
	 * name is already present in the {@code Menu}
	 * @param name
	 * @return boolean
	 */
	public boolean exist(String name) {
		for (Course c: menu) {
			if (c.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}