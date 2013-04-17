package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

import domein.LeertrajectController;
import domein.tableModel.SoortTableModel;

public class BeheerLeertrajectPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeheerLeertrajectPanel(LeertrajectController controller){
		super();
		initGUI(controller);
	}
	
	private void initGUI(LeertrajectController controller) {
		try{


			this.setLayout(new BorderLayout());
			
			this.setPreferredSize(new Dimension(1200	,800));			
		    
		    
			{
				
				JPanel actiesPanel = new ActiesPanel(controller);
				this.add(actiesPanel, BorderLayout.EAST);
				
				JPanel lijstPanel = new LijstPanel(controller.geefTableModel(SoortTableModel.LEERTRAJECT), controller.geefTableModel(SoortTableModel.ONDERDEEL));
				this.add(lijstPanel, BorderLayout.WEST);
				
				
				JPanel trajectDetailsPanel = new TrajectDetailsPanel(controller);	
				this.add(trajectDetailsPanel, BorderLayout.CENTER);
				

				{
				}
			}
			
		}
		catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
		
	}
}

