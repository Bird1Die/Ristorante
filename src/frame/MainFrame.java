package frame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import panels.*;
import libs.*;

/**
 * The MainFrame is the JFrame we use to display the program.
 * It has a fixed size of (600, 340) that is not changeable with an absolute layout.
 * Two JPanels are added to display the top menu bar and the main page.
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int panelWidth = 600;
	private final int panelHeight = 340;
	private final int menuTopBorder = 40;
	
	private CardLayout crd;
	
	private Menu menu;
	private Receipt receipt;
	
	private Container pane = getContentPane();
	
	private SelectionPanel selectionPanel;
	private ChefPanel chefPanel;
	private MenuPanel menuPanel;
	private TopPanel topPanel;
	private WaiterPanel waiterPanel;
	private CooksPanel cooksPanel;
	private CashierPanel cashierPanel;
	
	private ImageIcon menuIcon = new ImageIcon("images/mainFrame_menuIcon.png");
	private ImageIcon cookIcon = new ImageIcon("images/mainFrame_cookIcon.png");
	private ImageIcon chefIcon = new ImageIcon("images/mainFrame_chefIcon.png");
	private ImageIcon waiterIcon = new ImageIcon("images/mainFrame_waiterIcon.png");
	private ImageIcon cashierIcon = new ImageIcon("images/mainFrame_cashierIcon.png");

	
	private JButton cooksButton; 
	private JButton chefButton;
	private JButton waiterButton;
	private JButton cashierButton;
	private JButton homeButton;
	
	/**
	 * Constructs a {@code MainFrame} object, sets all the
	 * parameters and adds all the components to it
	 * @param width
	 * @param height
	 */
	public MainFrame(int width, int height) {
		setResizable(false);
		setBounds(100, 100, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		crd = new CardLayout();
		getContentPane().setLayout(null);	
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		setLocation ( ( screenSize.width / 2 ) - ( getWidth ( ) / 2 ), (
		screenSize.height / 2 ) - ( getHeight ( ) / 2 ) );
		
		addPanels();
		addComponents();	
	}
	
	/**
	 * Initialize all the {@code JPanel} and adds them to the frame
	 */
	private void addPanels() {
		
		selectionPanel = new SelectionPanel(menuTopBorder, panelWidth, panelHeight);
		selectionPanel.setLayout(crd);
		pane.add(selectionPanel);
		
		chefPanel = new ChefPanel(menuTopBorder, panelWidth, panelHeight);	
		chefPanel.update();
		menu = chefPanel.getMenu();
		selectionPanel.add("Chef Panel", chefPanel);
			
		waiterPanel = new WaiterPanel(menuTopBorder, panelWidth, panelHeight);
		waiterPanel.update(menu, receipt);
		selectionPanel.add("Waiter Panel", waiterPanel);
			
		cooksPanel = new CooksPanel(menuTopBorder, panelWidth, panelHeight);
		selectionPanel.add("Cooks Panel", cooksPanel);
		
		cashierPanel = new CashierPanel(menuTopBorder, panelWidth, panelHeight);
		receipt = cashierPanel.getReceipt();
		selectionPanel.add("Cashier Panel", cashierPanel);
		
		topPanel = new TopPanel(panelWidth, menuTopBorder);	
		pane.add(topPanel);
		
		menuPanel = new MenuPanel(menuTopBorder, panelWidth, panelHeight);
		selectionPanel.add("Menu Panel", menuPanel);
		
		crd.show(selectionPanel, "Menu Panel");
	}
	
	/**
	 * Initialize all the {@code Component} and adds them to the respective panels
	 */
	private void addComponents() {	
		
		chefButton = new JButton();
		chefIcon.setImage(chefIcon.getImage().getScaledInstance(300, 150, java.awt.Image.SCALE_SMOOTH));
		chefButton.setIcon(chefIcon);
		chefButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoChef();
			}
		});	
		chefButton.setBackground(Color.WHITE);
		menuPanel.add(chefButton);
		
		waiterButton = new JButton();
		waiterButton.setBackground(Color.WHITE);
		waiterIcon.setImage(waiterIcon.getImage().getScaledInstance(300, 150, java.awt.Image.SCALE_SMOOTH));
		waiterButton.setIcon(waiterIcon);
		waiterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoWaiter();
			}
		});	
		menuPanel.add(waiterButton);
		
		cooksButton = new JButton();	
		cooksButton.setBackground(Color.WHITE);
		cookIcon.setImage(cookIcon.getImage().getScaledInstance(300, 150, java.awt.Image.SCALE_SMOOTH));
		cooksButton.setIcon(cookIcon);
		cooksButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoCooks();
			}
		});	
		menuPanel.add(cooksButton);
		
		cashierButton = new JButton();
		cashierButton.setBackground(Color.WHITE);
		cashierIcon.setImage(cashierIcon.getImage().getScaledInstance(300, 150, java.awt.Image.SCALE_SMOOTH));
		cashierButton.setIcon(cashierIcon);
		cashierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoCashier();
			}
		});	
		menuPanel.add(cashierButton);
		
		homeButton = new JButton();
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goHome();
			}
		});		
		homeButton.setBackground(Color.WHITE);
		menuIcon.setImage(menuIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
		homeButton.setIcon(menuIcon);
		homeButton.setBounds(0, 0, menuTopBorder, menuTopBorder);
		homeButton.setBorder(null);
		topPanel.add(homeButton);	
	}
	
	/**
	 * Handle the event from the homeButton and returns to the main panel
	 */
	private void goHome() {
		menu = chefPanel.getMenu();
		if(cooksPanel.isVisible()) {
			cooksPanel.save();
		}
		if(chefPanel.isVisible()) {
			chefPanel.save();
		}
		if(waiterPanel.isVisible()) {
			waiterPanel.save();
		}
		if(cashierPanel.isVisible()) {
			cashierPanel.save();
		}
		crd.show(selectionPanel, "Menu Panel");
	}
	
	/**
	 * Handle the event from the chefButton and shows the {@code ChefPanel}
	 */
	private void gotoChef() {
		crd.show(selectionPanel, "Chef Panel");
		chefPanel.update();
	}

	/**
	 * Handle the event from the chefButton and shows the {@code WaiterPanel}
	 */
	private void gotoWaiter() {
		crd.show(selectionPanel, "Waiter Panel");
		menu = chefPanel.getMenu();
		waiterPanel.update(menu, receipt);
	}
	
	/**
	 * Handle the event from the chefButton and shows the {@code CooksPanel}
	 */
	private void gotoCooks() {
		crd.show(selectionPanel, "Cooks Panel");
		cooksPanel.update();
	}
	
	/**
	 * Handle the event from the chefButton and shows the {@code CashierPanel}
	 */
	private void gotoCashier() {
		crd.show(selectionPanel, "Cashier Panel");
		cashierPanel.update();
	}
	
}