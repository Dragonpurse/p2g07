package persistentie;

import java.util.ArrayList;

import domein.*;

public class PersistentieController {

	private LeertrajectMapper leertrajectMapper;
	private static PersistentieController pc;

	public static PersistentieController getInstance()
    {     
                  if (pc == null)
                	  pc = new PersistentieController();
                  return pc;
    }
	
	private PersistentieController(){
		leertrajectMapper = new LeertrajectMapper();
	}

	public ArrayList<Leertraject> getLijstLeertrajecten() {
		return leertrajectMapper.getLijstleertrajecten();
	}

	

}