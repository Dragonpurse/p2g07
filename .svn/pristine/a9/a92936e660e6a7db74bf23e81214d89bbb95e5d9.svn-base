package domein;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Stellingspel extends Onderdeel {
	private static final String[] standaardAntwoorden = {"geel","groen","rood","blauw"};
	private Map<String,List<String>> stellingen;
	
	public Stellingspel(){
		super();
		this.setType(SoortOnderdeel.STELLINGSPEL);
	}
	
	public void insertStelling(String stelling, List<String> antwoorden){
		stellingen.put(stelling, antwoorden);
	}
	
	public void insertStellingMetStandaardAntwoorden(String stelling){
		stellingen.put(stelling, Arrays.asList(standaardAntwoorden));
	}

	public static String[] getStandaardantwoorden() {
		return standaardAntwoorden;
	}
	
	public void setStellingen(Map<String,List<String>> stellingen){
		if(stellingen.size() < 5 && stellingen.size() > 1)
			this.stellingen = stellingen;
	}

	public Map<String, List<String>> getStellingen() {
		return stellingen;
	}

	
	
	

}
