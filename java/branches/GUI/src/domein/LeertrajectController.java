package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.table.TableModel;

import org.joda.time.LocalDate;

import domein.tableModel.SoortTableModel;


public class LeertrajectController extends Controller {

	private LeertrajectRepository leertrajectRepository;
	private Leertraject geselecteerdLeertraject;

	private String actorID;

	public LeertrajectController(String actorID){
		leertrajectRepository = new LeertrajectRepository();
		this.actorID = actorID;
	}

	public void aanmakenLeertraject(String leertrajectCode, String titel, String omschrijving, LocalDate startDatum, 
			String doelgroep, LocalDate  beschikbaarheidVan, LocalDate  beschikbaarheidTot, String externeMedewerker) {	
		geselecteerdLeertraject = new Leertraject(leertrajectCode, titel, omschrijving, startDatum, doelgroep, beschikbaarheidVan, beschikbaarheidTot, externeMedewerker);
		leertrajectRepository.voegLeertrajectToe(actorID, geselecteerdLeertraject);
	}

	public void kopieerLeertraject(String leertrajectCode, String titel, String omschrijving, LocalDate  startDatum, 
			String doelgroep, LocalDate  beschikbaarheidVan, LocalDate  beschikbaarheidTot, String externeMedewerker) {
		
		int aantal = leertrajectRepository.geefAantalLeertrajecten();
		for(int i = 0; i < aantal; i++){
			Leertraject lt = leertrajectRepository.geefLeertraject(i);
			if(lt.getLeertrajectCode().equals(leertrajectCode))
				throw new IllegalArgumentException("Kies een andere leertrajectcode");
		}

		if(titel == "" || titel == null){
			titel = geselecteerdLeertraject.getTitel();
		}
		
		if(omschrijving == "" || omschrijving == null){
			omschrijving = geselecteerdLeertraject.getOmschrijving();
		}
		
		if(doelgroep == "" || doelgroep == null){
			doelgroep = geselecteerdLeertraject.getDoelgroep();
		}
		
		List<Onderdeel> lijst = new ArrayList<>();
		for(int i = 0; i < geselecteerdLeertraject.geefAantalOnderdelen();i++){
			lijst.add(geselecteerdLeertraject.geefOnderdeel(i));
		}
		geselecteerdLeertraject = new Leertraject(leertrajectCode, titel, omschrijving, startDatum, doelgroep, beschikbaarheidVan, beschikbaarheidTot, externeMedewerker);
		for(int i = 0; i < lijst.size(); i++){
			geselecteerdLeertraject.voegOnderdeelToe(lijst.get(i));
		}
		leertrajectRepository.voegLeertrajectToe(actorID, geselecteerdLeertraject);
		
		
	}

	public void voegOnderdeelToe(Onderdeel onderdeel) {
		geselecteerdLeertraject.voegOnderdeelToe(onderdeel);
	}
	
	public void verwijderOnderdeel(Onderdeel onderdeel) {
		geselecteerdLeertraject.verwijderOnderdeel(onderdeel);
	}
	
	public TableModel geefTableModel(SoortTableModel soort)
	{
		return TableModelFactory.createTableModel(soort, this);
	}
	
	public LeertrajectRepository getLeertrajectRepository() {
		return leertrajectRepository;
	}
	
	public void setGeselecteerdLeertraject(Leertraject geselecteerdLeertraject) {
		this.geselecteerdLeertraject = geselecteerdLeertraject;
	}

	public Leertraject getGeselecteerdLeertraject() {
		return geselecteerdLeertraject;
	}
	
	public String getActorID() {
		return actorID;
	}

	@Override
	public void update(Observable o, Object object) {
		geselecteerdLeertraject = (Leertraject) object;
		setChanged();
		notifyObservers(geselecteerdLeertraject);
		
	}
	

}