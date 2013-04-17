package gui;

import java.awt.BorderLayout;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

public class OverzichtPanel extends JPanel implements ListSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TableModel model;
	private JTable overzichtTable;
	private Observer observer;
	
	public OverzichtPanel(TableModel model){
		super();
		this.model = model;
		initGUI();
	}
	private void initGUI() {
		BorderLayout overzichtPanelLayout = new BorderLayout();
		this.setLayout(overzichtPanelLayout);
		this.observer = (Observer) model;
		
		JScrollPane scrollPane = new JScrollPane();
		overzichtTable = new JTable();
		overzichtTable.setModel(model);
		overzichtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		overzichtTable.getSelectionModel().addListSelectionListener(this);
		overzichtTable.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
		scrollPane.setViewportView(overzichtTable);
		this.add(scrollPane);
		
		
	}
	@Override
	public void valueChanged(ListSelectionEvent event) {
		if(event.getValueIsAdjusting())
		{
			return;
		}
		observer.update(null, overzichtTable.getSelectedRow());
		
	}
	

}
