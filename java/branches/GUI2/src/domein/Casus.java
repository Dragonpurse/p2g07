package domein;



public class Casus extends Onderdeel {

	private CasusVraag casusRoot;
	private String situatieschets;
	private String locatieIntro;
	
	public Casus(){
		super();
		this.setType(SoortOnderdeel.CASUS);
	}
	
	public CasusVraag getCasusRoot() {
		return casusRoot;
	}
	public void setCasusRoot(CasusVraag casusRoot) {
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
