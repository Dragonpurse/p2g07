package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class CasusVraag{

	private List<CasusAntwoord> antwoorden;
	private String vraagTekst;
	
	public CasusVraag(String vraagTekst){
		setVraagTekst(vraagTekst);
		this.antwoorden = new ArrayList<>();
	}
	
	public void Add(CasusAntwoord newChild){
		antwoorden.add(newChild);
	}

	public Enumeration<CasusAntwoord> children() {
		return Collections.enumeration(antwoorden);
	}
	
	public CasusAntwoord getChildAt(int childIndex) {
		return antwoorden.get(childIndex);
	}
	
	public int getChildCount() {
		return antwoorden.size();
	}
	
	public int getIndex(CasusAntwoord node) {
		return antwoorden.indexOf(node);
	}

	
	public boolean isLeaf() {
		return antwoorden.isEmpty();
	}
	
	public void insert(CasusAntwoord child, int index) {
		antwoorden.add(index, child);
		
	}
	
	public void remove(int index) {
		antwoorden.remove(index);
		
	}
	
	public void remove(CasusAntwoord node) {
		antwoorden.remove(node);
		
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
}
