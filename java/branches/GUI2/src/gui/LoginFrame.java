package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

import domein.LeertrajectController;

public class LoginFrame extends JFrame implements ActionListener {
	private LeertrajectController lc;
	private JLabel lblNaam;
	private JTextField txtNaam;
	private JLabel lblWachtwoord;
	private JPasswordField txtWachtwoord;
	private JButton btnInloggen;
	private JPanel contentPanel;
	private JLabel lblIcon;

	public LoginFrame(LeertrajectController leertrajectController) {
		lc = leertrajectController;
		initGUI();
		createMiglayout();
		this.setVisible(true);
	}

	private void initGUI() {
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());	
			contentPanel = new JPanel(new MigLayout());
			contentPanel.setPreferredSize(new Dimension(400,120));
			
			{
				ImageIcon icon = new ImageIcon("src/gui/images/Key_small.png");
				lblIcon = new JLabel(icon);
			}
			{
				lblNaam = new JLabel("Naam :");
				txtNaam = new JTextField();
				txtNaam.setText("admin@gmail.com");
			}
			{
				lblWachtwoord = new JLabel("Wachtwoord :");
				txtWachtwoord = new JPasswordField();
				txtWachtwoord.setText("admin");
			}			
			{
				btnInloggen = new JButton("Inloggen");
				btnInloggen.addActionListener(this);
			}

			getContentPane().add(contentPanel);
			setSize(contentPanel.getPreferredSize());
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			setResizable(false);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == btnInloggen) {
			String naam = txtNaam.getText().trim();
			String passwoord = String.copyValueOf(txtWachtwoord.getPassword());
			
			if(lc.logIn(naam, passwoord)){
				MainFrame mf = new MainFrame(lc);
				dispose();
			}
		}
	}
	
	private void createMiglayout(){
		contentPanel.add(lblIcon,"spany");
		contentPanel.add(lblNaam,"right");
		contentPanel.add(txtNaam,"w 200,wrap");
		contentPanel.add(lblWachtwoord,"right");
		contentPanel.add(txtWachtwoord,"w 200,wrap");
		contentPanel.add(btnInloggen,"span 2, tag ok");
	}
}
