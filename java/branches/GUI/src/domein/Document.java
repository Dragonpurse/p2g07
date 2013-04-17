package domein;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

public class Document extends Onderdeel {

	private String titel;
	private String omschrijving;
	private List<String> kernwoorden;
	private String doelgroep;
	private String locatie;
	private LocalDate beschikbaarVan;
	private LocalDate beschikbaarTot;
	
	public Document(String titel, String omschrijving, ArrayList<String> kernwoorden, String doelgroep, String locatie, LocalDate beschikbaarVan, LocalDate beschikbaarTot){
		setTitel(titel);
		setOmschrijving(omschrijving);
		setKernwoorden(kernwoorden);
		setDoelgroep(doelgroep);
		setLocatie(locatie);
		setBeschikbaarVan(beschikbaarVan);
		setBeschikbaarTot(beschikbaarTot);
		// ddfsfdsfsd
		setDisplayNaam("displaynaam");
		setDisplayOmschrijving("Omschrijving");
		setTypeOnderdeel(TypeOnderdeel.DOCUMENT);
	}
	
	public String toString(){
		return String.format("%s,%s,%s,%s,%s,%s,%s,",titel,omschrijving,kernwoorden,doelgroep,locatie,beschikbaarVan,beschikbaarTot);
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

	public List<String> getKernwoorden() {
		return kernwoorden;
	}

	public void setKernwoorden(List<String> kernwoorden) {
		this.kernwoorden = kernwoorden;
	}

	public String getDoelgroep() {
		return doelgroep;
	}

	public void setDoelgroep(String doelgroep) {
		this.doelgroep = doelgroep;
	}

	public String getLocatie() {
		return locatie;
	}

	public void setLocatie(String locatie) {
		this.locatie = locatie;
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
}
