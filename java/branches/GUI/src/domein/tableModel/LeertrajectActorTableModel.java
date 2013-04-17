package domein.tableModel;

import javax.swing.table.AbstractTableModel;

import domein.Leertraject;
import domein.LeertrajectRepository;

public class LeertrajectActorTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LeertrajectRepository ltRep;
	private final String KOLOMNAMEN[] = {"Code", "Titel", "Omschrijving", "Startdatum"};
	private String actorID;

	public LeertrajectActorTableModel(String actorID, LeertrajectRepository leertrajectRepository) {
		ltRep = leertrajectRepository;
		this.actorID = actorID;
	}

	@Override
	public int getRowCount() {
		return ltRep.geefAantalLeertrajecten(actorID);
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
		Leertraject lt = ltRep.geefLeertraject(actorID,rowIndex);
		switch (columnIndex) {
		case 0 : return lt.getLeertrajectCode();
		case 1 : return lt.getTitel();
		case 2 : return lt.getOmschrijving();
		case 3 : return lt.getStartdatum();
		default: return "";
		}
	}

}
