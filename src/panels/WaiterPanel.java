package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

import libs.*;
import employees.*;
import interfaces.*;

/**
 * This Panel displays all the waiter functions and handle
 * all the events and the function a Waiter needs to have
 */
public class WaiterPanel extends JPanel implements Panels {

	private static final long serialVersionUID = 1L;
	
	private Waiter waiter;
	
	private Menu menu = new Menu();
	
	private Icon warnIcon = null;
	
	private JScrollPane scrollPaneMenu;
	private JScrollPane scrollPaneOrder;
	
	private JLabel quantityLabel;
	
//	private ImageIcon addButtonIcon = new ImageIcon("images/waiterPanel_addButtonIcon.png");
//	private ImageIcon removeButtonIcon = new ImageIcon("images/waiterPanel_removeButtonIcon.png");
	
	private JComboBox<Course> portateComboBox;
	private JComboBox<Table> orderComboBox;
	
	private JButton addButton;
	private JButton removeButton;
	private JButton saveButton;
	private JButton addQuantityButton;
	private JButton removeQuantityButton;
	private JButton addTableButton;
	private JButton closeTableButton;
	
	private DefaultTableModel dtmDish;
	private JTable dishTable;
	
	private DefaultTableModel dtmOrder;
	private JTable orderTable;

	/**
	 * Constructs a {@code WaiterPanel} with the given dimension, 
	 * sets all the attributes and adds all the Components
	 * @param menuTopBorder
	 * @param panelWidth
	 * @param panelHeight
	 */
	public WaiterPanel(int menuTopBorder, int panelWidth, int panelHeight) {

		
		setBorder(null);
		setVisible(false);
		setBounds(0, menuTopBorder, panelWidth, panelHeight - menuTopBorder);		
		setBackground(Color.WHITE);
		setLayout(null);
		
		waiter = new Waiter();
		
		setComponents();
		addComponents();
		updateComponents();
	}
	
	/**
	 * Adds all the Components to this panel
	 */
	public void addComponents() {
		
		add(portateComboBox);
		add(scrollPaneMenu);
		add(orderComboBox);
		add(scrollPaneOrder);
		add(addButton);
		add(removeButton);
		add(addQuantityButton);
		add(removeQuantityButton);
		add(saveButton);
		add(addTableButton);
		add(closeTableButton);
		add(quantityLabel);
	}
	
