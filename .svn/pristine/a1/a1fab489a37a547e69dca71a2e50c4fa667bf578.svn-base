package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import org.joda.time.LocalDate;

import com.toedter.calendar.JDateChooser;

import domein.Leertraject;
import domein.LeertrajectController;
import domein.LeertrajectBuilder;
import domein.Medewerker;
import domein.pattern.observer.Observer;

public class NieuwPanel extends JPanel  implements Observer{
	
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
	private JButton save;
	private JButton btnAddUsers;
	private JLabel lblWerknemers;
	private JTextField txtWerknemers;
	private LeertrajectController controller;
	private Map<String, Medewerker> medewerkersMap;
	private JPanel panelNT;
	
	
	public NieuwPanel(LeertrajectController controller) {
		super();
		this.controller = controller;
		initGUI();
	}

	private void initGUI(){
		this.setLayout(new MigLayout());
		controller.addObserver(this);
		
		setFocusable(true);
		panelNT = new JPanel(new MigLayout("wrap","[left][grow,fill,350::]"));
		panelNT.setBackground(new Color(250,250,250));
		panelNT.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		this.addSeparator("Algemeen");
			{
				lblCode = new JLabel("Leertrajectcode :");
				txtCode = new JTextField();
				txtCode.setName("Code");
				panelNT.add(lblCode,"w min!");
				panelNT.add(txtCode,"w 50!,split 3");
			}
			{
				lblDoelgroep = new JLabel("Doelgroep :");
				txtDoelgroep = new JTextField();
				panelNT.add(lblDoelgroep,"w min!");
				panelNT.add(txtDoelgroep,"growx");
			}
			{
				lblWerknemers = new JLabel("Medewerkers :");
				txtWerknemers = new JTextField();
				panelNT.add(lblWerknemers,"w min!");
				panelNT.add(txtWerknemers,"growx,split 2");
			}
			{
				btnAddUsers = new JButton("...");
				panelNT.add(btnAddUsers);
			}
			{
				lblTitel = new JLabel("Titel :");
				txtTitel = new JTextField();
				panelNT.add(lblTitel,"w min!");
				panelNT.add(txtTitel,"growx");
			}
			{
				lblOmschrijving = new JLabel("Omschrijving :");
				txaOmschrijving = new JTextArea();
				txaOmschrijving.setLineWrap(true);
				txaOmschrijving.setWrapStyleWord(true);
				scrOmschrijving = new JScrollPane(txaOmschrijving);
				scrOmschrijving.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				panelNT.add(lblOmschrijving,"w min!");
				panelNT.add(scrOmschrijving,"growx,h 210!");
			}
			
		this.addSeparator("Datum");
			{
				lblLifeDatum = new JLabel("Life datum :");
				fieldLifeDatum = new JDateChooser();
				fieldLifeDatum.addPropertyChangeListener(new PropertyChangeListener() {
					
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if(fieldLifeDatum.getDate() != null && fieldBeschikVan.getDate() == null){
							fieldBeschikVan.setDate(LocalDate.fromDateFields(fieldLifeDatum.getDate()).minusMonths(1).toDate());
						}
						if(fieldLifeDatum.getDate() != null && fieldBeschikTot.getDate() == null){
							fieldBeschikTot.setDate(LocalDate.fromDateFields(fieldLifeDatum.getDate()).plusMonths(1).toDate());
						}
					}
				});
				fieldLifeDatum.getCalendarButton().setFocusCycleRoot(false);
				fieldLifeDatum.setDateFormatString("dd/MM/yyyy");
				panelNT.add(lblLifeDatum,"w min!");
				panelNT.add(fieldLifeDatum,"growx");
			}
			{
				lblBeschikVan = new JLabel("Beschikbaar van :");
				fieldBeschikVan = new JDateChooser();
				fieldBeschikVan.setDateFormatString("dd/MM/yyyy");
				panelNT.add(lblBeschikVan,"w min!");
				panelNT.add(fieldBeschikVan,"growx");
			}
			{
				lblBeschikTot = new JLabel("Beschikbaar tot :");
				fieldBeschikTot = new JDateChooser();
				fieldBeschikTot.setDateFormatString("dd/MM/yyyy");
				panelNT.add(lblBeschikTot,"w min!");
				panelNT.add(fieldBeschikTot,"growx");
				
			}
		this.add(panelNT);
		{
			JPanel filler = new JPanel(new MigLayout("","push[right]"));
			filler.setBackground(new Color(250,250,250));
			filler.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			{
				clear = new JButton("Clear");
				clear.setMnemonic(KeyEvent.VK_L);
				clear.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {

					}
				});
				filler.add(clear,"split 2,tag back");
			}
			{
				save = new JButton("Save");
				save.setMnemonic(KeyEvent.VK_L);
				save.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(validateFields()) {
							voegLeertrajectToe();
							// switch naar beheer panel
						}
					}
				});
				filler.add(save,"tag next");
			}
			this.add(filler,"south,pad -1 0 0 0");
		}
	}
	
	

	private void addSeparator(String text)
	{
		JLabel l = new JLabel(text);
		l.setForeground(new Color(35,100,240));

		panelNT.add(l, "gapbottom 1, span, split 2, aligny center");
		panelNT.add((new JSeparator()), "gapleft rel, growx");
	}	
	
	private boolean validateFields() {
		boolean validate = true;
		if(txtCode.getText().isEmpty() || txtCode.getText().length() > 10) {
			validate = false;
			txtCode.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txtCode.setBorder(UIManager.getBorder("TextField.border"));
		
		if(txtTitel.getText().isEmpty() || !txtTitel.getText().matches("[a-zA-Z0-9À-ž\\s-]*")) {
			validate = false;
			txtTitel.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txtTitel.setBorder(UIManager.getBorder("TextField.border"));
		
		if(txaOmschrijving.getText().isEmpty()) {
			validate = false;
			scrOmschrijving.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else scrOmschrijving.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(txtDoelgroep.getText().isEmpty()) {
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
	
	protected void voegLeertrajectToe() {
		LeertrajectBuilder leertrajectBuilder = new LeertrajectBuilder();
		leertrajectBuilder.createNewLeertraject();
		leertrajectBuilder.buildTitel(txtTitel.getText());
		leertrajectBuilder.buildOmschrijving(txaOmschrijving.getText());
		leertrajectBuilder.buildDoelgroep(txtDoelgroep.getText());
		leertrajectBuilder.buildLeertrajectCode(txtCode.getText());
		leertrajectBuilder.buildStartdatum(LocalDate.fromDateFields(fieldLifeDatum.getDate()));
		leertrajectBuilder.buildBeschikbaarVan(LocalDate.fromDateFields(fieldBeschikVan.getDate()));
		leertrajectBuilder.buildBeschikbaarTot(LocalDate.fromDateFields(fieldBeschikTot.getDate()));
		
		
		medewerkersMap = new HashMap<>();
		medewerkersMap.put("admin", new Medewerker("admin",true));
		leertrajectBuilder.buildMedewerkerMap(medewerkersMap);
		controller.voegLeertrajectToe(leertrajectBuilder.getLeertraject());
	}
	
	
	public void updateFields(){
		txtTitel.setText(controller.getGeselecteerdLeertraject().getTitel());
		txaOmschrijving.setText(controller.getGeselecteerdLeertraject().getOmschrijving());
		txtCode.setText("");
		fieldBeschikTot.setDate(null);
		fieldBeschikVan.setDate(null);
		fieldLifeDatum.setDate(null);
	}
	
	@Override
	public void update(Object object) {
		if(object instanceof Leertraject){
			updateFields();
		}
	}
}
