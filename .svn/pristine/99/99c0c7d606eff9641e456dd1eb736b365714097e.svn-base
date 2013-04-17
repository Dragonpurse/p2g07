package testen;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import domein.DomeinController;
import domein.Leertraject;


public class UC2Test {

	private DomeinController dc;
	private GregorianCalendar today = DateTime.now().toGregorianCalendar();
	private GregorianCalendar threeDaysFromNow = DateTime.now().plusDays(3).toGregorianCalendar();
	private GregorianCalendar oneYearFromNow = DateTime.now().plusYears(1).toGregorianCalendar();
	private GregorianCalendar past = new GregorianCalendar(2000, 01, 01);
	
	@Before
	public void before(){
		dc = new DomeinController();
	}
	
	@Test
	public void testAanmakenLeertrajectCorrectParameters() {
		dc.aanmakenLeertraject("G001", "Titel", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, 
								"TIN", today, oneYearFromNow, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAanmakenLeertrajectVerkeerdeTitel() {
		dc.aanmakenLeertraject("G0001", "µ", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, 
								"TIN", today , oneYearFromNow, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAanmakenLeertrajectVerkeerdeStartDatum() {
		dc.aanmakenLeertraject("G0001", "titel", "DIT\nIS\nEEN\nOMSCHR", past, 
								"TIN", today , oneYearFromNow, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAanmakenLeertrajectVerkeerdeBeschikVan() {
		dc.aanmakenLeertraject("G0001", "titel", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, 
								"TIN", past , oneYearFromNow, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAanmakenLeertrajectVerkeerdeBeschikTot() {
		dc.aanmakenLeertraject("G0001", "titel", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, 
								"TIN", oneYearFromNow , past, "");
	}
	
	
	@Test
	public void kopieerLeertraject() {
		dc.aanmakenLeertraject("G001", "Titel", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, "TIN", today, oneYearFromNow, "bjorn");
		dc.kopieerLeertraject("G002", "", "", threeDaysFromNow, "WIN", today, oneYearFromNow, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void kopieerLeertrajectZelfdeCode(){
		dc.aanmakenLeertraject("G001", "Titel", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, "TIN", today, oneYearFromNow, "bjorn");
		dc.kopieerLeertraject("G001", "", "", threeDaysFromNow, "WIN", today, oneYearFromNow, "");
	}
	
	@Test
	public void kopieerLeertrajectLegeOptioneleVelden(){
		dc.aanmakenLeertraject("G001", "Titel", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, "TIN", today, oneYearFromNow, "");
		Leertraject lt = dc.getGeselecteerdLeertraject();
		dc.kopieerLeertraject("G002", "", "", threeDaysFromNow, "", today, oneYearFromNow, "");
		Leertraject kopielt = dc.getGeselecteerdLeertraject();
		
		Assert.assertEquals(lt.getTitel(), kopielt.getTitel());
		Assert.assertEquals(lt.getOmschrijving(), kopielt.getOmschrijving());
		Assert.assertEquals(lt.getDoelgroep(), kopielt.getDoelgroep());
	}
	
	// Later controleren : Zijn de elementen gelinkt (nog niet mogelijk in UC2)
	
	
	

}
