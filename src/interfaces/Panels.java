package interfaces;

import javax.swing.JButton;

public interface Panels {
	public static final JButton buttons = new JButton();
	
	public void update();
	
	public void save();
	
	public void addComponents();
	
	public void setComponents();
}
