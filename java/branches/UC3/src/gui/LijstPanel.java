package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.table.TableModel;

public class LijstPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LijstPanel(TableModel modelLeertrajecten, TableModel modelOnderdelen){
		super();
		initGUI(modelLeertrajecten, modelOnderdelen);
	}

	private void initGUI(TableModel modelLeertrajecten, TableModel modelOnderdelen) {
		this.setLayout(new GridLayout(2,1));
		this.setMinimumSize(new Dimension(400,0));
		this.setMaximumSize(new Dimension(400,0));
		this.setPreferredSize(new Dimension(400,0));
		JPanel trajectLijstPanel = new OverzichtPanel(modelLeertrajecten);
		JPanel onderdeelLijstPanel = new OverzichtPanel(modelOnderdelen);
		this.add(trajectLijstPanel);
		this.add(onderdeelLijstPanel);
		
	}
	
	

}
