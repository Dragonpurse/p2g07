package gui;


import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;
import domein.LeertrajectController;
import domein.pattern.observer.Observer;

public class OverzichtPane extends JTabbedPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5536438404284342402L;
	private JPanel nieuwTraject;
	private JPanel beheerTraject;
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
