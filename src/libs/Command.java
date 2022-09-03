package libs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * {@code Command} is a list of ordinations 
 */
public class Command {
	
	private ArrayList<Table> command;
	private File commandMenu = new File("database/CompletedOrders.txt");
	
	/**
	 * Constructs {@code Command} and a list of {@code Table}
	 */
	public Command() {
		command = new ArrayList<Table>();
		loadCommand();
	}
	
	/**
	 * Loads the ordination list from a text file
	 */
	public void loadCommand() {
		command.clear();
		try(Scanner sc = new Scanner(commandMenu)){
			Table b = null;
			while(sc.hasNextLine()) {
				String a = sc.nextLine();
				if(a.contains(":")) {
					String c = a.replace(":", "");
					if(b != null) {
						command.add(b);
						b = new Table(Integer.parseInt(c));
					}
					else {
						b = new Table(Integer.parseInt(c));
					}
				}
				else if(a.contains(";")) {
					String[] d = a.split(";");
					DishOrdered e = new DishOrdered(d[0], Double.parseDouble(d[1].replace(",", ".")), Integer.parseInt(d[2].replace(" ", "")), Boolean.parseBoolean(d[3].replace(" ", "")));
					b.addDish(e);
				}
			}
			if(b != null) {
				command.add(b);
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Saves the ordination list on a text file
	 */
	public void saveCommand() {
		try(PrintWriter sc = new PrintWriter(commandMenu)){
			for(Table a: command) {
				sc.println(a.toPrint());
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns a list of ordinations
	 * @return command
	 */
	public ArrayList<Table> getCommand(){
		return command;
	}
	
	/**
	 * Removes a table from the ordination list
	 * @param table
	 */
	public void removeTable (Table table) {
		command.remove(table);
	}
}
