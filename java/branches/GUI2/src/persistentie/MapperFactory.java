package persistentie;

public class MapperFactory {
	@SuppressWarnings("unchecked")
	public static <E> Mapper<E> geefMapper(SoortMapper soort){
		switch(soort) {
		case LEERTRAJECT : return (Mapper<E>) new LeertrajectMapper();
		case ONDERDEEL : return (Mapper<E>) new OnderdeelMapper();
		case MEDEWERKER : return (Mapper<E>) new MedewerkerMapper();
		default: return null;
		}
	}
}
