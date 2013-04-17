package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import org.joda.time.LocalDate;

import domein.Doos;
import domein.Leertraject;
import domein.LeertrajectController;
import domein.builders.DocumentBuilder;
import domein.builders.DoosBuilder;
import domein.pattern.observer.Observer;

public class DoosToevoegenPanel extends JPanel implements ActionListener, Observer{
	
	private LeertrajectController lc;
	private JLabel lblThema;
	private JTextField txtThema;
	private CardGeneralInfo cardGeneral;
	private JPanel filler;
	private JButton btnNext;
	private JButton btnBack;
	private JButton btnOpslaan;
	private CardInfoLink cardInfoLink;
	private JLabel lblVraag1;
	private JTextField txtVraag1;
	private JLabel lblVraag2;
	private JTextField txtVraag2;
	private JLabel lblVraag3;
	private JTextField txtVraag3;

	public DoosToevoegenPanel(LeertrajectController lc) {
		this.lc = lc;
		lc.addObserver(this);
		initGUI();
		createMigLayout();
	}

	private void createMigLayout() {
		cardGeneral.add(lblThema,"right");
		cardGeneral.add(txtThema,"wrap,growx");
		cardGeneral.add(lblVraag1,"right");
		cardGeneral.add(txtVraag1,"wrap,growx");
		cardGeneral.add(lblVraag2,"right");
		cardGeneral.add(txtVraag2,"wrap,growx");
		cardGeneral.add(lblVraag3,"right");
		cardGeneral.add(txtVraag3,"wrap,growx");
		filler.add(btnBack,"right,tag ok");
		filler.add(btnOpslaan,"right,tag ok");
		filler.add(btnNext,"right,tag ok");
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
			{
				lblThema = new JLabel("Thema :");
				txtThema = new JTextField();
			}
			{
				lblVraag1 = new JLabel("Eerst vraag :");
				txtVraag1 = new JTextField();
			}
			{
				lblVraag2 = new JLabel("Tweede vraag :");
				txtVraag2 = new JTextField();
			}
			{
				lblVraag3 = new JLabel("Derde vraag:");
				txtVraag3 = new JTextField();
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
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == btnNext) {
			if(lc.getGeselecteerdLeertraject() != null & cardGeneral.validateCardInfo() & validateFields()) {
				btnOpslaan.setVisible(true);
				btnBack.setVisible(true);
				btnNext.setVisible(false);
				cardGeneral.setVisible(false);
				cardInfoLink.setVisible(true);
				
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
				DoosBuilder doosBuilder = new DoosBuilder();
				doosBuilder.createNewDoos();
				
				DocumentBuilder documentBuilder = new DocumentBuilder();
				documentBuilder.createNewDocument();
				doosBuilder.buildDisplayNaam(cardInfoLink.getTxtDispNaam().getText());
				doosBuilder.buildDisplayOmschrijving(cardInfoLink.getTxaDispOmschrijving().getText());
				doosBuilder.buildBeschikbaarVan(LocalDate.fromDateFields(cardInfoLink.getFieldBeschikVan().getDate()));
				doosBuilder.buildBeschikbaarTot(LocalDate.fromDateFields(cardInfoLink.getFieldBeschikTot().getDate()));
				
				doosBuilder.buildTitel(cardGeneral.getTxtTitel().getText());
				doosBuilder.buildOmschrijving(cardGeneral.getTxaOmschrijving().getText());
				doosBuilder.buildDoelgroep(cardGeneral.getTxtDoelgroep().getText());
				doosBuilder.buildKernwoorden(cardGeneral.getTxaKernwoorden().getText());
				doosBuilder.buildThema(txtThema.getText());
				ArrayList<String> lijstVragen = new ArrayList<>();
				if(!txtVraag1.getText().trim().isEmpty())
					lijstVragen.add(txtVraag1.getText().trim());
				if(!txtVraag2.getText().trim().isEmpty())
					lijstVragen.add(txtVraag2.getText().trim());
				if(!txtVraag3.getText().trim().isEmpty())
					lijstVragen.add(txtVraag3.getText().trim());
				doosBuilder.buildVragen(lijstVragen);
				Doos doos = (Doos) doosBuilder.getOnderdeel();
				lc.save(doos);
				lc.linkOnderdeel(doos);
				clearFields();
			}
		}
		if(event.getSource() == btnBack) {
			btnOpslaan.setVisible(false);
			btnNext.setVisible(true);
			btnBack.setVisible(false);
			cardGeneral.setVisible(true);
			cardInfoLink.setVisible(false);
		}
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

	private boolean validateFields() {
		boolean validate = true;
		int aantalVragen = 0;
		
		if(txtThema.getText().trim().isEmpty()) {
			validate = false;
			txtThema.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txtThema.setBorder(UIManager.getBorder("TextField.border"));
		
		if(!txtVraag1.getText().trim().isEmpty())
			aantalVragen++;
		
		if(!txtVraag2.getText().trim().isEmpty())
			aantalVragen++;
		
		if(!txtVraag3.getText().trim().isEmpty())
			aantalVragen++;
		
		if(aantalVragen < 1) {
			txtVraag1.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else txtVraag1.setBorder(UIManager.getBorder("TextField.border"));
		
		return validate;
	}

	@Override
	public void update(Object object) {
		if(object instanceof Leertraject) {
			clearFields();
		}
	}

	private void clearFields() {
		txtVraag1.setText("");
		txtVraag2.setText("");
		txtVraag3.setText("");
		txtThema.setText("");
		cardInfoLink.clearFields();
		cardGeneral.clearFields();
		btnOpslaan.setVisible(false);
		btnNext.setVisible(true);
		btnBack.setVisible(false);
		cardGeneral.setVisible(true);
		cardInfoLink.setVisible(false);
	}

}
