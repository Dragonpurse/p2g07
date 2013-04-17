package domein.tableModel;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import domein.Controller;
import domein.Leertraject;
import domein.LeertrajectController;
import domein.Manager;
import domein.pattern.observer.Observer;
import domein.pattern.observer.Subject;

public class LeertrajectTableModel extends AbstractTableModel implements Observer, Subject{
	
	private static final long serialVersionUID = 1L;
	private LeertrajectController controller;
	private Manager manager;
	private final String KOLOMNAMEN[] = {"Trajectcode", "Titel", "Omschrijving", "Life leertraject"};  
	private Set<Observer> observers;


	public LeertrajectTableModel(Controller controller) {
		this.controller = (LeertrajectController) controller;
		manager = this.controller.getManager();
		observers = new HashSet<>();
	}
	
	@Override
	public int getRowCount() {
		return manager.geefAantalLeertrajecten();
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
		Leertraject lt = manager.geefLeertraject(rowIndex);
		switch (columnIndex) {
		case 0 : return lt.getLeertrajectCode();
		case 1 : return lt.getTitel();
		case 2 : Scanner sc = new Scanner(lt.getOmschrijving());
				 String output = "<html>";
				 int i = 0;
				 while(sc.hasNextLine() || i > 5) {
					 output += sc.nextLine() + "<br>";
					 i++;
				 }
				 sc.close();
				 return output;
		case 3 : return lt.getStartdatum();
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
			notifyObservers(manager.geefLeertraject(index));
		}
		if(object.equals("tableChanged")) {
			fireTableDataChanged();
		}
	}
}
