package gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
import domein.LeertrajectController;
import domein.tableModel.SoortTableModel;

public class LijstPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4830538926588492132L;
	private SearchPanel searchTraject, searchOnderdeel;
	private BrowserPane trajecten, onderdelen;
	private LeertrajectController controller;

	public LijstPanel(LeertrajectController controller) {
		super();
		this.controller = controller;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new MigLayout("fill","[::350]","[]20[]"));
		{
			searchTraject = new SearchPanel();
			searchTraject.getSearchField().getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					trajecten.searchTable(searchTraject.getSearchField().getText(), searchTraject.getCheckboxes());
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					trajecten.searchTable(searchTraject.getSearchField().getText(), searchTraject.getCheckboxes());
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					trajecten.searchTable(searchTraject.getSearchField().getText(), searchTraject.getCheckboxes());
					
				}
			});
			this.add(searchTraject,"north");
		}
		{
			trajecten = new BrowserPane(controller.geefTableModel(SoortTableModel.LEERTRAJECTACTOR), ListSelectionModel.SINGLE_SELECTION);
			this.add(trajecten,"north");
		}
		{
			onderdelen = new BrowserPane(controller.geefTableModel(SoortTableModel.ONDERDEEL), ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			this.add(onderdelen,"wrap,south");
		}
		{
			searchOnderdeel = new SearchPanel();
			searchOnderdeel.getSearchField().getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					onderdelen.searchTable(searchOnderdeel.getSearchField().getText(), searchOnderdeel.getCheckboxes());
					
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					onderdelen.searchTable(searchOnderdeel.getSearchField().getText(), searchOnderdeel.getCheckboxes());
					
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					onderdelen.searchTable(searchOnderdeel.getSearchField().getText(), searchOnderdeel.getCheckboxes());
					
				}
			});
			searchOnderdeel.addCheckBox("Document");
			searchOnderdeel.addCheckBox("Casus");
			searchOnderdeel.addCheckBox("Stellingspel");
			searchOnderdeel.addCheckBox("Vragenlijst");
			for(JCheckBox cb : searchOnderdeel.getCheckboxes()){
				cb.addItemListener(new ItemListener() {

					@Override
				    public void itemStateChanged(ItemEvent e) {
						onderdelen.searchTable(searchOnderdeel.getSearchField().getText(), searchOnderdeel.getCheckboxes());
				    }
				});
			}
		}
		this.add(searchOnderdeel,"south,spanx");
		
	}
	

}
