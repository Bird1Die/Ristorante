package panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * A {@code MenuPanel} is used to contain all the button that 
 * leads to the other panels
 *
 */
public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a {@code MenuPanel} with the given dimension
	 * @param menuTopBorder
	 * @param panelWidth
	 * @param panelHeight
	 */
	public MenuPanel(int menuTopBorder, int panelWidth, int panelHeight) {
		setBorder(null);
		setBounds(0, menuTopBorder, panelWidth, panelHeight - menuTopBorder);
		setVisible(true);		
		setLayout(new GridLayout(2, 2, 0, 0));

	}

	public void update() {
	}
}
