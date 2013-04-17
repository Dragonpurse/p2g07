package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import domein.CasusAntwoord;
import domein.CasusVraag;
import domein.builders.CasusBuilder;

public class CasusVragenPanel extends JPanel implements ActionListener{
	
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
	

	
	private DefaultComboBoxModel<CasusVraag> vragenListModel;
	private DefaultListModel<CasusAntwoord> antwoordenListModel;
	private JButton eersteVraagBtn;
	
	private JPanel antwoordenAanmakenPanel;
	
	private JPanel overzichtPanel;
	private JPanel vragenAanmakenPanel;
	
	private CasusVraag eersteVraag;
	private JLabel lblWarning;
	

	public CasusVragenPanel() {
		super();
		initGUI();
		createMigLayout();
	}

	private void createMigLayout() {
		Utility.addSeparator("Overzicht", this);
	    this.add(overzichtPanel,"wrap");
		Utility.addSeparator("Antwoorden aanmaken", this);
	    this.add(antwoordenAanmakenPanel, "wrap");
		Utility.addSeparator("Vragen aanmaken", this);
	    this.add(vragenAanmakenPanel, "wrap");    	
	}

	private void initGUI() {
		this.setLayout(new MigLayout("hidemode 3 , nocache","[grow, fill]","[grow, fill]"));
		this.setBackground(new Color(250,250,250));
		this.setBorder(BorderFactory.createEmptyBorder());

		
		
		
		// Overzicht vragen en antwoorden
		overzichtPanel = new JPanel(new MigLayout("hidemode 3 , nocache","[113::, right][grow,fill]","[]"));
		overzichtPanel.setBackground(new Color(250,250,250));
		overzichtPanel.setBorder(BorderFactory.createEmptyBorder());

		
		eersteVraagLbl = new JLabel("Eerste vraag :");
		eersteVraagTxt = new JTextField();
		eersteVraagTxt.setEditable(false);
		overzichtPanel.add(eersteVraagLbl);
		overzichtPanel.add(eersteVraagTxt, "wrap, growx");
		
		vragenLbl = new JLabel("Vragen :");
		vragenListModel = new DefaultComboBoxModel<>();
		vragenListModel.addElement(null);
		vragenJList = new JList<>(vragenListModel);
		vragenJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vragenJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				antwoordenListModel.clear();
				if(!(vragenJList.getSelectedValue() == null)){
					for(CasusAntwoord antwoord : vragenJList.getSelectedValue().getAntwoorden())
						antwoordenListModel.addElement(antwoord);
					

				}			
			}
		});
			
		vragenScroll = new JScrollPane();
		vragenScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		vragenScroll.setViewportView(vragenJList);
		overzichtPanel.add(vragenLbl);
		overzichtPanel.add(vragenScroll, "wrap, growx, h 50");
		
		vraagVerwijderenBtn = new JButton("Verwijderen");
		vraagVerwijderenBtn.addActionListener(this);
		overzichtPanel.add(vraagVerwijderenBtn, "spanx, right, split 2");
		
		eersteVraagBtn = new JButton("Eerste vraag");
		eersteVraagBtn.addActionListener(this);
		overzichtPanel.add(eersteVraagBtn,"wrap");
		
		antwoordenLbl = new JLabel("Antwoorden :");
		antwoordenListModel = new DefaultListModel<>();
		antwoordenJList = new JList<>(antwoordenListModel);
		antwoordenJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		antwoordenScroll = new JScrollPane();
		antwoordenScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		antwoordenScroll.setViewportView(antwoordenJList);
		overzichtPanel.add(antwoordenLbl);
		overzichtPanel.add(antwoordenScroll, "wrap, growx, h 50");
		
		antwoordVerwijderenBtn = new JButton("Verwijderen");
		antwoordVerwijderenBtn.addActionListener(this);
		overzichtPanel.add(antwoordVerwijderenBtn, "spanx, split 2, right");
		
		
		// Panel aanmaken antwoorden
		antwoordenAanmakenPanel = new JPanel(new MigLayout("hidemode 3 , nocache","[113::, right][grow,fill]","[]"));
		antwoordenAanmakenPanel.setBackground(new Color(250,250,250));
		antwoordenAanmakenPanel.setBorder(BorderFactory.createEmptyBorder());
		
		antwoordTekstLbl = new JLabel("Tekst :");
		antwoordTekstScroll = new JScrollPane();
		antwoordTekstScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		antwoordTekstTxtA = new JTextArea();
		antwoordTekstTxtA.setLineWrap(true);
		antwoordTekstTxtA.setWrapStyleWord(true);
		antwoordTekstScroll.setViewportView(antwoordTekstTxtA);
		antwoordenAanmakenPanel.add(antwoordTekstLbl);
		antwoordenAanmakenPanel.add(antwoordTekstScroll, "wrap, growx, h 50");
		
		antwoordBeschrijvingLbl = new JLabel("Beschrijving :");
		antwoordBeschrijvingScroll = new JScrollPane();
		antwoordBeschrijvingScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		antwoordBeschrijvingTxtA = new JTextArea();
		antwoordBeschrijvingTxtA.setLineWrap(true);
		antwoordBeschrijvingTxtA.setWrapStyleWord(true);
		antwoordBeschrijvingScroll.setViewportView(antwoordBeschrijvingTxtA);
		antwoordenAanmakenPanel.add(antwoordBeschrijvingLbl);
		antwoordenAanmakenPanel.add(antwoordBeschrijvingScroll, "wrap, growx, h 50");
		
		antwoordLinkLbl = new JLabel("Volgende vraag :");
		antwoordLinkCbx = new JComboBox<>(vragenListModel);
		antwoordLinkCbx.setSelectedItem(null);
		antwoordenAanmakenPanel.add(antwoordLinkLbl);
		antwoordenAanmakenPanel.add(antwoordLinkCbx, "wrap, growx");
		
		
		lblWarning = new JLabel();
		lblWarning.setForeground(Color.red);
		antwoordenAanmakenPanel.add(lblWarning,"spanx,split 2,center");
		
		antwoordAanmakenBtn = new JButton("Aanmaken");
		antwoordAanmakenBtn.addActionListener(this);
		antwoordenAanmakenPanel.add(antwoordAanmakenBtn, "spanx, right, wrap");

		
	
		// Panel aanmaken vragen
		vragenAanmakenPanel = new JPanel(new MigLayout("hidemode 3 , nocache","[113::, right][grow,fill]","[]"));
		vragenAanmakenPanel.setBackground(new Color(250,250,250));
		vragenAanmakenPanel.setBorder(BorderFactory.createEmptyBorder());	
		
		
		vraagTekstLbl = new JLabel("Tekst :");
		vraagTekstScroll = new JScrollPane();
		vraagTekstScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		vraagTekstTxtA = new JTextArea();
		vraagTekstTxtA.setLineWrap(true);
		vraagTekstTxtA.setWrapStyleWord(true);
		vraagTekstScroll.setViewportView(vraagTekstTxtA);
		vragenAanmakenPanel.add(vraagTekstLbl);
		vragenAanmakenPanel.add(vraagTekstScroll, "wrap, growx, h 50");
		
		vraagAanmakenBtn = new JButton("Aanmaken");
		vraagAanmakenBtn.addActionListener(this);
		vragenAanmakenPanel.add(vraagAanmakenBtn, "spanx, wrap, right");
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		lblWarning.setText("");
		if(e.getSource() == eersteVraagBtn){
			stelEersteVraagIn();
		}
		if(e.getSource() == antwoordAanmakenBtn){
			maakAntwoordAan();
		}
		if(e.getSource() == vraagAanmakenBtn){
			maakVraagAan();
		}
		if(e.getSource() == antwoordVerwijderenBtn){
			verwijderGeselecteerdAntwoord();
		}
		if(e.getSource() == vraagVerwijderenBtn){
			verwijderGeselecteerdeVraag();
		}
		
	}
	private void stelEersteVraagIn() {
	if(!(vragenJList.getSelectedValue() == null)){
		eersteVraagTxt.setText(vragenJList.getSelectedValue().toString());
		eersteVraag = vragenJList.getSelectedValue();
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
		if(antwoordTekstTxtA.getText().trim().isEmpty() || vragenJList.getSelectedValue() == null) {
			return;
		}
		if(antwoordLinkCbx.getSelectedItem() != null) {
			if(vragenJList.getSelectedValue() == antwoordLinkCbx.getSelectedItem()) {
				lblWarning.setText("Je kan geen antwoord met zijn eigen vraag linken.");
				return;
			}
		}
		CasusBuilder onderdeelBuilder = new CasusBuilder();
		CasusAntwoord antwoord = onderdeelBuilder.maakAntwoordAan(antwoordTekstTxtA.getText(), antwoordBeschrijvingTxtA.getText().trim());
		if(!(antwoordLinkCbx.getSelectedItem() == null)){
			antwoord.setVolgendeVraag((CasusVraag)antwoordLinkCbx.getSelectedItem());
		}
		vragenJList.getSelectedValue().voegAntwoordToe(antwoord);
		antwoordenListModel.addElement(antwoord);
	}
	
	private void verwijderGeselecteerdAntwoord() {
		if(!(antwoordenJList.getSelectedValue() == null)){
			CasusAntwoord geselecteerdAntwoord = antwoordenJList.getSelectedValue();
			vragenJList.getSelectedValue().verwijderAntwoord(geselecteerdAntwoord);
			antwoordenListModel.removeElement(geselecteerdAntwoord);
		}
		
	}
	
	private void verwijderGeselecteerdeVraag() {
		if(!(vragenJList.getSelectedValue() == null)){
			vragenListModel.removeElement(vragenJList.getSelectedValue());
		}
		
	}

	public boolean validateCardInfo() {
		boolean validate = true;
		if(eersteVraag == null)
		validate = false;
		
		return validate;
		
	}
	
	public CasusVraag getEersteVraag(){
		return this.eersteVraag;
	}
	
	public void clearFields(){
		this.eersteVraag = null;
		vragenListModel.removeAllElements();
		vragenListModel.addElement(null);
		antwoordenListModel.removeAllElements();
		vraagTekstTxtA.setText(null);
		antwoordBeschrijvingTxtA.setText(null);
		antwoordTekstTxtA.setText(null);
	}
	
}
