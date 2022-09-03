package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import employees.*;
import interfaces.*;
import libs.*;

/**
 * This Panel displays all the cooks functions and handle
 * all the events and the function a Cook needs to have
 */
public class CooksPanel extends JPanel implements Panels {

	private static final long serialVersionUID = 1L;
	
	private Cook cook;
	
	private JScrollPane scrollPaneOrders;
	
	private JComboBox<Table> orderComboBox;
	
	private JButton evadeButton;
	
	private JTable orderTable;
	
	private DefaultTableModel dtm;
	
	

	/**
	 * Constructs a {@code CooksPanel} with the given dimension, 
	 * sets all the attributes and adds all the Components
	 * @param menuTopBorder
	 * @param panelWidth
	 * @param panelHeight
	 */
	public CooksPanel(int menuTopBorder, int panelWidth, int panelHeight) {
		setBorder(null);
		setVisible(false);
		setBounds(0, menuTopBorder, panelWidth, panelHeight - menuTopBorder);		
		setBackground(Color.WHITE);
		setLayout(null);
		
		cook = new Cook();
		cook.loadCommand();
		
		setComponents();
		addComponents();
	}
	
	/**
	 * Adds all the Components to this panel
	 */
	public void addComponents() {
		add(scrollPaneOrders);
		add(orderComboBox);
		add(evadeButton);
	}
	
	/**
	 * Sets all the Components of this panel
	 */
	public void setComponents() {
		
		dtm = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
		};
		orderTable = new JTable();
		orderTable.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[] {"Name", "Qty"});
		orderTable.getTableHeader().setFont(new Font(null, Font.BOLD, 11));
		orderTable.getColumnModel().getColumn(1).setMaxWidth(30);
		scrollPaneOrders = new JScrollPane(orderTable);
		scrollPaneOrders.setBounds(50, 60, 175, 195);
		
		orderComboBox = new JComboBox<Table>();
		orderComboBox.setBounds(50, 30, 175, 20);
		orderComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateDishTableModel();
			}
		});
		
		evadeButton = new JButton("Evade");
		evadeButton.setBorder(null);
		evadeButton.setBounds(250, 145, 60, 20);
		evadeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evadeDish();
			}
		});
		
		updateOrderComboBox();
		updateDishTableModel();
	}
	
	/**
	 * Updates the panel when shown
	 */
	public void update() {
		cook.loadCommand();
		updateOrderComboBox();
		updateDishTableModel();
	}
	
	/**
	 * Updates the {@code JComboBox} that selects the order
	 */
	public void updateOrderComboBox() {
		orderComboBox.removeAllItems();
		for(Table a: cook.getCommand()) {
			orderComboBox.addItem(a);
		}
	}
	
	/**
	 * Updates the display of the chosen order
	 */
	public void updateDishTableModel() {
		Table a = (Table) orderComboBox.getSelectedItem();
		dtm.setRowCount(0);
		if(a == null) {return;}
		for(DishOrdered b: a.getTable()) {
			if(!b.isCooked()) {
				dtm.addRow(new Object[] {b, b.getQuantity()});
			}
		}	
	}

	/**
	 * Handles the removal of a {@code DishOrdered} when its cooked 
	 */
	public void evadeDish() {
		if (orderTable.getSelectedRow() < 0) {return;}
		DishOrdered a = (DishOrdered) dtm.getValueAt(orderTable.getSelectedRow(), 0);
		if(a==null) {return;}
		a.setCooked(true);	
		Table b = (Table) orderComboBox.getSelectedItem();
		if(checkEmptyTable(b)) {
			cook.removeTable(b);
			cook.addReceipt(b);
			updateOrderComboBox();
		}
		updateDishTableModel();
		cook.save();	
	}
	
	/**
	 * Check for the table if all the {@code DishOrdered} are cooked
	 * @param a
	 * @return
	 */
	public boolean checkEmptyTable(Table a) {
		for(DishOrdered b: a.getTable()) {
			if(!b.isCooked()) {
				return false;
			}
		}
		return true;
	}
		
	/**
	 * Saves the panel when it is hidden
	 */
	public void save() {
		cook.save();
	}
}
