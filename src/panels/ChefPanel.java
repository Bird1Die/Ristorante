package panels;
import javax.swing.*;

import libs.*;
import employees.*;
import interfaces.*;

import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JScrollPane;

/**
 * This Panel displays all the chef functions and handle
 * all the events and the function a Chef needs to have
 */

public class ChefPanel extends JPanel implements Panels{

	private static final long serialVersionUID = 1L;

	private ImageIcon warnIcon = null;
	
	private JTextField textField;
	private JTextField textField_1;
	
	private JButton btn_Aggiungi;
	private JButton btn_Annulla;
	private JButton btn_Salva;
	private JButton btn_Modifica;
	private JButton btn_Elimina;
	private JButton btn_RimuoviPortata;
	private JButton btnAddPortata;
	
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	
	private DefaultTableModel dtm;
	private JTable dishTable;
	private JComboBox<Course> courseComboBox;
	
	private JScrollPane scrollPane;

	private Chef chef;

	/**
	 * Constructs a {@code ChefPanel} with the given dimension, 
	 * sets all the attributes and adds all the Components
	 * @param menuTopBorder
	 * @param panelWidth
	 * @param panelHeight
	 */
	public ChefPanel(int menuTopBorder, int panelWidth, int panelHeight) {
		setBorder(null);
		setVisible(false);
		setBounds(0, menuTopBorder, panelWidth, panelHeight - menuTopBorder);		
		setBackground(Color.WHITE);
		setLayout(null);
		
		chef = new Chef();
		setComponents();
		addComponents();
	}
	
	/**
	 * Adds all the Components to this panel
	 */
	public void addComponents() {
		add(textField);
		add(textField_1);
		add(btn_Aggiungi);
		add(btn_Annulla);
		add(btn_Salva);
		add(btn_Modifica);
		add(btn_Elimina);
		add(lblNewLabel_2);
		add(lblNewLabel_3);
		add(courseComboBox);
		add(btnAddPortata);
		add(scrollPane);
		add(btn_RimuoviPortata);
	}
	
