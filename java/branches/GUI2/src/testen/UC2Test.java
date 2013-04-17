package testen;

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
import domein.Manager;
import domein.Medewerker;
import domein.builders.DocumentBuilder;
import domein.builders.LeertrajectBuilder;
import domein.builders.MedewerkerBuilder;


public class UC2Test {

	private LeertrajectController leertrajectController;
	private LeertrajectBuilder lBuilder;
	private DocumentBuilder DocumentBuilder;
	private LocalDate threeDaysFromNow = LocalDate.now().plusDays(3);
	private LocalDate twoWeeksFromNow = LocalDate.now().plusDays(14);
	private LocalDate oneYearFromNow = LocalDate.now().plusYears(1);
	private Map<String,Medewerker> medewerkersMap;
	private Document d1;

	private Leertraject l1;
	private List<String> kernwoorden;

	private Manager manager;
	
	@Before
	public void before(){
		LeegDatabase.leegDatabase();
		MedewerkerBuilder medewerkerBuilder = new MedewerkerBuilder();
		medewerkerBuilder.createNewMedewerker();
		medewerkerBuilder.buildMedewerkerLogin("admin@admin.com");
		medewerkerBuilder.buildMedewerkerIntern(true);
		medewerkerBuilder.buildMedewerkerNaam("admin");
		medewerkerBuilder.buildMedewerkerVoornaam("admin");
		medewerkerBuilder.buildMedewerkerOrganisatie("admin");
		medewerkerBuilder.buildMedewerkerTelefoon("34043294832");
		medewerkersMap = new HashMap<>();
		medewerkersMap.put(medewerkerBuilder.getMedewerker().getLogin(), medewerkerBuilder.getMedewerker());
		
		
		
		lBuilder = new LeertrajectBuilder();
		lBuilder.createNewLeertraject();
		lBuilder.buildLeertrajectCode("A098");
		lBuilder.buildTitel("titel");
		lBuilder.buildOmschrijving("omschrijving");
		lBuilder.buildDoelgroep("doelgroep");
		lBuilder.buildStartdatum(twoWeeksFromNow);
		lBuilder.buildBeschikbaarVan(threeDaysFromNow);
		lBuilder.buildBeschikbaarTot(oneYearFromNow);
		lBuilder.buildMedewerkerMap(medewerkersMap);
		l1 = lBuilder.getLeertraject();
		
		DocumentBuilder = new DocumentBuilder();
		DocumentBuilder.createNewDocument();
		DocumentBuilder.buildDisplayNaam("displayNaam");
		DocumentBuilder.buildDisplayOmschrijving("displayOmschrijving");
		DocumentBuilder.buildTitel("titel");
		DocumentBuilder.buildOmschrijving("omschrijving");
		DocumentBuilder.buildDoelgroep("doelgroep");
		DocumentBuilder.buildKernwoorden("kernwoorden");
		DocumentBuilder.buildBeschikbaarVan(threeDaysFromNow);
		DocumentBuilder.buildBeschikbaarTot(oneYearFromNow);
		DocumentBuilder.buildLocatie("locatie");
		d1 = (Document)DocumentBuilder.getOnderdeel();
		
		manager = new Manager();
		leertrajectController = new LeertrajectController(manager);
		leertrajectController.voegLeertrajectToe(l1);
		leertrajectController.save(d1);
		leertrajectController.linkOnderdeel(d1);
	}
	
	//TODO Error
	@Test
	public void aanmakenNieuwLeertraject() {
		lBuilder = new LeertrajectBuilder();
		lBuilder.createNewLeertraject();
		lBuilder.buildLeertrajectCode("A102");
		lBuilder.buildTitel("titel");
		lBuilder.buildOmschrijving("omschrijving");
		lBuilder.buildDoelgroep("doelgroep");
		lBuilder.buildStartdatum(twoWeeksFromNow);
		lBuilder.buildBeschikbaarVan(threeDaysFromNow);
		lBuilder.buildBeschikbaarTot(oneYearFromNow);
		lBuilder.buildMedewerkerMap(medewerkersMap);
		leertrajectController.voegLeertrajectToe(lBuilder.getLeertraject());
		Assert.assertEquals(lBuilder.getLeertraject().getLeertrajectCode(),"A102");
	}
	
