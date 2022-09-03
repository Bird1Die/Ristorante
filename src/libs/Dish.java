package libs;


/**
 *	A {@code Dish} is used for instantiate a Dish object
 */
public class Dish {
	private String name;
	private double price;

	
	/**
	 * Constructs a new {@code Dish} with a name and a price
	 * @param name
	 * @param price
	 */
	public Dish(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	/**
	 * Returns name of the dish
	 * @return name
	 */
	public String getNome() {
		return this.name;
	}
	
	/**
	 * Returns price of the dish
	 * @return price
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * Sets the name of the dish with a given name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the price of the dish with a given price
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Returns a string with the name of the dish
	 * @return name
	 */
	public String toString() {
		return name;
	}
	
	/**
	 * Returns a string with name and price to be save
	 * on a text file
	 * @return 
	 */
	public String toSave() {
		return String.format("%s; %6.2f", name, price);
	}
}


