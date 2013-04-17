package domein;

import javax.swing.table.TableModel;

import domein.tableModel.LeertrajectActorTableModel;
import domein.tableModel.LeertrajectTableModel;
import domein.tableModel.MedewerkerTableModel;
import domein.tableModel.OnderdeelTableModel;
import domein.tableModel.OnderdelenPerTrajectTableModel;
import domein.tableModel.SoortTableModel;

public class TableModelFactory {
	
	public static TableModel createTableModel(SoortTableModel soort, LeertrajectController controller){
		switch (soort) {
		case LEERTRAJECT:
			LeertrajectTableModel ltTableModel = new LeertrajectTableModel(controller);
			ltTableModel.addObserver(controller);
			controller.addObserver(ltTableModel);
			return ltTableModel;
		case LEERTRAJECTACTOR:
			LeertrajectActorTableModel ltActorTableModel = new LeertrajectActorTableModel(controller);
			ltActorTableModel.addObserver(controller);
			controller.addObserver(ltActorTableModel);
			return ltActorTableModel;
		case ONDERDEELTRAJECT:
			OnderdelenPerTrajectTableModel ondLtTableModel = new OnderdelenPerTrajectTableModel(controller);
			controller.addObserver(ondLtTableModel);
			return ondLtTableModel;
		case ONDERDEEL :
			OnderdeelTableModel ondTableModel = new OnderdeelTableModel(controller);
			ondTableModel.addObserver(controller);
			controller.addObserver(ondTableModel);
			return ondTableModel;
		case MEDEWERKER :
			MedewerkerTableModel mTableModel = new MedewerkerTableModel(controller);
			controller.addObserver(mTableModel);
			return mTableModel;
		default:
			return null;
		}
	}
}