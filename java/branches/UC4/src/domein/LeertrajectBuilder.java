package domein;

import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

public class LeertrajectBuilder {
	
	private Leertraject leertraject;
	
	public Leertraject getLeertraject(){
		return this.leertraject;
	}	

	public void createNewLeertraject(){
		this.leertraject = new Leertraject();
	}

    public void buildLeertrajectCode(String leertrajectCode){
    	leertraject.setLeertrajectCode(leertrajectCode);
    }
    
	public void buildTitel(String titel){
    	leertraject.setTitel(titel);
    }
    
	public void buildOmschrijving(String omschrijving){
    	leertraject.setOmschrijving(omschrijving);
    }
    
	public void buildDoelgroep(String doelgroep){
    	leertraject.setDoelgroep(doelgroep);
    }
    
	public void buildStartdatum(LocalDate startdatum){
    	leertraject.setStartdatum(startdatum);
    }
    
	public void buildBeschikbaarVan(LocalDate beschikbaarVan){
    	leertraject.setBeschikbaarVan(beschikbaarVan);
    }
    
	public void buildBeschikbaarTot(LocalDate beschikbaarTot){
    	leertraject.setBeschikbaarTot(beschikbaarTot);
    }
    
	public void buildMedewerkerMap(Map<String, Medewerker> medewerkerMap){
    	leertraject.setMedewerkerMap(medewerkerMap);
    }
	
	public void buildOnderdelenLijst(List<Onderdeel> onderdelenLijst){
		leertraject.setOnderdelenLijst(onderdelenLijst);
	}
    
    


	



}
