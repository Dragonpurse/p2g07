package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import domein.LeertrajectController;

public class DocumentToevoegenPanel extends JPanel {
	
	private LeertrajectController controller;
	private JLabel titelLabel;
	private JScrollPane omschrijvingInhoudScroll;
	private JLabel omschrijvingLabel;
	private JTextField titelInhoudTxt;
	private JTextArea omschrijvingInhoudTxtA;
	private JLabel kernwoordenLabel;
	private JTextField kernwoordenTxt;
	private JLabel doelgroepLbl;
	private JTextField doelgroepTxt;
	private JLabel beginDatumLbl;
	private JTextField beginDatumTxt;
	private JLabel eindDatumLbl;
	private JTextField eindDatumTxt;
	private JButton documentAanmakenBtn;
	
	public DocumentToevoegenPanel(LeertrajectController controller){
		super();
		initGUI(controller);
	}
	
	private void initGUI(LeertrajectController controller) {
		this.controller = controller;
		this.setPreferredSize(new Dimension(500,500));
		
		MigLayout migLayout = new MigLayout("","[pref!][grow,fill]","[][grow,fill,50::200][][][][][]");
		this.setLayout(migLayout);
		
		titelLabel = new JLabel("titel");
		titelInhoudTxt = new JTextField(" ");
		this.add(titelLabel);
		this.add(titelInhoudTxt,"wrap,growx");
		
		omschrijvingLabel = new JLabel("Omschrijving");
		omschrijvingInhoudScroll = new JScrollPane();
		omschrijvingInhoudTxtA = new JTextArea(" ");
		omschrijvingInhoudScroll.setViewportView(omschrijvingInhoudTxtA);
		this.add(omschrijvingLabel);
		this.add(omschrijvingInhoudScroll,"wrap,growx,growy");
		
		kernwoordenLabel = new JLabel("Kernwoorden");
		kernwoordenTxt = new JTextField();
		this.add(kernwoordenLabel);
		this.add(kernwoordenTxt,"wrap,growx");
		
		doelgroepLbl = new JLabel("Doelgroep");
		doelgroepTxt = new JTextField();
		this.add(doelgroepLbl);
		this.add(doelgroepTxt,"wrap,growx");
		
		beginDatumLbl = new JLabel("Begindatum");
		beginDatumTxt = new JTextField(controller.getGeselecteerdLeertraject().getBeschikbaarheidVan().toString());
		this.add(beginDatumLbl);
		this.add(beginDatumTxt,"wrap,growx");
		
		eindDatumLbl = new JLabel("Einddatum");
		eindDatumTxt = new JTextField(controller.getGeselecteerdLeertraject().getBeschikbaarheidTot().toString());
		this.add(eindDatumLbl);
		this.add(eindDatumTxt,"wrap,growx");
		
		documentAanmakenBtn = new JButton("Aanmaken");
		documentAanmakenBtn.addActionListener(new Listener());
		this.add(documentAanmakenBtn,"span, right");
		
		}
	
	private class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(documentAanmakenBtn)){
				maakDocumentAan();
			}
			
		}
		
		private void maakDocumentAan(){
			if(validatieInvulVelden()){
				/*
				controller.voegOnderdeelToe(onderdeel));
				*/
			}
		}
		
		private boolean validatieInvulVelden(){ 
			return false;
		}
		
	}

}
