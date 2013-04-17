package gui;

import java.awt.Color;
import java.awt.ScrollPane;
import java.util.Observable;
import java.util.Observer;

import javax.rmi.CORBA.Tie;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import domein.Controller;
import domein.LeertrajectController;

public class TrajectDetailsPanel extends JPanel implements Observer  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel titelLabel;
	private JTextField titelInhoudLabel;
	private JLabel omschrijvingLabel;
	private JScrollPane omschrijvingInhoudScroll;
	private JTextArea omschrijvingInhoudLabel;
	private LeertrajectController controller;
	

	public TrajectDetailsPanel(LeertrajectController controller) {
		super();
		initGUI(controller);
	}

	private void initGUI(LeertrajectController controller) {
		this.controller = controller;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		controller.addObserver(this);
		MigLayout trajectDetailsLayout = new MigLayout("","[grow,fill]","[][][][100::,fill,grow]");
		this.setLayout(trajectDetailsLayout);
		
		titelLabel = new JLabel("Titel");
		this.add(titelLabel,"wrap");
		
		titelInhoudLabel = new JTextField();
		titelInhoudLabel.setEditable(false);
		this.add(titelInhoudLabel,"wrap,growx");
		
		omschrijvingLabel = new JLabel("Omschrijving");
		this.add(omschrijvingLabel,"wrap");
		
		omschrijvingInhoudScroll = new JScrollPane();
		omschrijvingInhoudLabel = new JTextArea();
		omschrijvingInhoudLabel.setEditable(false);
		omschrijvingInhoudScroll.setViewportView(omschrijvingInhoudLabel);
		this.add(omschrijvingInhoudScroll,"wrap,growx,growy");
		
	}

	@Override
	public void update(Observable o, Object arg) {
		titelInhoudLabel.setText(controller.getGeselecteerdLeertraject().getTitel());
		omschrijvingInhoudLabel.setText(controller.getGeselecteerdLeertraject().getOmschrijving());
		
		
	}


}
