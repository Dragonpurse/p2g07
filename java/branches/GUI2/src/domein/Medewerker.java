package domein;

public class Medewerker {
	private String login,naam,voornaam,telefoon,organisatie;
	private Boolean Intern;
	
	
	public Medewerker(String email, String naam, String voornaam,String telefoon,String organisatie, Boolean Intern){
		setLogin(email);
		setIntern(Intern);
		setNaam(naam);
		setVoornaam(voornaam);
		setTelefoon(telefoon);
		setOrganisatie(organisatie);
	}

	public Medewerker() {
		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Boolean getIntern() {
		return Intern;
	}

	public void setIntern(Boolean intern) {
		Intern = intern;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getTelefoon() {
		return telefoon;
	}

	public void setTelefoon(String telefoon) {
		this.telefoon = telefoon;
	}

	public String getOrganisatie() {
		return organisatie;
	}

	public void setOrganisatie(String organisatie) {
		this.organisatie = organisatie;
	}
}
