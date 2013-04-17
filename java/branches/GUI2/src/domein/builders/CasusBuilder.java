package domein.builders;

import domein.CasusAntwoord;
import domein.Casus;
import domein.CasusVraag;

public class CasusBuilder extends OnderdeelBuilder {
	
	public void createNewCasus(){
		this.onderdeel = new Casus();
	}
	
	public void buildCasusRoot(CasusVraag eersteVraag){
		((Casus)onderdeel).setCasusRoot(eersteVraag);
	}
	
	public void buildLocatieIntro(String locatieIntro){
		((Casus)onderdeel).setLocatieIntro(locatieIntro);
	}
	
	public void buildSituatieSchets(String situatieSchets){
		((Casus)onderdeel).setSituatieschets(situatieSchets);
	}
	
	//Methoden voor casusStructuur op te stellen
	
	public CasusVraag maakVraagAan(String vraagTekst){
		CasusVraag vraag = new CasusVraag(vraagTekst);
		return vraag;
	}
	
	public CasusAntwoord maakAntwoordAan(String antwoordString, String beschrijving){
		CasusAntwoord antwoord = new CasusAntwoord(antwoordString, beschrijving);
		return antwoord;
	}

}
