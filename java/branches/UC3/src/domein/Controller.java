package domein;

import java.util.Observable;
import java.util.Observer;

public abstract class Controller extends Observable implements Observer{
	
	private Medewerker medewerker;
	
	public Controller(){
		setMedewerker(new Medewerker("admin",true));
	}

	public Medewerker getMedewerker() {
		return medewerker;
	}

	public void setMedewerker(Medewerker medewerker) {
		this.medewerker = medewerker;
	}
}
