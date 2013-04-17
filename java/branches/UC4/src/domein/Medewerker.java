package domein;

public class Medewerker {
	private String login;
	private Boolean Intern;
	
	public Medewerker(String login, Boolean Intern){
		setLogin(login);
		setIntern(Intern);
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
}
