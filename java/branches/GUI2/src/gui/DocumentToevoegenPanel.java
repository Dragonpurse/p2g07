package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import org.joda.time.LocalDate;

import domein.Document;
import domein.Leertraject;
import domein.LeertrajectController;
import domein.Onderdeel;
import domein.SoortOnderdeel;
import domein.builders.DocumentBuilder;
import domein.pattern.observer.Observer;

public class DocumentToevoegenPanel extends JPanel implements ActionListener, Observer {
	
	private LeertrajectController lc;
	private JLabel lblLocatie;
	private JTextField txtLocatie;
	private JButton btnNext;
	private JButton btnOpslaan;
	private JPanel filler;
	private JButton btnBack;
	private JButton btnLocatie;
	private JFileChooser fc;
	private CardInfoLink cardInfoLink;
	private CardGeneralInfo cardGeneral;
	private AbstractButton btnCopy;
	private JLabel lblWarning;
	private Onderdeel geselecteerdOnderdeel;
	
	public DocumentToevoegenPanel(LeertrajectController lc){
		this.lc = lc;
		lc.addObserver(this);
		initGUI();
		createMigLayout();
	}
	
	private void createMigLayout() {
		filler.add(btnCopy,"gapleft 115,tag left");
		filler.add(lblWarning,"center");
		filler.add(btnBack,"tag right,split 2");
		filler.add(btnOpslaan,"tag right");
		filler.add(btnNext,"tag right");
		cardGeneral.add(lblLocatie,"right");
		cardGeneral.add(txtLocatie,"growx, split 2");
		cardGeneral.add(btnLocatie,"right,w 50,wrap");
		add(cardGeneral,"wrap,growx,spany");
		add(cardInfoLink,"wrap,growx,spany");
		add(filler,"south");
	}

