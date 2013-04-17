package domein.tableModel;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import domein.Leertraject;
import domein.LeertrajectRepository;
import domein.Subject;

public class LeertrajectTableModel extends AbstractTableModel implements Observer, Subject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LeertrajectRepository ltRep;
	private final String KOLOMNAMEN[] = {"Code", "Titel", "Omschrijving"};  
	private Set<Observer> observers;

	public LeertrajectTableModel(LeertrajectRepository leertrajectRepository) {
		ltRep = leertrajectRepository;
		observers = new HashSet<>();
	}

	@Override
	public int getRowCount() {
		return ltRep.geefAantalLeertrajecten();
	}

	@Override
	public int getColumnCount() {
		return KOLOMNAMEN.length;
	}
	
	@Override
	public String getColumnName(int kolom)
	{
		return KOLOMNAMEN[kolom];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Leertraject lt = ltRep.geefLeertraject(rowIndex);
		switch (columnIndex) {
		case 0 : return lt.getLeertrajectCode();
		case 1 : return lt.getTitel();
		case 2 : Scanner sc = new Scanner(lt.getOmschrijving());
		String output = "";
		for(int i = 0; i < 5; i++){
			while(sc.hasNextLine()){
				output += sc.nextLine();
			}		
		}
			return output;
		default: return "";
		}
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
		
	}



	@Override
	public void notifyObservers(Object object) {
		for(Observer observer : observers)
		{
			observer.update(null, object);
		}
		
	}

	@Override
	public void update(Observable o, Object object) {
		Integer index = (Integer) object;
		notifyObservers(ltRep.geefLeertraject(index));
		
	}
}
