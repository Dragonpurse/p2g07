package gui;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OnderdeelToevoegenPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton volgendeButton;
	private JComboBox<String> soortOnderdeelCBox;
	private JLabel selecteerOnderdeelLabel;
	
	public OnderdeelToevoegenPanel(){
		super();
		initGUI();
	}

	private void initGUI() {


		GridBagLayout onderdeelToevoegenPanelLayout = new GridBagLayout();
		onderdeelToevoegenPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
		onderdeelToevoegenPanelLayout.rowHeights = new int[] {7, 7, 7, 7};
		onderdeelToevoegenPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
		onderdeelToevoegenPanelLayout.columnWidths = new int[] {7, 7, 7, 7};
		this.setLayout(onderdeelToevoegenPanelLayout);
		this.setEnabled(false);
		this.setVisible(false);
		{
			volgendeButton = new JButton();
			this.add(volgendeButton, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			volgendeButton.setText("Volgende");
		}
		{
			selecteerOnderdeelLabel = new JLabel();
			this.add(selecteerOnderdeelLabel, new GridBagConstraints(0, 0, 4, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			selecteerOnderdeelLabel.setText("Selecteer het soort onderdeel dat U wilt toevoegen");
		}
		{					
			soortOnderdeelCBox = new JComboBox<String>();
			this.add(soortOnderdeelCBox, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			soortOnderdeelCBox.setModel(new DefaultComboBoxModel<String>(
					new String[] { "Item One", "Item Two" }));
		}
		
	}

}
