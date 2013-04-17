package domein;

public enum TypeOnderdeel {
	
	DOCUMENT, CASUS, STELLINGSPEL, DOOS;
	
	private static final String[] typeOnderdelen = 
		{"Document", "Casus", "Stellingspel",
		"Doos"};
	
	public String toString()
	{
		switch(this)
		{
		case DOCUMENT: return typeOnderdelen[0];
		case CASUS: return typeOnderdelen[1];
		case STELLINGSPEL: return typeOnderdelen[2];
		case DOOS: return typeOnderdelen[4];
		default: return "type afwezigheid onbekend";
		}
	}
	
	public String[] geefTypeOnderdelen()
	{
		return typeOnderdelen;
	}
	
	public static TypeOnderdeel getTypeOnderdeel(String type)
	{
		switch(type.toLowerCase())
		{
		case "Document": return DOCUMENT;
		case "Casus":return CASUS;
		case "Stellingspel": return STELLINGSPEL;
		case "Doos":return DOOS;
		default: return null;
		}
	}

}
