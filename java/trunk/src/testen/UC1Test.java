package testen;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import persistentie.MedewerkerMapper;
import domein.LeertrajectController;
import domein.Manager;
import domein.Medewerker;

public class UC1Test {
	private LeertrajectController leertrajectController;
	private Map<String, Medewerker> medewerkerMap;


	@Before
	public void before() {
		MedewerkerMapper medewerkerMapperMock = Mockito.mock(MedewerkerMapper.class);
		medewerkerMap = new HashMap<String, Medewerker>();
		medewerkerMap.put("m1", new Medewerker("m1", true));
		medewerkerMap.put("m2", new Medewerker("m2", true));
		medewerkerMap.put("m3", new Medewerker("m3", false));
		Mockito.when(medewerkerMapperMock.geefMap()).thenReturn(medewerkerMap);
		Mockito.when(medewerkerMapperMock.logIn("m1", "wacht1")).thenReturn(true);
		Mockito.when(medewerkerMapperMock.logIn("m2", "wacht2")).thenReturn(false);
		Mockito.when(medewerkerMapperMock.logIn("m3", "wacht3")).thenReturn(true);	
		leertrajectController = new LeertrajectController(new Manager(medewerkerMapperMock));
		
	}
	
	@Test
	public void testLogInSuccesvol(){
		Assert.assertTrue(leertrajectController.logIn("m1", "wacht1"));
	}
	
	@Test
	public void testLogInNietSuccesvol(){
		Assert.assertFalse(leertrajectController.logIn("m2", "wacht2"));
	}


}
