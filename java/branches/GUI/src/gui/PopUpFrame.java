package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PopUpFrame extends JFrame {
	public PopUpFrame(JPanel panel){
		super();
		initGUI(panel);
	}

	private void initGUI(JPanel panel) {
		this.getContentPane().add(panel);
		this.setSize(panel.getPreferredSize());
		
	}

}
