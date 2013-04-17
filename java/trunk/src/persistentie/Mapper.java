package persistentie;

import java.util.List;
import java.util.Map;

public abstract class Mapper<E> {
	
	public  <F> Map<F, E> geefMap(){
		throw new UnsupportedOperationException();
	}

	public List<E> geefLijst(){
		throw new UnsupportedOperationException();
	}
	public List<E> geefLijstPerItem(String ID){
		throw new UnsupportedOperationException();
	}
	public void voegItemToe(String ID, E Object){
		throw new UnsupportedOperationException();
	}
	
	public void voegItemToe(E Object){
		throw new UnsupportedOperationException();
	}
	
	public void verwijderItem(String ID, E Object){
		throw new UnsupportedOperationException();
	}
}
