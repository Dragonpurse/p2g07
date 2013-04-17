package domein.tableModel;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import domein.Leertraject;
import domein.Onderdeel;

public class OnderdeelTableModel extends AbstractTableModel implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Leertraject leertraject;
	private final String KOLOMNAMEN[] = {"Naam","Omschrijving","Titel","Type"};  
	
	public OnderdeelTableModel(Leertraject leertraject){
		this.leertraject = leertraject;
	}

	@Override
	public int getColumnCount() {
		return KOLOMNAMEN.length;
	}

	@Override
	public int getRowCount() {
		if(leertraject == null)
			return 0;
		return leertraject.geefAantalOnderdelen();
	}
	
	@Override
	public String getColumnName(int kolom) {
		return KOLOMNAMEN[kolom];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Onderdeel onderdeel = leertraject.geefOnderdeel(rowIndex);
		switch (columnIndex) {
			case 0: return onderdeel.getDisplayNaam();
			case 1: return onderdeel.getDisplayOmschrijving();
			case 2: return onderdeel.getTypeOnderdeel();
		
			default: return "";
		}
	}

	@Override
	public void update(Observable o, Object object) {
		if (object instanceof Leertraject) {
			leertraject = (Leertraject) object;
			fireTableDataChanged();
		}
		
	}

}
