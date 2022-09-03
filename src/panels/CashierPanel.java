package panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import interfaces.Panels;
import employees.*;
import libs.*;

/**
 * This Panel displays all the cashier functions and handle
 * all the events and the function a Cashier needs to have
 */
public class CashierPanel extends JPanel implements Panels{

	private static final long serialVersionUID = 1L;
	
	private Cashier cashier;
	
	private JComboBox<Table> receiptComboBox;
	
	private JScrollPane receiptScrollPane;
	private JTable receiptTable;
	private DefaultTableModel dtm;
	
	private Icon warnIcon;
	
	private JButton numericButton1;
	private JButton numericButton2;
	private JButton numericButton3;
	private JButton numericButton4;
	private JButton numericButton5;
	private JButton numericButton6;
	private JButton numericButton7;
	private JButton numericButton8;
	private JButton numericButton9;
	private JButton numericButton0;
	private JButton numericButtonDot;
	private JButton numericButtonCancel;
	private JButton payButton;
	private JButton printReceiptButton;
	private JButton cancelTextButton;
	
	private ArrayList<JLabel> euroLabel;
	
	private JTextField totalTextField;
	private JTextField restTextField;
	private JTextField cashTextField;
	
	private JLabel totalLabel;
	private JLabel restLabel;
	private JLabel cashLabel;
	

	/**
	 * Constructs a {@code CashierPanel} with the given dimension, 
	 * sets all the attributes and adds all the Components
	 * @param menuTopBorder
	 * @param panelWidth
	 * @param panelHeight
	 */
	public CashierPanel(int menuTopBorder, int panelWidth, int panelHeight) {
		setBorder(null);
		setVisible(false);
		setBounds(0, menuTopBorder, panelWidth, panelHeight - menuTopBorder);		
		setBackground(Color.WHITE);
		setLayout(null);
		
		cashier = new Cashier();
		setComponents();
		addComponents();
	}
	
	/**
	 * Adds all the Components to this panel
	 */
	public void addComponents() {
		add(receiptComboBox);
		add(numericButton1);
		add(numericButton2);
		add(numericButton3);
		add(numericButton4);
		add(numericButton5);
		add(numericButton6);
		add(numericButton7);
		add(numericButton8);
		add(numericButton9);
		add(numericButton0);
		add(numericButtonDot);
		add(numericButtonCancel);
		
		add(payButton);
		add(printReceiptButton);
		
		add(totalLabel);
		add(restLabel);
		add(cashLabel);
		
		add(totalTextField);
		add(restTextField);
		add(cashTextField);
		
		add(receiptScrollPane);
		
		add(cancelTextButton);
		
		for(JLabel label: euroLabel) {
			add(label);
		}
	}

