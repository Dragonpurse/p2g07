package gui;


import java.awt.Color;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import com.toedter.calendar.JDateChooser;

import domein.Leertraject;
import domein.LeertrajectController;
import domein.pattern.observer.Observer;
import domein.pattern.observer.Subject;

public class BeheerTrajectPanel extends JPanel implements Subject, Observer{
	private JLabel lblTitel;
	private JTextField txtTitel;
	private JLabel lblOmschrijving;
	private JTextArea txaOmschrijving;
	private JScrollPane scrOmschrijving;
	private JLabel lblDoelgroep;
	private JTextField txtDoelgroep;
	private JLabel lblLifeDatum;
	private JDateChooser fieldLifeDatum;
	private HashSet<Observer> observers;
	private boolean activeTab = false;
	private JLabel lblWarning;
	private boolean hasGeselecteerdTraject;
	public BeheerTrajectPanel(LeertrajectController lc) {
		observers = new HashSet<>();
		lc.addObserver(this);
		initGUI();
		createMigLayout();
	}

	private void createMigLayout() {
		Utility.addSeparator("Beheer van traject", this);
		add(lblTitel,"right");
		add(txtTitel,"growx, wrap");
		add(lblDoelgroep,"right");
		add(txtDoelgroep,"growx, wrap");
		add(lblOmschrijving,"right");
		add(scrOmschrijving,"growx, h 210, wrap,");
		add(lblLifeDatum,"right");
		add(fieldLifeDatum,"growx, wrap");
		add(lblWarning,"spanx,center");
	}

	private void initGUI() {
		setLayout(new MigLayout("","[][grow,::1000][]"));
		setBackground(new Color(250,250,250));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	
		{
			lblTitel = new JLabel("Titel :");
			txtTitel = new JTextField();
			txtTitel.setEnabled(false);
		}
		{
			lblDoelgroep = new JLabel("Doelgroep :");
			txtDoelgroep = new JTextField();
			txtDoelgroep.setEnabled(false);
		}
		{
			lblOmschrijving = new JLabel("Omschrijving :");
			txaOmschrijving = new JTextArea();
			txaOmschrijving.setLineWrap(true);
			txaOmschrijving.setWrapStyleWord(true);
			scrOmschrijving = new JScrollPane(txaOmschrijving);
			scrOmschrijving.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			txaOmschrijving.setEnabled(false);
		}
		{
			lblLifeDatum = new JLabel("Life datum :");
			fieldLifeDatum = new JDateChooser();
			fieldLifeDatum.getCalendarButton().setFocusCycleRoot(false);
			fieldLifeDatum.setDateFormatString("dd/MM/yyyy");
			fieldLifeDatum.setEnabled(false);
		}
		{
			lblWarning = new JLabel();
			lblWarning.setForeground(Color.red);
		}
	}
	
	public void updateFields(Leertraject lt){
		txtTitel.setText(lt.getTitel());
		txaOmschrijving.setText(lt.getOmschrijving());
		txtDoelgroep.setText(lt.getDoelgroep());
		fieldLifeDatum.setDate(lt.getStartdatum().toDate());
	}


	@Override
	public void update(Object object) {
		if(object instanceof Leertraject && activeTab) {
			hasGeselecteerdTraject = true;
			lblWarning.setText("");
			updateFields((Leertraject) object);
			notifyObservers(hasGeselecteerdTraject);
		}
		if(object.equals("noChange"))
			lblWarning.setText("Gelieve een traject te selecteren");
	}

	public boolean isActiveTab() {
		return activeTab;
	}

	public void setActiveTab(boolean activeTab) {
		this.activeTab = activeTab;
//		if(!activeTab) {
//			hasGeselecteerdTraject = false;
//			notifyObservers(hasGeselecteerdTraject);
//		}
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
