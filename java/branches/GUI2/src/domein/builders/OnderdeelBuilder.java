package domein.builders;

import java.util.List;

import org.joda.time.LocalDate;

import domein.Onderdeel;

public abstract class OnderdeelBuilder {
	
	protected Onderdeel onderdeel;
	
	public Onderdeel getOnderdeel(){
		Onderdeel onderdeelReturn = this.onderdeel;
		this.onderdeel = null;
		return onderdeelReturn;
	}	
	
	public void buildDisplayNaam(String displayNaam){
		onderdeel.setDisplayNaam(displayNaam);
	}
	
	public void buildDisplayOmschrijving(String displayOmschrijving){
		onderdeel.setDisplayOmschrijving(displayOmschrijving);
	}
	
	public void buildTitel(String titel){
		onderdeel.setTitel(titel);
	}
	
	public void buildOmschrijving(String omschrijving){
		onderdeel.setOmschrijving(omschrijving);
	}
	
	public void buildDoelgroep(String doelgroep){
		onderdeel.setDoelgroep(doelgroep);
	}
	
	public void buildKernwoorden(String kernwoorden){
		onderdeel.setKernwoorden(kernwoorden);
	}
	
	public void buildBeschikbaarVan(LocalDate beschikbaarVan){
		onderdeel.setBeschikbaarVan(beschikbaarVan);
	}
	
	public void buildBeschikbaarTot(LocalDate beschikbaarTot){
		onderdeel.setBeschikbaarTot(beschikbaarTot);
	}
	


}
