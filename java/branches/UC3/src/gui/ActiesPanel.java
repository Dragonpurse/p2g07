package gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import domein.LeertrajectController;

public class ActiesPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LeertrajectController controller;

	public ActiesPanel(LeertrajectController controller) {
		super();
		initGUI(controller);
	}

	private void initGUI(LeertrajectController controller) {
		this.controller = controller;
		this.setPreferredSize(new Dimension(0,50));
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(boxLayout);
		this.add(Box.createRigidArea(new Dimension(20,0)));
		this.add(new JButton("Bestaand onderdeel toevoegen"));
		this.add(Box.createRigidArea(new Dimension(10,0)));
		this.add(new JButton("Document toevoegen"));
		this.add(Box.createRigidArea(new Dimension(10,0)));
		this.add(new JButton("Casus toevoegen"));
		this.add(Box.createRigidArea(new Dimension(10,0)));
		this.add(new JButton("Doos toevoegen"));
		this.add(Box.createRigidArea(new Dimension(10,0)));
		this.add(new JButton("Stellingspel toevoegen"));
		
	}

}