	//TODO Error
	@Test(expected = IllegalArgumentException.class)
	public void leertrajectcodeBestaatAl() {
		lBuilder = new LeertrajectBuilder();
		lBuilder.createNewLeertraject();
		lBuilder.buildLeertrajectCode("A098");
		lBuilder.buildTitel("titel");
		lBuilder.buildOmschrijving("omschrijving");
		lBuilder.buildDoelgroep("doelgroep");
		lBuilder.buildStartdatum(twoWeeksFromNow);
		lBuilder.buildBeschikbaarVan(threeDaysFromNow);
		lBuilder.buildBeschikbaarTot(oneYearFromNow);
		lBuilder.buildMedewerkerMap(medewerkersMap);
		leertrajectController.voegLeertrajectToe(lBuilder.getLeertraject());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void leertrajectcodeLangerDan10() {
		lBuilder = new LeertrajectBuilder();
		lBuilder.createNewLeertraject();
		lBuilder.buildLeertrajectCode("A0999999999");
		lBuilder.buildTitel("titel");
		lBuilder.buildOmschrijving("omschrijving");
		lBuilder.buildDoelgroep("doelgroep");
		lBuilder.buildStartdatum(twoWeeksFromNow);
		lBuilder.buildBeschikbaarVan(threeDaysFromNow);
		lBuilder.buildBeschikbaarTot(oneYearFromNow);
		lBuilder.buildMedewerkerMap(medewerkersMap);
		leertrajectController.voegLeertrajectToe(lBuilder.getLeertraject());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nietAlleVeldenIngevuld() {
		lBuilder = new LeertrajectBuilder();
		lBuilder.createNewLeertraject();
		lBuilder.buildLeertrajectCode("A097");
		lBuilder.buildTitel("");
		lBuilder.buildOmschrijving(null);
		lBuilder.buildDoelgroep("");
		lBuilder.buildStartdatum(twoWeeksFromNow);
		lBuilder.buildBeschikbaarVan(threeDaysFromNow);
		lBuilder.buildBeschikbaarTot(oneYearFromNow);
		lBuilder.buildMedewerkerMap(medewerkersMap);
		leertrajectController.voegLeertrajectToe(lBuilder.getLeertraject());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void titelHeeftOngeldigeTekens() {
		lBuilder = new LeertrajectBuilder();
		lBuilder.createNewLeertraject();
		lBuilder.buildLeertrajectCode("A096");
		lBuilder.buildTitel("~");
		lBuilder.buildOmschrijving("omschrijving");
		lBuilder.buildDoelgroep("doelgroep");
		lBuilder.buildStartdatum(twoWeeksFromNow);
		lBuilder.buildBeschikbaarVan(threeDaysFromNow);
		lBuilder.buildBeschikbaarTot(oneYearFromNow);
		lBuilder.buildMedewerkerMap(medewerkersMap);
		leertrajectController.voegLeertrajectToe(lBuilder.getLeertraject());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void begindatumValtVoorStartdatum() {
		lBuilder = new LeertrajectBuilder();
		lBuilder.createNewLeertraject();
		lBuilder.buildLeertrajectCode("A095");
		lBuilder.buildTitel("titel");
		lBuilder.buildOmschrijving("omschrijving");
		lBuilder.buildDoelgroep("doelgroep");
		lBuilder.buildStartdatum(threeDaysFromNow);
		lBuilder.buildBeschikbaarVan(twoWeeksFromNow);
		lBuilder.buildBeschikbaarTot(oneYearFromNow);
		lBuilder.buildMedewerkerMap(medewerkersMap);
		leertrajectController.voegLeertrajectToe(lBuilder.getLeertraject());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void einddatumValtVoorStartOfBegindatum() {
		lBuilder = new LeertrajectBuilder();
		lBuilder.createNewLeertraject();
		lBuilder.buildLeertrajectCode("A094");
		lBuilder.buildTitel("titel");
		lBuilder.buildOmschrijving("omschrijving");
		lBuilder.buildDoelgroep("doelgroep");
		lBuilder.buildStartdatum(oneYearFromNow);
		lBuilder.buildBeschikbaarVan(twoWeeksFromNow);
		lBuilder.buildBeschikbaarTot(threeDaysFromNow);
		lBuilder.buildMedewerkerMap(medewerkersMap);
		leertrajectController.voegLeertrajectToe(lBuilder.getLeertraject());
	}
	
	//TODO Error
	@Test
	public void kopieerLeertraject() {
		lBuilder = new LeertrajectBuilder();
		lBuilder.createNewLeertraject();
		lBuilder.buildLeertrajectCode("A093");
		lBuilder.buildTitel("titel");
		lBuilder.buildOmschrijving("omschrijving");
		lBuilder.buildDoelgroep("doelgroep");
		lBuilder.buildStartdatum(twoWeeksFromNow);
		lBuilder.buildBeschikbaarVan(threeDaysFromNow);
		lBuilder.buildBeschikbaarTot(oneYearFromNow);
		lBuilder.buildMedewerkerMap(medewerkersMap);
		leertrajectController.voegLeertrajectToe(lBuilder.getLeertraject());
		leertrajectController.linkOnderdeel(d1);
		
		//TODO kopiëren leertraject
		
		Assert.assertTrue(lBuilder.getLeertraject().geefAantalOnderdelen() == 1);
		Assert.assertEquals(lBuilder.getLeertraject().getLeertrajectCode(),"A095");
	}
}
