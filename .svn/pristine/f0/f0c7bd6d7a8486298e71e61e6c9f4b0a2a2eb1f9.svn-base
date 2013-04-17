package gui;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

import domein.LeertrajectController;
import domein.tableModel.SoortTableModel;

public class MainFrame extends JFrame implements ChangeListener {
	LeertrajectController lc;
	private JPanel contentPanel;
	private BrowserPane leftTopTab;
	private BrowserPane leftBottomTab;
	private BrowserPane leftBottomTab2;
	private SearchPanel zoektraject;
	private SearchPanel zoekonderdeel;
	private JTabbedPane overzicht;
	private SearchPanel zoektrajectactor;
	private BrowserPane leftTopTab2;
	private NieuwTrajectPanel nieuwTraject;
	private BeheerTrajectPanel beheerTraject;
	private MedewerkerPanel medewerkerPanel;
	private BeheerOnderdeelPanel beheerOnderdeel;
	
	public MainFrame(LeertrajectController lc ){
		this.lc = lc;
		initGUI();
		createMiglayout();
		this.setVisible(true);

		

	}

	private void initGUI() {
		try{		
			
			UIManager.setLookAndFeel(new WindowsLookAndFeel());	
			contentPanel = new JPanel(new MigLayout("hidemode 3 , nocache","[]15[grow,fill][]","[grow,fill]10[grow,fill][grow,fill]"));
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int height = (int) screenSize.getHeight();
			contentPanel.setPreferredSize(new Dimension(1200,height-50));
			
			{
				leftTopTab = new BrowserPane("Overzicht alle trajecten", lc.geefTableModel(SoortTableModel.LEERTRAJECT), ListSelectionModel.SINGLE_SELECTION);
				zoektraject = new SearchPanel();
				leftTopTab.addSearchPanel(zoektraject);
			}
			{
				leftTopTab2 = new BrowserPane("Overzicht van uw trajecten", lc.geefTableModel(SoortTableModel.LEERTRAJECTACTOR), ListSelectionModel.SINGLE_SELECTION);
				zoektrajectactor = new SearchPanel();
				leftTopTab2.addSearchPanel(zoektrajectactor);
				leftTopTab2.setVisible(false);
				
			}
			{
				leftBottomTab = new BrowserPane("Overzicht alle onderdelen", lc.geefTableModel(SoortTableModel.ONDERDEEL), ListSelectionModel.SINGLE_SELECTION);
				zoekonderdeel = new SearchPanel();
				zoekonderdeel.addCheckBox("Document");
				zoekonderdeel.addCheckBox("Casus");
				zoekonderdeel.addCheckBox("Stellingspel");
				zoekonderdeel.addCheckBox("Vragenlijst");
				leftBottomTab.addSearchPanel(zoekonderdeel);
				leftBottomTab.setVisible(false);
			}
			{
				leftBottomTab2 = new BrowserPane("Overzicht onderdelen van traject", lc.geefTableModel(SoortTableModel.ONDERDEELTRAJECT), ListSelectionModel.SINGLE_SELECTION);
			}
			{
				overzicht = new JTabbedPane();
				nieuwTraject = new NieuwTrajectPanel(lc);
				medewerkerPanel = new MedewerkerPanel(lc);
				medewerkerPanel.addObserver(nieuwTraject);
				JSplitPane splitPaneNieuw = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, nieuwTraject, medewerkerPanel);
				splitPaneNieuw.setResizeWeight(0.65);
				splitPaneNieuw.setBorder(BorderFactory.createEmptyBorder());
				overzicht.add("Nieuw traject",splitPaneNieuw);
				
				beheerTraject = new BeheerTrajectPanel(lc);
				beheerOnderdeel = new BeheerOnderdeelPanel(lc);
				beheerTraject.addObserver(beheerOnderdeel);
				beheerOnderdeel.addObserver(beheerTraject);
				JSplitPane splitPaneBeheer = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, beheerTraject, beheerOnderdeel);
				splitPaneBeheer.setResizeWeight(0.218);
				splitPaneBeheer.setBorder(BorderFactory.createEmptyBorder());
				overzicht.add("Beheer traject",splitPaneBeheer);
				overzicht.addChangeListener(this);
			}

			getContentPane().add(contentPanel);
			setSize(contentPanel.getPreferredSize());
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationRelativeTo(null);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void createMiglayout() {
		contentPanel.add(leftTopTab, "wrap");
		contentPanel.add(leftTopTab2, "wrap");
		contentPanel.add(leftBottomTab, "spany");
		contentPanel.add(leftBottomTab2, "spany");
		contentPanel.add(overzicht,"wrap,cell 1 0 1 3");
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		if(event.getSource() == beheerTraject);
			if(overzicht.getSelectedIndex() == 1) {
				leftTopTab.setVisible(false);
				leftTopTab2.setVisible(true);
				leftBottomTab.setVisible(true);
				leftBottomTab2.setVisible(false);
				beheerTraject.setActiveTab(true);
				nieuwTraject.setActiveTab(false);
				leftTopTab2.reselectRow();
			}
			if(overzicht.getSelectedIndex() == 0) {
				leftTopTab.setVisible(true);
				leftTopTab2.setVisible(false);
				leftBottomTab.setVisible(false);
				leftBottomTab2.setVisible(true);
				nieuwTraject.setActiveTab(true);
				beheerTraject.setActiveTab(false);
				leftTopTab.reselectRow();
			}
	}
}
