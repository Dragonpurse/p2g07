package domein;

public abstract class Onderdeel {
	private String displayNaam;
	private String displayOmschrijving;
	private TypeOnderdeel typeOnderdeel;
	
	public String getDisplayNaam(){
		return displayNaam;
	}
	public String getDisplayOmschrijving(){
		return displayOmschrijving;
	}
	public void setDisplayNaam(String displayNaam) {
		this.displayNaam = displayNaam;
	}
	public void setDisplayOmschrijving(String displayOmschrijving) {
		this.displayOmschrijving = displayOmschrijving;
	}
	public TypeOnderdeel getTypeOnderdeel() {
		return typeOnderdeel;
	}
	public void setTypeOnderdeel(TypeOnderdeel typeOnderdeel) {
		if(typeOnderdeel == null)
			return;
		this.typeOnderdeel = typeOnderdeel;
	}
}
