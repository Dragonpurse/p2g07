package gui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import domein.pattern.observer.Observer;

public class BrowserPane extends JScrollPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5308246871303386155L;
	private Observer observer;
	private JTable table;
	private TableModel model;
	private TableRowSorter<TableModel> sorter;
	
	public BrowserPane(TableModel model, int selection){
		this.model = model;
		initGUI(selection);
	}
	
	private void initGUI(int selection) {
		observer = (Observer) model;
		table = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 7787458280734849443L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
		        Component returnComp = super.prepareRenderer(renderer, row, column);
		        Color alternateColor = new Color(232,242,254);
		        Color whiteColor = Color.WHITE;
		        if (!returnComp.getBackground().equals(getSelectionBackground())){
		            Color bg = (row % 2 == 1 ? alternateColor : whiteColor);
		            returnComp .setBackground(bg);
		            bg = null;
		        }
		        return returnComp;
		}};
		table.setShowGrid(false);
		table.setModel(model);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(selection);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent event) {
						 if(event.getValueIsAdjusting())
					        return;
						for(int index : table.getSelectedRows())
							observer.update(index);
					}	
			});
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		setViewportView(table);
		getViewport().setBackground(Color.white);
	}
	
	public void searchTable(String search,List<JCheckBox> checkboxes){	
		sorter = new TableRowSorter<TableModel>(model);
		ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();
		if(search.equals("Typ hier om te zoeken"))
			search = "";
		RowFilter<Object,Object> searchFilter = RowFilter.regexFilter("(?i)" + Pattern.quote(search));
		filters.add(searchFilter);
		for(JCheckBox cb : checkboxes){
			if(!cb.isSelected()){
				filters.add(RowFilter.notFilter(RowFilter.regexFilter(cb.getText(), 4)));
			}
		}
		sorter.setRowFilter(RowFilter.andFilter(filters));
		table.setRowSorter(sorter);
	}
}
