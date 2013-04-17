package testen;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import domein.Document;
import domein.Leertraject;
import domein.LeertrajectController;
import domein.Onderdeel;


public class UC2Test {

	private LeertrajectController lc;
	private LocalDate today = LocalDate.now();
	private LocalDate threeDaysFromNow = LocalDate.now().plusDays(3);
	private LocalDate oneYearFromNow = LocalDate.now().plusYears(1);
	private LocalDate past = LocalDate.now().minusYears(1);
	
	private ArrayList<String> tags = new ArrayList<String>(Arrays.asList("lol", "wut", "test"));
	private Onderdeel doc1 = new Document("TestDoc1", "Dit is een test document", tags, "2TIN", "C:/Users/Test/document1.docx","naamDoc1","omschrijvingDoc1");
	private Onderdeel doc2 = new Document("TestDoc2", "Dit is een tweede test document", tags, "3TIN", "C:/Users/Test/document2.docx","naamDoc2","omschrijvingDoc2");
	
	@Before
	public void before(){
		lc = new LeertrajectController();
	}
	
	@Test
	public void testAanmakenLeertrajectCorrectParameters() {
		lc.maakLeertrajectAan("G001", "Titel", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, "TIN", today, oneYearFromNow, "");
		Assert.assertEquals(lc.getGeselecteerdLeertraject().getLeertrajectCode(),"G001");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAanmakenLeertrajectVerkeerdeTitel() {
		lc.maakLeertrajectAan("G001", "µ", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, 
								"TIN", today , oneYearFromNow, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAanmakenLeertrajectVerkeerdeStartDatum() {
		lc.maakLeertrajectAan("G0001", "titel", "DIT\nIS\nEEN\nOMSCHR", past, 
								"TIN", today , oneYearFromNow, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAanmakenLeertrajectVerkeerdeBeschikVan() {
		lc.maakLeertrajectAan("G0001", "titel", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, 
								"TIN", past , oneYearFromNow, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAanmakenLeertrajectVerkeerdeBeschikTot() {
		lc.maakLeertrajectAan("G0001", "titel", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, 
								"TIN", oneYearFromNow , past, "");
	}
	
	
	@Test
	public void kopieerLeertrajectZonderElementen() {
		lc.maakLeertrajectAan("G001", "Titel", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, "TIN", today, oneYearFromNow, "bjorn");
		Leertraject lt = lc.getGeselecteerdLeertraject();
		lc.kopieerLeertraject("G002", "", "", threeDaysFromNow, "WIN", today, oneYearFromNow, "");
		Leertraject kopieLt = lc.getGeselecteerdLeertraject();
		Assert.assertEquals(lt.getTitel(), kopieLt.getTitel());
		Assert.assertEquals(lt.getOmschrijving(), kopieLt.getOmschrijving());
	}
	
	@Test
	public void kopieerLeertrajectMetElementen() {
		lc.maakLeertrajectAan("G001", "Titel", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, "TIN", today, oneYearFromNow, "bjorn");
		lc.voegOnderdeelToe(doc1);
		lc.voegOnderdeelToe(doc2);
		Leertraject lt = lc.getGeselecteerdLeertraject();
		Document d = (Document) lt.geefOnderdeel(0);
		
		lc.kopieerLeertraject("G002", "", "", threeDaysFromNow, "WIN", today, oneYearFromNow, "");
		Leertraject kopieLt = lc.getGeselecteerdLeertraject();
		Document kopieD = (Document) kopieLt.geefOnderdeel(0);
		
		Assert.assertEquals(lt.geefAantalOnderdelen(), kopieLt.geefAantalOnderdelen());
		Assert.assertEquals(d.getLocatie(), kopieD.getLocatie());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void kopieerLeertrajectZelfdeCode(){
		lc.maakLeertrajectAan("G001", "Titel", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, "TIN", today, oneYearFromNow, "bjorn");
		lc.kopieerLeertraject("G001", "", "", threeDaysFromNow, "WIN", today, oneYearFromNow, "");
	}
	
	@Test
	public void kopieerLeertrajectLegeOptioneleVelden(){
		lc.maakLeertrajectAan("G001", "Titel", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, "TIN", today, oneYearFromNow, "");
		Leertraject lt = lc.getGeselecteerdLeertraject();
		lc.kopieerLeertraject("G002", "", "", threeDaysFromNow, "", today, oneYearFromNow, "");
		Leertraject kopieLt = lc.getGeselecteerdLeertraject();
		
		Assert.assertEquals(lt.getTitel(), kopieLt.getTitel());
		Assert.assertEquals(lt.getOmschrijving(), kopieLt.getOmschrijving());
		Assert.assertEquals(lt.getDoelgroep(), kopieLt.getDoelgroep());	
	}
}
