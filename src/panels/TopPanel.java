package panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;


/**
 * A {@code TopPanel} is used to contain the menu button and show the current date
 */
public class TopPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel timeLabel;
	
	private Timer timer;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");

	/**
	 * Constructs a {@code TopPanel} with the given dimension and
	 * sets the date text
	 * @param panelWidth
	 * @param menuTopBorder
	 */
	public TopPanel(int panelWidth, int menuTopBorder) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setBounds(0, 0, panelWidth, menuTopBorder);
		setLayout(null);
		setBorder(null);
				
		timeLabel = new JLabel();
		timeLabel.setText(sdf.format(new Date(System.currentTimeMillis())));
		timeLabel.setBounds(440,10,175,20);
		add(timeLabel);
		
		timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              updateTime();
            }
         });
		
		timer.setRepeats(true);
		timer.start();
	}
	
	/**
	 * Updates the current time every 0.5 seconds
	 */
	public void updateTime() {
		timeLabel.setText(sdf.format(new Date(System.currentTimeMillis())));
	}

}
