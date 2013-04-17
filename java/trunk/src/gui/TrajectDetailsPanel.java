package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import domein.LeertrajectController;
import domein.pattern.observer.Observer;

public class TrajectDetailsPanel extends JPanel implements Observer  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField titelInhoudTxt;
	private JScrollPane omschrijvingInhoudScroll;
	private JTextArea omschrijvingInhoudTxt;
	private LeertrajectController controller;
	

	public TrajectDetailsPanel(LeertrajectController controller) {
		super();
		initGUI(controller);
	}

	private void initGUI(LeertrajectController controller) {
		this.controller = controller;
		this.setBackground(new Color(250,250,250));
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		
		controller.addObserver(this);
		MigLayout trajectDetailsLayout = new MigLayout("","[grow,fill]","[][][][100::,fill,grow]");
		this.setLayout(trajectDetailsLayout);
		
		this.addSeparator("titel");
		
		titelInhoudTxt = new JTextField();
		titelInhoudTxt.setEditable(false);
		this.add(titelInhoudTxt,"wrap,growx");
		
		this.addSeparator("omschrijving");
		
		omschrijvingInhoudScroll = new JScrollPane();
		omschrijvingInhoudTxt = new JTextArea();
		omschrijvingInhoudTxt.setEditable(false);
		omschrijvingInhoudScroll.setViewportView(omschrijvingInhoudTxt);
		this.add(omschrijvingInhoudScroll,"wrap,growx,growy");
		
	}

	private void addSeparator(String text)
	{
		JLabel l = new JLabel(text);
		l.setForeground(new Color(35,100,240));

		this.add(l, "gapbottom 1, span, split 2, aligny center");
		this.add((new JSeparator()), "gapleft rel, growx");
	}
	
	@Override
	public void update(Object object) {
		titelInhoudTxt.setText(controller.getGeselecteerdLeertraject().getTitel());
		omschrijvingInhoudTxt.setText(controller.getGeselecteerdLeertraject().getOmschrijving());
	}


}
