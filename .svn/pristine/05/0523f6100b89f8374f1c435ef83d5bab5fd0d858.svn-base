package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import domein.LeertrajectController;

public class StartPanel extends JPanel {



	private JButton aanmakenLeertrajectBtn;
	private JButton kopierenLeertrajectBtn;
	private JButton beheerLeertrajectBtn;
	private LeertrajectController controller;

	public StartPanel(LeertrajectController controller) {
		super();
		initGUI(controller);
	}

	private void initGUI(LeertrajectController controller) {
		this.setPreferredSize(new Dimension(500,100));
		this.controller = controller;
		
		MigLayout startPanelLayout = new MigLayout();
		this.setLayout(startPanelLayout);
		
		
		aanmakenLeertrajectBtn = new JButton("Aanmaken leertraject");
		aanmakenLeertrajectBtn.addActionListener(new Listener());
		this.add(aanmakenLeertrajectBtn);
		
		kopierenLeertrajectBtn = new JButton("Kopieren leertraject");
		this.add(kopierenLeertrajectBtn);
		
		beheerLeertrajectBtn = new JButton("Beheren leertrajecten");
		beheerLeertrajectBtn.addActionListener(new Listener());
		this.add(beheerLeertrajectBtn);
		
	}
	
	private class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(aanmakenLeertrajectBtn))
				openTrajectToevoegenFrame();
			if(e.getSource().equals(beheerLeertrajectBtn))
				openBeheerLeertrajectFrame();
			
		}

		private void openTrajectToevoegenFrame() {
			JFrame trajectToevoegenFrame = new PopUpFrame(new TrajectToevoegenPanel(controller));
			trajectToevoegenFrame.setVisible(true);
			
		}
		
		private void openBeheerLeertrajectFrame(){
			JFrame beheerLeertrajectFrame = new PopUpFrame(new BeheerLeertrajectPanel(controller));
			beheerLeertrajectFrame.setVisible(true);
		}
		
	}

	

}
