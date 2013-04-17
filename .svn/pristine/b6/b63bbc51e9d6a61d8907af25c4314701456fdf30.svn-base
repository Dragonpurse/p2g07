package domein;

import java.util.List;

import org.joda.time.LocalDate;

public class Document extends Onderdeel {

	private String locatie;

	public Document(String displayNaam, String displayOmschrijving, String titel, String omschrijving, String doelgroep,
			List<String> kernwoorden, LocalDate beschikbaarVan, LocalDate beschikbaarTot, String locatie) {
		super(displayNaam, displayOmschrijving, titel, omschrijving, doelgroep,
				kernwoorden, beschikbaarVan, beschikbaarTot);
		this.setLocatie(locatie);
		this.setType(SoortOnderdeel.DOCUMENT);
	}

	public Document() {
		this.setType(SoortOnderdeel.DOCUMENT);
	}

	public String getLocatie() {
		return locatie;
	}

	public void setLocatie(String locatie) {
		this.locatie = locatie;
	}
}
