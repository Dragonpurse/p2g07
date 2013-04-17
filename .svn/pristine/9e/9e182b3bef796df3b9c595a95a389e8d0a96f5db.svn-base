package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import domein.LeertrajectController;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LeertrajectController controller;
	
	public MainFrame(LeertrajectController controller ){
		super();
		this.controller = controller;
		initGUI();	
	}
	
	private void initGUI() {
		try{
			
			JPanel BeheerLeertrajectPanel = new BeheerLeertrajectPanel(controller);
			getContentPane().add(BeheerLeertrajectPanel);
			this.setSize(BeheerLeertrajectPanel.getPreferredSize());
			
		}
		catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
		
	}
	




}
