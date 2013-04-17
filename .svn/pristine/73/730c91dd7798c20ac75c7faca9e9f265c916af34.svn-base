package domein;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import persistentie.Mapper;
import persistentie.MapperFactory;
import persistentie.SoortMapper;

public class Leertraject {

	private String leertrajectCode, titel, omschrijving, doelgroep;
	private LocalDate startdatum, beschikbaarVan, beschikbaarTot;
	private Map<String, Medewerker> medewerkerMap;
	private List<Onderdeel> onderdelenLijst;
	private Mapper<Onderdeel> oMapper;
 

	
	public Leertraject() {
		super();
		setOnderdelenLijst(new ArrayList<Onderdeel>());
		oMapper = MapperFactory.geefMapper(SoortMapper.ONDERDEEL);

	}


	public Leertraject(List<Onderdeel> onderdelenLijst) {
		setOnderdelenLijst(new ArrayList<Onderdeel>());
		oMapper = MapperFactory.geefMapper(SoortMapper.ONDERDEEL);
		setOnderdelenLijst(onderdelenLijst);
	}
	
	public void verwijderOnderdeel(Onderdeel onderdeel) {
		oMapper.verwijderItem(getLeertrajectCode(), onderdeel);
		onderdelenLijst.remove(onderdeel);
	}

	public void linkOnderdeel(Onderdeel onderdeel) {
		if(onderdeel.getDisplayNaam() == null || onderdeel.getDisplayNaam().equals(""))
			onderdeel.setDisplayNaam(onderdeel.getTitel());
		if(onderdeel.getDisplayOmschrijving() == null || onderdeel.getDisplayOmschrijving().equals(""))
			onderdeel.setDisplayOmschrijving(onderdeel.getOmschrijving());
		if(onderdeel.getBeschikbaarVan() == null);
			onderdeel.setBeschikbaarVan(getBeschikbaarVan());
		if(onderdeel.getBeschikbaarTot() == null)
			onderdeel.setBeschikbaarTot(getBeschikbaarTot());
		
	    oMapper.voegItemToe(getLeertrajectCode(), onderdeel);
	    onderdelenLijst.add(onderdeel);
	}
	
	// tableModel methodes

	public String getTitel() {
		return titel;
	}


	public String getOmschrijving() {
		return omschrijving;
	}


	public String getDoelgroep() {
		return doelgroep;
	}


	public LocalDate getStartdatum() {
		return startdatum;
	}


	public LocalDate getBeschikbaarVan() {
		return beschikbaarVan;
	}


	public LocalDate getBeschikbaarTot() {
		return beschikbaarTot;
	}


	public Onderdeel geefOnderdeel(int index){
		return onderdelenLijst.get(index);
	}
	
	public int geefAantalOnderdelen(){
		return onderdelenLijst.size();
	}
	
	// 	getters & setters
	
	public String getLeertrajectCode(){
		return this.leertrajectCode;
	}
	
	public void setLeertrajectCode(String leertrajectCode) {
		if(leertrajectCode.equals("") || leertrajectCode == null)
			throw new IllegalArgumentException("Het veld leertrajectcode mag niet leeg zijn.");
		if(leertrajectCode.length() > 10)
			throw new IllegalArgumentException("Het veld leertrajectcode mag niet langer zijn dan 10 karakters.");
		this.leertrajectCode = leertrajectCode;
	}


	public void setTitel(String titel) {
		if(titel.equals("") || titel == null)
			throw new IllegalArgumentException("Het veld titel mag niet leeg zijn.");
		if(titel.matches("[a-zA-Z0-9À-ž\\s-]*"))
				this.titel = titel;
		else throw new IllegalArgumentException("Het veld titel mag geen ongeldige tekens bevatten.");
	}



	public void setOmschrijving(String omschrijving) {
		if(omschrijving.equals("") || omschrijving == null)
			throw new IllegalArgumentException("Het veld omschrijving mag niet leeg zijn");
		this.omschrijving = omschrijving;
	}



	public void setStartdatum(LocalDate startDatum) {
	    this.startdatum = startDatum;
	}



	public void setDoelgroep(String doelgroep) {
		if(doelgroep.equals("") || doelgroep == null)
			throw new IllegalArgumentException("Het veld doelgroep mag niet leeg zijn");
		this.doelgroep = doelgroep;
	}

	public void setBeschikbaarVan(LocalDate beschikbaarVan) {
		if(startdatum.isBefore(beschikbaarVan))
				throw new IllegalArgumentException("De begindatum van het traject kan niet voor de startdatum zijn");
		this.beschikbaarVan = beschikbaarVan;
	}


	public void setBeschikbaarTot(LocalDate beschikbaarTot) {
		if(beschikbaarTot.isBefore(beschikbaarVan) || beschikbaarTot.isBefore(startdatum))
				throw new IllegalArgumentException("De einddatum van het traject kan niet voor de startdatum of de begindatum zijn");
		this.beschikbaarTot = beschikbaarTot;
	}

	public Map<String, Medewerker> getMedewerkerMap() {
		return medewerkerMap;
	}

	public void setMedewerkerMap(Map<String, Medewerker> medewerkerMap) {
		this.medewerkerMap = medewerkerMap;
	}



	public Mapper<Onderdeel> getoMapper() {
		return oMapper;
	}



	public void setoMapper(Mapper<Onderdeel> oMapper) {
		this.oMapper = oMapper;
	}
	
	public List<Onderdeel> getOnderdelenLijst() {
		return onderdelenLijst;
	}

	public void setOnderdelenLijst(List<Onderdeel> onderdelenLijst) {
		Iterator<Onderdeel> it = onderdelenLijst.iterator();
		while(it.hasNext()){
			Onderdeel o = it.next();
			if(o.getBeschikbaarVan() == null)
				o.setBeschikbaarVan(getBeschikbaarVan());
			if(o.getBeschikbaarTot() == null)
				o.setBeschikbaarTot(getBeschikbaarTot());
		}
		this.onderdelenLijst = onderdelenLijst;
	}
	
	

}
