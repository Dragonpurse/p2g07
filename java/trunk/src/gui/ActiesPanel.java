package gui;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import domein.LeertrajectController;

public class ActiesPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LeertrajectController controller;
	private JButton documentToevoegenBtn;
	private JButton casusToevoegenBtn;
	private JButton doosToevoegenBtn;
	private JButton stellingspelToevoegenBtn;
	
	private JDialog doosToevoegenDialog;
	private JDialog casusToevoegenDialog;
	private JDialog stellingspelToevoegenDialog;
	private JDialog documentToevoegenDialog;

	public ActiesPanel(LeertrajectController controller) {
		super();
		initGUI(controller);
	}

	private void initGUI(LeertrajectController controller) {
		this.controller = controller;
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		MigLayout actiesPanelLayout = new MigLayout("","[220!]");
		this.setLayout(actiesPanelLayout);
		
		documentToevoegenBtn = new JButton("Document toevoegen");
		documentToevoegenBtn.addActionListener(new Listener());
		this.add(documentToevoegenBtn,"wrap,w 220!");	
		
		casusToevoegenBtn = new JButton("Casus toevoegen");
		casusToevoegenBtn.addActionListener(new Listener());
		this.add(casusToevoegenBtn,"wrap,w 220!");
				
		doosToevoegenBtn = new JButton("Doos toevoegen");
		doosToevoegenBtn.addActionListener(new Listener());
		this.add(doosToevoegenBtn,"wrap,w 220!");		
		
		stellingspelToevoegenBtn = new JButton("Stellingspel toevoegen");
		stellingspelToevoegenBtn.addActionListener(new Listener());
		this.add(stellingspelToevoegenBtn,"wrap,w 220!");
	}
	
	private class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(documentToevoegenBtn))
			openDocumentToevoegenFrame();
			if(e.getSource().equals(stellingspelToevoegenBtn))
				openStellingspelToevoegenFrame();
			if(e.getSource().equals(casusToevoegenBtn))
				openCasusToevoegenFrame();
			if(e.getSource().equals(doosToevoegenBtn))
				openDoosToevoegenFrame();
			
		}
		
		private void openDoosToevoegenFrame() {
			if((controller.getGeselecteerdLeertraject() != null)){
				doosToevoegenDialog = new DoosToevoegenDialog(controller);
				doosToevoegenDialog.setVisible(true);
			}
			
		}

		private void openCasusToevoegenFrame() {
			if((controller.getGeselecteerdLeertraject() != null)){
				casusToevoegenDialog = new CasusToevoegenDialog(controller);
			    casusToevoegenDialog.setVisible(true);
			}
			
		}

		private void openStellingspelToevoegenFrame() {
			if((controller.getGeselecteerdLeertraject() != null)){
				stellingspelToevoegenDialog = new StellingspelToevoegenDialog(controller);
				stellingspelToevoegenDialog.setVisible(true);
			}
			
		}

		private void openDocumentToevoegenFrame(){
			if((controller.getGeselecteerdLeertraject() != null)){
				documentToevoegenDialog = new DocumentToevoegenDialog(controller);
				documentToevoegenDialog.setVisible(true);
			}
		}
		
	}

}
