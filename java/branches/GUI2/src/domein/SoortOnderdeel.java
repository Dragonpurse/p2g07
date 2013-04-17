package domein;

public enum SoortOnderdeel {
	
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
		case DOOS: return typeOnderdelen[3];
		default: return "type onderdeel onbekend";
		}
	}
}
