package domein;

import javax.swing.table.TableModel;

import domein.tableModel.LeertrajectActorTableModel;
import domein.tableModel.LeertrajectTableModel;
import domein.tableModel.OnderdeelTableModel;
import domein.tableModel.SoortTableModel;

public class TableModelFactory {
	
	public static TableModel createTableModel(SoortTableModel soort, Controller controller){
		switch (soort) {
		case LEERTRAJECT:
			LeertrajectTableModel ltTableModel = new LeertrajectTableModel(controller.getLeertrajectRepository());
			ltTableModel.addObserver(controller);
			return ltTableModel;
			
		case LEERTRAJECTACTOR:
			LeertrajectActorTableModel ltActorTableModel = new LeertrajectActorTableModel(controller.getActorID(),controller.getLeertrajectRepository());
			return ltActorTableModel;
			
		case ONDERDEEL:
			OnderdeelTableModel ondTableModel = new OnderdeelTableModel(controller.getGeselecteerdLeertraject());
			controller.addObserver(ondTableModel);
			return ondTableModel;
			
		default:
			return null;
		}
	}
}
