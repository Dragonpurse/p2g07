package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import org.joda.time.LocalDate;

import com.toedter.calendar.JDateChooser;

import domein.Leertraject;
import domein.LeertrajectController;
import domein.Medewerker;
import domein.builders.LeertrajectBuilder;
import domein.pattern.observer.Observer;

public class NieuwTrajectPanel extends JPanel implements PropertyChangeListener, ActionListener, Observer{
	
	private static final long serialVersionUID = 3182733315485037005L;
	private JLabel lblTitel;
	private JTextField txtTitel;
	private JLabel lblCode;
	private JTextField txtCode;
	private JLabel lblDoelgroep;
	private JTextField txtDoelgroep;
	private JLabel lblOmschrijving;
	private JTextArea txaOmschrijving;
	private JScrollPane scrOmschrijving;
	private JLabel lblLifeDatum;
	private JDateChooser fieldLifeDatum;
	private JLabel lblBeschikVan;
	private JDateChooser fieldBeschikVan;
	private JLabel lblBeschikTot;
	private JDateChooser fieldBeschikTot;
	private JButton clear;
	private JButton opslaan;
	private JLabel lblWerknemers;
	private JTextField txtWerknemers;
	private LeertrajectController lc;
	private Map<String, Medewerker> medewerkersMap;
	private JPanel filler;
	private boolean activeTab = true;
	private boolean heeftGeselecteerdTraject = false;
	
	
	public NieuwTrajectPanel(LeertrajectController lc) {
		this.lc = lc;
		medewerkersMap = new HashMap<>();
		initGUI();
		createMigLayout();
	}

	private void createMigLayout() {
		Utility.addSeparator("Nieuw traject toevoegen", this);
		add(lblCode,"right");
		add(txtCode,"growx, wrap, split 3");
		add(lblDoelgroep,"right");
		add(txtDoelgroep,"growx, wrap");
		add(lblWerknemers,"right");
		add(txtWerknemers,"growx,wrap");
		add(lblTitel,"right");
		add(txtTitel,"growx, wrap");
		add(lblOmschrijving,"right");
		add(scrOmschrijving,"growx, h 210, wrap,");
		add(lblLifeDatum,"right");
		add(fieldLifeDatum,"growx, wrap");
		add(lblBeschikVan,"right");
		add(fieldBeschikVan,"growx, wrap");
		add(lblBeschikTot,"right");
		add(fieldBeschikTot,"growx, wrap");
		add(filler,"spanx, right");
	}

