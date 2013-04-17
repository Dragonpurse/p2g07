package gui;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class OnderdeelToevoegenFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel onderdeelToevoegenPanel;


	private JPanel jPanel1;

		
	public OnderdeelToevoegenFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				
			}
			{
				onderdeelToevoegenPanel = new OnderdeelToevoegenPanel();
				getContentPane().add(onderdeelToevoegenPanel, BorderLayout.CENTER);
			}
			pack();
			this.setSize(436, 324);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
