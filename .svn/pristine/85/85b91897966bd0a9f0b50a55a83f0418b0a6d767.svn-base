package domein;

public enum StellingAntwoordKleur {
	
    GROEN, ROOD, BLAUW, GEEL;
	
	private static final String[] standaardAntwoorden = 
		{"Ik ben akkoord", "Ik ben niet akkoord", "Ik heb een gemengde mening",
		"Ik heb geen mening"};
	
	public String geefStandaardAntwoord()
	{
		switch(this)
		{
		case GROEN: return standaardAntwoorden[0];
		case ROOD: return standaardAntwoorden[1];
		case BLAUW: return standaardAntwoorden[2];
		case GEEL: return standaardAntwoorden[3];
		default: return "kleur onbekend";
		}
	}
	
	
	public String toString()
	{
		switch(this)
		{
		case GROEN: return "groen";
		case ROOD: return "rood";
		case BLAUW: return "blauw";
		case GEEL: return "geel";
		default: return "kleur onbekend";
		}

	}

	
	public static StellingAntwoordKleur getStellingAntwoordKleur(String kleur){
		switch(kleur){
		case("Rood"):return StellingAntwoordKleur.ROOD;
		case("Groen"):return StellingAntwoordKleur.GROEN;
		case("Blauw"):return StellingAntwoordKleur.BLAUW;
		case("Geel"):return StellingAntwoordKleur.GEEL;
		default: return null;		
		}
	}

}
