package testen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import persistentie.LeertrajectMapper;
import persistentie.MedewerkerMapper;
import persistentie.OnderdeelMapper;
import domein.Leertraject;
import domein.LeertrajectController;
import domein.Manager;
import domein.Medewerker;
import domein.Onderdeel;
import domein.SoortOnderdeel;
import domein.Stelling;
import domein.StellingAntwoord;
import domein.StellingAntwoordKleur;
import domein.builders.StellingspelBuilder;

public class UC6Test {
	
	private LeertrajectController leertrajectController;
	private Manager manager;
	private LeertrajectMapper leertrajectMapperMock;
	private OnderdeelMapper onderdeelMapperMock;
	private MedewerkerMapper medewerkerMapperMock;
	private Map<String, Medewerker> medewerkerMap;
	private List<Onderdeel> onderdeelLijst;
	private List<Leertraject> leertrajectLijst;

	@Before
	public void before(){
		medewerkerMap = new HashMap<String, Medewerker>();
		medewerkerMap.put("test", new Medewerker("e-mail","naam","voornaam","telefoon","organisatie", true));
		onderdeelLijst = new ArrayList<>();
		leertrajectLijst = new ArrayList<>();
		leertrajectMapperMock = Mockito.mock(LeertrajectMapper.class);
		onderdeelMapperMock = Mockito.mock(OnderdeelMapper.class);
		medewerkerMapperMock = Mockito.mock(MedewerkerMapper.class);
		Mockito.when(medewerkerMapperMock.logIn("test", "test")).thenReturn(true);
		Mockito.when(medewerkerMapperMock.geefMap()).thenReturn(medewerkerMap);

		Mockito.when(onderdeelMapperMock.geefLijst()).thenReturn(onderdeelLijst);
		
		Mockito.when(leertrajectMapperMock.geefLijst()).thenReturn(leertrajectLijst);
		manager = new Manager(leertrajectMapperMock, onderdeelMapperMock, medewerkerMapperMock);
		leertrajectController = new LeertrajectController(manager);
	}
	
	@Test
	public void testAanmakenStellingspel() {
		StellingspelBuilder stellingspelBuilder = new StellingspelBuilder();
		stellingspelBuilder.createNewStellingspel();
		
		stellingspelBuilder.buildTitel("titel");
		stellingspelBuilder.buildOmschrijving("omschrijving");
		stellingspelBuilder.buildDoelgroep("doelgroep");
		stellingspelBuilder.buildKernwoorden("kernwoorden");
		stellingspelBuilder.buildBeschikbaarVan(new LocalDate(2000, 10, 10));
		stellingspelBuilder.buildBeschikbaarTot(new LocalDate(2000, 11, 10));
		stellingspelBuilder.buildDisplayNaam("displayNaam");
		stellingspelBuilder.buildDisplayOmschrijving("displayOmschrijving");
		
		List<Stelling> stellingen = new ArrayList<>();
		
		List<StellingAntwoord> antwoorden = new ArrayList<>();
		
		antwoorden.add(stellingspelBuilder.maakStellingAntwoordAan(StellingAntwoordKleur.ROOD.geefStandaardAntwoord(), StellingAntwoordKleur.ROOD));
		antwoorden.add(stellingspelBuilder.maakStellingAntwoordAan(StellingAntwoordKleur.GEEL.geefStandaardAntwoord(), StellingAntwoordKleur.GEEL));
		antwoorden.add(stellingspelBuilder.maakStellingAntwoordAan(StellingAntwoordKleur.BLAUW.geefStandaardAntwoord(), StellingAntwoordKleur.BLAUW));
		antwoorden.add(stellingspelBuilder.maakStellingAntwoordAan(StellingAntwoordKleur.GROEN.geefStandaardAntwoord(), StellingAntwoordKleur.GROEN));
		
		stellingen.add(stellingspelBuilder.maakStellingAan("stelling", antwoorden));
		
		stellingspelBuilder.buildStellingen(stellingen);
		
		leertrajectController.save(stellingspelBuilder.getOnderdeel());
		Assert.assertEquals(SoortOnderdeel.STELLINGSPEL, leertrajectController.getManager().geefOnderdeel(0).getType());
		
	}

}
