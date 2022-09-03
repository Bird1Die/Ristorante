package libs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * A {@code Receipt} is a collection of {@code Table}
 * and has the methods to add, remove and print the receipts
 *
 */
public class Receipt {
	
	private File fileReceipts;
	private ArrayList<Table> receipts;
	
	/**
	 * Constructs a {@code Receipt} object and initialize
	 * the receipts list
	 */
	public Receipt() {
		fileReceipts = new File("database/Receipt.txt");
		receipts = new ArrayList<Table>();
	}
	
	/**
	 * Adds a {@code Table} to the list
	 * @param table
	 */
	
	public void addReceipt(Table table) {
		receipts.add(table);
	}
	
	/**
	 * Saves the list of {@code Table} to a text file with a specific format
	 */
	public void saveReceipts() {
		try(PrintWriter sc = new PrintWriter(fileReceipts)){
			for(Table a: receipts) {
				sc.println(a.toPrint());
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the list of {@code Table} from a formatted text file
	 */
	public void loadReceipts() {
		receipts.clear();
		try(Scanner sc = new Scanner(fileReceipts)){
			Table b = null;
			while(sc.hasNextLine()) {
				String a = sc.nextLine();
				if(a.contains(":")) {
					String c = a.replace(":", "");
					if(b != null) {
						receipts.add(b);
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
				receipts.add(b);
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @return ArrayList<Table>
	 * This function returns the ArrayList of the Receipts
	 */
	public ArrayList<Table> getReceipts(){
		return receipts;
	}
	
	/**
	 * Prints the receipt of a {@code Table} to a text file
	 * @param table
	 * @param total
	 * @param rest
	 * @param cash
	 */
	public void makeReceipt(Table table, String total, String rest, String cash) {
		receipts.remove(table);
		int LEN_MAX = 40;
		int SPACE = 3;
		Path path = Paths.get("");
		Date date = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	SimpleDateFormat f2 = new SimpleDateFormat("ddMMyyyyHHmmss");
    	String dataFile = f2.format(date);
    	String d = formatter.format(date);
	    String[] fileName = table.toString().split(" ");
	    String directoryName = path.toAbsolutePath().toString().concat("/database/Receipts/"+fileName[0]+fileName[1]+fileName[2]+ "_"+ dataFile +".txt");
	    try(PrintWriter out = new PrintWriter(directoryName)){	
	    	
	    	out.println(StringUtils.center("NOME RISTORANTE",LEN_MAX));
	    	out.println(StringUtils.center("XXXXXXXX srl",LEN_MAX));
	    	out.println(StringUtils.center("Via XXXXXXXXX",LEN_MAX));
	    	out.println(StringUtils.center("CAP CITTA'",LEN_MAX));
	    	out.println(StringUtils.center("IVA: XXXXXXXXXXX",LEN_MAX));
	    	out.println(StringUtils.center("06 XXXXXXX",LEN_MAX));
	    	
	    	
	    	int dateDistance = LEN_MAX-d.length();
	    	out.println(" ".repeat(dateDistance)+d);
	    	out.println(StringUtils.center("CONTO",LEN_MAX));
	    	String qnt = "Qnt";
	    	String descrizione = "Descrizione";
	    	String importo = "Importo";
	    	int FOOD_SPACE = LEN_MAX-qnt.length()-SPACE-importo.length()-4;
	    	out.println("-".repeat(LEN_MAX));
	    	out.print(qnt);
	    	out.print(" ".repeat(SPACE)+descrizione);
	    	out.print(" ".repeat(LEN_MAX-qnt.length()-SPACE-descrizione.length()-importo.length())+ importo);
	    	out.println();
	    	for(DishOrdered dish : table.getTable()) {
	    		String quantity = String.valueOf(dish.getQuantity());
	    		out.print(" ".repeat(3-quantity.length())+quantity+" ".repeat(SPACE));
	    		if(dish.getNome().length()<FOOD_SPACE) {
	    			double price = dish.getPrice()*dish.getQuantity();
	    			String priceS = String.valueOf(price)+ "€";
	    			int spaces = LEN_MAX - qnt.length() - SPACE - dish.getNome().length() - priceS.length();
	    			out.println(dish.getNome()+" ".repeat(spaces)+priceS);
	    		}
	    		else {
	    			double price = dish.getPrice()*dish.getQuantity();
	    			String priceS = String.valueOf(price)+ "€";
	    			String[] name = dish.getNome().split(" ");
	    			String firstString = "";
	    			String secondString = "";
	    			boolean empty = true;
	    			for(String el : name) {
	    				if(firstString.length() == 0 && empty) {
	    					firstString += el;
	    				}
	    				else if((firstString + " "+el).length()<FOOD_SPACE  && empty) {
	    					firstString += " " + el;
	    				}
	    				else {
	    					empty = false;
	    					if(secondString.length() == 0) {
	    						secondString += el;
	    					}
	    					else if ((secondString + " "+el).length()<FOOD_SPACE){
	    						secondString += " "+el;
	    					}
	   
	    					}
	    				}
	    			int spaces = LEN_MAX - qnt.length() - SPACE - firstString.length() - priceS.length();
					out.println(firstString + " ".repeat(spaces) + priceS);
					out.println(" ".repeat(qnt.length()+SPACE)+secondString);
	    			}
	    			
	    		}
	    	out.println("-".repeat(LEN_MAX));
	    	String importoEuro = "IMPORTO EURO";
	    	String contanti = "CONTANTI";
	    	String resto = "RESTO";
	    	String tot = total+"€";
	    	String cashEuro = cash+"€";
	    	String restEuro = rest+"€";
	    	out.println(importoEuro + " ".repeat(LEN_MAX-importoEuro.length()-tot.length())+tot);
	    	out.println(contanti + " ".repeat(LEN_MAX-contanti.length()-cashEuro.length())+cashEuro);
	    	out.println(resto + " ".repeat(LEN_MAX-resto.length()-restEuro.length())+restEuro);
	    	out.println("-".repeat(LEN_MAX));
	    	out.println(table.toString());
	    	out.print(StringUtils.center("GRAZIE E ARRIVEDERCI", LEN_MAX));
	    	}
	    catch(FileNotFoundException e) {
	    	e.printStackTrace();
	    }
	}
}
