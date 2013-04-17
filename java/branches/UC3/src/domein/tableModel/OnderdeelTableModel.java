package domein.tableModel;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import domein.Controller;
import domein.Leertraject;
import domein.LeertrajectController;
import domein.Onderdeel;

public class OnderdeelTableModel extends AbstractTableModel implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LeertrajectController controller;
	private Leertraject geselecteerdLeertraject;
	private final String KOLOMNAMEN[] = {"Naam","Omschrijving","Titel","Type"};  
	

	public OnderdeelTableModel(Controller controller) {
		this.controller = (LeertrajectController) controller;
		geselecteerdLeertraject = this.controller.getGeselecteerdLeertraject();
	}

	@Override
	public int getColumnCount() {
		return KOLOMNAMEN.length;
	}

	@Override
	public int getRowCount() {
		if(geselecteerdLeertraject == null)
			return 0;
		return geselecteerdLeertraject.geefAantalOnderdelen();
	}
	
	@Override
	public String getColumnName(int kolom) {
		return KOLOMNAMEN[kolom];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Onderdeel onderdeel = geselecteerdLeertraject.geefOnderdeel(rowIndex);
		switch (columnIndex) {
			case 0: return onderdeel.getDisplayNaam();
			case 1: return onderdeel.getDisplayOmschrijving();
			case 2: return onderdeel.getType();
		
			default: return "";
		}
	}

	@Override
	public void update(Observable o, Object object) {
		if (object.getClass().getSimpleName().equals("Leertraject")) {
			geselecteerdLeertraject = (Leertraject) object;
			fireTableDataChanged();
		}	
	}
}
