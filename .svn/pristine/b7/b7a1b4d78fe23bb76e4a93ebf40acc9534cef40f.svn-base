package domein;

import javax.swing.table.TableModel;

import domein.tableModel.LeertrajectActorTableModel;
import domein.tableModel.LeertrajectTableModel;
import domein.tableModel.OnderdeelTableModel;
import domein.tableModel.OnderdelenPerTrajectTableModel;
import domein.tableModel.SoortTableModel;

public class TableModelFactory {
	
	public static TableModel createTableModel(SoortTableModel soort, LeertrajectController controller){
		switch (soort) {
		case LEERTRAJECT:
			LeertrajectTableModel ltTableModel = new LeertrajectTableModel(controller);
			ltTableModel.addObserver(controller);
			return ltTableModel;
		case LEERTRAJECTACTOR:
			LeertrajectActorTableModel ltActorTableModel = new LeertrajectActorTableModel(controller);
			ltActorTableModel.addObserver(controller);
			return ltActorTableModel;
		case ONDERDEELTRAJECT:
			OnderdelenPerTrajectTableModel ondLtTableModel = new OnderdelenPerTrajectTableModel(controller);
			controller.addObserver(ondLtTableModel);
			return ondLtTableModel;
		case ONDERDEEL :
			OnderdeelTableModel ondTableModel = new OnderdeelTableModel(controller);
			ondTableModel.addObserver(controller);
			return ondTableModel;
		default:
			return null;
		}
	}
}