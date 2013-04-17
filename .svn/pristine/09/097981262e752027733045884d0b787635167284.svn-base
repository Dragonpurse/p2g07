package gui;


import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import domein.LeertrajectController;

public class OverzichtPane extends JTabbedPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5536438404284342402L;
	private NieuwPanel nieuwTraject;
	private TrajectDetailsPanel beheerTraject;
	private LeertrajectController controller;


	
	public OverzichtPane(LeertrajectController controller){
		this.controller = controller;
		initGUI();
	}

	private void initGUI(){
		
		nieuwTraject = new NieuwPanel(controller);
		
		beheerTraject = new TrajectDetailsPanel(controller);
		
		this.add("Nieuw", nieuwTraject);
		
		this.add("Beheer",beheerTraject);
	}

}
