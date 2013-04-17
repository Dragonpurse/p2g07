package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import domein.StellingAntwoordKleur;

public class VraagAntwoordenPanel extends JPanel{
	
	private JLabel lblVraag;
	private JTextArea txaVraag;
	private JScrollPane scrVraag;
	private JLabel lblAntwoord1;
	private JTextField txtAntwoord1;
	private JLabel lblAntwoord2;
	private JTextField txtAntwoord2;
	private JLabel lblAntwoord3;
	private JTextField txtAntwoord3;
	private JLabel lblAntwoord4;
	private JTextField txtAntwoord4;
	private Graphics g;
	
	public VraagAntwoordenPanel() {
		initGUI();
		createMigLayout();
	}

	private void createMigLayout() {
		add(lblVraag,"wrap,growx,cell 1 1");
		add(txaVraag,"wrap,growx,h 100,cell 1 2");
		add(lblAntwoord1,"right,w 20");
		add(txtAntwoord1,"wrap,growx");
		add(lblAntwoord2,"right,w 20");
		add(txtAntwoord2,"wrap,growx");
		add(lblAntwoord3,"right,w 20");
		add(txtAntwoord3,"wrap,growx");
		add(lblAntwoord4,"right,w 20");
		add(txtAntwoord4,"wrap,growx");
	}

	private void initGUI() {
		setLayout(new MigLayout("","[][grow,fill]",""));
		setBackground(new Color(250,250,250));
		setBorder(BorderFactory.createEmptyBorder());
		{
			lblVraag = new JLabel("De omschrijving van de stelling");
			txaVraag = new JTextArea();
			txaVraag.setLineWrap(true);
			txaVraag.setWrapStyleWord(true);
			txaVraag.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			scrVraag = new JScrollPane(txaVraag);
		}
		{
			lblAntwoord1 = new JLabel(" ");
			lblAntwoord1.setBackground(Color.green);
			lblAntwoord1.setOpaque(true);
			txtAntwoord1 = new JTextField(StellingAntwoordKleur.GROEN.geefStandaardAntwoord());
		}
		{
			lblAntwoord2 = new JLabel(" ");
			lblAntwoord2.setBackground(Color.blue);
			lblAntwoord2.setOpaque(true);
			txtAntwoord2 = new JTextField(StellingAntwoordKleur.BLAUW.geefStandaardAntwoord());
		}
		{
			lblAntwoord3 = new JLabel(" ");
			lblAntwoord3.setBackground(Color.red);
			lblAntwoord3.setOpaque(true);
			txtAntwoord3 = new JTextField(StellingAntwoordKleur.ROOD.geefStandaardAntwoord());
		}
		{
			lblAntwoord4 = new JLabel(" ");
			lblAntwoord4.setBackground(new Color(244,192,50));
			lblAntwoord4.setOpaque(true);
			txtAntwoord4 = new JTextField(StellingAntwoordKleur.GEEL.geefStandaardAntwoord());
		}
	}

	public JTextArea getTxaVraag() {
		return txaVraag;
	}

	public JTextField getTxtAntwoord1() {
		return txtAntwoord1;
	}

	public JTextField getTxtAntwoord2() {
		return txtAntwoord2;
	}

	public JTextField getTxtAntwoord3() {
		return txtAntwoord3;
	}

	public JTextField getTxtAntwoord4() {
		return txtAntwoord4;
	}

	public boolean validateFields() {
		boolean validate = true;
		int aantalAntwoorden = 0;
		
		if(txaVraag.getText().trim().isEmpty()) {
			validate = false;
			txaVraag.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txaVraag.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(!txtAntwoord1.getText().trim().isEmpty()) 
			aantalAntwoorden++;
		
		if(!txtAntwoord2.getText().trim().isEmpty()) 
			aantalAntwoorden++;
	
		if(!txtAntwoord3.getText().trim().isEmpty()) 
			aantalAntwoorden++;
		
		if(!txtAntwoord4.getText().trim().isEmpty()) 
			aantalAntwoorden++;
		if(aantalAntwoorden < 2)
			validate = false;
		
		return validate;
	}
	
	public String ToString(){
		return String.format("%s", txaVraag.getText());
	}
}
