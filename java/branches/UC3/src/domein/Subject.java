package domein;

import java.util.Observer;

public interface Subject {
	void addObserver(Observer o);
	void notifyObservers(Object object);
}