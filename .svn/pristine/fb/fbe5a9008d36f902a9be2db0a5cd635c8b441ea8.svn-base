package testen;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import domein.Document;
import domein.LeertrajectController;
import domein.Manager;
import domein.Medewerker;
import domein.builders.DocumentBuilder;
import domein.builders.LeertrajectBuilder;

public class UC4Test {
	
	private LeertrajectController leertrajectController;
	private LeertrajectBuilder lBuilder;
	private DocumentBuilder documentBuilder;
	private LocalDate threeDaysFromNow = LocalDate.now().plusDays(3);
	private LocalDate twoWeeksFromNow = LocalDate.now().plusDays(14);
	private LocalDate oneYearFromNow = LocalDate.now().plusYears(1);
	private List<String> kernwoorden;
	private Map<String,Medewerker> medewerkersMap;
	private Document d1;
	private Manager manager;
	
	@Before
	public void before(){
		LeegDatabase.leegDatabase();
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
		documentBuilder.buildKernwoorden(kernwoorden);
		documentBuilder.buildBeschikbaarVan(threeDaysFromNow);
		documentBuilder.buildBeschikbaarTot(oneYearFromNow);
		documentBuilder.buildLocatie("locatie");
		d1 = (Document)documentBuilder.getOnderdeel();
		manager = new Manager();
		leertrajectController = new LeertrajectController(manager);
		leertrajectController.save(d1);	
	}
	
	@Test
	public void bestaandOnderdeelToevoegen() {
		leertrajectController.linkOnderdeel(d1);
		Assert.assertEquals(lBuilder.getLeertraject().geefOnderdeel(0), d1);
	}

}
