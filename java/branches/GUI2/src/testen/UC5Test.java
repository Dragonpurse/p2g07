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
import domein.CasusAntwoord;
import domein.Casus;
import domein.Leertraject;
import domein.LeertrajectController;
import domein.Manager;
import domein.Medewerker;
import domein.Onderdeel;
import domein.CasusVraag;
import domein.builders.CasusBuilder;

public class UC5Test {
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
	public void testAanmakenCasus() {
		CasusBuilder builder = new CasusBuilder();
		CasusVraag vraag = builder.maakVraagAan("vraag 1");
		CasusAntwoord antwoord1 = builder.maakAntwoordAan("antwoord1", "");
		CasusAntwoord antwoord2 = builder.maakAntwoordAan("antwoord2", "beschrijving1");
		
		vraag.voegAntwoordToe(antwoord1);
		vraag.voegAntwoordToe(antwoord2);
		
		builder.createNewCasus();
		builder.buildCasusRoot(vraag);
		builder.buildTitel("casus1");
		builder.buildOmschrijving("omschrijving");
		builder.buildKernwoorden("kernwoorden");
		builder.buildDoelgroep("doelgroep");
		LocalDate beschikbaarVan = new LocalDate(2000, 10, 10);
		LocalDate beschikbaarTot = new LocalDate(2000, 11, 10);
		builder.buildBeschikbaarVan(beschikbaarVan);
		builder.buildBeschikbaarTot(beschikbaarTot);
		builder.buildLocatieIntro("casus.avi");
		builder.buildSituatieSchets("situatie");
		Casus nieuweCasus = (Casus)builder.getOnderdeel();
		leertrajectController.save(nieuweCasus);
		Assert.assertEquals("casus1", leertrajectController.getManager().geefOnderdeel(0).getTitel());
		Assert.assertEquals("antwoord2", ((CasusAntwoord)((Casus)leertrajectController.getManager().geefOnderdeel(0)).getCasusRoot().getAntwoordAt(1)).getAntwoord());

	}

}
