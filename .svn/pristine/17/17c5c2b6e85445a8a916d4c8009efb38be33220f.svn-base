package testen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import domein.Document;
import domein.Leertraject;
import domein.LeertrajectController;
import domein.LeertrajectBuilder;
import domein.Medewerker;
import domein.Onderdeel;

public class UC3Test {
	
	private LeertrajectController lcTest;
	private LocalDate today = LocalDate.now();
	private LocalDate threeDaysFromNow = LocalDate.now().plusDays(3);
	private LocalDate twoWeeksFromNow = LocalDate.now().plusDays(14);
	private LocalDate oneYearFromNow = LocalDate.now().plusYears(1);
	private LocalDate past = LocalDate.now().minusYears(1);
	private List<String> kernwoorden = new ArrayList<String>();
	private Map<String,Medewerker> medewerkersMap;
	private Document d1;
	private Document d2;
	private Leertraject lTest;
	private LeegDatabase ld;
	
	@Before
	public void before(){
		LeegDatabase.leegDatabase();
		lcTest = new LeertrajectController();
		medewerkersMap = new HashMap<String,Medewerker>();
		medewerkersMap.put("m1",new Medewerker("user1",true));
		medewerkersMap.put("m2",new Medewerker("user2",false));
		lcTest.maakLeertrajectAan(new LeertrajectBuilder("A099","titel","omschrijving","doelgroep",twoWeeksFromNow,threeDaysFromNow,oneYearFromNow,medewerkersMap));
		lTest = lcTest.getGeselecteerdLeertraject();
		d1 = new Document("displayNaam","displayOmschrijving","titel","omschrijving","doelgroep",kernwoorden,threeDaysFromNow,oneYearFromNow,"locatie");
		d2 = new Document("displayNaam2","displayOmschrijving2","titel2","omschrijving2","doelgroep2",kernwoorden,threeDaysFromNow,oneYearFromNow,"locatie2");
	}
	
	//Error
	@Test
	public void onderdeelToevoegen(){
		lcTest.linkOnderdeel(d1);
		Assert.assertEquals(lTest.geefOnderdeel(0), d1);
		lcTest.linkOnderdeel(d2);
		Assert.assertEquals(lTest.geefOnderdeel(1), d2);
	}
	
	@Test(expected = NullPointerException.class)
	public void leegOnderdeelToevoegen(){
		Onderdeel o1 = null;
		lcTest.linkOnderdeel(o1);
	}
	
	//Error
	@Test
	public void verwijderOnderdeel(){
		lcTest.linkOnderdeel(d1);
		lcTest.verwijderOnderdeel(d1);
		Assert.assertTrue(lTest.geefAantalOnderdelen() == 0);
	}
	
	
	@Test(expected = NullPointerException.class)
	public void VerwijderOnbekendOnderdeel(){
		lcTest.verwijderOnderdeel(d2);
	}
	
	//Error
	@Test
	public void legeLijstBijGeenOnderdelen(){
		Assert.assertTrue(lTest.geefAantalOnderdelen() == 0);
	}
	

}
