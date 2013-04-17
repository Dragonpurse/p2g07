package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import persistentie.LeertrajectMapper;
import persistentie.Mapper;
import persistentie.MapperFactory;
import persistentie.MedewerkerMapper;
import persistentie.OnderdeelMapper;
import persistentie.SoortMapper;

public class Manager {
	
	private List<Leertraject> trajectLijst;
	private List<Leertraject> trajectLijstActor;
	private List<Onderdeel> onderdelenLijst;
	private Map<String, Medewerker> medewerkerMap;
	private Mapper<Leertraject> leertrajectMapper;
	private Mapper<Onderdeel> onderdeelMapper;
	private Mapper<Medewerker> medewerkerMapper;
	private Medewerker ingelogdeMedewerker;

	public Manager(){
		leertrajectMapper = MapperFactory.geefMapper(SoortMapper.LEERTRAJECT);
		onderdeelMapper = MapperFactory.geefMapper(SoortMapper.ONDERDEEL);
		medewerkerMapper = MapperFactory.geefMapper(SoortMapper.MEDEWERKER);
		medewerkerMap = medewerkerMapper.geefMap();
		trajectLijst = ((LeertrajectMapper)leertrajectMapper).geefLijst(medewerkerMap);
		onderdelenLijst = onderdeelMapper.geefLijst();
	}

	//Constructor voor test UC1
	public Manager(MedewerkerMapper medewerkerMapper){
		this.medewerkerMapper = medewerkerMapper;
		medewerkerMap = medewerkerMapper.geefMap();
		
	}
	
	public Manager(LeertrajectMapper leertrajectMapper, OnderdeelMapper onderdeelMapper, MedewerkerMapper medewerkerMapper){
		this.leertrajectMapper = leertrajectMapper;
		this.onderdeelMapper = onderdeelMapper;
		this.medewerkerMapper = medewerkerMapper;
		medewerkerMap = medewerkerMapper.geefMap();
		trajectLijst = ((LeertrajectMapper)leertrajectMapper).geefLijst(medewerkerMap);
		onderdelenLijst = onderdeelMapper.geefLijst();
		
	}
	
	public void save(Object o) {
		if(o instanceof Leertraject){
			((Leertraject) o).getMedewerkerMap().put(ingelogdeMedewerker.getLogin(), ingelogdeMedewerker);
			leertrajectMapper.voegItemToe((Leertraject) o);
			trajectLijst.add((Leertraject) o);
			trajectLijstActor.add((Leertraject) o);
		}
		if(o instanceof Onderdeel){
			onderdeelMapper.voegItemToe((Onderdeel) o);
            onderdelenLijst.add((Onderdeel) o);
		}
	}
	
	public boolean logIn(String naam, String paswoord){
		Boolean ingelogd = ((MedewerkerMapper)medewerkerMapper).logIn(naam, paswoord);
		if(ingelogd){
			setIngelogdeMedewerker(medewerkerMap.get(naam));
			vulTrajectLijstActor();
		}
		return ingelogd;
	}
	
	public Medewerker getIngelogdeMedewerker() {
		return ingelogdeMedewerker;
	}

	public void setIngelogdeMedewerker(Medewerker ingelogdeMedewerker) {
		this.ingelogdeMedewerker = ingelogdeMedewerker;
	}
	
	private void vulTrajectLijstActor() {
		trajectLijstActor = new ArrayList<>();
		for(Leertraject traject : trajectLijst){
			if(traject.getMedewerkerMap().containsValue(ingelogdeMedewerker)){
				trajectLijstActor.add(traject);
			}
		}
		
	}

	// TableModel methodes
	
	
	// Alle leertrajecten
	public int geefAantalLeertrajecten() {
		return trajectLijst.size();
	}

	public Leertraject geefLeertraject(int index) {
		return trajectLijst.get(index);
	}
	
	// Leertrajecten actor
	
	public int geefAantalLeertrajectenActor(){
		return trajectLijstActor.size();
	}
	
	public Leertraject geefLeertrajectActor(int index){
		return trajectLijstActor.get(index);
	}
	
	// Onderdelen
	
	public int geefAantalOnderdelen(){
		return onderdelenLijst.size();
	}
	
	public Onderdeel geefOnderdeel(int index){
		return onderdelenLijst.get(index);
	}


}
