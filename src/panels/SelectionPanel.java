package panels;

import javax.swing.JPanel;

/**
 * A {@code SelectionPanel} is used to contain all other panels \
 * and with his {@code CardLayout} choose which one to show
 */
public class SelectionPanel extends JPanel{

		private static final long serialVersionUID = 1L;

		/**
		 * Constructs a {@code SelectionPanel} object with the given dimension
		 * @param menuTopBorder
		 * @param panelWidth
		 * @param panelHeight
		 */
		public SelectionPanel(int menuTopBorder, int panelWidth, int panelHeight) {
			setBorder(null);
			setBounds(0, menuTopBorder, panelWidth, panelHeight - menuTopBorder);
			setVisible(true);		
		}			
}