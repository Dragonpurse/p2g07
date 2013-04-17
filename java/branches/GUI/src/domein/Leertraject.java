package domein;

import java.util.*;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import persistentie.Mapper;
import persistentie.MapperFactory;
import persistentie.SoortMapper;

public class Leertraject {

	private String leertrajectCode;
	private String titel;
	private String omschrijving;
	private LocalDate startdatum;
	private String doelgroep;
	private LocalDate beschikbaarheidVan;
	private LocalDate beschikbaarheidTot;
	private String externeMedewerker;
	private List<Onderdeel> onderdelenLijst;
	private Mapper<Onderdeel> onderdelenMapper;

	public Leertraject(String leertrajectCode, String titel, String omschrijving, 
			LocalDate sdat, String doelgroep, LocalDate beschikVan, 
			LocalDate beschikTot, String externeMedewerker) {
		setLeertrajectCode(leertrajectCode);
		setTitel(titel);
		setOmschrijving(omschrijving);
		setStartdatum(sdat);
		setDoelgroep(doelgroep);
		setBeschikbaarheidVan(beschikVan);
		setBeschikbaarheidTot(beschikTot);
		setExterneMedewerker(externeMedewerker);
		onderdelenMapper = MapperFactory.geefMapper(SoortMapper.ONDERDEEL);
	}
 
	public void voegOnderdeelToe(Onderdeel onderdeel) {
		if(onderdelenLijst == null)
			onderdelenAanmaken();
		if(onderdeel == null)
			throw new NullPointerException();
		onderdelenLijst.add(onderdeel);
		onderdelenMapper.voegItemToe(leertrajectCode, onderdeel);
	}

	public void verwijderOnderdeel(Onderdeel onderdeel) {
		if(onderdelenLijst == null)
			onderdelenAanmaken();
		if(!onderdelenLijst.contains(onderdeel)){
			throw new NullPointerException();
		}
		onderdelenLijst.remove(onderdeel);
		onderdelenMapper.verwijderItem(leertrajectCode, onderdeel);
	}

	public Onderdeel geefOnderdeel(int index){
		if(onderdelenLijst == null)
			onderdelenAanmaken();
		return onderdelenLijst.get(index);
	}
	
	public int geefAantalOnderdelen(){
		if(onderdelenLijst == null)
			onderdelenAanmaken();
		return onderdelenLijst.size();
	}
	
	private void onderdelenAanmaken(){
		onderdelenLijst = onderdelenMapper.geefLijstPerItem(leertrajectCode);
	}
	
	@Override
	public String toString(){
		DateTime sdat = startdatum.toDateTimeAtStartOfDay();
		DateTime beschikVan = beschikbaarheidVan.toDateTimeAtStartOfDay();
		DateTime beschikTot = beschikbaarheidTot.toDateTimeAtStartOfDay();
		DateTimeFormatter fmt = DateTimeFormat.shortDate();
		return String.format("%s,%s,%s,%s,%s,%s,%s,%s", leertrajectCode, titel, omschrijving, fmt.print(sdat), doelgroep,  fmt.print(beschikVan),  fmt.print(beschikTot), externeMedewerker);	
	}
	
	// 	getters & setters

	public String getLeertrajectCode() {
		return this.leertrajectCode;
	}

	public void setLeertrajectCode(String leertrajectCode) {
		if(leertrajectCode == "" || leertrajectCode == null)
			throw new IllegalArgumentException("Leertrajectcode mag niet leeg zijn.");
		if(leertrajectCode.length() > 10)
			throw new IllegalArgumentException("Leertrajectcode mag niet langer zijn dan 10 karakters.");
		this.leertrajectCode = leertrajectCode;
	}

	public String getTitel() {
		return this.titel;
	}
	
	public void setTitel(String titel) {
		if(titel == "" || titel == null)
			throw new IllegalArgumentException("Titel mag niet leeg zijn.");
		if(titel.matches("[a-zA-Z0-9À-ž\\s-]*"))
				this.titel = titel;
		else throw new IllegalArgumentException("Titel mag geen ongeldige tekens bevatten.");
	}

	public String getOmschrijving() {
		return this.omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		if(omschrijving == "" || omschrijving == null)
			throw new IllegalArgumentException("Omschrijving mag niet leeg zijn");
		this.omschrijving = omschrijving;
	}

	public LocalDate getStartdatum() {
		return this.startdatum;
	}

	public void setStartdatum(LocalDate sdat) {
		/*
		if(sdat.isBefore(LocalDate.now()))
			throw new IllegalArgumentException("Startdatum kan niet voor de huidige datum vallen");
			*/
	    this.startdatum = sdat;
	}

	public String getDoelgroep() {
		return this.doelgroep;
	}

	public void setDoelgroep(String doelgroep) {
		if(doelgroep == "" || doelgroep == null)
			throw new IllegalArgumentException("Doelgroep mag niet leeg zijn");
		this.doelgroep = doelgroep;
	}

	public LocalDate getBeschikbaarheidVan() {
		return this.beschikbaarheidVan;
	}

	public void setBeschikbaarheidVan(LocalDate beschikVan) {
		/*
		if(beschikVan.isBefore(LocalDate.now()))
				throw new IllegalArgumentException("Begindatum van beschikbaarheid kan niet voor de huidige datum vallen");
				*/
		this.beschikbaarheidVan = beschikVan;
	}

	public LocalDate getBeschikbaarheidTot() {
		return this.beschikbaarheidTot;
	}

	public void setBeschikbaarheidTot(LocalDate beschikTot) {
		/*
		if(beschikTot.isBefore(LocalDate.now()))
				throw new IllegalArgumentException("Einddatum van beschikbaarheid kan niet voor de huidige datum vallen");
				*/
		this.beschikbaarheidTot = beschikTot;
	}

	public String getExterneMedewerker() {
		return this.externeMedewerker;
	}

	public void setExterneMedewerker(String externeMedewerker) {
		if(externeMedewerker == null)
			this.externeMedewerker = "";
		this.externeMedewerker = externeMedewerker;
	}
	
	// Methodes voor het TableModel
	
	public void setTypeOnderdeel(int index, TypeOnderdeel typeAfwezigheid)
	{
		geefOnderdeel(index).setTypeOnderdeel(typeAfwezigheid);
	}
}
