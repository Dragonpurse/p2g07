package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

import net.miginfocom.swing.MigLayout;

import org.joda.time.LocalDate;

import com.toedter.calendar.JDateChooser;

import domein.Document;
import domein.LeertrajectController;
import domein.OnderdelenFactory;
import domein.SoortOnderdeel;

public class DocumentToevoegenDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2008073591525594925L;
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
	private JButton documentAanmakenBtn;
	private JLabel locatieLbl;
	private JTextField locatieTxt;
	private JLabel displayNaamLabel;
	private JTextField displayNaamTxt;
	private JLabel displayOmschrijvingLabel;
	private JScrollPane displayOmschrijvingScroll;
	private JTextArea displayOmschrijvingTxtA;
	
	public DocumentToevoegenDialog(LeertrajectController controller){
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
		contentPanel.add(displayNaamTxt,"wrap,growx");
		
		displayOmschrijvingLabel = new JLabel("Displayomschrijving");
		displayOmschrijvingScroll = new JScrollPane();
		displayOmschrijvingScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		displayOmschrijvingTxtA = new JTextArea();
		displayOmschrijvingTxtA.setLineWrap(true);
		displayOmschrijvingTxtA.setWrapStyleWord(true);
		displayOmschrijvingScroll.setViewportView(displayOmschrijvingTxtA);
		contentPanel.add(displayOmschrijvingLabel);
		contentPanel.add(displayOmschrijvingScroll,"wrap,growx,growy");
		
		titelLabel = new JLabel("titel");
		titelInhoudTxt = new JTextField();
		contentPanel.add(titelLabel);
		contentPanel.add(titelInhoudTxt,"wrap,growx");
		
		omschrijvingLabel = new JLabel("Omschrijving");
		omschrijvingInhoudScroll = new JScrollPane();
		omschrijvingInhoudScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		omschrijvingInhoudTxtA = new JTextArea();
		omschrijvingInhoudTxtA.setLineWrap(true);
		omschrijvingInhoudTxtA.setWrapStyleWord(true);
		omschrijvingInhoudScroll.setViewportView(omschrijvingInhoudTxtA);
		contentPanel.add(omschrijvingLabel);
		contentPanel.add(omschrijvingInhoudScroll,"wrap,growx,growy");
		
		kernwoordenLabel = new JLabel("Kernwoorden");
		kernwoordenCbx = new JComboBox<>();
		contentPanel.add(kernwoordenLabel);
		contentPanel.add(kernwoordenCbx,"wrap,growx");

		kernwoordenTxt = new JTextField();
		kernwoordenToevoegenBtn = new JButton("Kernwoord toevoegen");
		kernwoordenToevoegenBtn.addActionListener(new Listener());
		contentPanel.add(kernwoordenToevoegenBtn);
		contentPanel.add(kernwoordenTxt,"growx, wrap");
		
		doelgroepLbl = new JLabel("Doelgroep");
		doelgroepTxt = new JTextField();
		contentPanel.add(doelgroepLbl);
		contentPanel.add(doelgroepTxt,"wrap,growx");
		
		beginDatumLbl = new JLabel("Begindatum");
		beschikbaarVanField = new JDateChooser();
		beschikbaarVanField.getCalendarButton().setFocusCycleRoot(false);
		beschikbaarVanField.setDateFormatString("dd/MM/yyyy");
		beschikbaarVanField.setDate(controller.getGeselecteerdLeertraject().getBeschikbaarVan().toDate());
		contentPanel.add(beginDatumLbl);
		contentPanel.add(beschikbaarVanField,"wrap,growx");
		
		eindDatumLbl = new JLabel("Einddatum");
		beschikbaarTotField = new JDateChooser();
		beschikbaarTotField.getCalendarButton().setFocusCycleRoot(false);
		beschikbaarTotField.setDateFormatString("dd/MM/yyyy");
		beschikbaarTotField.setDate(controller.getGeselecteerdLeertraject().getBeschikbaarTot().toDate());
		contentPanel.add(eindDatumLbl);
		contentPanel.add(beschikbaarTotField,"wrap,growx");
		
		locatieLbl = new JLabel("Locatie");
		locatieTxt = new JTextField();
		contentPanel.add(locatieLbl);
		contentPanel.add(locatieTxt,"wrap,growx");
		
		documentAanmakenBtn = new JButton("Aanmaken");
		documentAanmakenBtn.addActionListener(new Listener());
		contentPanel.add(documentAanmakenBtn,"span, right");
		
		this.add(contentPanel);
		
		}
	
	private class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(documentAanmakenBtn))
				maakDocumentAan();

			if(e.getSource().equals(kernwoordenToevoegenBtn))
				voegKernwoordToe();
			
		}
		
		private void voegKernwoordToe() {
			kernwoordenCbx.addItem(kernwoordenTxt.getText());
			
		}

		private void maakDocumentAan(){
			if(validatieInvulVelden()){
				String displayNaam = displayNaamTxt.getText();
				String displayOmschrijving = displayOmschrijvingTxtA.getText();
				String titel = titelInhoudTxt.getText();
				String omschrijving = omschrijvingInhoudTxtA.getText();
				String doelgroep = doelgroepTxt.getText();
				List<String> kernwoorden = new ArrayList<>();
				for(int i = 0; i < kernwoordenCbx.getItemCount(); i++){
					kernwoorden.add(kernwoordenCbx.getItemAt(i));
				}
				LocalDate beschikbaarVan = LocalDate.fromDateFields(beschikbaarVanField.getDate());
				LocalDate beschikbaarTot = LocalDate.fromDateFields(beschikbaarTotField.getDate());
				String locatie = locatieTxt.getText();
				
				Document nieuwDocument = (Document)OnderdelenFactory.maakDocumentAan(displayNaam, displayOmschrijving, titel, omschrijving, doelgroep, kernwoorden, beschikbaarVan, beschikbaarTot, locatie);
				controller.save(nieuwDocument);
				controller.linkOnderdeel(nieuwDocument);
				dispose();
			}
		}
		
		private boolean validatieInvulVelden(){ 
			boolean validatie = true;
			if(displayNaamTxt.getText().isEmpty()){
				validatie = false;
				displayNaamTxt.setBorder(BorderFactory.createLineBorder(Color.RED));
			} else displayNaamTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			
			if(displayOmschrijvingTxtA.getText().isEmpty()){
				validatie = false;
				displayOmschrijvingScroll.setBorder(BorderFactory.createLineBorder(Color.RED));
			} else displayOmschrijvingScroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			
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
			
			if(locatieTxt.getText().isEmpty()){
				validatie = false;
				locatieTxt.setBorder(BorderFactory.createLineBorder(Color.RED));
			} else locatieTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			
			return validatie;
			
		}
		
	}

}
