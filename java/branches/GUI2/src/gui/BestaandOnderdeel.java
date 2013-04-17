package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

import org.joda.time.LocalDate;

import domein.LeertrajectController;
import domein.Onderdeel;
import domein.pattern.observer.Observer;
import domein.tableModel.SoortTableModel;

public class BestaandOnderdeel extends JPanel implements ActionListener, Observer{
	
	private LeertrajectController lc;
	private BrowserPane onderdelenlist;
	private JPanel filler;
	private JButton btnAdd;
	private JButton btnRemove;
	private JPanel linkOnderdeel;
	private JButton btnBack;
	private JButton btnOpslaan;
	private CardInfoLink cardInfoLink;
	private Onderdeel geselecteerdOnderdeel;
	private JButton btnBack2;

	public BestaandOnderdeel(LeertrajectController lc) {
		this.lc = lc;
		lc.addObserver(this);
		initGUI();
		createMigLayout();
	}

	private void createMigLayout() {
		filler.add(btnAdd,"w 35,h 35");
		filler.add(btnRemove,"w 35,h 35");
		linkOnderdeel.add(cardInfoLink,"wrap");
		linkOnderdeel.add(btnBack,"right,tag ok, egx btn,split 2");
		linkOnderdeel.add(btnOpslaan,"right,tag ok, egx btn");
		add(filler,"cell 0 0,spany");
		add(onderdelenlist,"cell 1 0,spany");
		add(linkOnderdeel,"cell 1 0,spany");
	}

	private void initGUI() {
		setLayout(new MigLayout("hidemode 3 , nocache","[][grow,fill,::620]"));
		setBackground(new Color(250,250,250));
		setBorder(BorderFactory.createEmptyBorder());
		{
			onderdelenlist = new BrowserPane("Overzicht onderdelen van traject", lc.geefTableModel(SoortTableModel.ONDERDEELTRAJECT), ListSelectionModel.SINGLE_SELECTION);
		}
		{
			filler = new JPanel(new MigLayout("wrap","[::40]"));
			filler.setBackground(new Color(250,250,250));
			filler.setBorder(BorderFactory.createEmptyBorder());
			{
				btnAdd = new JButton();
				ImageIcon addIcon = new ImageIcon("src/gui/images/add.png");
				btnAdd.setToolTipText("Een geselecteerd onderdeel toevoegen aan het traject");
				btnAdd.setIcon(addIcon);
				btnAdd.addActionListener(this);
			}
			{
				btnRemove = new JButton();
				btnRemove.setToolTipText("Een geselecteerd onderdeel verwijderen van het traject");
				ImageIcon removeIcon = new ImageIcon("src/gui/images/remove.png");
				btnRemove.setIcon(removeIcon);
				btnRemove.addActionListener(this);
			}
		}
		{
			linkOnderdeel = new JPanel(new MigLayout("","[grow,fill]"));
			linkOnderdeel.setVisible(false);
			linkOnderdeel.setBackground(new Color(250,250,250));
			linkOnderdeel.setBorder(BorderFactory.createEmptyBorder());
			{
				cardInfoLink = new CardInfoLink();
			}
			{
				btnBack = new JButton("Terug");
				btnBack.addActionListener(this);
			}
			{
				btnOpslaan = new JButton("Opslaan");
				btnOpslaan.addActionListener(this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOpslaan) {
			if(cardInfoLink.validateCardInfo()) {
				geselecteerdOnderdeel.setBeschikbaarVan(LocalDate.fromDateFields(cardInfoLink.getFieldBeschikVan().getDate()));
				geselecteerdOnderdeel.setBeschikbaarTot(LocalDate.fromDateFields(cardInfoLink.getFieldBeschikTot().getDate()));
				geselecteerdOnderdeel.setDisplayNaam(cardInfoLink.getTxtDispNaam().getText());
				geselecteerdOnderdeel.setOmschrijving(cardInfoLink.getTxaDispOmschrijving().getText());
				lc.linkOnderdeel(geselecteerdOnderdeel);
				onderdelenlist.setVisible(true);
				filler.setVisible(true);
				linkOnderdeel.setVisible(false);
			}
		}
		if(e.getSource() == btnRemove) {
			int selectedRow = onderdelenlist.getTable().getSelectedRow();
			if(!(onderdelenlist.getTable().getRowSorter() == null)){
				selectedRow = onderdelenlist.getTable().getRowSorter().convertRowIndexToModel(selectedRow);
			}
			if(selectedRow != -1){
				lc.verwijderOnderdeel(lc.getGeselecteerdLeertraject().geefOnderdeel(selectedRow));
			}	
		}
		if(e.getSource() == btnAdd) {
			if(geselecteerdOnderdeel != null && lc.getGeselecteerdLeertraject() != null) {
				cardInfoLink.getTxtDispNaam().setText(geselecteerdOnderdeel.getTitel());
				cardInfoLink.getTxaDispOmschrijving().setText(geselecteerdOnderdeel.getOmschrijving());
				cardInfoLink.getFieldBeschikVan().setDate(lc.getGeselecteerdLeertraject().getBeschikbaarVan().toDate());
				cardInfoLink.getFieldBeschikTot().setDate(lc.getGeselecteerdLeertraject().getBeschikbaarTot().toDate());
				onderdelenlist.setVisible(false);
				filler.setVisible(false);
				linkOnderdeel.setVisible(true);
			}
		}
		if(e.getSource() == btnBack || e.getSource() == btnBack2) {
			onderdelenlist.setVisible(true);
			filler.setVisible(true);
			linkOnderdeel.setVisible(false);
		}
	}

	@Override
	public void update(Object object) {
		if(object instanceof Onderdeel) {
			geselecteerdOnderdeel = (Onderdeel) object;
			
		}
	}

}