	/**
	 * Sets all the Components to this panel
	 */
	public void setComponents() {
		portateComboBox = new JComboBox<Course>();
		portateComboBox.setBounds(375, 30, 175, 20);
		portateComboBox.setVisible(true);
		portateComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePortateListModel();
			}
		});
		
		dtmDish = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
		};
		dtmDish.setColumnIdentifiers(new Object [] {"Name", "Price"});
		dishTable = new JTable();
		dishTable.getTableHeader().setFont(new Font(null, Font.BOLD, 11));
		dishTable.setModel(dtmDish);
		
		dishTable.getColumnModel().getColumn(1).setMaxWidth(50);
		dishTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				quantityLabel.setText("1");
			}
		});
		scrollPaneMenu = new JScrollPane(dishTable);
		scrollPaneMenu.setBounds(375, 60, 175, 195);
		
		orderComboBox = new JComboBox<Table>();
		orderComboBox.setBounds(50, 30, 175, 20);
		orderComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateOrderTableModel();
			}
		});
		
		dtmOrder = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
		};
		dtmOrder.setColumnIdentifiers(new Object [] {"Name", "Qty"});
		orderTable = new JTable();
		orderTable.getTableHeader().setFont(new Font(null, Font.BOLD, 11));
		orderTable.setModel(dtmOrder);
		orderTable.getColumnModel().getColumn(1).setMaxWidth(30);
		orderTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateQuantityLabel();
			}
		});
		scrollPaneOrder = new JScrollPane(orderTable);
		scrollPaneOrder.setBounds(50, 60, 175, 195);
		
		addButton = new JButton("Add");
		addButton.setBounds(235, 120, 130, 20);
		addButton.setBorder(null);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDish();
			}
		});
		
		removeButton = new JButton("Remove");
		removeButton.setBounds(235, 150, 130, 20);
		removeButton.setBorder(null);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeDish();
			}
		});
		
		saveButton = new JButton("Save");
		saveButton.setBounds(260, 210, 80, 20);
		saveButton.setBorder(null);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveQuantity();
			}
		});
		
		quantityLabel = new JLabel(warnIcon);
		quantityLabel.setBounds(280, 180, 40, 20);
		quantityLabel.setBorder(null);
		quantityLabel.setHorizontalTextPosition(JLabel.CENTER);
		quantityLabel.setVerticalTextPosition(JLabel.CENTER);
		quantityLabel.setText("1");
		
		addQuantityButton = new JButton("+");
		addQuantityButton.setBounds(320,180,20,20);
		addQuantityButton.setBorder(null);
		addQuantityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addQuantity();
			}
		});
		
		removeQuantityButton = new JButton("-");
		removeQuantityButton.setBounds(260,180,20,20);
		removeQuantityButton.setBorder(null);
		removeQuantityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeQuantity();
			}
		});
		
		addTableButton = new JButton("Open table");
		addTableButton.setBounds(50, 265, 80, 20);
		addTableButton.setBorder(null);
		addTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				waiter.addTable();
				updateOrderComboBox();
				updateOrderTableModel();
			}
		});
		
		closeTableButton = new JButton("Close table");
		closeTableButton.setBounds(145, 265, 80, 20);
		closeTableButton.setBorder(null);
		closeTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeTable();
			}
		});
	}
	
	/**
	 * Handles the closing of a table
	 */
	public void removeTable() {
		Table a = (Table) orderComboBox.getSelectedItem();
		if(a != null) {
			waiter.closeOrder(a.getId());
			updateComponents();
			save();
		}
	}
	
	/**
	 * Updates the display of the selected course
	 */
	public void updatePortateListModel() {
		dtmDish.setRowCount(0);
		Course port = (Course) portateComboBox.getSelectedItem();
		if(port == null) {return;}
		for(Dish a: port.getDishs()) {
			dtmDish.addRow(new Object[] {a, String.format("%6.2f", a.getPrice())});;
		}
	}
	
	/**
	 * Adds 1 to the quantity label
	 */
	public void addQuantity() {
		int a = Integer.parseInt(quantityLabel.getText());
		a += 1;
		quantityLabel.setText(String.format("%d", a));
	}
	
	/**
	 * Removes 1 from the quantity label
	 */
	public void removeQuantity() {
		int a = Integer.parseInt(quantityLabel.getText());
		if(a>1) {
			a -= 1;
			quantityLabel.setText(String.format("%d", a));
		}
	}
	
	/**
	 * Updates the {@code JComboBox} that selects the course
	 */
	public void updatePortateComboBox() {
		portateComboBox.removeAllItems();
		for(Course a: menu.getMenu()) {
			portateComboBox.addItem(a);
		}
	}
	
	/**
	 * Updates the {@code JComboBox} that selects the order
	 */
	public void updateOrderComboBox() {
		orderComboBox.removeAllItems();
		for(Table a: waiter.getOrdini()) {
			orderComboBox.addItem(a);
		}
	}
	
	/**
	 * Updates the display of the selected order
	 */
	public void updateOrderTableModel() {
		dtmOrder.setRowCount(0);
		Table a = (Table) orderComboBox.getSelectedItem();
		if(a == null) {return;}
		for (DishOrdered d: a.getTable()) {
			dtmOrder.addRow(new Object [] {d, d.getQuantity()});
		}
	}
	
	/**
	 * Adds a {@code DishOrdered} from the menu to the order
	 */
	public void addDish() {
		if(dishTable.getSelectedRow() < 0) {return;}
		Dish dish = (Dish) dtmDish.getValueAt(dishTable.getSelectedRow(), 0);
		if (dish != null) {
			int quantity = Integer.parseInt(quantityLabel.getText());
			Table table = (Table) orderComboBox.getSelectedItem();
			if(table != null) {
				waiter.addDish(table.getId(), quantity, dish);
			}
		}
		quantityLabel.setText("1");
		updateOrderTableModel();
		save();
	}
	
	/**
	 * Removes a {@code DishOrdered} from the order
	 */
	public void removeDish() {
		if(orderTable.getSelectedRow() < 0) {return;}
		DishOrdered a = (DishOrdered) dtmOrder.getValueAt(orderTable.getSelectedRow(), 0);
		if (a == null) {return;}	
		Table table = (Table) orderComboBox.getSelectedItem();
		waiter.removeDish(a, table.getId());
		updateOrderTableModel();
		save();
	}
	
	/**
	 * Updates the quantity label to the quantity of the selected {@code DishOrdered} from an order
	 */
	public void updateQuantityLabel() {
		if(orderTable.getSelectedRow() < 0) {return;}
		DishOrdered a = (DishOrdered) dtmOrder.getValueAt(orderTable.getSelectedRow(), 0);
		if(a == null) {
			quantityLabel.setText("1");
			return;
		}
		quantityLabel.setText(String.format("%d", a.getQuantity()));	
	}
	
	/**
	 * Changes the quantity of a selected {@code DishOrdered} in an order
	 */
	public void saveQuantity() {		
		if(orderTable.getSelectedRow() < 0) {return;}
		DishOrdered e = (DishOrdered) dtmOrder.getValueAt(orderTable.getSelectedRow(), 0);
		if(e == null) {return;}
		e.setQuantity(Integer.parseInt(quantityLabel.getText()));
		quantityLabel.setText("1");
		updateOrderTableModel();
		save();
	}
	
	/**
	 * Updates the display of both tables and comboBox
	 */
	public void updateComponents() {
		updatePortateComboBox();
		updatePortateListModel();
		updateOrderComboBox();
		updateOrderTableModel();
	}
	
	/**
	 * Updates the panel when shown
	 * @param menu
	 * @param receipt
	 */
	public void update(Menu menu, Receipt receipt) {
		this.menu = menu;
		waiter.setMenu(this.menu);
		waiter.setReceipt(receipt);
		waiter.loadOrders();
		updateComponents();
	}
	
	/**
	 * Returns a {@code Order} object 
	 * @return order
	 */
	public Order getOrdini(){
		return waiter.getOrder();
	}
	
	/**
	 * Saves the panel when it is hidden
	 */
	public void save() {
		waiter.saveOrders();
	}

	/**
	 * Updates the panel when shown
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}