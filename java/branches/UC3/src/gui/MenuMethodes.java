package gui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MenuMethodes {
	public void doeMethode(String methodeNaam) {
		Method methode;
		try {
			methode = getClass().getMethod(methodeNaam, new Class[0]);
			methode.invoke(this, new Object[0]);
		} catch (SecurityException e) {
			throw new UnsupportedOperationException("methode bestaat niet");
		} catch (NoSuchMethodException e) {
			throw new UnsupportedOperationException("methode bestaat niet");
		} catch (IllegalArgumentException e) {
			throw new UnsupportedOperationException("methode bestaat niet");
		} catch (IllegalAccessException e) {
			throw new UnsupportedOperationException("methode bestaat niet");		
		} catch (InvocationTargetException e) {
			throw new UnsupportedOperationException("methode bestaat niet");	
		}
	}
	

}
