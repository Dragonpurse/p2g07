package domein.builders;

import java.util.List;

import domein.Doos;

public class DoosBuilder extends OnderdeelBuilder {
	public void createNewDoos(){
		this.onderdeel = new Doos();
	}
	
	public void buildVragen(List<String> vragen){
		((Doos)getOnderdeel()).setVragen(vragen);
	}
}
