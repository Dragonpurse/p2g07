package gui;

import java.awt.Color;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

import domein.LeertrajectController;
import domein.pattern.observer.Observer;
import domein.pattern.observer.Subject;

public class BeheerOnderdeelPanel extends JTabbedPane implements Observer,Subject {

	private LeertrajectController lc;
	private DocumentToevoegenPanel docPanel;
	private CasusToevoegenPanel casusPanel;
	private StellingspelToevoegenPanel stellPanel;
	private BestaandOnderdeel onderdelenPanel;
	private JComponent doosPanel;
	private boolean hasGeselecteerdTraject = false;
	private HashSet<Observer> observers;

	public BeheerOnderdeelPanel(LeertrajectController lc) {
		this.lc = lc;
		lc.addObserver(this);
		observers = new HashSet<>();
		initGUI();
		createTabs();
	}

	private void createTabs() {
		add("Bestaand onderdelen",onderdelenPanel);
		add("Document",docPanel);
		add("Casus",casusPanel);
		add("Stellingspel",stellPanel);
		add("Vragenlijst",doosPanel);
	}

	private void initGUI() {
		setBackground(new Color(250,250,250));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		{
			docPanel = new DocumentToevoegenPanel(lc);
		}
		{
			onderdelenPanel = new BestaandOnderdeel(lc);
		}
		{
			casusPanel = new CasusToevoegenPanel(lc);
		}
		{
			stellPanel = new StellingspelToevoegenPanel(lc);
		}
		{
			doosPanel = new DoosToevoegenPanel(lc);
		}
	}

	@Override
	public void update(Object object) {
		if(object.equals(true)) {
			if(!hasGeselecteerdTraject)
				super.setSelectedIndex(0);
			hasGeselecteerdTraject = (boolean) object;
		}
	}
	
	@Override
	public void setSelectedIndex(int index){
		if(hasGeselecteerdTraject)
			super.setSelectedIndex(index);
		else {
			notifyObservers("noChange");
		}
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
