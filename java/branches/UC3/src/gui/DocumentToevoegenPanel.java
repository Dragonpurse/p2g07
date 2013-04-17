package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DocumentToevoegenPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel doelgroepLabel;
	private JLabel displayomschrijvingLabel;
	private JLabel displaynaamLabel;
	private JTextField einddatumTxt;
	private JTextField beginDatumTxt;
	private JLabel eindDatumLabel;
	private JLabel beginDatumLabel;
	private JTextArea omschrijvingTxtA;
	private JScrollPane omschrijvingScrollPane;
	private JTextField locatieTxt;
	private JTextField doelgroepTxt;
	private JTextField titelTxt;
	private JButton aanmakenLabel;
	private JLabel locatieLabel;
	private JLabel kernwoordenLabel;
	private JLabel omschrijvingLabel;
	private JLabel titelLabel;
	
	public DocumentToevoegenPanel(){
		super();
		initGUI();
	}

	private void initGUI() {
		GridBagLayout documentToevoegenPanelLayout = new GridBagLayout();
		documentToevoegenPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
		documentToevoegenPanelLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
		documentToevoegenPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
		documentToevoegenPanelLayout.columnWidths = new int[] {7, 7, 7, 7};
		this.setLayout(documentToevoegenPanelLayout);
		this.setPreferredSize(new java.awt.Dimension(10, 100));
		{
			titelLabel = new JLabel();
			this.add(titelLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			titelLabel.setText("Titel");
		}
		{
			omschrijvingLabel = new JLabel();
			this.add(omschrijvingLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			omschrijvingLabel.setText("Omschrijving");
		}
		{
			kernwoordenLabel = new JLabel();
			this.add(kernwoordenLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			kernwoordenLabel.setText("kernwoorden");
		}
		{
			doelgroepLabel = new JLabel();
			this.add(doelgroepLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			doelgroepLabel.setText("doelgroep");
		}
		{
			locatieLabel = new JLabel();
			this.add(locatieLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			locatieLabel.setText("Locatie");
		}
		{
			aanmakenLabel = new JButton();
			this.add(aanmakenLabel, new GridBagConstraints(3, 9, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			aanmakenLabel.setText("Aanmaken");
		}
		{
			titelTxt = new JTextField();
			this.add(titelTxt, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			titelTxt.setText("titelTxt");
			titelTxt.setPreferredSize(new java.awt.Dimension(160, 20));
		}
		{
			doelgroepTxt = new JTextField();
			this.add(doelgroepTxt, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			doelgroepTxt.setText("doelgroepTxt");
			doelgroepTxt.setPreferredSize(new java.awt.Dimension(160, 20));
		}
		{
			locatieTxt = new JTextField();
			this.add(locatieTxt, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			locatieTxt.setText("locatieTxt");
			locatieTxt.setPreferredSize(new java.awt.Dimension(160, 20));
		}
		{
			omschrijvingScrollPane = new JScrollPane();
			this.add(omschrijvingScrollPane, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			omschrijvingScrollPane.setSize(3, -1);
			omschrijvingScrollPane.setPreferredSize(new java.awt.Dimension(160, 50));
			{
				omschrijvingTxtA = new JTextArea();
				omschrijvingScrollPane.setViewportView(omschrijvingTxtA);
				omschrijvingTxtA.setText("omschrijvingTxtA");
			}
		}
		{
			beginDatumLabel = new JLabel();
			this.add(beginDatumLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			beginDatumLabel.setText("Begindatum");
		}
		{
			eindDatumLabel = new JLabel();
			this.add(eindDatumLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			eindDatumLabel.setText("Einddatum");
		}
		{
			beginDatumTxt = new JTextField();
			this.add(beginDatumTxt, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			beginDatumTxt.setText("beginDatumTxt");
			beginDatumTxt.setPreferredSize(new java.awt.Dimension(160, 20));
		}
		{
			einddatumTxt = new JTextField();
			this.add(einddatumTxt, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			einddatumTxt.setText("einddatumTxt");
			einddatumTxt.setPreferredSize(new java.awt.Dimension(160, 20));
		}
		{
			displaynaamLabel = new JLabel();
			this.add(displaynaamLabel, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			displaynaamLabel.setText("Displaynaam");
		}
		{
			displayomschrijvingLabel = new JLabel();
			this.add(displayomschrijvingLabel, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			displayomschrijvingLabel.setText("Displayomschrijving");
		}
		
	}



	
}
