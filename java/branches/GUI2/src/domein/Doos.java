package domein;

import java.util.List;

public class Doos extends Onderdeel {
	private String thema;
	private List<String> vragen;
	private String doosLocatie;
	public Doos() {
		super();
		this.setType(SoortOnderdeel.DOOS);
	}
	
	public String getThema() {
		return thema;
	}
	public void setThema(String thema) {
		this.thema = thema;
	}
	public List<String> getVragen() {
		return vragen;
	}
	public void setVragen(List<String> vragen) {
		this.vragen = vragen;
	}
	public String getDoosLocatie() {
		return doosLocatie;
	}
	public void setDoosLocatie(String doosLocatie) {
		this.doosLocatie = doosLocatie;
	}
	
	
	

}
