package domein.builders;

import java.util.List;

import domein.Stelling;
import domein.StellingAntwoord;
import domein.StellingAntwoordKleur;
import domein.Stellingspel;

public class StellingspelBuilder extends OnderdeelBuilder {
	
	public void createNewStellingspel(){
		this.onderdeel = new Stellingspel();
	}
	
	public void buildStellingen(List<Stelling> stellingen){
		((Stellingspel)onderdeel).setStellingen(stellingen);
	}
	
	//Methoden voor stellingen op te bouwen
	
	public StellingAntwoord maakStellingAntwoordAan(String antwoord, StellingAntwoordKleur kleur){
		return new StellingAntwoord(antwoord, kleur);
	}
	
	public Stelling maakStellingAan(String stelling, List<StellingAntwoord> antwoorden){
		return new Stelling(stelling, antwoorden);
	}
}
