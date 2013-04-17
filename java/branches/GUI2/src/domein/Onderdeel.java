package domein;

import java.util.List;

import org.joda.time.LocalDate;

public class Onderdeel {
	private String displayNaam, displayOmschrijving, titel, omschrijving, doelgroep, kernwoorden;
	private SoortOnderdeel type;
	private int ID;
	private LocalDate beschikbaarVan, beschikbaarTot;
	

	
	public Onderdeel() {
		super();
	}
	public String getDisplayNaam(){
		return displayNaam;
	}

	public String getDisplayOmschrijving(){
		return displayOmschrijving;
	}
	public void setDisplayNaam(String displayNaam) {
		this.displayNaam = displayNaam;
	}
	public void setDisplayOmschrijving(String displayOmschrijving) {
		this.displayOmschrijving = displayOmschrijving;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getOmschrijving() {
		return omschrijving;
	}
	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	public String getKernwoorden() {
		return kernwoorden;
	}
	public void setKernwoorden(String kernwoorden) {
		this.kernwoorden = kernwoorden;
	}
	public String getDoelgroep() {
		return doelgroep;
	}
	public void setDoelgroep(String doelgroep) {
		this.doelgroep = doelgroep;
	}

	public SoortOnderdeel getType() {
		return type;
	}

	public void setType(SoortOnderdeel type) {
		this.type = type;
	}

	public LocalDate getBeschikbaarVan() {
		return beschikbaarVan;
	}

	public void setBeschikbaarVan(LocalDate beschikbaarVan) {
		this.beschikbaarVan = beschikbaarVan;
	}

	public LocalDate getBeschikbaarTot() {
		return beschikbaarTot;
	}

	public void setBeschikbaarTot(LocalDate beschikbaarTot) {
		this.beschikbaarTot = beschikbaarTot;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
