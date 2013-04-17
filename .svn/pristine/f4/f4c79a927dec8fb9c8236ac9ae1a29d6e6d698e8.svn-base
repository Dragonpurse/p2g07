package domein.cobol;

import com.legacyj.api.Callable;

public class CobolBridge {
	private Callable callable;
	
	public CobolBridge(String programName){
		try{
			Class<Callable> cobolProg =
					(Class<Callable>) Class.forName(programName);
			callable = cobolProg.newInstance();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(InstantiationException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}
	}
	
	public Object run(boolean [] booleans, Object[] objects){
		try{
			return callable.call(booleans, objects);
		} catch(Throwable e){
			e.printStackTrace();
			return null;
		}
	}
	

}
