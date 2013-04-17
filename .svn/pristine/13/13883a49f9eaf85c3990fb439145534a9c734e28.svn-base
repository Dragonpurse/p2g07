package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import domein.LeertrajectController;

public class LoginDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8663077646311164615L;
	private LeertrajectController controller;
	private JLabel lblNaam;
	private JTextField txtNaam;
	private JLabel lblWachtwoord;
	private JPasswordField txtWachtwoord;
	private JButton btnInloggen;

	public LoginDialog(LeertrajectController controller) {
		super();
		setController(controller);
		initGUI();
	}

	private void initGUI() {

		JPanel contentPanel = new JPanel(new MigLayout("wrap","[left][grow,fill]"));
		this.setSize(new Dimension(300,120));
		this.setResizable(false);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		
		{
			lblNaam = new JLabel("Naam :");
			txtNaam = new JTextField();
			contentPanel.add(lblNaam,"w min!");
			contentPanel.add(txtNaam,"growx");
		}
		{
			lblWachtwoord = new JLabel("Wachtwoord :");
			txtWachtwoord = new JPasswordField();
			contentPanel.add(lblWachtwoord,"w min!");
			contentPanel.add(txtWachtwoord,"growx");
		}
		{
			btnInloggen = new JButton("Inloggen");
			btnInloggen.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String naam = txtNaam.getText().trim();
					String passwoord = String.copyValueOf(txtWachtwoord.getPassword());
					
					if(controller.logIn(naam, passwoord)){
							dispose();
					}
					
				}
			});
			contentPanel.add(btnInloggen,"span 2, right");
		}
		this.add(contentPanel);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
				
			}
		});
		
		
		
	}

	public LeertrajectController getController() {
		return controller;
	}

	public void setController(LeertrajectController controller) {
		this.controller = controller;
	}

	

}
