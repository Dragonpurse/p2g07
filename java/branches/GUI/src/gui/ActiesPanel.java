package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import domein.Leertraject;
import domein.LeertrajectController;

public class ActiesPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LeertrajectController controller;
	private JButton bestaandOnderdeelToevoegenBtn;
	private JButton documentToevoegenBtn;
	private JButton casusToevoegenBtn;
	private JButton doosToevoegenBtn;
	private JButton stellingspelToevoegenBtn;

	public ActiesPanel(LeertrajectController controller) {
		super();
		initGUI(controller);
	}

	private void initGUI(LeertrajectController controller) {
		this.controller = controller;
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		MigLayout actiesPanelLayout = new MigLayout("","[220!]");
		this.setLayout(actiesPanelLayout);
		
		bestaandOnderdeelToevoegenBtn = new JButton("Bestaand onderdeel toevoegen");
		this.add(bestaandOnderdeelToevoegenBtn,"wrap,w 220!");
		
		
		documentToevoegenBtn = new JButton("Document toevoegen");
		documentToevoegenBtn.addActionListener(new Listener());
		this.add(documentToevoegenBtn,"wrap,w 220!");
		
		
		casusToevoegenBtn = new JButton("Casus toevoegen");
		this.add(casusToevoegenBtn,"wrap,w 220!");
		
		
		doosToevoegenBtn = new JButton("Doos toevoegen");
		this.add(doosToevoegenBtn,"wrap,w 220!");
		
		
		stellingspelToevoegenBtn = new JButton("Stellingspel toevoegen");
		this.add(stellingspelToevoegenBtn,"wrap,w 220!");
	}
	
	private class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(documentToevoegenBtn))
			openDocumentToevoegenFrame();
			
		}
		
		public void openDocumentToevoegenFrame(){
			if((controller.getGeselecteerdLeertraject() != null)){
				PopUpFrame documentToevoegenFrame = new PopUpFrame(new DocumentToevoegenPanel(controller));
				documentToevoegenFrame.setVisible(true);
			}
		}
		
	}

}
