package domein;

import java.util.ArrayList;
import java.util.List;

public class CasusVraag{

	private List<CasusAntwoord> antwoorden;
	private String vraagTekst;
	private int vraagID;
	
	public CasusVraag(String vraagTekst){
		setVraagTekst(vraagTekst);
		this.antwoorden = new ArrayList<>();
		setVraagID(-1);
	}
	
	public void voegAntwoordToe(CasusAntwoord antwoord){
		antwoorden.add(antwoord);
	}
	
	public CasusAntwoord getAntwoordAt(int childIndex) {
		return antwoorden.get(childIndex);
	}
	
	
	public void verwijderAntwoord(int index) {
		antwoorden.remove(index);
		
	}
	
	public void verwijderAntwoord(CasusAntwoord node) {
		antwoorden.remove(node);
		
	}
	
	public List<CasusAntwoord> getAntwoorden() {
		return antwoorden;
	}

	public void setAntwoorden(List<CasusAntwoord> antwoorden) {
		this.antwoorden = antwoorden;
	}

	public String getVraagTekst() {
		return vraagTekst;
	}
	public void setVraagTekst(String vraagText) {
		this.vraagTekst = vraagText;
	}
	
	@Override
	public String toString(){
		if(vraagTekst.length() < 40){
			return vraagTekst;
		}
		else return vraagTekst.substring(0, 40);
	}

	public int getVraagID() {
		return vraagID;
	}

	public void setVraagID(int vraagID) {
		this.vraagID = vraagID;
	}
}
