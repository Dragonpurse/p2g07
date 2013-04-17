package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import domein.Casus;
import domein.CasusAntwoord;
import domein.CasusVraag;
import domein.Document;
import domein.Doos;
import domein.Onderdeel;
import domein.SoortOnderdeel;
import domein.Stelling;
import domein.StellingAntwoord;
import domein.StellingAntwoordKleur;
import domein.Stellingspel;

public class OnderdeelMapper extends Mapper<Onderdeel>{

	private List<Onderdeel> oLijst;
	private List<Integer> toegevoegdeVragenIDs;
	private DbConnectie dbCon;
	private String sqlQuery;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public OnderdeelMapper(){
		dbCon = new DbConnectie();
	}
	
	@Override
	public List<Onderdeel> geefLijst() {
		oLijst = new ArrayList<>();
		dbCon.openConnection();
	    try {
	    	geefDocumenten();
	    	geefStellingspellen();
	    	geefCasussen();
	    	geefDozen();   
	    } catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbCon.closeConnection();
		}
		return oLijst;
	}
	
	@Override
	public List<Onderdeel> geefLijstPerItem(String ltCode) {
		oLijst = new ArrayList<>();
		dbCon.openConnection();
		try {
			sqlQuery = "SELECT * FROM tblTrajectOnderdelen WHERE LeertrajectCode = '" + ltCode + "'";
			con = dbCon.getConn();
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String type = rs.getString("Type");
				
				switch(type){
				case "Document": 
				geefDocumentenPerItem();	
				break;
				case "Stellingspel": 
				geefStellingspellenPerItem();
				break; 
				case "Casus": 
				geefCasussenPerItem();
				break; 
				case "Doos": 
				geefDozenPerItem();
				break; 
				default : break;
				}
			}
		    pstmt.close();
		    rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbCon.closeConnection();
		}
		
	return oLijst;
	}

	// Voeg nieuw onderdeel toe zonder link
	@Override
	public void voegItemToe(Onderdeel onderdeel) {
		dbCon.openConnection();
		try {
			String type = onderdeel.getType().toString();
			switch(type) {
			case "Document" :
			voegDocumentToe(onderdeel); 
			break;
			case "Stellingspel" : 
			voegStellingspelToe(onderdeel); 
			break;
			case "Casus":   
			voegCasusToe(onderdeel); 
			break;
			case "Doos": 
			voegDoosToe(onderdeel); 
			break;
			default: break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbCon.closeConnection();
		}
	}
	
	// Link onderdeel met traject
	@Override
	public void voegItemToe(String leertrajectCode, Onderdeel onderdeel) {
		dbCon.openConnection();
		try {
			sqlQuery = "INSERT INTO tblTrajectOnderdelen VALUES ('"+leertrajectCode+"','"+onderdeel.getID()+"','"+onderdeel.getType().toString()+"','"+onderdeel.getDisplayNaam()
					        + "','"+onderdeel.getDisplayOmschrijving()+"','"+onderdeel.getBeschikbaarVan()+"','"+onderdeel.getBeschikbaarTot()+"')";
			con = dbCon.getConn();
			pstmt = con.prepareStatement(sqlQuery);
			pstmt.executeUpdate();			
			pstmt.close();
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			dbCon.closeConnection();
		}
	}

	@Override
	public void verwijderItem(String ltCode, Onderdeel onderdeel) {	
		dbCon.openConnection();
		try {
			sqlQuery = "DELETE FROM tblTrajectOnderdelen WHERE LeertrajectCode = '"+ltCode+"' AND OnderdeelID = '"+onderdeel.getID()
					  +"' AND Type = '"+onderdeel.getType().toString()+"'";
			con = dbCon.getConn();
			pstmt = con.prepareStatement(sqlQuery);
			pstmt.executeUpdate();			
			pstmt.close();
		} catch (SQLException e) {
            e.printStackTrace(); 
		} finally{
			dbCon.closeConnection();
		}
	}
	
	//4 private methodes voor geefLijst
	private void geefDocumenten() throws SQLException
	{
		sqlQuery = "SELECT * FROM tblDocument";
		con = dbCon.getConn();
		pstmt = con.prepareStatement(sqlQuery);
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			Document doc = new Document();
			doc.setTitel(rs.getString("Titel"));
			doc.setOmschrijving(rs.getString("Omschrijving"));
			doc.setKernwoorden(rs.getString("Kernwoorden"));
			doc.setDoelgroep(rs.getString("Doelgroep"));
			doc.setLocatieDocument(rs.getString("Locatie"));
			doc.setID(rs.getInt("DocumentID"));
			doc.setType(SoortOnderdeel.DOCUMENT);
			oLijst.add(doc);
		}
	    pstmt.close();
	    rs.close();
	}
	
	
	private void geefStellingspellen() throws SQLException
	{
		 	sqlQuery = "SELECT * FROM tblStellingspel";
			con = dbCon.getConn();
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Stellingspel spel = new Stellingspel();
				spel.setTitel(rs.getString("Titel"));
				spel.setOmschrijving(rs.getString("Omschrijving"));
				spel.setKernwoorden(rs.getString("Kernwoorden"));
				spel.setDoelgroep(rs.getString("Doelgroep"));
				
				sqlQuery = "SELECT * FROM tblStelling WHERE StellingspelID = '"+rs.getInt("StellingspelID")+"'";
				pstmt = con.prepareStatement(sqlQuery);
				ResultSet rs2 = pstmt.executeQuery();
				List<Stelling> stellingen = new ArrayList<>();
				while(rs2.next()){
					sqlQuery = "SELECT * FROM tblAntwoordStelling WHERE StellingID = '"+rs2.getString("StellingID")+"'";
					pstmt = con.prepareStatement(sqlQuery);
					List<StellingAntwoord> antwoorden = new ArrayList<>();
					ResultSet rs3 = pstmt.executeQuery();
					while(rs3.next()){
						StellingAntwoord antwoord = new StellingAntwoord(rs3.getString("Antwoord"),StellingAntwoordKleur.getStellingAntwoordKleur(rs3.getString("Kleur")));
						antwoorden.add(antwoord);
					}
					Stelling stelling = new Stelling(rs2.getString("Stelling"),antwoorden);
					stellingen.add(stelling);
				}
				spel.setID(rs.getInt("StellingspelID"));
				spel.setType(SoortOnderdeel.STELLINGSPEL);
				spel.setStellingen(stellingen);
				oLijst.add(spel);
			}
		    pstmt.close();
		    rs.close();
	}
	
	
	private void geefCasussen() throws SQLException
	{
		sqlQuery = "SELECT * FROM tblCasus";
		con = dbCon.getConn();
		pstmt = con.prepareStatement(sqlQuery);
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			Casus casus = new Casus();
			casus.setTitel(rs.getString("Titel"));
			casus.setOmschrijving(rs.getString("Omschrijving"));
			casus.setDoelgroep(rs.getString("Doelgroep"));
			casus.setKernwoorden(rs.getString("Kernwoorden"));
			casus.setSituatieschets(rs.getString("Situatieschets"));
			casus.setLocatieIntro(rs.getString("LocatieFilm"));		
			casus.setID(rs.getInt("CasusID"));
			casus.setType(SoortOnderdeel.CASUS);
			int eersteVraagID = rs.getInt("EersteVraag");
			voegCasusVragenToe(casus, eersteVraagID);

			oLijst.add(casus);
		}
	    pstmt.close();
	    rs.close();
	}
	
	private void voegCasusVragenToe(Casus casus, int eersteVraagID) throws SQLException{
		toegevoegdeVragenIDs = new ArrayList<>();
		sqlQuery = "SELECT * FROM tblVraag WHERE vraagID = '"+eersteVraagID+"'";
		pstmt = con.prepareStatement(sqlQuery);
		ResultSet rs2 = pstmt.executeQuery();
		int vraagID = -1;
		if(rs2.next())
			vraagID = rs2.getInt("VraagID");
		CasusVraag vraag = new CasusVraag(rs2.getString("VraagTekst"));
		toegevoegdeVragenIDs.add(vraagID);
		voegCasusAntwoordenToe(vraag, vraagID);
	}
	
	private void voegCasusAntwoordenToe(CasusVraag vraag, int vraagID) throws SQLException{
		
		sqlQuery = "SELECT * FROM tblAntwoordCasus WHERE VraagID = '"+vraagID+"'";
		pstmt = con.prepareStatement(sqlQuery);
		ResultSet rs3 = pstmt.executeQuery();
		while(rs3.next()){
			CasusAntwoord antwoord = new CasusAntwoord(rs3.getString("AntwoordTekst"), rs3.getString("AntwoordBeschrijving"));
			int volgendeVraag = rs3.getInt("VolgendeVraag");
			if(!(volgendeVraag == -1 || toegevoegdeVragenIDs.contains(volgendeVraag))){
				sqlQuery = "SELECT * FROM tblVraag WHERE vraagID = '"+rs3.getInt("VolgendeVraag")+"'";
				pstmt = con.prepareStatement(sqlQuery);
				ResultSet rs4 = pstmt.executeQuery();
				if(rs4.next()){
					CasusVraag nieuwevraag = new CasusVraag(rs4.getString("VraagTekst"));
					voegCasusAntwoordenToe(nieuwevraag, rs4.getInt("VraagID"));
				}
			}
			vraag.voegAntwoordToe(antwoord);
		}
		
	}
	
	
	
	
	
	private void geefDozen() throws SQLException
	{
		sqlQuery = "SELECT * FROM tblDoos";
		con = dbCon.getConn();
		pstmt = con.prepareStatement(sqlQuery);
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			Doos doos = new Doos();
			doos.setTitel(rs.getString("Titel"));
			doos.setOmschrijving(rs.getString("Omschrijving"));
			doos.setKernwoorden(rs.getString("Kernwoorden"));
			doos.setDoelgroep(rs.getString("Doelgroep"));
			doos.setThema(rs.getString("Thema"));
			sqlQuery = "SELECT Vraag FROM tblVraagDoos WHERE DoosID ='"+rs.getInt("DoosID")+"'";
			pstmt = con.prepareStatement(sqlQuery);
			ResultSet rs2 = pstmt.executeQuery();
			List<String> vragenLijst = new ArrayList<>();
			while(rs2.next()){
				vragenLijst.add(rs2.getString("Vraag"));
			}
			doos.setVragen(new ArrayList<String>());
			doos.setID(rs.getInt("DoosID"));
			doos.setType(SoortOnderdeel.DOOS);
			oLijst.add(doos);
		}
	    pstmt.close();
	    rs.close();
	}
	
	//4 private methodes voor geefLijstPerItem
	private void geefDocumentenPerItem() throws SQLException
	{
		sqlQuery = "SELECT * FROM tblDocument WHERE DocumentID ='"+rs.getInt("OnderdeelID")+"'";
		pstmt = con.prepareStatement(sqlQuery);
		ResultSet rs2 = pstmt.executeQuery();
		
		if(rs2.next()){
			Document doc = new Document();
			doc.setTitel(rs2.getString("Titel"));
			doc.setOmschrijving(rs2.getString("Omschrijving"));
			doc.setKernwoorden(rs2.getString("Kernwoorden"));
			doc.setDoelgroep(rs2.getString("Doelgroep"));
			doc.setLocatieDocument(rs2.getString("Locatie"));
			doc.setDisplayNaam(rs.getString("displayNaam"));
			doc.setID(rs.getInt("OnderdeelID"));
			doc.setDisplayOmschrijving(rs.getString("DisplayOmschrijving"));
			doc.setDisplayNaam(rs.getString("DisplayNaam"));
			doc.setBeschikbaarVan(LocalDate.fromDateFields(rs.getDate("BeschikbaarVan")));
			doc.setBeschikbaarTot(LocalDate.fromDateFields(rs.getDate("BeschikbaarTot")));
			oLijst.add(doc);}
	    rs2.close();
	}
	
	
	private void geefStellingspellenPerItem() throws SQLException
	{
		sqlQuery = "SELECT * FROM tblStellingspel WHERE StellingspelID ='"+rs.getInt("OnderdeelID")+"'";
    	pstmt = con.prepareStatement(sqlQuery);
    	ResultSet rs2 = pstmt.executeQuery();
    	
    	if(rs2.next()){
			Stellingspel spel = new Stellingspel();
			spel.setTitel(rs2.getString("Titel"));
			spel.setOmschrijving(rs2.getString("Omschrijving"));
			spel.setKernwoorden(rs2.getString("Kernwoorden"));
			spel.setDoelgroep(rs2.getString("Doelgroep"));
			
			sqlQuery = "SELECT * FROM tblStelling WHERE StellingspelID = '"+rs.getInt("OnderdeelID")+"'";
			pstmt = con.prepareStatement(sqlQuery);
			ResultSet rs3 = pstmt.executeQuery();
			List<Stelling> stellingen = new ArrayList<>();
			while(rs3.next()){
				sqlQuery = "SELECT * FROM tblAntwoordStelling WHERE StellingID = '"+rs3.getString("StellingID")+"'";
				pstmt = con.prepareStatement(sqlQuery);
				List<StellingAntwoord> antwoorden = new ArrayList<>();
				ResultSet rs4 = pstmt.executeQuery();
				while(rs4.next()){
					StellingAntwoord antwoord = new StellingAntwoord(rs4.getString("Antwoord"),StellingAntwoordKleur.getStellingAntwoordKleur(rs4.getString("Kleur")));
					antwoorden.add(antwoord);
				}
				Stelling stelling = new Stelling(rs3.getString("Stelling"),antwoorden);
				stellingen.add(stelling);
			}
			spel.setStellingen(stellingen);
			spel.setDisplayNaam(rs.getString("displayNaam"));
			spel.setID(rs.getInt("OnderdeelID"));
			spel.setDisplayOmschrijving(rs.getString("DisplayOmschrijving"));
			spel.setDisplayNaam(rs.getString("DisplayNaam"));
			spel.setBeschikbaarVan(LocalDate.fromDateFields(rs.getDate("BeschikbaarVan")));
			spel.setBeschikbaarTot(LocalDate.fromDateFields(rs.getDate("BeschikbaarTot")));
			oLijst.add(spel);}
		rs2.close();
	}
	
	
	private void geefCasussenPerItem() throws SQLException
	{
		sqlQuery = "SELECT * FROM tblCasus WHERE CasusID ='"+rs.getInt("OnderdeelID")+"'";
    	pstmt = con.prepareStatement(sqlQuery);
    	ResultSet rs2 = pstmt.executeQuery();
    	
    	if(rs2.next()){
			Casus cas = new Casus();
			cas.setTitel(rs2.getString("Titel"));
			cas.setOmschrijving(rs2.getString("Omschrijving"));
			cas.setKernwoorden(rs2.getString("Kernwoorden"));
			cas.setDoelgroep(rs2.getString("Doelgroep"));
			cas.setSituatieschets(rs2.getString("Situatieschets"));
			cas.setLocatieIntro(rs2.getString("LocatieFilm"));
			cas.setDisplayNaam(rs.getString("displayNaam"));
			cas.setID(rs.getInt("OnderdeelID"));
			cas.setDisplayOmschrijving(rs.getString("DisplayOmschrijving"));
			cas.setDisplayNaam(rs.getString("DisplayNaam"));
			cas.setBeschikbaarVan(LocalDate.fromDateFields(rs.getDate("BeschikbaarVan")));
			cas.setBeschikbaarTot(LocalDate.fromDateFields(rs.getDate("BeschikbaarTot")));		
			int eersteVraagID = rs2.getInt("EersteVraag");
			voegCasusVragenToe(cas, eersteVraagID);
			oLijst.add(cas);}
		rs2.close();
	}
	
	private void geefDozenPerItem() throws SQLException
	{
		sqlQuery = "SELECT * FROM tblDoos WHERE DoosID ='"+rs.getInt("OnderdeelID")+"'";
    	pstmt = con.prepareStatement(sqlQuery);
    	ResultSet rs2 = pstmt.executeQuery();
    	
    	if(rs2.next()){
			Doos doos = new Doos();
			doos.setTitel(rs2.getString("Titel"));
			doos.setOmschrijving(rs2.getString("Omschrijving"));
			doos.setKernwoorden(rs2.getString("Kernwoorden"));
			doos.setDoelgroep(rs2.getString("Doelgroep"));
			doos.setThema(rs2.getString("Thema"));
			
			sqlQuery = "SELECT Vraag FROM tblVraagDoos WHERE DoosID ='"+rs.getInt("OnderdeelID")+"'";
			pstmt = con.prepareStatement(sqlQuery);
			ResultSet rs3 = pstmt.executeQuery();
			List<String> vragenLijst = new ArrayList<>();
			while(rs3.next()){
				vragenLijst.add(rs3.getString("Vraag"));
			}
			doos.setVragen(vragenLijst);
			doos.setDisplayNaam(rs.getString("displayNaam"));
			doos.setID(rs.getInt("OnderdeelID"));
			doos.setDisplayOmschrijving(rs.getString("DisplayOmschrijving"));
			doos.setDisplayNaam(rs.getString("DisplayNaam"));
			doos.setBeschikbaarVan(LocalDate.fromDateFields(rs.getDate("BeschikbaarVan")));
			doos.setBeschikbaarTot(LocalDate.fromDateFields(rs.getDate("BeschikbaarTot")));
			oLijst.add(doos);}
		rs2.close();
	}
	
	//4 private methodes voor voegItemToe
	private void voegDocumentToe(Onderdeel onderdeel) throws SQLException
	{
		  Document doc = (Document) onderdeel;
		  sqlQuery = "INSERT INTO tblDocument VALUES ('"+doc.getTitel()+"','"+doc.getOmschrijving()+"','"+doc.getLocatie()+"','"+doc.getDoelgroep()+"','"+doc.getKernwoorden()+"')";
		  con = dbCon.getConn();
		  pstmt = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
		  pstmt.executeUpdate();
		  rs = pstmt.getGeneratedKeys();
		  if(rs.next())
			  onderdeel.setID(rs.getInt(1));
		  
		  rs.close();
		  pstmt.close();
	}
	
	private void voegStellingspelToe(Onderdeel onderdeel) throws SQLException
	{
		  Stellingspel spel = (Stellingspel) onderdeel;
	      sqlQuery = "INSERT INTO tblStellingspel VALUES ('"+spel.getTitel()+"','"+spel.getOmschrijving()+"','"+spel.getDoelgroep()+"','"+spel.getKernwoorden()+"')";
		  con = dbCon.getConn();
		  pstmt = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
		  pstmt.executeUpdate();
		  rs = pstmt.getGeneratedKeys();
		  if(rs.next())
			  onderdeel.setID(rs.getInt(1));
		  for(Stelling stelling : spel.getStellingen()){
			  sqlQuery = "INSERT INTO tblStelling VALUES('"+rs.getInt(1)+"','"+stelling.getStelling()+"')";
			  pstmt = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			  pstmt.executeUpdate();
			  ResultSet rs2 = pstmt.getGeneratedKeys();
			  rs2.next();
			  for(StellingAntwoord antwoord : stelling.getAntwoorden()){
				  sqlQuery = "INSERT INTO tblAntwoordStelling VALUES('"+rs2.getInt(1)+"','"+antwoord.getAntwoord()+"','"+antwoord.getKleur().toString()+"')";
				  pstmt = con.prepareStatement(sqlQuery);
				  pstmt.executeUpdate();
			  }
		  }
		  rs.close();
		  pstmt.close();
	}
	
	private void voegCasusToe(Onderdeel onderdeel) throws SQLException
	{
		con = dbCon.getConn();
		Casus cas = (Casus) onderdeel;
		sqlQuery = "INSERT INTO tblCasus(Titel, Omschrijving, LocatieFilm, Doelgroep, Situatieschets, Kernwoorden)" +
				" VALUES ('"+cas.getTitel()+"','"+cas.getOmschrijving()+"','"+cas.getLocatieIntro()+"','"+cas.getDoelgroep()+"','"+cas.getSituatieschets()+"','"+cas.getKernwoorden()+"')";
		pstmt = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
		pstmt.executeUpdate();
		rs = pstmt.getGeneratedKeys();
		int casusID = -1 ;
		if(rs.next())
			casusID = rs.getInt(1);
			onderdeel.setID(casusID);
		slaCasusVraagOp(cas.getCasusRoot(), casusID);
		
		sqlQuery = "UPDATE tblCasus SET EersteVraag = '"+cas.getCasusRoot().getVraagID()+"' WHERE CasusID = '"+casusID+"'";
		pstmt = con.prepareStatement(sqlQuery);
		pstmt.executeUpdate();
		
		rs.close();
		pstmt.close();
	}
	
	
	private void slaCasusVraagOp(CasusVraag vraag, int casusID) throws SQLException{
		if(vraag.getVraagID() == -1){
			sqlQuery = "INSERT INTO tblVraag VALUES('"+vraag.getVraagTekst()+"','"+casusID+"')";
			pstmt = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			int vraagID = -1;
			if(rs.next())
				vraagID = rs.getInt(1);
				vraag.setVraagID(vraagID);
			
			for(CasusAntwoord antwoord : vraag.getAntwoorden()){
				slaCasusAntwoordOp(antwoord, vraagID, casusID);
			}
		}		
	}
	
	private void slaCasusAntwoordOp(CasusAntwoord antwoord, int vorigeVraagID, int casusID) throws SQLException{
		int volgendeVraag;
		if(antwoord.getVolgendeVraag() == null)
			volgendeVraag = -1;
		else {
			slaCasusVraagOp(antwoord.getVolgendeVraag(), casusID);
			volgendeVraag = antwoord.getVolgendeVraag().getVraagID();
		}
		sqlQuery = "INSERT INTO tblAntwoordCasus VALUES('"+antwoord.getAntwoord()+"','"+antwoord.getFeedback()+"','"+volgendeVraag+"','"+vorigeVraagID+"')";
		pstmt = con.prepareStatement(sqlQuery);
		pstmt.executeUpdate();			
	}
	
	private void voegDoosToe(Onderdeel onderdeel) throws SQLException
	{
		 Doos doos = (Doos) onderdeel;
		 sqlQuery = "INSERT INTO tblDoos VALUES ('"+doos.getTitel()+"','"+doos.getOmschrijving()+"','"+doos.getDoelgroep()+"','"+doos.getKernwoorden()+"','"+doos.getThema()+"')";
		 con = dbCon.getConn();
		 pstmt = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
		 pstmt.executeUpdate();
		 rs = pstmt.getGeneratedKeys();
		 if(rs.next())
			 onderdeel.setID(rs.getInt(1));
		 for(String vraag : doos.getVragen()){
			 sqlQuery = "INSERT INTO tblVraagDoos VALUES('"+doos.getID()+"','"+vraag+"')";
			 pstmt = con.prepareStatement(sqlQuery);
			 pstmt.executeUpdate();
		 }
		 rs.close();
		 pstmt.close();
	}
}
