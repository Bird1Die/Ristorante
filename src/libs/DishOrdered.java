package libs;


/**
 * A {@code DishOrdered} is used for instantiate 
 * an object {@code Dish} adding attribute quantity
 */
public class DishOrdered extends Dish {
	
	private int quantity;
	private boolean cooked;

	/**
	 * Constucts a {@code DishOrdered} with name, price
	 * and quantity
	 * @param name
	 * @param price
	 * @param quantity
	 */
	public DishOrdered(String name, double price, int quantity) {
		super(name, price);
		this.quantity = quantity;
		this.cooked = false;
	}
	
	/**
	 * Constucts a {@code DishOrdered} with name, price,
	 * quantity and sets cooked status
	 * @param name
	 * @param price
	 * @param quantity
	 * @param cooked
	 */
	public DishOrdered(String name, double price, int quantity, boolean cooked) {
		super(name, price);
		this.quantity = quantity;
		this.cooked = cooked;
	}
	
	/**
	 * Returns cooked status of the Dish
	 * @return boolean
	 */
	public boolean isCooked() {
		return cooked;
	}

	/**
	 * Sets cooked status
	 * @param cooked
	 */
	public void setCooked(boolean cooked) {
		this.cooked = cooked;
	}
	
	/**
	 * Returns the quantity ordered of the dish
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Sets the quantity ordered of the dish
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 *	Returns string to be save on a text file
	 */
	public String toSave() {
		return String.format("%s; %6.2f; %d; %b", super.getNome(), super.getPrice(), quantity, cooked);
	}
}