	/**
	 * Sets all the Components of this panel
	 */
	public void setComponents() {
		
		warnIcon = new ImageIcon();
		
		receiptComboBox = new JComboBox<Table>();
		receiptComboBox.setBounds(50, 30, 175, 20);
		receiptComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTableModel();
			}
		});
		
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(new Object[] {"Qty", "Name", "Price"});
		receiptTable = new JTable();
		receiptTable.setFocusable(false);
		receiptTable.setRowSelectionAllowed(false);
		receiptTable.setColumnSelectionAllowed(false);
		receiptTable.setModel(dtm);
		receiptTable.getColumnModel().getColumn(0).setMaxWidth(30);
		receiptTable.getColumnModel().getColumn(2).setMaxWidth(50);
		receiptScrollPane = new JScrollPane(receiptTable);
		receiptScrollPane.setBounds(50, 60, 175, 200);
		receiptScrollPane.setBorder(null);
		
		numericButton1 = new JButton("1");
		numericButton1.setBounds(450, 60, 30, 30);
		numericButton1.setBorder(null);
		numericButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("1");
			}
		});
		
		numericButton2 = new JButton("2");
		numericButton2.setBounds(490, 60, 30, 30);
		numericButton2.setBorder(null);
		numericButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("2");
			}
		});
		
		numericButton3 = new JButton("3");
		numericButton3.setBounds(530, 60, 30, 30);
		numericButton3.setBorder(null);
		numericButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("3");
			}
		});
		
		numericButton4 = new JButton("4");
		numericButton4.setBounds(450, 100, 30, 30);
		numericButton4.setBorder(null);
		numericButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("4");
			}
		});
		
		numericButton5 = new JButton("5");
		numericButton5.setBounds(490, 100, 30, 30);
		numericButton5.setBorder(null);
		numericButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("5");
			}
		});
		
		numericButton6 = new JButton("6");
		numericButton6.setBounds(530, 100, 30, 30);
		numericButton6.setBorder(null);
		numericButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("6");
			}
		});
		
		numericButton7 = new JButton("7");
		numericButton7.setBounds(450, 140, 30, 30);
		numericButton7.setBorder(null);
		numericButton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("7");
			}
		});
		
		numericButton8 = new JButton("8");
		numericButton8.setBounds(490, 140, 30, 30);
		numericButton8.setBorder(null);
		numericButton8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("8");
			}
		});
		
		numericButton9 = new JButton("9");
		numericButton9.setBounds(530, 140, 30, 30);
		numericButton9.setBorder(null);
		numericButton9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("9");
			}
		});
		
		numericButton0 = new JButton("0");
		numericButton0.setBounds(490, 180, 30, 30);
		numericButton0.setBorder(null);
		numericButton0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeNumber("0");
			}
		});
		
		numericButtonDot = new JButton(".00");
		numericButtonDot.setBounds(450, 180, 30, 30);
		numericButtonDot.setBorder(null);
		numericButtonDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zeroZeroText();
			}
		});
		
		numericButtonCancel = new JButton("<-");
		numericButtonCancel.setBounds(530, 180, 30, 30);
		numericButtonCancel.setBorder(null);
		numericButtonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelText();
			}
		});
		
		cancelTextButton = new JButton("Cancel");
		cancelTextButton.setBounds(450, 230, 110, 30);
		cancelTextButton.setBorder(null);
		cancelTextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cashTextField.setText("0,00");
			}
		});
		
		
		payButton = new JButton("Pay");
		payButton.setBounds(260, 200, 160, 20);
		payButton.setBorder(null);
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payButton();
				
			}
		});
		
		printReceiptButton = new JButton("Print Receipt");
		printReceiptButton.setBounds(260, 230, 160, 20);
		printReceiptButton.setBorder(null);
		printReceiptButton.setEnabled(false);
		printReceiptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printReceipt();
				printReceiptButton.setEnabled(false);
			}
		});
		
		totalLabel = new JLabel(warnIcon);
		totalLabel.setBounds(260,65,60,20);
		totalLabel.setText("Total:");
		totalLabel.setHorizontalTextPosition(JLabel.RIGHT);
		
		restLabel = new JLabel(warnIcon);
		restLabel.setBounds(260,105,60,20);
		restLabel.setText("Rest:");
		restLabel.setHorizontalTextPosition(JLabel.RIGHT);
		
		cashLabel = new JLabel(warnIcon);
		cashLabel.setBounds(260,145,60,20);
		cashLabel.setText("Cash:");
		cashLabel.setHorizontalTextPosition(JLabel.RIGHT);
		
		totalTextField = new JTextField();
		totalTextField.setBounds(320,65, 80, 20);
		totalTextField.setHorizontalAlignment(JTextField.RIGHT);
		totalTextField.setEditable(false);
		
		restTextField = new JTextField();
		restTextField.setBounds(320, 105, 80, 20);
		restTextField.setHorizontalAlignment(JTextField.RIGHT);
		restTextField.setEditable(false);
		
		cashTextField = new JTextField();
		cashTextField.setBounds(320, 145, 80, 20);
		cashTextField.setHorizontalAlignment(JTextField.RIGHT);
		cashTextField.setEditable(false);
		
		euroLabel = new ArrayList<JLabel>();
		int yPos = 65;
		for(int i = 0; i < 3; i++) {
			JLabel euro = new JLabel(warnIcon);
			euro.setBounds(400, yPos, 20, 20);
			euro.setHorizontalAlignment(JLabel.LEFT);
			euro.setText("$");
			euroLabel.add(euro);
			yPos += 40;
		}
		
		updateReceiptComboBox();
		updateTableModel();
	}
	
	/**
	 * Updates the {@code JComboBox} that selects the receipt
	 */
	public void updateReceiptComboBox() {
		receiptComboBox.removeAllItems();
		for(Table a: cashier.getReceipt().getReceipts()) {
			receiptComboBox.addItem(a);
		}
		cashTextField.setText("0,00");
	}
	
	/**
	 * Handles the writing events from the numeric keypad
	 * @param number
	 */
	public void writeNumber(String number) {
		String[] text = cashTextField.getText().split(",");
		String numberToTransport = String.valueOf(text[1].charAt(0));
		text[1] = text[1].substring(1,2).concat(number);
		text[0] = text[0].concat(numberToTransport);
		String firstNumber = String.valueOf(text[0].charAt(0));
		if(firstNumber.equals("0")) {
			text[0] = text[0].substring(1);
		}
		cashTextField.setText(String.join(",", text[0], text[1]));
//		cashTextField.setText();
		
	}
	
	/**
	 * Handles the deletion of the text
	 */
	public void cancelText() {
		String[] text = cashTextField.getText().split(",");
		String numberToTransport = String.valueOf(text[0].charAt(text[0].length() - 1));
		if(text[0].length() == 1) {
			text[0] = "0";
		}
		else {
			text[0] = text[0].substring(0, text[0].length() - 1);
		}
		text[1] = numberToTransport.concat(text[1].substring(0,1));
		cashTextField.setText(String.join(",", text[0], text[1]));
	}
	
	/**
	 * Adds two zero to the writen number
	 */
	public void zeroZeroText() {
		writeNumber("0");
		writeNumber("0");
	}
	
	/**
	 * Calls for the save of the receipts
	 */
	public void save() {
		cashier.saveReceipts();
	}
	
	/**
	 * Returns the {@code Receipt} object
	 * @return {@code Receipt}
	 */
	public Receipt getReceipt() {
		return cashier.getReceipt();	
	}
	
	/**
	 * Handle the pay event disabling all other Components if the payment
	 * can be done and only allowing the Print Receipt button to be toggled
	 */
	public void payButton() {
		Table table = (Table)receiptComboBox.getSelectedItem();
		if (table == null) {
			
			JOptionPane.showMessageDialog(null, "Selezionare un tavolo!", "Errore", 0);
			return;}
		double cash = Double.parseDouble(cashTextField.getText().replace(",", "."));
		double total = Double.parseDouble(totalTextField.getText().replace(",", "."));
		if(cash < total) {
			JOptionPane.showMessageDialog(null, "Inserire importo valido!", "Errore", 0);
			printReceiptButton.setEnabled(false);
			return;
		}
		Double rest = cash - total;
		restTextField.setText(String.format("%6.2f", rest));
		numericButton0.setEnabled(false);
		numericButton1.setEnabled(false);
		numericButton2.setEnabled(false);
		numericButton3.setEnabled(false);
		numericButton4.setEnabled(false);
		numericButton5.setEnabled(false);
		numericButton6.setEnabled(false);
		numericButton7.setEnabled(false);
		numericButton8.setEnabled(false);
		numericButton9.setEnabled(false);
		numericButtonCancel.setEnabled(false);
		numericButtonDot.setEnabled(false);
		receiptComboBox.setEnabled(false);
		cancelTextButton.setEnabled(false);
		payButton.setEnabled(false);
		printReceiptButton.setEnabled(true);	
	}
	
	/**
	 * Prints the receipt to a text file and enables all the other Components
	 */
	public void printReceipt() {
		Table table = (Table)receiptComboBox.getSelectedItem();
		if (table == null) {return;}
		cashier.printReceipt(table, totalTextField.getText(), restTextField.getText(), cashTextField.getText());
		numericButton0.setEnabled(true);
		numericButton1.setEnabled(true);
		numericButton2.setEnabled(true);
		numericButton3.setEnabled(true);
		numericButton4.setEnabled(true);
		numericButton5.setEnabled(true);
		numericButton6.setEnabled(true);
		numericButton7.setEnabled(true);
		numericButton8.setEnabled(true);
		numericButton9.setEnabled(true);
		numericButtonCancel.setEnabled(true);
		numericButtonDot.setEnabled(true);
		receiptComboBox.setEnabled(true);
		cancelTextButton.setEnabled(true);
		payButton.setEnabled(true);
		restTextField.setText("0,00");
		totalTextField.setText("0,00");
		updateReceiptComboBox();
		updateTableModel();
		save();
	}
	
	/**
	 * Updates the display of the selected receipt on the Table
	 */
	public void updateTableModel() {
		dtm.setRowCount(0);
		double total = 0;
		Table table = (Table) receiptComboBox.getSelectedItem();
		if(table == null) {return;}
		for(DishOrdered dish: table.getTable()) {
			dtm.addRow(new Object[] { " ".repeat(5 - String.format("%d", dish.getQuantity()).length()) + dish.getQuantity(), dish, String.format("%6.2f", dish.getPrice())});
			total += dish.getPrice() * dish.getQuantity();
		}
		totalTextField.setText(String.format("%6.2f", total));
		restTextField.setText("0,00");
		cashTextField.setText("0,00");
	}
	
	/**
	 * Updates the panel when shown
	 */
	@Override
	public void update() {
		cashier.loadReceipts();
		updateReceiptComboBox();
		updateTableModel();
		numericButton0.setEnabled(true);
		numericButton1.setEnabled(true);
		numericButton2.setEnabled(true);
		numericButton3.setEnabled(true);
		numericButton4.setEnabled(true);
		numericButton5.setEnabled(true);
		numericButton6.setEnabled(true);
		numericButton7.setEnabled(true);
		numericButton8.setEnabled(true);
		numericButton9.setEnabled(true);
		numericButtonCancel.setEnabled(true);
		numericButtonDot.setEnabled(true);
		receiptComboBox.setEnabled(true);
		cancelTextButton.setEnabled(true);
		payButton.setEnabled(true);
	}
}

