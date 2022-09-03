package program;

import java.awt.EventQueue;

import javax.swing.*;

import frame.*;

/**
 * This class contains the main function that
 * creates a instance of the {@code MainFrame}
 *
 */
public class Main {
	
	private int width = 600;
	private int height = 379;
	private JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}
	public Main() {
		frame = new MainFrame(width, height);	
	}	
}