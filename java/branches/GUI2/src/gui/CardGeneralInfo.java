package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.StringTokenizer;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

public class CardGeneralInfo extends JPanel implements ActionListener{
	
	private JLabel lblTitel;
	private JTextField txtTitel;
	private JLabel lblOmschrijving;
	private JTextArea txaOmschrijving;
	private JScrollPane scrOmschrijving;
	private JLabel lblKernwoorden;
	private JScrollPane scrKernwoorden;
	private JTextArea txaKernwoorden;
	private JLabel lblKernwoord;
	private JTextField txtNieuwKernwoord;
	private AbstractButton btnAddKernwoord;
	private JButton btnRemoveKernwoord;
	private JLabel lblDoelgroep;
	private JTextField txtDoelgroep;
	private HashSet<String> kernwoordenlijst;

	public CardGeneralInfo() {
		initGUI();
		createMigLayout();
	}
	

	private void createMigLayout() {
		add(lblTitel,"right");
		add(txtTitel,"growx, wrap");
		add(lblOmschrijving,"right");
		add(scrOmschrijving,"growx, h 170, wrap,");
		add(lblKernwoorden,"right");
		add(scrKernwoorden,"growx,h 40,wrap");
		add(lblKernwoord,"right");
		add(txtNieuwKernwoord,"growx,split 3");
		add(btnRemoveKernwoord,"right, w 50");
		add(btnAddKernwoord,"right,w 50,wrap");
		add(lblDoelgroep,"right");
		add(txtDoelgroep,"growx, wrap");
	}

	private void initGUI() {
		setLayout(new MigLayout("hidemode 3 , nocache","[113::][grow,fill]","[]"));
		setBackground(new Color(250,250,250));
		setBorder(BorderFactory.createEmptyBorder());
		Utility.addSeparator("Nieuw onderdeel toevoegen", this);
		{
			lblTitel = new JLabel("Titel :");
			txtTitel = new JTextField();
		}
		{
			lblOmschrijving = new JLabel("Omschrijving :");
			txaOmschrijving = new JTextArea();
			txaOmschrijving.setLineWrap(true);
			txaOmschrijving.setWrapStyleWord(true);
			scrOmschrijving = new JScrollPane(txaOmschrijving);
			scrOmschrijving.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		{
			lblKernwoorden = new JLabel("Kernwoorden :");
			txaKernwoorden = new JTextArea("Scheidt kernwoorden met ;");
			txaKernwoorden.setEnabled(false);
			scrKernwoorden = new JScrollPane(txaKernwoorden);
			scrKernwoorden.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			kernwoordenlijst = new HashSet<>();
		}
		{
			lblKernwoord = new JLabel("Kernwoord :");
			txtNieuwKernwoord = new JTextField();
			btnAddKernwoord = new JButton("Voeg toe");
			btnAddKernwoord.addActionListener(this);
			btnRemoveKernwoord = new JButton("Verwijder");
			btnRemoveKernwoord.addActionListener(this);
		}
		{
			lblDoelgroep = new JLabel("Doelgroep :");
			txtDoelgroep = new JTextField();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == btnAddKernwoord) {
			if(txaKernwoorden.getText().equals("Scheidt kernwoorden met ;")) {
				txaKernwoorden.setText("");
			}
			String delim = ";";
			if(txaKernwoorden.getText().contains(";"))
				delim = ";";
			if(txtNieuwKernwoord.getText().contains(" "))
				delim = " ";
			StringTokenizer st = new StringTokenizer(txtNieuwKernwoord.getText(),delim);
			while(st.hasMoreTokens()) {	
				kernwoordenlijst.add(st.nextToken());
			}
			updateTxaKernwoorden();
		}
		if(event.getSource() == btnRemoveKernwoord) {
			HashSet<String> removeLijst = new HashSet<>();
			if(txaKernwoorden.getText().equals("Scheidt kernwoorden met ;")) {
				txaKernwoorden.setText("");
				return;
			}
			
			String delim = " ";
			if(txaKernwoorden.getText().contains(";"))
					delim = ";";
			if(txtNieuwKernwoord.getText().contains(" "))
					delim = " ";
			StringTokenizer st = new StringTokenizer(txtNieuwKernwoord.getText(),delim);
			while(st.hasMoreTokens()) {
				removeLijst.add(st.nextToken());
			}
			kernwoordenlijst.removeAll(removeLijst);
			updateTxaKernwoorden();
		}
	}


	public boolean validateCardInfo() {
		boolean validate = true;
		if(txtTitel.getText().trim().isEmpty()) {
			validate = false;
			txtTitel.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txtTitel.setBorder(UIManager.getBorder("TextField.border"));
		
		if(txaOmschrijving.getText().trim().isEmpty()) {
			validate = false;
			scrOmschrijving.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else scrOmschrijving.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(txtDoelgroep.getText().trim().isEmpty()) {
			validate = false;
			txtDoelgroep.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txtDoelgroep.setBorder(UIManager.getBorder("TextField.border"));
		
		if(txaKernwoorden.getText().trim().isEmpty() | txaKernwoorden.getText().equals("Scheidt kernwoorden met ;")) {
			validate = false;
			scrKernwoorden.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else scrKernwoorden.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		return validate;
	}
	
	public void clearFields() {
		txtTitel.setText("");
		txtDoelgroep.setText("");
		txtNieuwKernwoord.setText("");
		txaOmschrijving.setText("");
		txaKernwoorden.setText("Scheidt kernwoorden met ;");
		txtTitel.setBorder(UIManager.getBorder("TextField.border"));
		scrOmschrijving.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txtDoelgroep.setBorder(UIManager.getBorder("TextField.border"));
		scrKernwoorden.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}
	
	private void updateTxaKernwoorden() {
		txaKernwoorden.setText("");
		for(String woord : kernwoordenlijst) {
			if(txaKernwoorden.getText().equals(""))
				txaKernwoorden.setText(woord);
			else txaKernwoorden.setText(txaKernwoorden.getText() + ";" + woord);
		}
	}


	public JTextField getTxtTitel() {
		return txtTitel;
	}


	public JTextArea getTxaOmschrijving() {
		return txaOmschrijving;
	}


	public JTextArea getTxaKernwoorden() {
		return txaKernwoorden;
	}


	public JTextField getTxtNieuwKernwoord() {
		return txtNieuwKernwoord;
	}


	public JTextField getTxtDoelgroep() {
		return txtDoelgroep;
	}


	public void updateKernwoordenLijst(String kernwoorden) {
		if(kernwoorden.equals(""))
			return;
		String delim = ";";
		if(txaKernwoorden.getText().contains(";"))
			delim = ";";
		if(txtNieuwKernwoord.getText().contains(" "))
			delim = " ";
		StringTokenizer st = new StringTokenizer(kernwoorden,delim);
		while(st.hasMoreTokens()) {	
			kernwoordenlijst.add(st.nextToken());
		}
		updateTxaKernwoorden();
	}
}
