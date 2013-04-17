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
import domein.builders.DoosBuilder;

public class UC7Test {

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
	public void testAanmakenDoos() {
		DoosBuilder doosBuilder = new DoosBuilder();
		doosBuilder.createNewDoos();
		
		doosBuilder.buildTitel("titel");
		doosBuilder.buildOmschrijving("omschrijving");
		doosBuilder.buildDoelgroep("doelgroep");
		doosBuilder.buildKernwoorden("kernwoorden");
		doosBuilder.buildBeschikbaarVan(new LocalDate(2000, 10, 10));
		doosBuilder.buildBeschikbaarTot(new LocalDate(2000, 11, 10));
		doosBuilder.buildDisplayNaam("displayNaam");
		doosBuilder.buildDisplayOmschrijving("displayOmschrijving");
		doosBuilder.buildThema("thema");
		
		List<String> vragen = new ArrayList<>();
		vragen.add("vraag1");
		vragen.add("vraag2");
		
		doosBuilder.buildVragen(vragen);
		
		leertrajectController.save(doosBuilder.getOnderdeel());
		Assert.assertEquals(SoortOnderdeel.DOOS, leertrajectController.getManager().geefOnderdeel(0).getType());
	}

}
