package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.joda.time.LocalDate;

import domein.Leertraject;
import domein.LeertrajectController;
import domein.Stelling;
import domein.StellingAntwoord;
import domein.StellingAntwoordKleur;
import domein.Stellingspel;
import domein.builders.StellingspelBuilder;
import domein.pattern.observer.Observer;

public class StellingspelToevoegenPanel extends JPanel implements Observer, ActionListener, ItemListener{
	
	private LeertrajectController lc;
	private CardGeneralInfo cardGeneral;
	private JPanel filler;
	private JButton btnNext;
	private AbstractButton btnBack;
	private ArrayList<VraagAntwoordenPanel> cards;
	private int currentCard = -1;
	private VraagAntwoordenPanel newCard;
	private CardInfoLink cardInfoLink;
	private JButton btnCardNext;
	private AbstractButton btnCardBack;
	private JButton btnOpslaan;
	private JPanel toolBar;
	private JButton btnRemove;
	private DefaultComboBoxModel<String> model;
	private JComboBox<String> cbxCards;

	public StellingspelToevoegenPanel(LeertrajectController lc) {
		this.lc = lc;
		lc.addObserver(this);
		initGUI();
		createMigLayout();
	}

	private void createMigLayout() {
		filler.add(btnBack,"right,tag ok");
		filler.add(btnNext,"right,tag ok");
		filler.add(btnCardBack,"right,tag ok");
		filler.add(btnCardNext,"right,tag ok");
		filler.add(btnOpslaan,"tag right");
		toolBar.add(cbxCards,"spanx,split 3");
		toolBar.add(btnRemove,"right,tag ok");
		toolBar.add(btnOpslaan,"right,tag ok");
		add(toolBar,"wrap,growx,gap 20 0 10");
		add(cardGeneral,"wrap,growx,spany");
		add(cardInfoLink,"wrap,growx,spany,gapbottom 32");
		add(filler,"south");
	}

	private void initGUI() {
		setLayout(new MigLayout("hidemode 3 , nocache","[grow,fill,::600]",""));
		setBackground(new Color(250,250,250));
		setBorder(BorderFactory.createEmptyBorder());
		{
			cardGeneral = new CardGeneralInfo();
		}
		{
			filler = new JPanel(new MigLayout("hidemode 3 , nocache","push[right][right]"));
			filler.setBackground(new Color(250,250,250));
			filler.setBorder(BorderFactory.createEmptyBorder());
			{
				btnNext = new JButton("Volgende");
				btnNext.addActionListener(this);
				btnNext.setVisible(true);
			}
			{
				btnBack = new JButton("Terug");
				btnBack.addActionListener(this);
				btnBack.setVisible(false);
			}
			{
				btnCardNext = new JButton("Volgende");
				btnCardNext.addActionListener(this);
				btnCardNext.setVisible(false);
			}
			{
				btnCardBack = new JButton("Terug");
				btnCardBack.addActionListener(this);
				btnCardBack.setVisible(false);
			}
		}
		{
			cardInfoLink = new CardInfoLink();
			cardInfoLink.setVisible(false);
		}
		{
			cards = new ArrayList<>();
		}
		{
			toolBar = new JPanel(new MigLayout("","push[right][right][right]"));
			toolBar.setBackground(new Color(235,235,235));
			toolBar.setBorder(BorderFactory.createEtchedBorder());
			toolBar.setVisible(false);
			{
				model = new DefaultComboBoxModel<>();
				cbxCards = new JComboBox<>(model);
				cbxCards.setEditable(false);
				cbxCards.setPreferredSize(new Dimension(500,20));
				cbxCards.addItemListener(this);
			}
			{
				btnRemove = new JButton("Verwijder");
				btnRemove.addActionListener(this);
			}
			{
				btnOpslaan = new JButton("Opslaan");
				btnOpslaan.addActionListener(this);
			}
		}
	}

	@Override
	public void update(Object object) {
		if(object instanceof Leertraject) {
			clearFields();
		}
	}