	private void initGUI(){
		setLayout(new MigLayout("","[][grow,::600][]"));
		setBackground(new Color(250,250,250));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		lc.addObserver(this);
		
		{
			lblCode = new JLabel("Leertrajectcode :");
			txtCode = new JTextField();
		}
		{
			lblDoelgroep = new JLabel("Doelgroep :");
			txtDoelgroep = new JTextField();
		}
		{
			lblWerknemers = new JLabel("Medewerkers :");
			txtWerknemers = new JTextField();
			medewerkersMap.put(lc.getManager().getIngelogdeMedewerker().getLogin(),lc.getManager().getIngelogdeMedewerker());
			txtWerknemers.setText("["+lc.getManager().getIngelogdeMedewerker().getLogin()+"]");
			txtWerknemers.setEditable(false);
			txtWerknemers.setEnabled(false);
			
		}
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
			lblLifeDatum = new JLabel("Life datum :");
			fieldLifeDatum = new JDateChooser();
			fieldLifeDatum.addPropertyChangeListener(this);
			fieldLifeDatum.getCalendarButton().setFocusCycleRoot(false);
			fieldLifeDatum.setDateFormatString("dd/MM/yyyy");
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
		
		{
			filler = new JPanel(new MigLayout("","push[right]"));
			filler.setBackground(new Color(250,250,250));
			filler.setBorder(BorderFactory.createEmptyBorder());
			{
				clear = new JButton("Verwijder");
				clear.setMnemonic(KeyEvent.VK_L);
				clear.addActionListener(this);
				filler.add(clear,"split 2,tag back");
			}
			{
				opslaan = new JButton("Opslaan");
				opslaan.setMnemonic(KeyEvent.VK_L);
				opslaan.addActionListener(this);
				filler.add(opslaan,"tag next");
			}
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if(fieldLifeDatum.getDate() != null && fieldBeschikVan.getDate() == null){
			fieldBeschikVan.setDate(LocalDate.fromDateFields(fieldLifeDatum.getDate()).minusMonths(1).toDate());
		}
		if(fieldLifeDatum.getDate() != null && fieldBeschikTot.getDate() == null){
			fieldBeschikTot.setDate(LocalDate.fromDateFields(fieldLifeDatum.getDate()).plusMonths(1).toDate());
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == clear) {
			LeertrajectBuilder leertrajectBuilder = new LeertrajectBuilder();
			leertrajectBuilder.createNewLeertraject();
			updateFields(leertrajectBuilder.getLeertraject());
		}
		if(event.getSource() == opslaan) {
			if(validateFields()) {
					LeertrajectBuilder leertrajectBuilder = new LeertrajectBuilder();
					leertrajectBuilder.createNewLeertraject();
					leertrajectBuilder.buildTitel(txtTitel.getText().trim());
					leertrajectBuilder.buildOmschrijving(txaOmschrijving.getText().trim());
					leertrajectBuilder.buildDoelgroep(txtDoelgroep.getText().trim());
					leertrajectBuilder.buildLeertrajectCode(txtCode.getText().trim());
					leertrajectBuilder.buildStartdatum(LocalDate.fromDateFields(fieldLifeDatum.getDate()));
					leertrajectBuilder.buildBeschikbaarVan(LocalDate.fromDateFields(fieldBeschikVan.getDate()));
					leertrajectBuilder.buildBeschikbaarTot(LocalDate.fromDateFields(fieldBeschikTot.getDate()));
					leertrajectBuilder.buildMedewerkerMap(medewerkersMap);
					
					if(!heeftGeselecteerdTraject)
						lc.voegLeertrajectToe(leertrajectBuilder.getLeertraject());
					else 
						lc.kopieerLeertraject(leertrajectBuilder.getLeertraject());
			}
		}
	}
	
	private boolean validateFields() {
		boolean validate = true;
		if(txtCode.getText().trim().isEmpty() || txtCode.getText().length() > 10 || lc.getManager().bestaatTrajectcode(txtCode.getText())) {
			validate = false;
			txtCode.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txtCode.setBorder(UIManager.getBorder("TextField.border"));
		
		if(txtTitel.getText().trim().isEmpty() || !txtTitel.getText().matches("[a-zA-Z0-9�-�\\s-]*")) {
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
		
		if(fieldLifeDatum.getDate() == null) {
			validate = false;
			fieldLifeDatum.getDateEditor().getUiComponent().setBorder(BorderFactory.createLineBorder(Color.RED));
		} else fieldLifeDatum.getDateEditor().getUiComponent().setBorder(UIManager.getBorder("TextField.border"));
		
		if(fieldBeschikVan.getDate() == null || LocalDate.fromDateFields(fieldLifeDatum.getDate()).isBefore(LocalDate.fromDateFields(fieldBeschikVan.getDate()))) {
			validate = false;
			fieldBeschikVan.getDateEditor().getUiComponent().setBorder(BorderFactory.createLineBorder(Color.RED));
		} else fieldBeschikVan.getDateEditor().getUiComponent().setBorder(UIManager.getBorder("TextField.border"));
		
		if(fieldBeschikTot.getDate() == null || LocalDate.fromDateFields(fieldBeschikTot.getDate()).isBefore(LocalDate.fromDateFields(fieldBeschikVan.getDate()))
											 || LocalDate.fromDateFields(fieldBeschikTot.getDate()).isBefore(LocalDate.fromDateFields(fieldLifeDatum.getDate()))) {
			validate = false;
			fieldBeschikTot.getDateEditor().getUiComponent().setBorder(BorderFactory.createLineBorder(Color.RED));
		} else fieldBeschikTot.getDateEditor().getUiComponent().setBorder(UIManager.getBorder("TextField.border"));
		
		return validate;
	}
	
	public void updateFields(Leertraject lt){
		txtTitel.setText("");
		if(lt.getTitel() != null) 
			txtTitel.setText(lt.getTitel());
		txaOmschrijving.setText("");
		if(lt.getOmschrijving() != null) 
			 txaOmschrijving.setText(lt.getOmschrijving());
		txtCode.setText("");
		txtDoelgroep.setText("");
		medewerkersMap = new HashMap<>();
		medewerkersMap.put(lc.getManager().getIngelogdeMedewerker().getLogin(),lc.getManager().getIngelogdeMedewerker());
		txtWerknemers.setText(lc.getManager().getIngelogdeMedewerker().getLogin());
		fieldBeschikTot.setDate(null);
		fieldBeschikVan.setDate(null);
		fieldLifeDatum.setDate(null);
	}

	@Override
	public void update(Object object) {
		if(object instanceof Leertraject && activeTab) {
			heeftGeselecteerdTraject  = true;
			updateFields((Leertraject) object);
		}
		if(object instanceof Medewerker) {
			if(!(medewerkersMap.containsKey(((Medewerker) object).getLogin()))) {
				medewerkersMap.put(((Medewerker) object).getLogin(), (Medewerker) object);
				if(txtWerknemers.getText().isEmpty())
					txtWerknemers.setText(txtWerknemers.getText() + "[" + ((Medewerker) object).getLogin() + "]");
				else txtWerknemers.setText(txtWerknemers.getText() + ", [" + ((Medewerker) object).getLogin() + "]");
				this.repaint();
			}
		}
	}

	public boolean isActiveTab() {
		return activeTab;
	}

	public void setActiveTab(boolean activeTab) {
		this.activeTab = activeTab;
	}
}