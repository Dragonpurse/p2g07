package domein.tableModel;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import domein.LeertrajectController;
import domein.Medewerker;
import domein.pattern.observer.Observer;

public class MedewerkerTableModel extends AbstractTableModel implements Observer {

	private LeertrajectController lc;
	private final String KOLOMNAMEN[] = {"Email", "Voornaam", "Naam", "Type"};
	private Map<String, Medewerker> mwMap;
	private ArrayList<Medewerker> mlijst;

	public MedewerkerTableModel(LeertrajectController lc) {
		this.lc = lc;
		mwMap = lc.getManager().getMedewerkerMap();
		mwMapconvert();
	}

	@Override
	public int getRowCount() {
		return mlijst.size();
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
		Medewerker m = mlijst.get(rowIndex);
		switch (columnIndex) {
		case 0 : return m.getLogin();
		case 1 : return m.getVoornaam();
		case 2 : return m.getNaam();
		case 3 : if(m.getIntern())
					return "Intern";
				 return "Extern";
		default: return "";
		}
	}
	
	
	private void mwMapconvert() {
		mlijst = new ArrayList<>();
		for(String key : mwMap.keySet()) {
			mlijst.add(mwMap.get(key));
		}
	}

	@Override
	public void update(Object object) {
		if(object instanceof Medewerker) {
			mwMap = lc.getManager().getMedewerkerMap();
			mwMapconvert();
			fireTableDataChanged();
		}
	}

}
