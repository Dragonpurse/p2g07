package domein.tableModel;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import domein.Controller;
import domein.LeertrajectController;
import domein.Manager;
import domein.Onderdeel;
import domein.pattern.observer.Observer;
import domein.pattern.observer.Subject;

public class OnderdeelTableModel extends AbstractTableModel implements Observer, Subject{


	private static final long serialVersionUID = 1L;
	private LeertrajectController controller;
	private Manager manager;
	private final String KOLOMNAMEN[] = {"Titel", "Omschrijving", "Kernwoorden","Doelgroep","Type"};  
	private Set<Observer> observers;


	public OnderdeelTableModel(Controller controller) {
		this.controller = (LeertrajectController) controller;
		manager = this.controller.getManager();
		observers = new HashSet<>();
	}

	@Override
	public int getRowCount() {
		return manager.geefAantalOnderdelen();
	}

	@Override
	public int getColumnCount() {
		return KOLOMNAMEN.length;
	}
	
	@Override
	public String getColumnName(int kolom) {
		return KOLOMNAMEN[kolom];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Onderdeel o = manager.geefOnderdeel(rowIndex);
		switch (columnIndex) {
		case 0 : return o.getTitel();
		case 1 : Scanner sc = new Scanner(o.getOmschrijving());
				 String output = "";
				 int i = 0;
				 while(sc.hasNextLine() || i > 5) {
					 output += sc.nextLine();
					 i++;
				 }
				 sc.close();
				 return output;
		case 2 : return o.getKernwoorden();
		case 3 : return o.getDoelgroep();
		case 4 : return o.getType();
		default: return "";
		}
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObservers(Object object) {
		for(Observer o : observers)
			o.update(object);
		
	}

	@Override
	public void update(Object object) {
		if(object instanceof Integer) {
			Integer index = (Integer) object;
			notifyObservers(manager.geefOnderdeel(index));
		}
		if(object.equals("tableChanged")) {
			fireTableDataChanged();
		}
	}
}
