package domein;

import java.util.*;
import javax.swing.table.TableModel;
import domein.tableModel.SoortTableModel;


public class LeertrajectController extends Controller {
	private Manager manager;
	private Leertraject geselecteerdLeertraject;
	
	public LeertrajectController(){
		setManager(new Manager());
	}
	
	public Leertraject maakLeertrajectAan() {
		setGeselecteerdLeertraject(new Leertraject());
		return geselecteerdLeertraject;
	}

	public Leertraject kopieerLeertraject() {
		setGeselecteerdLeertraject(new Leertraject(geselecteerdLeertraject.getOnderdelenLijst()));
		return geselecteerdLeertraject;
	}

	public Onderdeel geefOnderdeel(String type){
		switch(type){
		case "Document" : return new Document();
		default : return null;
		}
	}
	
	public void verwijderOnderdeel(Onderdeel onderdeel) {
		geselecteerdLeertraject.verwijderOnderdeel(onderdeel);
	}
	
	public void linkOnderdeel(Onderdeel onderdeel){
		geselecteerdLeertraject.linkOnderdeel(onderdeel);
	}
	
	public void save(Object o){
		manager.save(o);
	}
	
	// TableModel methodes
	
	public TableModel geefTableModel(SoortTableModel soort){
		return TableModelFactory.createTableModel(soort, this);
	}
	
	@Override
	public void update(Observable o, Object object) {
		setGeselecteerdLeertraject((Leertraject) object);
		setChanged();
		notifyObservers(geselecteerdLeertraject);
	}
	
	// Getters & setters
	public Leertraject getGeselecteerdLeertraject() {
		return geselecteerdLeertraject;
	}

	public void setGeselecteerdLeertraject(Leertraject geselecteerdLeertraject) {
		this.geselecteerdLeertraject = geselecteerdLeertraject;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

}
