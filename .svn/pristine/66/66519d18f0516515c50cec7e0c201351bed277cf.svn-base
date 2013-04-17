package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.joda.time.LocalDate;

import net.miginfocom.swing.MigLayout;

import com.toedter.calendar.JDateChooser;

import domein.LeertrajectController;

public class StellingspelToevoegenDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4050643586921134140L;
	private LeertrajectController controller;
	private JLabel titelLabel;
	private JScrollPane omschrijvingInhoudScroll;
	private JLabel omschrijvingLabel;
	private JTextField titelInhoudTxt;
	private JTextArea omschrijvingInhoudTxtA;
	private JLabel kernwoordenLabel;
	private JComboBox<String> kernwoordenCbx;
	private JTextField kernwoordenTxt;
	private JButton kernwoordenToevoegenBtn;
	private JLabel doelgroepLbl;
	private JTextField doelgroepTxt;
	private JLabel beginDatumLbl;
	private JDateChooser beschikbaarVanField;
	private JLabel eindDatumLbl;
	private JDateChooser beschikbaarTotField;
	private JButton stellingspelAanmakenBtn;
	private JLabel displayNaamLabel;
	private JTextField displayNaamTxt;
	private JLabel displayOmschrijvingLabel;
	private JScrollPane displayOmschrijvingScroll;
	private JTextArea displayOmschrijvingTxtA;
	private JButton kernwoordVerwijderenBtn;
	public StellingspelToevoegenDialog(LeertrajectController controller) {
		super();
		this.controller = controller;
		initGUI();
		
	}
	private void initGUI() {
		JPanel contentPanel = new JPanel(new MigLayout("","[pref!][grow,fill][pref!]","[][grow,fill,50::200][][grow,fill,50::200][][][][][][][]"));
		this.setSize(new Dimension(500,500));
		this.setResizable(false);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		
		displayNaamLabel = new JLabel("Displaynaam");
		displayNaamTxt = new JTextField();
		contentPanel.add(displayNaamLabel);
		contentPanel.add(displayNaamTxt,"wrap,growx, spanx");
		
		displayOmschrijvingLabel = new JLabel("Displayomschrijving");
		displayOmschrijvingScroll = new JScrollPane();
		displayOmschrijvingScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		displayOmschrijvingTxtA = new JTextArea();
		displayOmschrijvingTxtA.setLineWrap(true);
		displayOmschrijvingTxtA.setWrapStyleWord(true);
		displayOmschrijvingScroll.setViewportView(displayOmschrijvingTxtA);
		contentPanel.add(displayOmschrijvingLabel);
		contentPanel.add(displayOmschrijvingScroll,"wrap,growx,growy, spanx");
		
		titelLabel = new JLabel("titel");
		titelInhoudTxt = new JTextField();
		contentPanel.add(titelLabel);
		contentPanel.add(titelInhoudTxt,"wrap,growx, spanx");
		
		omschrijvingLabel = new JLabel("Omschrijving");
		omschrijvingInhoudScroll = new JScrollPane();
		omschrijvingInhoudScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		omschrijvingInhoudTxtA = new JTextArea();
		omschrijvingInhoudTxtA.setLineWrap(true);
		omschrijvingInhoudTxtA.setWrapStyleWord(true);
		omschrijvingInhoudScroll.setViewportView(omschrijvingInhoudTxtA);
		contentPanel.add(omschrijvingLabel);
		contentPanel.add(omschrijvingInhoudScroll,"wrap,growx,growy, spanx");
		
		kernwoordenLabel = new JLabel("Kernwoorden");
		kernwoordenCbx = new JComboBox<>();
		kernwoordVerwijderenBtn = new  JButton("Kernwoord verwijderen");
		kernwoordVerwijderenBtn.addActionListener(new Listener());
		contentPanel.add(kernwoordenLabel);
		contentPanel.add(kernwoordenCbx, "growx");
		contentPanel.add(kernwoordVerwijderenBtn,"wrap");

		kernwoordenTxt = new JTextField();
		kernwoordenToevoegenBtn = new JButton("Kernwoord toevoegen");
		kernwoordenToevoegenBtn.addActionListener(new Listener());
		contentPanel.add(kernwoordenTxt,"growx, skip");
		contentPanel.add(kernwoordenToevoegenBtn,"wrap");
		
		doelgroepLbl = new JLabel("Doelgroep");
		doelgroepTxt = new JTextField();
		contentPanel.add(doelgroepLbl);
		contentPanel.add(doelgroepTxt,"wrap,growx, spanx");
		
		beginDatumLbl = new JLabel("Begindatum");
		beschikbaarVanField = new JDateChooser();
		beschikbaarVanField.getCalendarButton().setFocusCycleRoot(false);
		beschikbaarVanField.setDateFormatString("dd/MM/yyyy");
		beschikbaarVanField.setDate(controller.getGeselecteerdLeertraject().getBeschikbaarVan().toDate());
		contentPanel.add(beginDatumLbl);
		contentPanel.add(beschikbaarVanField,"wrap,growx, spanx");
		
		eindDatumLbl = new JLabel("Einddatum");
		beschikbaarTotField = new JDateChooser();
		beschikbaarTotField.getCalendarButton().setFocusCycleRoot(false);
		beschikbaarTotField.setDateFormatString("dd/MM/yyyy");
		beschikbaarTotField.setDate(controller.getGeselecteerdLeertraject().getBeschikbaarTot().toDate());
		contentPanel.add(eindDatumLbl);
		contentPanel.add(beschikbaarTotField,"wrap,growx, spanx");
		
		stellingspelAanmakenBtn = new JButton("Aanmaken");
		stellingspelAanmakenBtn.addActionListener(new Listener());
		contentPanel.add(stellingspelAanmakenBtn,"span, right");
		
		this.add(contentPanel);
		
	}
	
	private boolean validatieInvulVelden(){ 
		boolean validatie = true;
		
		if(titelInhoudTxt.getText().isEmpty() || !titelInhoudTxt.getText().matches("[a-zA-Z0-9À-ž\\s-]*")) {
			validatie = false;
			titelInhoudTxt.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else titelInhoudTxt.setBorder(UIManager.getBorder("TextField.border"));
		
		if(omschrijvingInhoudTxtA.getText().isEmpty()) {
			validatie = false;
			omschrijvingInhoudScroll.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else omschrijvingInhoudScroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(doelgroepTxt.getText().isEmpty()) {
			validatie = false;
			doelgroepTxt.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else doelgroepTxt.setBorder(UIManager.getBorder("TextField.border"));
		
		if(beschikbaarVanField.getDate() == null){
			validatie = false;
			beschikbaarVanField.getDateEditor().getUiComponent().setBorder(BorderFactory.createLineBorder(Color.RED));
		} else beschikbaarVanField.getDateEditor().getUiComponent().setBorder(UIManager.getBorder("TextField.border"));
		
		if(beschikbaarTotField.getDate() == null 
				|| LocalDate.fromDateFields(beschikbaarTotField.getDate()).isBefore(LocalDate.fromDateFields(beschikbaarVanField.getDate()))){
			validatie = false;
			beschikbaarTotField.getDateEditor().getUiComponent().setBorder(BorderFactory.createLineBorder(Color.RED));
		} else beschikbaarTotField.getDateEditor().getUiComponent().setBorder(UIManager.getBorder("Textfield.border"));
		
		return validatie;
		
	}
	
	private class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(stellingspelAanmakenBtn))
				maakStellingspelAan();

			if(e.getSource().equals(kernwoordenToevoegenBtn))
				voegKernwoordToe();
			
			if(e.getSource().equals(kernwoordVerwijderenBtn))
				verwijderKernwoord();
			
		}
		
		private void verwijderKernwoord() {
			kernwoordenCbx.removeItem(kernwoordenCbx.getSelectedItem());
			
		}
		
		private void voegKernwoordToe() {
			kernwoordenCbx.addItem(kernwoordenTxt.getText());
			
		}
				
		private void maakStellingspelAan() {
			// TODO Auto-generated method stub
			
		}


	}
	
	

}
