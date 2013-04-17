package gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import domein.LeertrajectController;

import net.miginfocom.swing.MigLayout;

public class TrajectToevoegenPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5459973223237746554L;
	private LeertrajectController controller;

	public TrajectToevoegenPanel(LeertrajectController controller){
		super();
		initGUI(controller);
	}

	private void initGUI(LeertrajectController controller) {
		this.controller = controller;
		this.setPreferredSize(new Dimension(500,400));
		
		MigLayout trajectToevoegenPanelLayout = new MigLayout("","[pref]50[grow,fill,200::]");
		this.setLayout(trajectToevoegenPanelLayout);
		
		JLabel leertrajectCodeLbl = new JLabel("Code leertraject");
		JTextField leertrajectCodeTxt = new JTextField();
		this.add(leertrajectCodeLbl);
		this.add(leertrajectCodeTxt,"wrap");
		
		JLabel titelLbl = new JLabel("Titel");
		JTextField titelTxt = new JTextField();
		this.add(titelLbl);
		this.add(titelTxt,"wrap");
		
		JLabel omschrijvingLbl = new JLabel("Omschrijving");
		JScrollPane omschrijvingScroll = new JScrollPane();
		JTextArea omschrijvingTxtA = new JTextArea();
		omschrijvingScroll.setViewportView(omschrijvingTxtA);
		omschrijvingScroll.setPreferredSize(new Dimension(0,100));
		this.add(omschrijvingLbl);
		this.add(omschrijvingScroll,"wrap");
		
		JLabel startDatumLbl = new JLabel("Startdatum");
		JTextField startDatumTxt = new JTextField();
		this.add(startDatumLbl);
		this.add(startDatumTxt,"wrap");
		
		JLabel doelgroepLbl = new JLabel("Doelgroep");
		JTextField doelgroepTxt = new JTextField();
		this.add(doelgroepLbl);
		this.add(doelgroepTxt,"wrap");
		
		JLabel beschikbaarheidVanLbl = new JLabel("Beschikbaar van");
		JTextField beschikbaarheidVanTxt = new JTextField();
		this.add(beschikbaarheidVanLbl);
		this.add(beschikbaarheidVanTxt,"wrap");
		
		JLabel beschikbaarheidTotLbl = new JLabel("Beschikbaar tot");
		JTextField beschikbaarheidTotTxt = new JTextField();
		this.add(beschikbaarheidTotLbl);
		this.add(beschikbaarheidTotTxt,"wrap");
		
		JButton aanmakenBtn = new JButton("Aanmaken");
		this.add(aanmakenBtn,"span, right");
		
		
		
	}

}
