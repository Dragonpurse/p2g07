package domein;

import java.util.List;

import org.joda.time.LocalDate;

public class OnderdelenFactory {
	
	public static Document maakDocumentAan(String displayNaam, String displayOmschrijving, String titel, String omschrijving, String doelgroep,
			List<String> kernwoorden, LocalDate beschikbaarVan, LocalDate beschikbaarTot, String locatie){
		
		return new Document(displayNaam, displayOmschrijving, titel, omschrijving, doelgroep,
				kernwoorden, beschikbaarVan, beschikbaarTot, locatie);
	}
	
	
	public static Casus maakCasusAan(String displayNaam, String displayOmschrijving, String titel,
			String omschrijving, String doelgroep, List<String> kernwoorden,
			LocalDate beschikbaarVan, LocalDate beschikbaarTot, Vraag root, String situatieSchets, String locatieIntro){
		return new Casus(displayNaam, displayOmschrijving, titel,
				omschrijving, doelgroep, kernwoorden, beschikbaarVan, beschikbaarTot,
				root, situatieSchets, locatieIntro);
	}
	
	

}
