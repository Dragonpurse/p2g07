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

public class UC3Test {
	
	private LeertrajectController lc;
	private Leertraject lt;
	private LocalDate today = LocalDate.now();
	private LocalDate threeDaysFromNow = LocalDate.now().plusDays(3);
	private LocalDate oneYearFromNow = LocalDate.now().plusYears(1);
	private ArrayList<String> tags = new ArrayList<String>(Arrays.asList("lol", "wut", "test"));
	private Onderdeel doc1 = new Document("TestDoc1", "Dit is een test document", tags, "2TIN", "C:/Users/Test/document1.docx","naamDoc1","omschrijvingDoc1");
	private Onderdeel doc2 = new Document("TestDoc2", "Dit is een tweede test document", tags, "3TIN", "C:/Users/Test/document2.docx","naamDoc2","omschrijvingDoc2");
	
	@Before
	public void before(){
		lc = new LeertrajectController();
		lc.maakLeertrajectAan("G001", "Titel", "DIT\nIS\nEEN\nOMSCHR", threeDaysFromNow, 
				"TIN", today, oneYearFromNow, "");
		lt = lc.getGeselecteerdLeertraject();
	}
	
	@Test
	public void testAddOnderdeel(){
		lc.voegOnderdeelToe(doc1);
		Assert.assertEquals(lt.geefOnderdeel(0), doc1);
		lc.voegOnderdeelToe(doc2);
		Assert.assertEquals(lt.geefOnderdeel(1), doc2);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddOnderdeelDieErNietZijn(){
		Onderdeel doc3 = null;
		Onderdeel doc4 = null;
		lc.voegOnderdeelToe(doc3);
		lc.voegOnderdeelToe(doc4);
	}
	
	@Test
	public void testVerwijderOnderdeel(){
		lc.voegOnderdeelToe(doc1);
		lc.voegOnderdeelToe(doc2);
		lc.verwijderOnderdeel(doc1);
		Document d = (Document) lt.geefOnderdeel(0);
		Assert.assertEquals(((Document) doc2).getLocatie(), d.getLocatie());
		lc.verwijderOnderdeel(doc2);
		Assert.assertTrue(lt.geefAantalOnderdelen() == 0);
	}
	
	
	@Test(expected = NullPointerException.class)
	public void testVerwijderOnderdeelDieErNietZijn(){
		lc.verwijderOnderdeel(doc1);
		lc.verwijderOnderdeel(doc2);
	}
	
	@Test
	public void testGetLijstOnderdelen(){
		testAddOnderdeel();
		Assert.assertEquals(2, lt.geefAantalOnderdelen());
	}
	
	@Test
	public void testGetLegeLijstOnderdelen(){
		Assert.assertTrue(lt.geefAantalOnderdelen() == 0);
	}
	

}