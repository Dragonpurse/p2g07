package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;

public class CasusAntwoord {

	private List<CasusVraag> vragen;
	private String antwoord;
	private String feedback;
	
	public CasusAntwoord(String antwoord, String feedback){
		setAntwoord(antwoord);
		setFeedback(feedback);
		this.vragen = new ArrayList<>();
	}
	
	public void Add(CasusVraag newChild){
		vragen.add(newChild);
	}

	
	public Enumeration<CasusVraag> children() {
		return Collections.enumeration(vragen);
	}

	
	public CasusVraag getChildAt(int childIndex) {
		return vragen.get(childIndex);
	}
	
	public int getChildCount() {
		return vragen.size();
	}
	
	public int getIndex(CasusVraag node) {
		return vragen.indexOf(node);
	}

	
	public boolean isLeaf() {
		return vragen.isEmpty();
	}
	
	public void insert(CasusVraag child, int index) {
		vragen.add(index, child);
		
	}
	
	public void remove(int index) {
		vragen.remove(index);
		
	}
	
	public void remove(MutableTreeNode node) {
		vragen.remove(node);
		
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
}
