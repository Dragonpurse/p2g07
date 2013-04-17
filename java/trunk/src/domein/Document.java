package domein;


public class Document extends Onderdeel {

	private String locatieDocument;


	public Document() {
		this.setType(SoortOnderdeel.DOCUMENT);
	}

	public String getLocatie() {
		return locatieDocument;
	}

	public void setLocatieDocument(String locatieDocument) {
		this.locatieDocument = locatieDocument;
	}
}