	/**
	 * Sets all the Components of this panel
	 */
	public void setComponents() {
		
		dtm = new DefaultTableModel();
		dishTable = new JTable();
		dtm.setColumnIdentifiers(new Object[] {"Name", "Price"});
		dishTable.setModel(dtm);
		dishTable.getColumnModel().getColumn(1).setMaxWidth(50);
			
		courseComboBox = new JComboBox<Course>();
		courseComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDish();
			}
		});
		courseComboBox.setBounds(50, 30, 175, 20);
			
		textField = new JTextField();
		textField.setBounds(370, 80, 180, 20);
		textField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				String text = textField.getText();
				if(text.length() == 1) {
					textField.setText(text.substring(0,1).toUpperCase().concat(text.substring(1,text.length())));
				}	
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setBounds(370, 110, 65, 20);
		
		btn_Aggiungi = new JButton("Aggiungi");
		btn_Aggiungi.setBounds(300, 160, 250, 20);
		btn_Aggiungi.setBorder(null); 
		btn_Aggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDish();
			}
		});
		
		btn_Annulla = new JButton("Annulla");
		btn_Annulla.setVisible(false);
		btn_Annulla.setBorder(null);
		btn_Annulla.setBounds(300, 235, 120, 20);
		
		btn_Salva = new JButton("Salva");
		btn_Salva.setVisible(false);
		btn_Salva.setBorder(null);
		btn_Salva.setBounds(430, 235, 120, 20);
		
		btn_Annulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		
		btn_Salva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveModify();
			}
		});
		
		lblNewLabel_2 = new JLabel(warnIcon);
		lblNewLabel_2.setText("Name:");
		lblNewLabel_2.setBounds(300, 80, 60, 20);
		lblNewLabel_2.setHorizontalTextPosition(JLabel.RIGHT);
		
		lblNewLabel_3 = new JLabel(warnIcon);
		lblNewLabel_3.setText("Price:");
		lblNewLabel_3.setBounds(300, 110, 60, 20);
		lblNewLabel_3.setHorizontalTextPosition(JLabel.RIGHT);
			
		btn_Modifica = new JButton("Modifica");
		btn_Modifica.setBounds(300, 205, 120, 20);
		btn_Modifica.setBorder(null);
		btn_Modifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyDish();
			}
		});
		
		btnAddPortata = new JButton("Aggiungi portata");
		btnAddPortata.setBounds(300, 30, 120, 20);
		btnAddPortata.setBorder(null);
		btnAddPortata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCourse();	
			}
		});
		
		btn_Elimina = new JButton("Elimina");
		btn_Elimina.setBounds(430, 205, 120, 20);
		btn_Elimina.setBorder(null);
		btn_Elimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});
	
		scrollPane = new JScrollPane(dishTable);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(50, 60, 175, 195);
		
		btn_RimuoviPortata = new JButton("Rimuovi portata");
		btn_RimuoviPortata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCourse();
			}
		});
		btn_RimuoviPortata.setBounds(430, 30, 120, 20);
		btn_RimuoviPortata.setBorder(null);
		
		reloadCourse();
		loadDish();
	}
	
	/**
	 * Update the {@code JComboBox} that selects the {@code Course}
	 */
	private void reloadCourse() {
		chef.loadMenu();
		courseComboBox.removeAllItems();
		for (Course p:chef.getMenu().getMenu()) {
			courseComboBox.addItem(p);
		}
		loadDish();
	}
	
					
	/**
	 * Updates the display of the selected {@code Course} to the Table
	 */
	private void loadDish() {
		Course course = (Course) courseComboBox.getSelectedItem();
		dtm.setRowCount(0);
		if (course == null) {return;}
		for (Dish a: course.getDishs()) {
			dtm.addRow(new Object[] {a, String.format("%6.2f", a.getPrice())});
		}		
	}

	/**
	 * Removes the selected {@code Dish} from the selected {@code Course}
	 */
	public void remove() {
		if (dishTable.getSelectedColumn()<0) {
			JOptionPane.showMessageDialog(btn_Modifica, "Seleziona un piatto", null, 2);
			return;}
		Dish a = (Dish) dtm.getValueAt(dishTable.getSelectedRow(), 0);
		int b = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare il piatto?", null, 2);				
		if (b == 0) {
			Course p = (Course) courseComboBox.getSelectedItem();
			chef.removeDish(p, a);
			chef.saveMenu();
			loadDish();
			}
	}
	
	/**
	 * Handles the modification of the selected {@code Dish}
	 */
	public void modifyDish() {
		if (dishTable.getSelectedColumn()<0) {
			JOptionPane.showMessageDialog(btn_Modifica, "Seleziona un piatto", null, 2);
			return;}
		dishTable.setEnabled(false);
		btn_Annulla.setVisible(true);
		btn_Salva.setVisible(true);
		textField.setText(((Dish) dtm.getValueAt(dishTable.getSelectedRow(), 0)).getNome());
		textField_1.setText(String.valueOf(((Dish) dtm.getValueAt(dishTable.getSelectedRow(), 0)).getPrice()));
		btn_Aggiungi.setEnabled(false);
		btn_Elimina.setEnabled(false);
		btn_RimuoviPortata.setEnabled(false);
		btnAddPortata.setEnabled(false);
		courseComboBox.setEnabled(false);
	}
	
	
	/**
	 * Cancel the editing operation of the selected {@code Dish}
	 */
	public void cancel() {
		btn_Salva.setVisible(false);
		btn_Annulla.setVisible(false);
		dishTable.setEnabled(true);
		textField.setText("");
		textField_1.setText("");	
		btn_Aggiungi.setEnabled(true);
		btn_Elimina.setEnabled(true);
		btn_RimuoviPortata.setEnabled(true);
		btnAddPortata.setEnabled(true);
		courseComboBox.setEnabled(true);
	}
	
	/**
	 * Saves the modification made on the selected {@code Dish}
	 */
	public void saveModify() {
		Course course = (Course) courseComboBox.getSelectedItem();
		Dish d = (Dish) dtm.getValueAt(dishTable.getSelectedRow(), 0);
		String nameString = textField.getText();
		String priceString = textField_1.getText().replace(",", ".");
		cancel();
		chef.modifyDish(course, nameString, priceString, d);
		reloadCourse();
	}
	
	/**
	 * Deletes the selected {@code Course}
	 */
	public void deleteCourse() {
		int b = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare la portata?", null, 2);	
		if (b == 0) {
			Course p = (Course) courseComboBox.getSelectedItem();
			chef.removeCourse(p.getName());	
			reloadCourse();
			
		}
	}
	
	/**
	 * Adds a {@code Course} to the menu
	 */
	public void addCourse() {
		String s = (String) JOptionPane.showInputDialog(null, "Inserisci nome", "Aggiungi portata", 1, null, null, null);
		if (s == null) { return;}
		Course p = new Course(s);
		chef.addCourse(s);;
		chef.saveMenu();
		reloadCourse();
		courseComboBox.setSelectedItem(p);	
	}
	
	/**
	 * Adds a {@code Dish} to the {@code Course}
	 */
	public void addDish() {
		String name = textField.getText();
		String priceString = textField_1.getText();
		Course portata = (Course) courseComboBox.getSelectedItem();
		if(name.isEmpty()|| priceString.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Riempire i campi vuoti", "Errore", 0);
        return;
    	}					
		try {
			double price = Double.parseDouble(priceString.replace(",", "."));
			Dish dish = new Dish(name, price);
			if (portata.exist(name)) {
				JOptionPane.showMessageDialog(null, "Il piatto è già presente nel menù", "Errore", 0);
			return;
			}	
			portata.addDish(dish);
			chef.saveMenu();
			JOptionPane.showMessageDialog(null, "Piatto aggiunto con successo", "", 3);
			textField.setText("");
			textField_1.setText("");	
			loadDish();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Inserisci un prezzo valido", "Errore", 0);
		} catch (NumberFormatException ex1) {
			JOptionPane.showMessageDialog(null, "Inserisci un prezzo valido", "Errore", 0);
		}	
	
	}
	
	/**
	 * Updates the panel when shown
	 */
	@Override
	public void update() {
		chef.loadMenu();
		reloadCourse();
		
	}
	
	/**
	 * Returns the {@code Menu} object
	 * @return menu
	 */
	public Menu getMenu() {
		return chef.getMenu();
	}

	/**
	 * Saves the panel when is hidden
	 */
	@Override
	public void save() {
	}


}
