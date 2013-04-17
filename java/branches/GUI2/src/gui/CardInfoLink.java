package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import org.joda.time.LocalDate;

import com.toedter.calendar.JDateChooser;

public class CardInfoLink extends JPanel {
	
	private JLabel lblDispNaam;
	private JTextField txtDispNaam;
	private JLabel lblDispOmschrijving;
	private JTextArea txaDispOmschrijving;
	private JScrollPane scrDispOmschrijving;
	private JLabel lblBeschikVan;
	private JDateChooser fieldBeschikVan;
	private JLabel lblBeschikTot;
	private JDateChooser fieldBeschikTot;
	
	public CardInfoLink() {
		initGUI();
		createMigLayout();
	}

	private void createMigLayout() {
		Utility.addSeparator("Extra informatie", this);
		add(lblDispNaam,"right");
		add(txtDispNaam,"growx,wrap");
		add(lblDispOmschrijving,"right");
		add(scrDispOmschrijving,"growx,h 210,wrap");
		add(lblBeschikVan,"right");
		add(fieldBeschikVan,"growx,wrap");
		add(lblBeschikTot,"right");
		add(fieldBeschikTot,"growx,wrap");
		
	}

	private void initGUI() {
		setLayout(new MigLayout("hidemode 3 , nocache","[][grow,fill]",""));
		setBackground(new Color(250,250,250));
		setBorder(BorderFactory.createEmptyBorder());
		{
			lblDispNaam = new JLabel("Displaynaam :");
			txtDispNaam = new JTextField();
		}
		{
			lblDispOmschrijving = new JLabel("Displayomschrijving :");
			txaDispOmschrijving = new JTextArea();
			txaDispOmschrijving.setLineWrap(true);
			txaDispOmschrijving.setWrapStyleWord(true);
			scrDispOmschrijving = new JScrollPane(txaDispOmschrijving);
			scrDispOmschrijving.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		{
			lblBeschikVan = new JLabel("Beschikbaar van :");
			fieldBeschikVan = new JDateChooser();
			fieldBeschikVan.setDateFormatString("dd/MM/yyyy");
		}
		{
			lblBeschikTot = new JLabel("Beschikbaar tot :");
			fieldBeschikTot = new JDateChooser();
			fieldBeschikTot.setDateFormatString("dd/MM/yyyy");
			
		}
	}

	
	public boolean validateCardInfo() {
		boolean validate = true;
		
		if(txtDispNaam.getText().trim().isEmpty()) {
			validate = false;
			txtDispNaam.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txtDispNaam.setBorder(UIManager.getBorder("TextField.border"));
		
		if(txaDispOmschrijving.getText().trim().isEmpty()) {
			validate = false;
			scrDispOmschrijving.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else scrDispOmschrijving.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(fieldBeschikVan.getDate() == null) {
			validate = false;
			fieldBeschikVan.getDateEditor().getUiComponent().setBorder(BorderFactory.createLineBorder(Color.RED));
		} else fieldBeschikVan.getDateEditor().getUiComponent().setBorder(UIManager.getBorder("TextField.border"));
		
		if(fieldBeschikTot.getDate() == null || LocalDate.fromDateFields(fieldBeschikTot.getDate()).isBefore(LocalDate.fromDateFields(fieldBeschikVan.getDate()))) {
			validate = false;
			fieldBeschikTot.getDateEditor().getUiComponent().setBorder(BorderFactory.createLineBorder(Color.RED));
		} else fieldBeschikTot.getDateEditor().getUiComponent().setBorder(UIManager.getBorder("TextField.border"));
		
		return validate;
	}
	
	public void clearFields() {
		txtDispNaam.setBorder(UIManager.getBorder("TextField.border"));
		scrDispOmschrijving.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		fieldBeschikVan.getDateEditor().getUiComponent().setBorder(UIManager.getBorder("TextField.border"));
		fieldBeschikTot.getDateEditor().getUiComponent().setBorder(UIManager.getBorder("TextField.border"));
	}

	public JTextField getTxtDispNaam() {
		return txtDispNaam;
	}

	public JTextArea getTxaDispOmschrijving() {
		return txaDispOmschrijving;
	}

	public JDateChooser getFieldBeschikVan() {
		return fieldBeschikVan;
	}

	public JDateChooser getFieldBeschikTot() {
		return fieldBeschikTot;
	}
}
