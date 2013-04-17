package persistentie;

import java.util.List;

public abstract class Mapper<E> {

	public List<E> geefLijst(){
		throw new UnsupportedOperationException();
	}
	public List<E> geefLijstPerItem(String ID){
		throw new UnsupportedOperationException();
	}
	public void voegItemToe(String ID, E Object){
		throw new UnsupportedOperationException();
	}
	public void verwijderItem(String ID, E Object){
		throw new UnsupportedOperationException();
	}
}
