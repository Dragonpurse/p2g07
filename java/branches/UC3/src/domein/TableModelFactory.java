package domein;

import javax.swing.table.TableModel;
import domein.tableModel.LeertrajectTableModel;
import domein.tableModel.OnderdeelTableModel;
import domein.tableModel.SoortTableModel;

public class TableModelFactory {
	
	public static TableModel createTableModel(SoortTableModel soort, Controller controller){
		switch (soort) {
		case LEERTRAJECT:
			LeertrajectTableModel ltTableModel = new LeertrajectTableModel(controller);
			ltTableModel.addObserver(controller);
			return ltTableModel;
		case ONDERDEEL:
			OnderdeelTableModel ondTableModel = new OnderdeelTableModel(controller);
			controller.addObserver(ondTableModel);
			return ondTableModel;
		default:
			return null;
		}
	}
}