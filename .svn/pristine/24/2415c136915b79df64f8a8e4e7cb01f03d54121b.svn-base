package testen;

import java.util.ArrayList;
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


public class UC2Test {

	private LeertrajectController lcTest;
	private Leertraject lTest;
	private LocalDate today = LocalDate.now();
	private LocalDate threeDaysFromNow = LocalDate.now().plusDays(3);
	private LocalDate twoWeeksFromNow = LocalDate.now().plusDays(14);
	private LocalDate oneYearFromNow = LocalDate.now().plusYears(1);
	private LocalDate past = LocalDate.now().minusYears(1);
	private Map<String,Medewerker> medewerkersMap;
	private Document d1;
	private Document d2;
	private List<String> kernwoorden = new ArrayList<String>();
	private LeegDatabase ld;
	
	@Before
	public void before(){
		LeegDatabase.leegDatabase();
		lcTest = new LeertrajectController();
		medewerkersMap = new HashMap<String,Medewerker>();
		medewerkersMap.put("m1",new Medewerker("user1",true));
		medewerkersMap.put("m2",new Medewerker("user2",false));
		LeertrajectBuilder lsTest = new LeertrajectBuilder("A098","titel","omschrijving","doelgroep",twoWeeksFromNow,threeDaysFromNow,oneYearFromNow,medewerkersMap);
		lcTest.maakLeertrajectAan(lsTest);
		lTest = lcTest.getGeselecteerdLeertraject();
		kernwoorden.add("woord1");
		kernwoorden.add("woord2");
		d1 = new Document("displayNaam","displayOmschrijving","titel","omschrijving","doelgroep",kernwoorden,threeDaysFromNow,oneYearFromNow,"locatie");
		d2 = new Document("displayNaam2","displayOmschrijving2","titel2","omschrijving2","doelgroep2",kernwoorden,threeDaysFromNow,oneYearFromNow,"locatie2");
	}
	
	//Error
	@Test
	public void aanmakenNieuwLeertraject() {
		LeertrajectBuilder ls = new LeertrajectBuilder("A102","titel","omschrijving","doelgroep",twoWeeksFromNow,threeDaysFromNow,oneYearFromNow,medewerkersMap);
		lcTest.voegLeertrajectToe(lcTest.maakLeertrajectAan(ls));
		Assert.assertEquals(lcTest.getGeselecteerdLeertraject().getLeertrajectSpec().getLeertrajectCode(),"A102");
	}
	
	//Error
	@Test(expected = IllegalArgumentException.class)
	public void leertrajectcodeBestaatAl() {
		LeertrajectBuilder ls = new LeertrajectBuilder("A098","titel","omschrijving","doelgroep",twoWeeksFromNow,threeDaysFromNow,oneYearFromNow,medewerkersMap);
		lcTest.voegLeertrajectToe(lcTest.maakLeertrajectAan(ls));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void leertrajectcodeLangerDan10() {
		LeertrajectBuilder ls = new LeertrajectBuilder("A0999999999","titel","omschrijving","doelgroep",twoWeeksFromNow,threeDaysFromNow,oneYearFromNow,medewerkersMap);
		lcTest.maakLeertrajectAan(ls);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nietAlleVeldenIngevuld() {
		LeertrajectBuilder ls = new LeertrajectBuilder("A097","",null,"",twoWeeksFromNow,threeDaysFromNow,oneYearFromNow,medewerkersMap);
		lcTest.maakLeertrajectAan(ls);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void titelHeeftOngeldigeTekens() {
		LeertrajectBuilder ls = new LeertrajectBuilder("A096","~","omschrijving","doelgroep",twoWeeksFromNow,threeDaysFromNow,oneYearFromNow,medewerkersMap);
		lcTest.maakLeertrajectAan(ls);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void begindatumValtVoorStartdatum() {
		LeertrajectBuilder ls = new LeertrajectBuilder("A096","titel","omschrijving","doelgroep",threeDaysFromNow,twoWeeksFromNow,oneYearFromNow,medewerkersMap);
		lcTest.maakLeertrajectAan(ls);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void einddatumValtVoorStartOfBegindatum() {
		LeertrajectBuilder ls = new LeertrajectBuilder("A096","titel","omschrijving","doelgroep",oneYearFromNow,twoWeeksFromNow,threeDaysFromNow,medewerkersMap);
		lcTest.maakLeertrajectAan(ls);
	}
	
	//Error
	@Test
	public void kopieerLeertraject() {
		LeertrajectBuilder ls = new LeertrajectBuilder("A095","titel","omschrijving","doelgroep",twoWeeksFromNow,threeDaysFromNow,oneYearFromNow,medewerkersMap);
		lcTest.voegLeertrajectToe(lcTest.maakLeertrajectAan(ls));
		lcTest.linkOnderdeel(d1);
		lcTest.linkOnderdeel(d2);
		
		lcTest.kopieerLeertraject(ls);
		Assert.assertTrue(lcTest.getGeselecteerdLeertraject().geefAantalOnderdelen() == 2);
		Assert.assertEquals(lcTest.getGeselecteerdLeertraject().getLeertrajectSpec().getLeertrajectCode(),"A095");
	}
	
}
