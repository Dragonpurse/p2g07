package domein;

import java.util.List;

import org.joda.time.LocalDate;


public class Casus extends Onderdeel {

	private Vraag casusRoot;
	private String situatieschets;
	private String locatieIntro;
	
	public Casus(String displayNaam, String displayOmschrijving, String titel,
			String omschrijving, String doelgroep, List<String> kernwoorden,
			LocalDate beschikbaarVan, LocalDate beschikbaarTot, Vraag root, String situatieSchets, String locatieIntro) {
		super(displayNaam, displayOmschrijving, titel, omschrijving, doelgroep,
				kernwoorden, beschikbaarVan, beschikbaarTot);
		setCasusRoot(root);
		setSituatieschets(situatieSchets);
		setLocatieIntro(locatieIntro);
	}
	
	public Vraag getCasusRoot() {
		return casusRoot;
	}
	public void setCasusRoot(Vraag casusRoot) {
		this.casusRoot = casusRoot;
	}
	public String getSituatieschets() {
		return situatieschets;
	}
	public void setSituatieschets(String situatieschets) {
		this.situatieschets = situatieschets;
	}
	public String getLocatieIntro() {
		return locatieIntro;
	}
	public void setLocatieIntro(String locatieIntro) {
		this.locatieIntro = locatieIntro;
	}
	
	

}
