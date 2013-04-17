package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import net.miginfocom.layout.AC;
import net.miginfocom.swing.MigLayout;
import domein.LeertrajectController;
import domein.builders.MedewerkerBuilder;
import domein.pattern.observer.Observer;
import domein.pattern.observer.Subject;
import domein.tableModel.SoortTableModel;

public class MedewerkerPanel extends JPanel implements ActionListener, Subject {
	private LeertrajectController lc;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblNaam;
	private JTextField txtNaam;
	private JLabel lblVoornaam;
	private JTextField txtVoornaam;
	private JLabel lblTelnr;
	private JTextField txtTelnr;
	private JLabel lblOrganisatie;
	private JTextField txtOrganisatie;
	private JButton opslaan;
	private JButton toevoegen;
	private JButton gebruikersToevoegen;
	private HashSet<Observer> observers;
	private BrowserPane medewerkerlist;
	private SearchPanel zoekMedewerker;
	private JButton clear;

	public MedewerkerPanel(LeertrajectController lc) {
		this.lc = lc;
		observers = new HashSet<>();
		initGUI();
		createMigLayout();
	}

	private void initGUI() {
		setLayout(new MigLayout("","[][grow,fill,::620][]"));
		setBackground(new Color(250,250,250));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		{
			medewerkerlist = new BrowserPane("Overzicht alle medewerkers", lc.geefTableModel(SoortTableModel.MEDEWERKER), ListSelectionModel.SINGLE_SELECTION);
			medewerkerlist.setPreferredSize(new Dimension(500,300));
			((MigLayout) medewerkerlist.getContentPanel().getLayout()).setColumnConstraints(new AC().grow().fill());
			zoekMedewerker = new SearchPanel();
			zoekMedewerker.addCheckBox("Intern");
			zoekMedewerker.addCheckBox("Extern");
			medewerkerlist.addSearchPanel(zoekMedewerker);
		}
		{
			toevoegen = new JButton("Toevoegen");
			toevoegen.setMnemonic(KeyEvent.VK_L);
			toevoegen.addActionListener(this);
		}
		{
			gebruikersToevoegen = new JButton("Gebruikers toevoegen uit parameterbestand");
			gebruikersToevoegen.setMnemonic(KeyEvent.VK_L);
			gebruikersToevoegen.addActionListener(this);
		}
		{
			lblEmail = new JLabel("Email :");
			txtEmail = new JTextField();
		}
		{
			lblNaam = new JLabel("Naam :");
			txtNaam = new JTextField();
		}
		{
			lblVoornaam = new JLabel("Voornaam :");
			txtVoornaam = new JTextField();
		}
		{
			lblTelnr = new JLabel("Telnr. :");
			txtTelnr = new JTextField();
		}
		{
			lblOrganisatie = new JLabel("Organisatie :");
			txtOrganisatie = new JTextField();
		}
		{
			opslaan = new JButton("Opslaan");
			opslaan.setMnemonic(KeyEvent.VK_L);
			opslaan.addActionListener(this);
		}
		{
			clear = new JButton("Verwijder");
			clear.setMnemonic(KeyEvent.VK_L);
			clear.addActionListener(this);
		}
	}
	
	private void createMigLayout() {
		add(medewerkerlist,"wrap,growx,spanx");
		add(toevoegen,"spanx,right,gaptop 8, tag next, split 2");
		add(gebruikersToevoegen,"spanx, right, gaptop 8, tag next");
		Utility.addSeparator("Nieuwe externe medewerker toevoegen", this);
		add(lblEmail,"right");
		add(txtEmail,"growx,wrap");
		add(lblNaam,"right");
		add(txtNaam,"growx,wrap");
		add(lblVoornaam,"right");
		add(txtVoornaam,"growx,wrap");
		add(lblTelnr,"right");
		add(txtTelnr,"growx,wrap");
		add(lblOrganisatie,"right");
		add(txtOrganisatie,"growx,wrap");
		add(clear,"spanx,right,gaptop 8, tag next,split 2");
		add(opslaan,"spanx,right,gaptop 8, tag next");
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == opslaan) {
			if(validateFields()) {
				MedewerkerBuilder medewerkerBuilder = new MedewerkerBuilder();
				medewerkerBuilder.createNewMedewerker();
				medewerkerBuilder.buildMedewerkerIntern(false);
				medewerkerBuilder.buildMedewerkerLogin(txtEmail.getText().trim());
				medewerkerBuilder.buildMedewerkerNaam(txtNaam.getText().trim());
				medewerkerBuilder.buildMedewerkerVoornaam(txtVoornaam.getText().trim());
				medewerkerBuilder.buildMedewerkerTelefoon(txtTelnr.getText().trim());
				medewerkerBuilder.buildMedewerkerOrganisatie(txtOrganisatie.getText().trim());
				lc.voegExterneMedewerkerToe(medewerkerBuilder.getMedewerker());
				clearFields();
			}
		}
		if(event.getSource() == gebruikersToevoegen){
			selecteerParameterBestand();
		}
		if(event.getSource() == toevoegen) {
			notifyObservers(lc.getManager().getMedewerker((String)medewerkerlist.getTable().getValueAt(medewerkerlist.getTable().getSelectedRow(), 0)));
		}
		if(event.getSource() == clear) {
			clearFields();
		}
	}

	private void selecteerParameterBestand() {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(this);
		String parameterFile;
		if(returnVal == JFileChooser.APPROVE_OPTION){
			parameterFile = fileChooser.getSelectedFile().getAbsolutePath();
			lc.registreerGebruikers(parameterFile);
		}

	}
		
	

	private void clearFields() {
		txtEmail.setText("");
		txtNaam.setText("");
		txtOrganisatie.setText("");
		txtTelnr.setText("");
		txtVoornaam.setText("");
		
	}

	private boolean validateFields() {
		boolean validate = true;
		if(txtEmail.getText().trim().isEmpty() || !txtEmail.getText().matches("[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})") || lc.getManager().bestaatMedewerker(txtEmail.getText())) {
			validate = false;
			txtEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txtEmail.setBorder(UIManager.getBorder("TextField.border"));
		if(txtNaam.getText().trim().isEmpty()) {
			validate = false;
			txtNaam.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txtNaam.setBorder(UIManager.getBorder("TextField.border"));
		if(txtVoornaam.getText().trim().isEmpty()) {
			validate = false;
			txtVoornaam.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txtVoornaam.setBorder(UIManager.getBorder("TextField.border"));
		if(txtTelnr.getText().trim().isEmpty()) {
			validate = false;
			txtTelnr.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txtTelnr.setBorder(UIManager.getBorder("TextField.border"));
		if(txtOrganisatie.getText().trim().isEmpty()) {
			validate = false;
			txtOrganisatie.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txtOrganisatie.setBorder(UIManager.getBorder("TextField.border"));
		return validate;
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void notifyObservers(Object object) {
		for(Observer o : observers)
			o.update(object);
	}
}
