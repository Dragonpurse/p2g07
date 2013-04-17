package domein.builders;

import domein.Medewerker;

public class MedewerkerBuilder {
	private Medewerker medewerker;
	
	public Medewerker getMedewerker() {
		return medewerker;
	}

	public void createNewMedewerker(){
		this.medewerker = new Medewerker();
	}

    public void buildMedewerkerLogin(String login){
    	medewerker.setLogin(login);
    }
    
    public void buildMedewerkerNaam(String naam) {
    	medewerker.setNaam(naam);
    }
    
    public void buildMedewerkerVoornaam(String voornaam) {
    	medewerker.setVoornaam(voornaam);
    }
    
    public void buildMedewerkerIntern(boolean intern) {
    	medewerker.setIntern(intern);
    }

	public void buildMedewerkerTelefoon(String telefoon) {
		medewerker.setTelefoon(telefoon);
		
	}

	public void buildMedewerkerOrganisatie(String organisatie) {
		medewerker.setOrganisatie(organisatie);
		
	}

    
    


	



}
