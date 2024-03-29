package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

import org.joda.time.LocalDate;

import com.toedter.calendar.JDateChooser;

import domein.CasusAntwoord;
import domein.LeertrajectController;
import domein.CasusVraag;
import domein.builders.CasusBuilder;

public class CasusToevoegenDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8754400591874045350L;
	private JPanel algemeenPanel;
	private JPanel vragenAanmakenPanel;
	private JPanel casusAanmakenPanel;
	private JPanel overzichtPanel;
	private JPanel antwoordenAanmakenPanel;
	
	private JDialog antwoordAanmakenDialog;
	
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
	private JButton casusAanmakenBtn;
	private JLabel displayNaamLabel;
	private JTextField displayNaamTxt;
	private JLabel displayOmschrijvingLabel;
	private JScrollPane displayOmschrijvingScroll;
	private JTextArea displayOmschrijvingTxtA;
	private JButton kernwoordVerwijderenBtn;
	private JScrollPane vraagTekstScroll;
	private JLabel vraagTekstLbl;
	private JList<CasusVraag> vragenJList;
	private JLabel vragenLbl;
	private JTextField eersteVraagTxt;
	private JLabel eersteVraagLbl;
	private JTextArea vraagTekstTxtA;
	private JLabel antwoordenLbl;
	private JList<CasusAntwoord> antwoordenJList;
	private JLabel antwoordTekstLbl;
	private JScrollPane antwoordTekstScroll;
	private JTextArea antwoordTekstTxtA;
	private JLabel antwoordBeschrijvingLbl;
	private JButton antwoordAanmakenBtn;
	private JButton antwoordVerwijderenBtn;
	private JScrollPane antwoordBeschrijvingScroll;
	private JTextArea antwoordBeschrijvingTxtA;
	private JButton vraagAanmakenBtn;
	private JButton vraagVerwijderenBtn;
	private JLabel antwoordLinkLbl;
	private JComboBox<CasusVraag> antwoordLinkCbx;
	private JScrollPane antwoordenScroll;
	private JScrollPane vragenScroll;
	private JLabel situatieSchetsLabel;
	private JScrollPane situatieSchetsScroll;
	private JTextArea situatieSchetsTxtA;
	private JLabel locatieIntroLabel;
	private JTextField locatieIntroTxt;
	private JButton volgendeBtn;
	private DefaultListModel<CasusVraag> vragenListModel;
	private DefaultListModel<CasusAntwoord> antwoordenListModel;
	private JButton openAntwoordAanmakenDialogBtn;
	private JButton eersteVraagBtn;
	
	public CasusToevoegenDialog(LeertrajectController controller) {
		super();
		this.controller = controller;
		initGUI();
		
	}
	private void initGUI() {
		this.setResizable(false);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		
		
		
		// Panel algemene attributen
		algemeenPanel = new JPanel(new MigLayout("","[pref!][grow,fill][fill, pref!]","[]5"));
        algemeenPanel.setPreferredSize(new Dimension(500,500));
		Utility.addSeparator("Algemeen", algemeenPanel);
		
		displayNaamLabel = new JLabel("Displaynaam");
		displayNaamTxt = new JTextField();
		algemeenPanel.add(displayNaamLabel);
		algemeenPanel.add(displayNaamTxt,"wrap,growx, spanx");
		
		displayOmschrijvingLabel = new JLabel("Displayomschrijving");
		displayOmschrijvingScroll = new JScrollPane();
		displayOmschrijvingScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		displayOmschrijvingTxtA = new JTextArea();
		displayOmschrijvingTxtA.setLineWrap(true);
		displayOmschrijvingTxtA.setWrapStyleWord(true);
		displayOmschrijvingScroll.setViewportView(displayOmschrijvingTxtA);
		algemeenPanel.add(displayOmschrijvingLabel);
		algemeenPanel.add(displayOmschrijvingScroll,"wrap,growx,growy, spanx, h 50");
		
		titelLabel = new JLabel("titel");
		titelInhoudTxt = new JTextField();
		algemeenPanel.add(titelLabel);
		algemeenPanel.add(titelInhoudTxt,"wrap,growx, spanx");
		
		omschrijvingLabel = new JLabel("Omschrijving");
		omschrijvingInhoudScroll = new JScrollPane();
		omschrijvingInhoudScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		omschrijvingInhoudTxtA = new JTextArea();
		omschrijvingInhoudTxtA.setLineWrap(true);
		omschrijvingInhoudTxtA.setWrapStyleWord(true);
		omschrijvingInhoudScroll.setViewportView(omschrijvingInhoudTxtA);
		algemeenPanel.add(omschrijvingLabel);
		algemeenPanel.add(omschrijvingInhoudScroll,"wrap,growx,growy, spanx, h 50");
		
		situatieSchetsLabel = new JLabel("Situatieschets");
		situatieSchetsScroll = new JScrollPane();
		situatieSchetsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		situatieSchetsTxtA = new JTextArea();
		situatieSchetsTxtA.setLineWrap(true);
		situatieSchetsTxtA.setWrapStyleWord(true);
		situatieSchetsScroll.setViewportView(situatieSchetsTxtA);
		algemeenPanel.add(situatieSchetsLabel);
		algemeenPanel.add(situatieSchetsScroll,"wrap, grow, spanx, h 50");
		
		locatieIntroLabel = new JLabel("Locatie intro");
		locatieIntroTxt = new JTextField();
		algemeenPanel.add(locatieIntroLabel);
		algemeenPanel.add(locatieIntroTxt,"wrap, growx, spanx");
		
		
		kernwoordenLabel = new JLabel("Kernwoorden");
		kernwoordenCbx = new JComboBox<>();
		kernwoordVerwijderenBtn = new  JButton("Verwijderen");
		kernwoordVerwijderenBtn.addActionListener(new Listener());
		algemeenPanel.add(kernwoordenLabel);
		algemeenPanel.add(kernwoordenCbx, "growx");
		algemeenPanel.add(kernwoordVerwijderenBtn,"wrap");

		kernwoordenTxt = new JTextField();
		kernwoordenToevoegenBtn = new JButton("Toevoegen");
		kernwoordenToevoegenBtn.addActionListener(new Listener());
		algemeenPanel.add(kernwoordenTxt,"growx, skip");
		algemeenPanel.add(kernwoordenToevoegenBtn,"wrap");
		
		doelgroepLbl = new JLabel("Doelgroep");
		doelgroepTxt = new JTextField();
		algemeenPanel.add(doelgroepLbl);
		algemeenPanel.add(doelgroepTxt,"wrap,growx, spanx");
		
		beginDatumLbl = new JLabel("Begindatum");
		beschikbaarVanField = new JDateChooser();
		beschikbaarVanField.getCalendarButton().setFocusCycleRoot(false);
		beschikbaarVanField.setDateFormatString("dd/MM/yyyy");
		beschikbaarVanField.setDate(controller.getGeselecteerdLeertraject().getBeschikbaarVan().toDate());
		algemeenPanel.add(beginDatumLbl);
		algemeenPanel.add(beschikbaarVanField,"wrap,growx, spanx");
		
		eindDatumLbl = new JLabel("Einddatum");
		beschikbaarTotField = new JDateChooser();
		beschikbaarTotField.getCalendarButton().setFocusCycleRoot(false);
		beschikbaarTotField.setDateFormatString("dd/MM/yyyy");
		beschikbaarTotField.setDate(controller.getGeselecteerdLeertraject().getBeschikbaarTot().toDate());
		algemeenPanel.add(eindDatumLbl);
		algemeenPanel.add(beschikbaarTotField,"wrap,growx, spanx");
		
		volgendeBtn = new JButton("Volgende");
		volgendeBtn.addActionListener(new Listener());
		algemeenPanel.add(volgendeBtn,"spanx, right");
		
		
		
		
		// Panel aanmaken casus	
		casusAanmakenPanel = new JPanel(new MigLayout("","[400!, fill, grow]","[fill, grow, pref!]"));
		casusAanmakenPanel.setPreferredSize(new Dimension(500,430));
		
		
		
		// Panel overzicht vragen en antwoorden
		overzichtPanel = new JPanel(new MigLayout("","[pref!][grow,fill][fill, pref!]"));
	    overzichtPanel.setBorder(BorderFactory.createTitledBorder("Overzicht"));
	    casusAanmakenPanel.add(overzichtPanel,"wrap");
		
		eersteVraagLbl = new JLabel("Eerste vraag");
		eersteVraagTxt = new JTextField();
		eersteVraagTxt.setEditable(false);
		overzichtPanel.add(eersteVraagLbl);
		overzichtPanel.add(eersteVraagTxt, "wrap, growx, span 2");
		
		vragenLbl = new JLabel("Vragen");
		vragenListModel = new DefaultListModel<>();
		vragenJList = new JList<>(vragenListModel);
		vragenJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vragenJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				antwoordenListModel.clear();
				if(!(vragenJList.getSelectedValue() == null)){
					Enumeration<CasusAntwoord> antwoordenEnum = vragenJList.getSelectedValue().children();
					while(antwoordenEnum.hasMoreElements()){
						antwoordenListModel.addElement(antwoordenEnum.nextElement());
					}	
				}			
			}
		});
			
		vragenScroll = new JScrollPane();
		vragenScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		vragenScroll.setViewportView(vragenJList);
		overzichtPanel.add(vragenLbl);
		overzichtPanel.add(vragenScroll, "wrap, growx, span2, h 50");
		
		vraagVerwijderenBtn = new JButton("Verwijderen");
		vraagVerwijderenBtn.addActionListener(new Listener());
		overzichtPanel.add(vraagVerwijderenBtn, "spanx, right, split 2");
		
		eersteVraagBtn = new JButton("Eerste vraag");
		eersteVraagBtn.addActionListener(new Listener());
		overzichtPanel.add(eersteVraagBtn,"wrap");
		
		antwoordenLbl = new JLabel("Antwoorden");
		antwoordenListModel = new DefaultListModel<>();
		antwoordenJList = new JList<>(antwoordenListModel);
		antwoordenJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		antwoordenScroll = new JScrollPane();
		antwoordenScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		antwoordenScroll.setViewportView(antwoordenJList);
		overzichtPanel.add(antwoordenLbl);
		overzichtPanel.add(antwoordenScroll, "wrap, growx, spanx, h 50");
		
		antwoordVerwijderenBtn = new JButton("Verwijderen");
		antwoordVerwijderenBtn.addActionListener(new Listener());
		overzichtPanel.add(antwoordVerwijderenBtn, "spanx, split 2, right");
		openAntwoordAanmakenDialogBtn = new JButton("Aanmaken");
		openAntwoordAanmakenDialogBtn.addActionListener(new Listener());
		overzichtPanel.add(openAntwoordAanmakenDialogBtn, "wrap");
		
		
		// Panel aanmaken antwoorden
		antwoordenAanmakenPanel = new JPanel(new MigLayout("","[pref!][grow,fill][fill, pref!]","[]5"));
		antwoordenAanmakenPanel.setBorder(BorderFactory.createTitledBorder("Antwoorden aanmaken"));		
		
		antwoordTekstLbl = new JLabel("Tekst");
		antwoordTekstScroll = new JScrollPane();
		antwoordTekstScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		antwoordTekstTxtA = new JTextArea();
		antwoordTekstTxtA.setLineWrap(true);
		antwoordTekstTxtA.setWrapStyleWord(true);
		antwoordTekstScroll.setViewportView(antwoordTekstTxtA);
		antwoordenAanmakenPanel.add(antwoordTekstLbl);
		antwoordenAanmakenPanel.add(antwoordTekstScroll, "wrap, growx, spanx, h 50");
		
		antwoordBeschrijvingLbl = new JLabel("Beschrijving");
		antwoordBeschrijvingScroll = new JScrollPane();
		antwoordBeschrijvingScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		antwoordBeschrijvingTxtA = new JTextArea();
		antwoordBeschrijvingTxtA.setLineWrap(true);
		antwoordBeschrijvingTxtA.setWrapStyleWord(true);
		antwoordBeschrijvingScroll.setViewportView(antwoordBeschrijvingTxtA);
		antwoordenAanmakenPanel.add(antwoordBeschrijvingLbl);
		antwoordenAanmakenPanel.add(antwoordBeschrijvingScroll, "wrap, growx, spanx, h 50");
		
		antwoordLinkLbl = new JLabel("Link naar");
		antwoordLinkCbx = new JComboBox<>();
		antwoordenAanmakenPanel.add(antwoordLinkLbl);
		antwoordenAanmakenPanel.add(antwoordLinkCbx, "wrap, growx, spanx");
		
		antwoordAanmakenBtn = new JButton("Aanmaken");
		antwoordAanmakenBtn.addActionListener(new Listener());
		antwoordenAanmakenPanel.add(antwoordAanmakenBtn, "span, right, wrap");
		
	
		// Panel aanmaken vragen
		vragenAanmakenPanel = new JPanel(new MigLayout("","[pref!][grow,fill][fill, pref!]","[]5"));
		vragenAanmakenPanel.setBorder(BorderFactory.createTitledBorder("Vragen aanmaken"));
		casusAanmakenPanel.add(vragenAanmakenPanel,"wrap");	
		
		
		vraagTekstLbl = new JLabel("Tekst");
		vraagTekstScroll = new JScrollPane();
		vraagTekstScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		vraagTekstTxtA = new JTextArea();
		vraagTekstTxtA.setLineWrap(true);
		vraagTekstTxtA.setWrapStyleWord(true);
		vraagTekstScroll.setViewportView(vraagTekstTxtA);
		vragenAanmakenPanel.add(vraagTekstLbl);
		vragenAanmakenPanel.add(vraagTekstScroll, "wrap, growx, span2, h 50");
		
		vraagAanmakenBtn = new JButton("Aanmaken");
		vraagAanmakenBtn.addActionListener(new Listener());
		vragenAanmakenPanel.add(vraagAanmakenBtn, "span 3, wrap, right");
		
		casusAanmakenBtn = new JButton("Casus aanmaken");
		casusAanmakenBtn.addActionListener(new Listener());
		casusAanmakenPanel.add(casusAanmakenBtn,"spanx, right, w pref!");
		

		
		
		setContentPane(algemeenPanel);
		this.setSize(algemeenPanel.getPreferredSize());
		
	}
	
	private boolean validatieAlgemeen(){ 
		boolean validatie = true;
		
		if(titelInhoudTxt.getText().trim().isEmpty() || !titelInhoudTxt.getText().matches("[a-zA-Z0-9�-�\\s-]*")) {
			validatie = false;
			titelInhoudTxt.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else titelInhoudTxt.setBorder(UIManager.getBorder("TextField.border"));
		
		if(omschrijvingInhoudTxtA.getText().trim().isEmpty()) {
			validatie = false;
			omschrijvingInhoudScroll.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else omschrijvingInhoudScroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(doelgroepTxt.getText().trim().isEmpty()) {
			validatie = false;
			doelgroepTxt.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else doelgroepTxt.setBorder(UIManager.getBorder("TextField.border"));
		
		if(situatieSchetsTxtA.getText().trim().isEmpty()){
			validatie = false;
			situatieSchetsScroll.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else situatieSchetsScroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(locatieIntroTxt.getText().trim().isEmpty()){
			validatie = false;
			locatieIntroTxt.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else locatieIntroTxt.setBorder(UIManager.getBorder("TextField.border"));
		
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
			if(e.getSource().equals(casusAanmakenBtn))
				maakCasusAan();

			if(e.getSource().equals(kernwoordenToevoegenBtn))
				voegKernwoordToe();
			
			if(e.getSource().equals(kernwoordVerwijderenBtn))
				verwijderKernwoord();
			
			if(e.getSource().equals(volgendeBtn))
				openCasusAanmakenPanel();
			if(e.getSource().equals(vraagVerwijderenBtn))
				verwijderGeselecteerdeVraag();
			if(e.getSource().equals(antwoordVerwijderenBtn))
				verwijderGeselecteerdAntwoord();
			if(e.getSource().equals(antwoordAanmakenBtn))
				maakAntwoordAan();
			if(e.getSource().equals(vraagAanmakenBtn))
				maakVraagAan();
			if(e.getSource().equals(openAntwoordAanmakenDialogBtn))
				openAntwoordAanmakenDialog();
			if(e.getSource().equals(eersteVraagBtn))
				stelEersteVraagIn();
			
		}
		
		private void stelEersteVraagIn() {
			if(!(vragenJList.getSelectedValue() == null)){
				eersteVraagTxt.setText(vragenJList.getSelectedValue().toString());
			}
			
		}

		private void openAntwoordAanmakenDialog() {
			if(!(vragenJList.getSelectedValue() == null)){
				antwoordAanmakenDialog = new JDialog();
				antwoordAanmakenDialog.setModalityType(ModalityType.APPLICATION_MODAL);
				antwoordAanmakenDialog.setContentPane(antwoordenAanmakenPanel);
				antwoordAanmakenDialog.setVisible(true);
			}		
		}

		private void maakVraagAan() {
			if(vraagTekstTxtA.getText().trim().isEmpty()){
				
			}else{
				CasusBuilder onderdeelBuilder = new CasusBuilder();
				vragenListModel.addElement(onderdeelBuilder.maakVraagAan(vraagTekstTxtA.getText()));
			}
		}

		private void maakAntwoordAan() {
			if(antwoordTekstTxtA.getText().trim().isEmpty()){
				
			}else{
				CasusBuilder onderdeelBuilder = new CasusBuilder();
				CasusAntwoord antwoord = onderdeelBuilder.maakAntwoordAan(antwoordTekstTxtA.getText(), antwoordBeschrijvingTxtA.getText().trim());
				if(!(antwoordLinkCbx.getSelectedItem() == null)){
					antwoord.Add((CasusVraag)antwoordLinkCbx.getSelectedItem());
				}
				vragenJList.getSelectedValue().Add(antwoord);
				antwoordenListModel.addElement(antwoord);
				antwoordAanmakenDialog.dispose();
			}			
		}

		private void verwijderGeselecteerdAntwoord() {
			if(!(antwoordenJList.getSelectedValue() == null)){
				CasusAntwoord geselecteerdAntwoord = antwoordenJList.getSelectedValue();
				vragenJList.getSelectedValue().remove(geselecteerdAntwoord);
				antwoordenListModel.removeElement(geselecteerdAntwoord);
			}
			
		}

		private void verwijderGeselecteerdeVraag() {
			if(!(vragenJList.getSelectedValue() == null)){
				vragenListModel.removeElement(vragenJList.getSelectedValue());
			}
			
		}

		private void openCasusAanmakenPanel() {
			if(validatieAlgemeen()){
				setContentPane(casusAanmakenPanel);
				setSize(casusAanmakenPanel.getPreferredSize());
			}
			
		}

		private void verwijderKernwoord() {
			kernwoordenCbx.removeItem(kernwoordenCbx.getSelectedItem());
			
		}
		
		private void voegKernwoordToe() {
			kernwoordenCbx.addItem(kernwoordenTxt.getText());
			
		}
				
		private void maakCasusAan() {
			// TODO Auto-generated method stub
			
		}


	}

}
