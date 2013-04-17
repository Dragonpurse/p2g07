package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import org.joda.time.LocalDate;

import domein.Casus;
import domein.Leertraject;
import domein.LeertrajectController;
import domein.builders.CasusBuilder;
import domein.pattern.observer.Observer;

public class CasusToevoegenPanel extends JPanel implements ActionListener, Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8754400591874045350L;

	
	private JButton btnNext;
	private JButton btnOpslaan;
	private JPanel filler;
	private JButton btnBack;
	
	private JLabel situatieSchetsLabel;
	private JScrollPane situatieSchetsScroll;
	private JTextArea situatieSchetsTxtA;

	
	private LeertrajectController controller;
	private CardInfoLink cardInfoLink;
	private CardGeneralInfo cardGeneral;
	private CasusVragenPanel cardCasusVragen;
	
	private JFileChooser fileChooser;
	
	
	
	private JLabel locatieIntroLabel;
	private JTextField locatieIntroTxt;


	private JButton btnLocatieIntro;
	
	public CasusToevoegenPanel(LeertrajectController controller) {
		super();
		this.controller = controller;
		this.controller.addObserver(this);
		initGUI();
		createMigLayout();
		
	}
	
	private void createMigLayout() {
		filler.add(btnBack,"right,tag ok");
		filler.add(btnOpslaan,"right,tag ok");
		filler.add(btnNext,"right,tag ok");
		cardGeneral.add(situatieSchetsLabel, "right");
		cardGeneral.add(situatieSchetsScroll, "growx, h 50, wrap");
		cardGeneral.add(locatieIntroLabel,"right");
		cardGeneral.add(locatieIntroTxt,"growx, split 2");
		cardGeneral.add(btnLocatieIntro,"right,w 50,wrap");
		add(cardGeneral,"wrap,growx,spany");
		add(cardInfoLink,"wrap,growx,spany,gapbottom 32");
		add(cardCasusVragen,"wrap,growx,spany");
		add(filler,"south");
	}
	
	private void initGUI() {
		// Panel algemene attributen
		setLayout(new MigLayout("hidemode 3 , nocache","[grow,fill,::600]"));
		setBackground(new Color(250,250,250));
		setBorder(BorderFactory.createEmptyBorder());
		{
			cardGeneral = new CardGeneralInfo();
			{
				locatieIntroLabel = new JLabel("Filmbestand :");
				locatieIntroTxt = new JTextField();
				locatieIntroTxt.setEnabled(false);
				btnLocatieIntro = new JButton("...");
				btnLocatieIntro.addActionListener(this);
				situatieSchetsLabel = new JLabel("Situatieschets :");
				situatieSchetsScroll = new JScrollPane();
				situatieSchetsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				situatieSchetsTxtA = new JTextArea();
				situatieSchetsTxtA.setLineWrap(true);
				situatieSchetsTxtA.setWrapStyleWord(true);
				situatieSchetsScroll.setViewportView(situatieSchetsTxtA);
				
			}
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
				btnOpslaan = new JButton("Opslaan");
				btnOpslaan.addActionListener(this);
				btnOpslaan.setVisible(false);
			}
		}
		{
			cardInfoLink = new CardInfoLink();
			cardInfoLink.setVisible(false);
		}
		{
			cardCasusVragen = new CasusVragenPanel();
			cardCasusVragen.setVisible(false);
		}


	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnLocatieIntro) {
			fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION)
				locatieIntroTxt.setText(fileChooser.getSelectedFile().getAbsolutePath());
		}
		
		
		
		if(e.getSource() == btnNext) {
			if(controller.getGeselecteerdLeertraject() != null) {
				if(cardGeneral.isVisible() && cardGeneral.validateCardInfo()) {
					if(cardInfoLink.getTxtDispNaam().getText().equals(""))
						cardInfoLink.getTxtDispNaam().setText(cardGeneral.getTxtTitel().getText());
					if(cardInfoLink.getTxaDispOmschrijving().getText().equals(""))
						cardInfoLink.getTxaDispOmschrijving().setText(cardGeneral.getTxaOmschrijving().getText());
					if(cardInfoLink.getFieldBeschikVan().getDate() == null)
						cardInfoLink.getFieldBeschikVan().setDate(controller.getGeselecteerdLeertraject().getBeschikbaarVan().toDate());
					if(cardInfoLink.getFieldBeschikTot().getDate() == null)
						cardInfoLink.getFieldBeschikTot().setDate(controller.getGeselecteerdLeertraject().getBeschikbaarTot().toDate());
					
					btnBack.setVisible(true);
					btnNext.setVisible(true);
					cardGeneral.setVisible(false);
					cardInfoLink.setVisible(true);
					return;
				}
				if(cardInfoLink.isVisible() && cardInfoLink.validateCardInfo()) {
					btnNext.setVisible(false);
					btnOpslaan.setVisible(true);
					cardInfoLink.setVisible(false);
					cardCasusVragen.setVisible(true);
					
				}			
				
			}
		}
		
		if(e.getSource() == btnBack) {
			if(cardInfoLink.isVisible()){
				btnNext.setVisible(true);
				btnBack.setVisible(false);
				cardGeneral.setVisible(true);
				cardInfoLink.setVisible(false);
			}
			else{
				btnOpslaan.setVisible(false);
				btnNext.setVisible(true);
				btnBack.setVisible(true);
				cardCasusVragen.setVisible(false);
				cardInfoLink.setVisible(true);
			}
		}
		
		if(e.getSource() == btnOpslaan){
			if(validateCards()){
				CasusBuilder casusBuilder = new CasusBuilder();
				casusBuilder.createNewCasus();
				casusBuilder.buildDisplayNaam(cardInfoLink.getTxtDispNaam().getText());
				casusBuilder.buildDisplayOmschrijving(cardInfoLink.getTxaDispOmschrijving().getText());
				casusBuilder.buildTitel(cardGeneral.getTxtTitel().getText());
				casusBuilder.buildOmschrijving(cardGeneral.getTxaOmschrijving().getText());
				casusBuilder.buildDoelgroep(cardGeneral.getTxtDoelgroep().getText());
				casusBuilder.buildKernwoorden(cardGeneral.getTxaKernwoorden().getText());
				casusBuilder.buildSituatieSchets(situatieSchetsTxtA.getText());
				casusBuilder.buildLocatieIntro(locatieIntroTxt.getText());
				casusBuilder.buildBeschikbaarTot(LocalDate.fromDateFields(cardInfoLink.getFieldBeschikTot().getDate()));
				casusBuilder.buildBeschikbaarVan(LocalDate.fromDateFields(cardInfoLink.getFieldBeschikVan().getDate()));
				casusBuilder.buildCasusRoot(cardCasusVragen.getEersteVraag());
				Casus casus = (Casus)casusBuilder.getOnderdeel();
				controller.save(casus);
				controller.linkOnderdeel(casus);
				clearFields();
			}
		}
		
		

		
	}
	
	private void clearFields() {
		locatieIntroTxt.setText("");
		locatieIntroTxt.setBorder(UIManager.getBorder("TextField.border"));
		cardInfoLink.clearFields();
		cardGeneral.clearFields();
		cardCasusVragen.clearFields();
		btnOpslaan.setVisible(false);
		btnNext.setVisible(true);
		btnBack.setVisible(false);
		cardGeneral.setVisible(true);
		cardInfoLink.setVisible(false);
        cardCasusVragen.setVisible(false);

	}
	

	
	private boolean validateCards() {
		boolean validate = true;
		if(validate)
			validate = cardGeneral.validateCardInfo();
		if(validate)
			validate = cardInfoLink.validateCardInfo();
		if(validate)
			validate = cardCasusVragen.validateCardInfo();
		return validate;
	}			

	@Override
	public void update(Object object) {
		if(object instanceof Leertraject) {
			clearFields();
		}
	}

}
