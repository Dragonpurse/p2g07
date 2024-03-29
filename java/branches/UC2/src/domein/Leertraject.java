package domein;

import java.util.*;
import org.joda.time.*;

public class Leertraject {

	private String leertrajectCode;
	private String titel;
	private String omschrijving;
	private Calendar startdatum;
	private String doelgroep;
	private Calendar beschikbaarheidVan;
	private Calendar beschikbaarheidTot;
	private String externeMedewerker;

	public Leertraject(String leertrajectCode, String titel,
			String omschrijving, Calendar startDatum, String doelgroep,
			Calendar beschikbaarheidVan, Calendar beschikbaarheidTot,
			String externeMedewerker) {
		
		setLeertrajectCode(leertrajectCode);
		setTitel(titel);
		setOmschrijving(omschrijving);
		setStartdatum(startDatum);
		setDoelgroep(doelgroep);
		setBeschikbaarheidVan(beschikbaarheidVan);
		setBeschikbaarheidTot(beschikbaarheidTot);
		setExterneMedewerker(externeMedewerker);	
	}


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
		if(titel.matches("[a-zA-Z0-9�-�\\s-]*"))
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

	public Calendar getStartdatum() {
		return this.startdatum;
	}

	public void setStartdatum(Calendar startdatum) {
		LocalDate first = LocalDate.fromCalendarFields(startdatum);
		if(first.isBefore(LocalDate.now()))
				throw new IllegalArgumentException();
	    this.startdatum = startdatum;
	}

	public String getDoelgroep() {
		return this.doelgroep;
	}

	public void setDoelgroep(String doelgroep) {
		if(doelgroep == "" || doelgroep == null)
			throw new IllegalArgumentException("Doelgroep mag niet leeg zijn");
		this.doelgroep = doelgroep;
	}

	public Calendar getBeschikbaarheidVan() {
		return this.beschikbaarheidVan;
	}

	public void setBeschikbaarheidVan(Calendar beschikbaarheidVan) {
		LocalDate first = LocalDate.fromCalendarFields(beschikbaarheidVan);
		if(first.isBefore(LocalDate.now()))
				throw new IllegalArgumentException();
		this.beschikbaarheidVan = beschikbaarheidVan;
	}

	public Calendar getBeschikbaarheidTot() {
		return this.beschikbaarheidTot;
	}

	public void setBeschikbaarheidTot(Calendar beschikbaarheidTot) {
		LocalDate first = LocalDate.fromCalendarFields(beschikbaarheidTot);
		if(first.isBefore(LocalDate.now()))
				throw new IllegalArgumentException();
		this.beschikbaarheidTot = beschikbaarheidTot;
	}

	public String getExterneMedewerker() {
		return this.externeMedewerker;
	}

	public void setExterneMedewerker(String externeMedewerker) {
		if(externeMedewerker == null)
			this.externeMedewerker = "";
		this.externeMedewerker = externeMedewerker;
	}
}
