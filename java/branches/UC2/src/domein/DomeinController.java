package domein;

import java.util.*;

import persistentie.PersistentieController;

public class DomeinController {

	private Leertraject geselecteerdLeertraject;

	public void aanmakenLeertraject(String leertrajectCode, String titel,
			String omschrijving, Calendar startDatum, String doelgroep,
			Calendar beschikbaarheidVan, Calendar beschikbaarheidTot,
			String externeMedewerker) {	
		setGeselecteerdLeertraject(new Leertraject(leertrajectCode, titel, omschrijving, startDatum, doelgroep, beschikbaarheidVan, beschikbaarheidTot, externeMedewerker));
	}

	public ArrayList<Leertraject> getLijstLeertrajecten() {
		return PersistentieController.getInstance().getLijstLeertrajecten();
	}

	public void kopieerLeertraject(String leertrajectCode, String titel,
			String omschrijving, Calendar startDatum, String doelgroep,
			Calendar beschikbaarheidVan, Calendar beschikbaarheidTot,
			String externeMedewerker) {
		
		if(geselecteerdLeertraject.getLeertrajectCode().equalsIgnoreCase(leertrajectCode))
			throw new IllegalArgumentException();
		
		if(titel == "" || titel == null){
			titel = geselecteerdLeertraject.getTitel();
		}
		
		if(omschrijving == "" || omschrijving == null){
			omschrijving = geselecteerdLeertraject.getOmschrijving();
		}
		
		if(doelgroep == "" || doelgroep == null){
			doelgroep = geselecteerdLeertraject.getDoelgroep();
		}
		
		setGeselecteerdLeertraject(new Leertraject(leertrajectCode, titel, omschrijving, startDatum, doelgroep, beschikbaarheidVan, beschikbaarheidTot, externeMedewerker));
		
	}
	
	public Leertraject getGeselecteerdLeertraject() {
		return this.geselecteerdLeertraject;
	}

	public void setGeselecteerdLeertraject(Leertraject geselecteerdLeertraject) {
		this.geselecteerdLeertraject = geselecteerdLeertraject;
	}
}
