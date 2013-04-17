package gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class SearchPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -358077137173463985L;
	private JTextField searchField;
	private List<JCheckBox> checkboxes;
	
	public SearchPanel(){
		super(new MigLayout(""));
		searchField = new JTextField();
		checkboxes = new ArrayList<>();
		initGUI();
	}
	
	private void initGUI(){
		searchField.setPreferredSize(new Dimension(350,20));
		add(searchField,"spanx, gapx 0px 0px");
		for(JCheckBox cb : checkboxes) {
			cb.setSelected(true);
			add(cb,"gapx 10px 0px");
		}
	}
	
	public void addCheckBox(String tekst){
		checkboxes.add(new JCheckBox(tekst));
		initGUI();
	}
	
	public JTextField getSearchField() {
		return searchField;
	}

	public List<JCheckBox> getCheckboxes() {
		return checkboxes;
	}
}
