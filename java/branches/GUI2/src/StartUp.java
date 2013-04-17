import gui.LoginFrame;
import domein.LeertrajectController;

public class StartUp {

	public static void main(String[] args) {
	  LoginFrame loginFrame = new LoginFrame(new LeertrajectController());
	}
}
