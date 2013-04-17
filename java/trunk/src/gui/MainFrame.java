package gui;


import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

import domein.LeertrajectController;
import domein.tableModel.SoortTableModel;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LeertrajectController controller;
	private JPanel contentPanel;
	private BrowserPane onderdeelTraject;
	private LijstPanel lijstPanel;
	private ActiesPanel rightPanel;
	
	private JDialog loginDialog;

	
	public MainFrame(LeertrajectController controller ){
		super();
		this.controller = controller;
		initGUI();
	}
	
	private void initGUI() {
		try{		
			UIManager.setLookAndFeel(new WindowsLookAndFeel());		
			contentPanel = new JPanel(new MigLayout("","[]15[grow,fill][]","[grow,fill][grow,fill]5[]"));
			contentPanel.setPreferredSize(new Dimension(1250,800));

			OverzichtPane overzicht = new OverzichtPane(this.controller);
			overzicht.setFocusable(false);
			
			contentPanel.add(overzicht);
			
			JTabbedPane leftTab = new JTabbedPane();
			lijstPanel = new LijstPanel(controller);
			
			leftTab.add("Browser",lijstPanel);
			contentPanel.add(leftTab, "west, spany");
			

			rightPanel = new ActiesPanel(controller);;
			contentPanel.add(rightPanel, "east, spany");
			
			JTabbedPane bottomTab = new JTabbedPane();
			bottomTab.setFocusable(false);
			JPanel bottomPanel = new JPanel(new MigLayout());
			onderdeelTraject = new BrowserPane(controller.geefTableModel(SoortTableModel.ONDERDEELTRAJECT), ListSelectionModel.SINGLE_SELECTION);
			bottomPanel.add(onderdeelTraject,"spanx,grow,east");
			bottomTab.add("Onderdelen per traject",bottomPanel);
			contentPanel.add(bottomTab, "south, spanx");
			

			
			getContentPane().add(contentPanel);
			setSize(contentPanel.getPreferredSize());
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			
			loginDialog = new LoginDialog(controller);
			loginDialog.setLocationRelativeTo(null);
			loginDialog.setVisible(true);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}