	private void initGUI() {
		setLayout(new MigLayout("hidemode 3 , nocache","[grow,fill,::600]"));
		setBackground(new Color(250,250,250));
		setBorder(BorderFactory.createEmptyBorder());
		{
			cardGeneral = new CardGeneralInfo();
			{
				lblLocatie = new JLabel("Locatie :");
				txtLocatie = new JTextField();
				txtLocatie.setEnabled(false);
				btnLocatie = new JButton("...");
				btnLocatie.addActionListener(this);
			}
		}
		{
			filler = new JPanel(new MigLayout("hidemode 3 , nocache","[]push[]"));
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
			{
				lblWarning = new JLabel();
				lblWarning.setForeground(Color.red);
			}
			{
				btnCopy = new JButton("Kopieer");
				btnCopy.addActionListener(this);
				btnCopy.setVisible(true);
			}
		}
		{
			cardInfoLink = new CardInfoLink();
			cardInfoLink.setVisible(false);
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == btnCopy){
			if(geselecteerdOnderdeel == null || !(geselecteerdOnderdeel.getType().equals(SoortOnderdeel.DOCUMENT))) {
				lblWarning.setText("Gelieve een document te selecteren.");
				return;
			}
			lblWarning.setText("");
			updateFields((Document) geselecteerdOnderdeel);
					
		}
		if(event.getSource() == btnLocatie) {
			fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION)
				txtLocatie.setText(fc.getSelectedFile().getAbsolutePath());
		}
		if(event.getSource() == btnNext) {
			if(lc.getGeselecteerdLeertraject() != null & cardGeneral.validateCardInfo() & validateFields()) {
				btnOpslaan.setVisible(true);
				btnBack.setVisible(true);
				btnNext.setVisible(false);
				btnCopy.setVisible(false);
				cardGeneral.setVisible(false);
				cardInfoLink.setVisible(true);
				lblWarning.setText("");
				
				cardInfoLink.getFieldBeschikVan().setDate(lc.getGeselecteerdLeertraject().getBeschikbaarVan().toDate());
				cardInfoLink.getFieldBeschikTot().setDate(lc.getGeselecteerdLeertraject().getBeschikbaarTot().toDate());
				cardInfoLink.getTxaDispOmschrijving().setText(cardGeneral.getTxaOmschrijving().getText());
				cardInfoLink.getTxtDispNaam().setText(cardGeneral.getTxtTitel().getText());
			}
			else {
			}
		}
		if(event.getSource() == btnOpslaan) {
			if(validateCards()) {
				DocumentBuilder documentBuilder = new DocumentBuilder();
				documentBuilder.createNewDocument();
				documentBuilder.buildDisplayNaam(cardInfoLink.getTxtDispNaam().getText());
				documentBuilder.buildDisplayOmschrijving(cardInfoLink.getTxaDispOmschrijving().getText());
				documentBuilder.buildTitel(cardGeneral.getTxtTitel().getText());
				documentBuilder.buildOmschrijving(cardGeneral.getTxaOmschrijving().getText());
				documentBuilder.buildDoelgroep(cardGeneral.getTxtDoelgroep().getText());
				documentBuilder.buildKernwoorden(cardGeneral.getTxaKernwoorden().getText());
				documentBuilder.buildBeschikbaarVan(LocalDate.fromDateFields(cardInfoLink.getFieldBeschikVan().getDate()));
				documentBuilder.buildBeschikbaarTot(LocalDate.fromDateFields(cardInfoLink.getFieldBeschikTot().getDate()));
				documentBuilder.buildLocatie(txtLocatie.getText());
				Document nieuwDocument = (Document)documentBuilder.getOnderdeel();
				lc.save(nieuwDocument);
				lc.linkOnderdeel(nieuwDocument);
				clearFields();
				
			}
		}
		if(event.getSource() == btnBack) {
			btnOpslaan.setVisible(false);
			btnNext.setVisible(true);
			btnBack.setVisible(false);
			btnCopy.setVisible(true);
			cardGeneral.setVisible(true);
			cardInfoLink.setVisible(false);
		}
			
	}

	@Override
	public void update(Object object) {
		if(object instanceof Leertraject) {
			clearFields();
		}
		if(object instanceof Document) {
			geselecteerdOnderdeel = (Document) object;
		}
	}

	private void clearFields() {
		txtLocatie.setText("");
		txtLocatie.setBorder(UIManager.getBorder("TextField.border"));
		cardInfoLink.clearFields();
		cardGeneral.clearFields();
		btnOpslaan.setVisible(false);
		btnCopy.setVisible(true);
		btnNext.setVisible(true);
		btnBack.setVisible(false);
		cardGeneral.setVisible(true);
		cardInfoLink.setVisible(false);
	}
	
	public boolean validateFields() {
		boolean validate = true;
		if(txtLocatie.getText().trim().isEmpty()) {
			validate = false;
			txtLocatie.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txtLocatie.setBorder(UIManager.getBorder("TextField.border"));
		return validate;
	}
	
	private boolean validateCards() {
		boolean validate = true;
		validate = validateFields();
		if(validate)
			validate = cardGeneral.validateCardInfo();
		if(validate)
			validate = cardInfoLink.validateCardInfo();
		return validate;
	}

	public void updateFields(Document document) {
		cardInfoLink.getFieldBeschikVan().setDate(lc.getGeselecteerdLeertraject().getBeschikbaarVan().toDate());
		cardInfoLink.getFieldBeschikTot().setDate(lc.getGeselecteerdLeertraject().getBeschikbaarTot().toDate());
		cardInfoLink.getTxaDispOmschrijving().setText(document.getOmschrijving());
		cardInfoLink.getTxtDispNaam().setText(document.getTitel());
		txtLocatie.setText(document.getLocatie());
		cardGeneral.getTxtTitel().setText(document.getTitel());
		cardGeneral.getTxaOmschrijving().setText(document.getOmschrijving());
		cardGeneral.getTxtDoelgroep().setText(document.getDoelgroep());
		cardGeneral.updateKernwoordenLijst(document.getKernwoorden());	
	}
}
