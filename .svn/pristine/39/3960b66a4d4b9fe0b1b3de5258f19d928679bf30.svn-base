package testen;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import domein.Document;
import domein.LeertrajectController;
import domein.Manager;
import domein.Medewerker;
import domein.Onderdeel;
import domein.builders.DocumentBuilder;
import domein.builders.LeertrajectBuilder;

public class UC3Test {
	
	private LeertrajectController leertrajectController;
	private LeertrajectBuilder lBuilder;
	private DocumentBuilder documentBuilder;
	private LocalDate threeDaysFromNow = LocalDate.now().plusDays(3);
	private LocalDate twoWeeksFromNow = LocalDate.now().plusDays(14);
	private LocalDate oneYearFromNow = LocalDate.now().plusYears(1);
	private Map<String,Medewerker> medewerkersMap;
	private Document d1;
	private Document d2;
	private Manager manager;
	
	@Before
	public void before(){
		LeegDatabase.leegDatabase();
		medewerkersMap = new HashMap<String,Medewerker>();
		lBuilder = new LeertrajectBuilder();
		lBuilder.createNewLeertraject();
		lBuilder.buildLeertrajectCode("A099");
		lBuilder.buildTitel("titel");
		lBuilder.buildOmschrijving("omschrijving");
		lBuilder.buildDoelgroep("doelgroep");
		lBuilder.buildStartdatum(twoWeeksFromNow);
		lBuilder.buildBeschikbaarVan(threeDaysFromNow);
		lBuilder.buildBeschikbaarTot(oneYearFromNow);
		lBuilder.buildMedewerkerMap(medewerkersMap);
		documentBuilder = new DocumentBuilder();
		documentBuilder.createNewDocument();
		documentBuilder.buildDisplayNaam("displayNaam");
		documentBuilder.buildDisplayOmschrijving("displayOmschrijving");
		documentBuilder.buildTitel("titel");
		documentBuilder.buildOmschrijving("omschrijving");
		documentBuilder.buildDoelgroep("doelgroep");
		documentBuilder.buildKernwoorden("kernwoorden");
		documentBuilder.buildBeschikbaarVan(threeDaysFromNow);
		documentBuilder.buildBeschikbaarTot(oneYearFromNow);
		documentBuilder.buildLocatie("locatie");
		d1 = (Document)documentBuilder.getOnderdeel();
		manager = new Manager();
		leertrajectController = new LeertrajectController(manager);
		leertrajectController.save(d1);	
	}
	
	//TODO Error
	@Test
	public void onderdeelToevoegen(){
		leertrajectController.linkOnderdeel(d1);
		Assert.assertEquals(lBuilder.getLeertraject().geefOnderdeel(0), d1);

	}
	
	@Test(expected = NullPointerException.class)
	public void leegOnderdeelToevoegen(){
		Onderdeel o1 = null;
		leertrajectController.linkOnderdeel(o1);
	}
	
	//TODO Error
	@Test
	public void verwijderOnderdeel(){
		leertrajectController.linkOnderdeel(d1);
		leertrajectController.verwijderOnderdeel(d1);
		Assert.assertTrue(lBuilder.getLeertraject().geefAantalOnderdelen() == 0);
	}
	
	@Test(expected = NullPointerException.class)
	public void VerwijderOnbekendOnderdeel(){
		leertrajectController.verwijderOnderdeel(d2);
	}
	
	@Test
	public void legeLijstBijGeenOnderdelen(){
		Assert.assertTrue(lBuilder.getLeertraject().geefAantalOnderdelen() == 0);
		
	}
}
