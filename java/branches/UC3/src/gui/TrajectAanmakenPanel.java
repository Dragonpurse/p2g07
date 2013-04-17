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

public class TrajectAanmakenPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel leertrajectCodeLabel;
	private JLabel leertrajectTitelLabel;
	private JLabel leertrajectOmschrijvingLabel;
	private JLabel startDatumLabel;
	private JLabel leertrajectBeschikbaarheidVanLabel;
	private JLabel leertrajectBeschikbaarheidTotLabel;
	private JLabel leertrajectNaamExtMedewerkerLabel;
	private JTextField jTextField1;
	private JTextField leertrajectTitelTxt;
	private JTextField startDatumTxt;
	private JButton startDatumButton;
	private JTextField beschikbaarHeidVanTxt;
	private JTextField beschikbaarheidTotTxt;
	private JTextField naamExtMedewerkerTxt;
	private JButton beschikbaarheidVanBtn;
	private JButton beschikbaarheidTotBtn;
	private JButton leertrajectAanmakenBtn;
	private JScrollPane leertrajectOmschrijvingScrollPane;
	private JTextArea leertrajectOmschrijvingTxtA;
	
	public TrajectAanmakenPanel(){
		GridBagLayout trajectAanmakenPanelLayout = new GridBagLayout();
		trajectAanmakenPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
		trajectAanmakenPanelLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7};
		trajectAanmakenPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 1.0};
		trajectAanmakenPanelLayout.columnWidths = new int[] {7, 7, 7, 7};
		this.setLayout(trajectAanmakenPanelLayout);
		this.setPreferredSize(new java.awt.Dimension(400, 500));
		this.setSize(400, 500);
		this.setMaximumSize(new java.awt.Dimension(400, 500));
		{
			leertrajectCodeLabel = new JLabel();
			leertrajectCodeLabel.setText("Code");
			leertrajectCodeLabel.setPreferredSize(new java.awt.Dimension(150, 20));
			leertrajectCodeLabel.setMaximumSize(new java.awt.Dimension(150, 20));
			leertrajectCodeLabel.setMinimumSize(new java.awt.Dimension(0, 20));
			leertrajectCodeLabel.setLocation(new java.awt.Point(0, 0));
			this.add(leertrajectCodeLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
		}
		{
			leertrajectTitelLabel = new JLabel();
			leertrajectTitelLabel.setText("Titel");
			leertrajectTitelLabel.setPreferredSize(new java.awt.Dimension(150, 20));
			leertrajectTitelLabel.setMinimumSize(new java.awt.Dimension(150, 20));
			leertrajectTitelLabel.setMaximumSize(new java.awt.Dimension(150, 20));
			leertrajectTitelLabel.setLocation(new java.awt.Point(0, 0));
			this.add(leertrajectTitelLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
		}
		{
			leertrajectOmschrijvingLabel = new JLabel();
			leertrajectOmschrijvingLabel.setText("Omschrijving");
			leertrajectOmschrijvingLabel.setPreferredSize(new java.awt.Dimension(150, 20));
			leertrajectOmschrijvingLabel.setMaximumSize(new java.awt.Dimension(150, 20));
			leertrajectOmschrijvingLabel.setMinimumSize(new java.awt.Dimension(0, 20));
			leertrajectOmschrijvingLabel.setLocation(new java.awt.Point(0, 0));
			this.add(leertrajectOmschrijvingLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
		}
		{
			startDatumLabel = new JLabel();
			startDatumLabel.setText("Startdatum");
			startDatumLabel.setPreferredSize(new java.awt.Dimension(150, 20));
			startDatumLabel.setMaximumSize(new java.awt.Dimension(150, 20));
			startDatumLabel.setMinimumSize(new java.awt.Dimension(0, 20));
			startDatumLabel.setLocation(new java.awt.Point(0, 0));
			this.add(startDatumLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
		}
		{
			leertrajectBeschikbaarheidVanLabel = new JLabel();
			leertrajectBeschikbaarheidVanLabel.setText("Beschikbaarheid Van");
			leertrajectBeschikbaarheidVanLabel.setPreferredSize(new java.awt.Dimension(150, 20));
			leertrajectBeschikbaarheidVanLabel.setMaximumSize(new java.awt.Dimension(150, 20));
			leertrajectBeschikbaarheidVanLabel.setMinimumSize(new java.awt.Dimension(0, 20));
			leertrajectBeschikbaarheidVanLabel.setLocation(new java.awt.Point(0, 0));
			this.add(leertrajectBeschikbaarheidVanLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
		}
		{
			leertrajectBeschikbaarheidTotLabel = new JLabel();
			leertrajectBeschikbaarheidTotLabel.setText("Beschikbaarheid Tot");
			leertrajectBeschikbaarheidTotLabel.setPreferredSize(new java.awt.Dimension(150, 20));
			leertrajectBeschikbaarheidTotLabel.setMaximumSize(new java.awt.Dimension(150, 20));
			leertrajectBeschikbaarheidTotLabel.setMinimumSize(new java.awt.Dimension(0, 20));
			leertrajectBeschikbaarheidTotLabel.setLocation(new java.awt.Point(0, 0));
			this.add(leertrajectBeschikbaarheidTotLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
		}
		{
			leertrajectNaamExtMedewerkerLabel = new JLabel();
			leertrajectNaamExtMedewerkerLabel.setText("Naam externe medewerker");
			leertrajectNaamExtMedewerkerLabel.setPreferredSize(new java.awt.Dimension(150, 20));
			leertrajectNaamExtMedewerkerLabel.setMaximumSize(new java.awt.Dimension(150, 20));
			leertrajectNaamExtMedewerkerLabel.setMinimumSize(new java.awt.Dimension(0, 20));
			leertrajectNaamExtMedewerkerLabel.setLocation(new java.awt.Point(0, 0));
			this.add(leertrajectNaamExtMedewerkerLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
		}
		{
			jTextField1 = new JTextField();
			jTextField1.setPreferredSize(new java.awt.Dimension(160, 23));
			jTextField1.setSize(150, 23);
			jTextField1.setMinimumSize(new java.awt.Dimension(160, 20));
			this.add(jTextField1, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			leertrajectTitelTxt = new JTextField();
			leertrajectTitelTxt.setPreferredSize(new java.awt.Dimension(160, 23));
			leertrajectTitelTxt.setSize(150, 23);
			leertrajectTitelTxt.setMinimumSize(new java.awt.Dimension(160, 20));
			this.add(leertrajectTitelTxt, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			startDatumTxt = new JTextField();
			startDatumTxt.setPreferredSize(new java.awt.Dimension(160, 20));
			startDatumTxt.setMinimumSize(new java.awt.Dimension(160, 20));
			this.add(startDatumTxt, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			startDatumButton = new JButton();
			startDatumButton.setText("Popup");
			startDatumButton.setPreferredSize(new java.awt.Dimension(50, 20));
			this.add(startDatumButton, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
		}
		{
			beschikbaarHeidVanTxt = new JTextField();
			beschikbaarHeidVanTxt.setPreferredSize(new java.awt.Dimension(160, 20));
			beschikbaarHeidVanTxt.setMinimumSize(new java.awt.Dimension(160, 20));
			this.add(beschikbaarHeidVanTxt, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			beschikbaarheidTotTxt = new JTextField();
			beschikbaarheidTotTxt.setPreferredSize(new java.awt.Dimension(160, 20));
			beschikbaarheidTotTxt.setMinimumSize(new java.awt.Dimension(160, 20));
			this.add(beschikbaarheidTotTxt, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			naamExtMedewerkerTxt = new JTextField();
			naamExtMedewerkerTxt.setPreferredSize(new java.awt.Dimension(160, 20));
			naamExtMedewerkerTxt.setMinimumSize(new java.awt.Dimension(160, 20));
			this.add(naamExtMedewerkerTxt, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			beschikbaarheidVanBtn = new JButton();
			beschikbaarheidVanBtn.setText("Popup");
			beschikbaarheidVanBtn.setSize(49, 23);
			beschikbaarheidVanBtn.setPreferredSize(new java.awt.Dimension(50, 20));
			this.add(beschikbaarheidVanBtn, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
		}
		{
			beschikbaarheidTotBtn = new JButton();
			beschikbaarheidTotBtn.setText("Popup");
			beschikbaarheidTotBtn.setSize(49, 23);
			beschikbaarheidTotBtn.setPreferredSize(new java.awt.Dimension(50, 20));
			this.add(beschikbaarheidTotBtn, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
		}
		{
			leertrajectAanmakenBtn = new JButton();
			leertrajectAanmakenBtn.setText("Aanmaken");
			leertrajectAanmakenBtn.setPreferredSize(new java.awt.Dimension(200, 40));
			this.add(leertrajectAanmakenBtn, new GridBagConstraints(0, 7, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			leertrajectOmschrijvingScrollPane = new JScrollPane();
			leertrajectOmschrijvingScrollPane.setPreferredSize(new java.awt.Dimension(160, 100));
			leertrajectOmschrijvingScrollPane.setMinimumSize(new java.awt.Dimension(160, 100));
			this.add(leertrajectOmschrijvingScrollPane, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			{
				leertrajectOmschrijvingTxtA = new JTextArea();
				leertrajectOmschrijvingTxtA.setText("jTextArea1");
				leertrajectOmschrijvingScrollPane.setViewportView(leertrajectOmschrijvingTxtA);

			}
		}
	}

}