	private void clearFields() {
		cardGeneral.clearFields();
		cardInfoLink.clearFields();
		cards = new ArrayList<>();
		currentCard = -1;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == btnOpslaan) {
			StellingspelBuilder stellBuilder = new StellingspelBuilder();
			stellBuilder.createNewStellingspel();
			stellBuilder.buildDisplayNaam(cardInfoLink.getTxtDispNaam().getText());
			stellBuilder.buildDisplayOmschrijving(cardInfoLink.getTxaDispOmschrijving().getText());
			stellBuilder.buildTitel(cardGeneral.getTxtTitel().getText());
			stellBuilder.buildOmschrijving(cardGeneral.getTxaOmschrijving().getText());
			stellBuilder.buildDoelgroep(cardGeneral.getTxtDoelgroep().getText());
			stellBuilder.buildKernwoorden(cardGeneral.getTxaKernwoorden().getText());
			stellBuilder.buildBeschikbaarVan(LocalDate.fromDateFields(cardInfoLink.getFieldBeschikVan().getDate()));
			stellBuilder.buildBeschikbaarTot(LocalDate.fromDateFields(cardInfoLink.getFieldBeschikTot().getDate()));
			List<Stelling> stellingen = new ArrayList<>();
			for(VraagAntwoordenPanel card : cards) {
				if(card.validateFields()) {
					List<StellingAntwoord> antwoorden = new ArrayList<>();
					antwoorden.add(stellBuilder.maakStellingAntwoordAan(card.getTxtAntwoord1().getText().trim(), StellingAntwoordKleur.GROEN));
					antwoorden.add(stellBuilder.maakStellingAntwoordAan(card.getTxtAntwoord2().getText().trim(), StellingAntwoordKleur.BLAUW));
					antwoorden.add(stellBuilder.maakStellingAntwoordAan(card.getTxtAntwoord3().getText().trim(), StellingAntwoordKleur.ROOD));
					antwoorden.add(stellBuilder.maakStellingAntwoordAan(card.getTxtAntwoord4().getText().trim(), StellingAntwoordKleur.GEEL));
					stellingen.add(stellBuilder.maakStellingAan(card.getTxaVraag().getText().trim(), antwoorden));
				}
			}
			stellBuilder.buildStellingen(stellingen);
			Stellingspel stellspel = (Stellingspel) stellBuilder.getOnderdeel();
			lc.save(stellspel);
			lc.linkOnderdeel(stellspel);
			clearFields();
		}
		if(event.getSource() == btnRemove) {
			cards.get(currentCard).setVisible(false);
			if(currentCard > 0) {
				cards.get(currentCard-1).setVisible(true);
				currentCard--;
			}
			else {
				btnCardNext.setVisible(false);
				btnCardBack.setVisible(false);
				btnBack.setVisible(true);
				btnNext.setVisible(true);
				cardInfoLink.setVisible(true);
			}
			int index = cbxCards.getSelectedIndex();
			model.removeElementAt(index);
			cards.remove(index);
		}
		if(event.getSource() == btnCardNext) {
			if(currentCard == -1) {
				currentCard++;
				cards.get(currentCard).setVisible(true);
			}
			else {
				if(cards.get(currentCard).validateFields()) {
					if(currentCard == cards.size() - 1) {
						model.addElement(cards.get(currentCard).ToString());
						newCard = new VraagAntwoordenPanel();
						cards.get(currentCard).setVisible(false);
						add(newCard,"wrap,growx,spany");
						cards.add(newCard);
						currentCard++;
					}
					else {
						cards.get(currentCard).setVisible(false);
						cards.get(currentCard+1).setVisible(true);
						currentCard++;
					}
				}
			}
		}
		
		if(event.getSource() == btnCardBack) {
			if(currentCard == 0) {
				cards.get(currentCard).setVisible(false);				
				currentCard--;
				btnCardNext.setVisible(false);
				btnCardBack.setVisible(false);
				btnBack.setVisible(true);
				btnNext.setVisible(true);
				cardInfoLink.setVisible(true);
				toolBar.setVisible(false);
				
			} else {
				cards.get(currentCard).setVisible(false);
				cards.get(currentCard-1).setVisible(true);
				currentCard--;
			}
		}
		if(event.getSource() == btnNext) {
			if(lc.getGeselecteerdLeertraject() != null) {
				if(cardGeneral.isVisible() && cardGeneral.validateCardInfo()) {
					if(cardInfoLink.getTxtDispNaam().getText().equals(""))
						cardInfoLink.getTxtDispNaam().setText(cardGeneral.getTxtTitel().getText());
					if(cardInfoLink.getTxaDispOmschrijving().getText().equals(""))
						cardInfoLink.getTxaDispOmschrijving().setText(cardGeneral.getTxaOmschrijving().getText());
					if(cardInfoLink.getFieldBeschikVan().getDate() == null)
						cardInfoLink.getFieldBeschikVan().setDate(lc.getGeselecteerdLeertraject().getBeschikbaarVan().toDate());
					if(cardInfoLink.getFieldBeschikTot().getDate() == null)
						cardInfoLink.getFieldBeschikTot().setDate(lc.getGeselecteerdLeertraject().getBeschikbaarTot().toDate());
					
					btnBack.setVisible(true);
					btnNext.setVisible(true);
					cardGeneral.setVisible(false);
					cardInfoLink.setVisible(true);
					return;
				}
				if(cardInfoLink.isVisible() && cardInfoLink.validateCardInfo()) {
					toolBar.setVisible(true);
					btnCardNext.setVisible(true);
					btnCardBack.setVisible(true);
					btnBack.setVisible(false);
					btnNext.setVisible(false);
					cardInfoLink.setVisible(false);
					
					if(cards.isEmpty()) {
						newCard = new VraagAntwoordenPanel();
						add(newCard,"wrap,growx,spany");
						cards.add(newCard);
						currentCard++;
					} 
					else {
						if(currentCard == -1) {
							currentCard++;
							cards.get(currentCard).setVisible(true);
							}
					}
				}
			}
		}
		if(event.getSource() == btnBack) {
			btnBack.setVisible(false);
			cardInfoLink.setVisible(false);
			btnNext.setVisible(true);
			cardGeneral.setVisible(true);
		}
	}
	@Override
	public void itemStateChanged(ItemEvent event) {
		if(event.getSource() == cbxCards) {
			if(event.getStateChange() == ItemEvent.SELECTED) {
				cards.get(currentCard).setVisible(false);
				currentCard = cbxCards.getSelectedIndex();
				cards.get(currentCard).setVisible(true);
			}	
		}
	}
}