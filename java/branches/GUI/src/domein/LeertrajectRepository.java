package domein;

import java.util.List;

import persistentie.Mapper;
import persistentie.MapperFactory;
import persistentie.SoortMapper;

public class LeertrajectRepository {
	
	private List<Leertraject> trajectLijst;
	private List<Leertraject> trajectActorLijst;
	private Mapper<Leertraject> lm;
	
	public LeertrajectRepository(){
		lm = MapperFactory.geefMapper(SoortMapper.LEERTRAJECT);
	}
	
	public void voegLeertrajectToe(String actorID, Leertraject leertraject) {
		if(trajectLijst == null)
			leertrajectenAanmaken();
		if(trajectActorLijst == null)
			leertrajectenAanmaken(actorID);
		if(leertraject == null)
			throw new NullPointerException();
		trajectLijst.add(leertraject);
		trajectActorLijst.add(leertraject);
		lm.voegItemToe(actorID, leertraject);
	}

	public int geefAantalLeertrajecten() {
		if(trajectLijst == null)
			leertrajectenAanmaken();
		return trajectLijst.size();
	}

	public Leertraject geefLeertraject(int index) {
		if(trajectLijst == null)
			leertrajectenAanmaken();
		return trajectLijst.get(index);
	}
	
	private void leertrajectenAanmaken() {
		trajectLijst = lm.geefLijst();
	}

	public int geefAantalLeertrajecten(String actorID) {
		if(trajectActorLijst == null)
			leertrajectenAanmaken(actorID);
		return trajectActorLijst.size();
	}

	public Leertraject geefLeertraject(String actorID, int index) {
		if(trajectActorLijst == null)
			leertrajectenAanmaken(actorID);
		return trajectActorLijst.get(index);
	}
	
	private void leertrajectenAanmaken(String actorID) {
		trajectActorLijst = lm.geefLijstPerItem(actorID);
	}
}
