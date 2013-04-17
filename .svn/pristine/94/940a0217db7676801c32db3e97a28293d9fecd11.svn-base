package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;

public class CasusAntwoord {

	private CasusVraag volgendeVraag;
	private String antwoord;
	private String feedback;
	
	public CasusAntwoord(String antwoord, String feedback){
		setAntwoord(antwoord);
		setFeedback(feedback);
	}
	

	

	
	public String getAntwoord() {
		return antwoord;
	}
	public void setAntwoord(String antwoord) {
		this.antwoord = antwoord;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	

	
	@Override
	public String toString(){
		if(antwoord.length() < 40){
			return antwoord;
		}
		else return antwoord.substring(0, 40);
	}





	public CasusVraag getVolgendeVraag() {
		return volgendeVraag;
	}





	public void setVolgendeVraag(CasusVraag volgendeVraag) {
		this.volgendeVraag = volgendeVraag;
	}
}
