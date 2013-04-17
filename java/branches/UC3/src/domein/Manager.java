package domein;

import java.util.List;
import persistentie.Mapper;
import persistentie.MapperFactory;
import persistentie.SoortMapper;

public class Manager {
	
	private List<Leertraject> trajectLijst;
	private List<Onderdeel> onderdelenLijst;
	private Mapper<Leertraject> lm;
	private Mapper<Onderdeel> om;

	public Manager(){
		lm = MapperFactory.geefMapper(SoortMapper.LEERTRAJECT);
		om = MapperFactory.geefMapper(SoortMapper.ONDERDEEL);
		trajectLijst = lm.geefLijst();
		onderdelenLijst = om.geefLijst();
	}
	
	public void save(Object o) {
		switch(o.getClass().getSimpleName().toLowerCase()){
		case "leertraject": lm.voegItemToe((Leertraject) o);
							trajectLijst.add((Leertraject) o);
						    break;
		case "document": om.voegItemToe((Onderdeel) o);
		                 onderdelenLijst.add((Onderdeel) o);
		                 break;
		default : break;
		}
	}
	
	// TableModel methodes

	public int geefAantalLeertrajecten() {
		return trajectLijst.size();
	}

	public Leertraject geefLeertraject(int index) {
		return trajectLijst.get(index);
	}
	
	public int geefAantalOnderdelen(){
		return onderdelenLijst.size();
	}
	
	public Onderdeel geefOnderdeel(int index){
		return onderdelenLijst.get(index);
	}
}
