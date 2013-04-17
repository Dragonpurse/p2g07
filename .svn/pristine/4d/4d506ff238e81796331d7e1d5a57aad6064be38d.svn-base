package domein;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import persistentie.Mapper;
import persistentie.MapperFactory;
import persistentie.SoortMapper;

public class Leertraject {

	private String leertrajectCode, titel, omschrijving, doelgroep;
	private LocalDate startdatum, beschikbaarVan, beschikbaarTot;
	private List<Medewerker> medewerkerLijst;
	private List<Onderdeel> onderdelenLijst;
	private Mapper<Onderdeel> oMapper;
 
	public Leertraject() {
		setOnderdelenLijst(new ArrayList<Onderdeel>());
		setMedewerkerLijst(new ArrayList<Medewerker>());
		oMapper = MapperFactory.geefMapper(SoortMapper.ONDERDEEL);
	}

	public Leertraject(List<Onderdeel> onderdelenLijst) {
		this();
		setOnderdelenLijst(onderdelenLijst);
	}
	
	public void verwijderOnderdeel(Onderdeel onderdeel) {
		oMapper.verwijderItem(leertrajectCode, onderdeel);
		onderdelenLijst.remove(onderdeel);
	}

	public void linkOnderdeel(Onderdeel onderdeel) {
	    oMapper.voegItemToe(leertrajectCode, onderdeel);
	    onderdelenLijst.add(onderdeel);
	}
	
	// tableModel methodes

	public Onderdeel geefOnderdeel(int index){
		return onderdelenLijst.get(index);
	}
	
	public int geefAantalOnderdelen(){
		return onderdelenLijst.size();
	}
	
	// 	getters & setters

	public String getLeertrajectCode() {
		return this.leertrajectCode;
	}

	public void setLeertrajectCode(String leertrajectCode) {
		if(leertrajectCode == "" || leertrajectCode == null)
			throw new IllegalArgumentException("Het veld leertrajectcode mag niet leeg zijn.");
		if(leertrajectCode.length() > 10)
			throw new IllegalArgumentException("Het veld leertrajectcode mag niet langer zijn dan 10 karakters.");
		this.leertrajectCode = leertrajectCode;
	}

	public String getTitel() {
		return this.titel;
	}
	
	public void setTitel(String titel) {
		if(titel == "" || titel == null)
			throw new IllegalArgumentException("Het veld titel mag niet leeg zijn.");
		if(titel.matches("[a-zA-Z0-9À-ž\\s-]*"))
				this.titel = titel;
		else throw new IllegalArgumentException("Het veld titel mag geen ongeldige tekens bevatten.");
	}

	public String getOmschrijving() {
		return this.omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		if(omschrijving == "" || omschrijving == null)
			throw new IllegalArgumentException("Het veld omschrijving mag niet leeg zijn");
		this.omschrijving = omschrijving;
	}

	public LocalDate getStartdatum() {
		return this.startdatum;
	}

	public void setStartdatum(LocalDate startDatum) {
		if(startDatum.isBefore(LocalDate.now()))
				throw new IllegalArgumentException("De startdatum kan niet voor de huidige datum zijn");
	    this.startdatum = startDatum;
	}

	public String getDoelgroep() {
		return this.doelgroep;
	}

	public void setDoelgroep(String doelgroep) {
		if(doelgroep == "" || doelgroep == null)
			throw new IllegalArgumentException("Het veld doelgroep mag niet leeg zijn");
		this.doelgroep = doelgroep;
	}

	public LocalDate getBeschikbaarheidVan() {
		return this.beschikbaarVan;
	}

	public void setBeschikbaarheidVan(LocalDate beschikbaarVan) {
		if(beschikbaarVan.isBefore(LocalDate.now()) && startdatum.isBefore(beschikbaarVan))
				throw new IllegalArgumentException("De begindatum van het traject kan niet voor de huidige datum of de startdatum zijn");
		this.beschikbaarVan = beschikbaarVan;
	}

	public LocalDate getBeschikbaarheidTot() {
		return this.beschikbaarTot;
	}

	public void setBeschikbaarheidTot(LocalDate beschikbaarTot) {
		if(beschikbaarTot.isBefore(LocalDate.now()) && beschikbaarTot.isBefore(beschikbaarVan) && beschikbaarTot.isBefore(startdatum))
				throw new IllegalArgumentException("De einddatum van het traject kan niet voor de huidige datum, de startdatum of de begindatum zijn");
		this.beschikbaarTot = beschikbaarTot;
	}
	
	public List<Onderdeel> getOnderdelenLijst() {
		return onderdelenLijst;
	}

	public void setOnderdelenLijst(List<Onderdeel> onderdelenLijst) {
		for(Onderdeel onderdeel : onderdelenLijst){
			onderdeel.setBeschikbaarVan(this.beschikbaarVan);
			onderdeel.setBeschikbaarTot(this.beschikbaarTot);
		}
		this.onderdelenLijst = onderdelenLijst;
	}

	public List<Medewerker> getMedewerkerLijst() {
		return medewerkerLijst;
	}

	public void setMedewerkerLijst(List<Medewerker> medewerkerLijst) {
		this.medewerkerLijst = medewerkerLijst;
	}
}
