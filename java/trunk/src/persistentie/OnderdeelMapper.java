package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.joda.time.LocalDate;

import domein.Casus;
import domein.CasusVraag;
import domein.Document;
import domein.Doos;
import domein.Onderdeel;
import domein.SoortOnderdeel;
import domein.Stellingspel;

public class OnderdeelMapper extends Mapper<Onderdeel>{

	private List<Onderdeel> oLijst;
	private DbConnectie dbCon;
	
	public OnderdeelMapper(){
		dbCon = new DbConnectie();
	}
	
	@Override
	public List<Onderdeel> geefLijst() {
		oLijst = new ArrayList<>();
		dbCon.openConnection();
	    try {
	    	//Documenten
			String sqlQuery = "SELECT * FROM tblDocument";
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Document doc = new Document();
				doc.setTitel(rs.getString("Titel"));
				doc.setOmschrijving(rs.getString("Omschrijving"));
			    //TODO Kernwoorden
				doc.setKernwoorden(new ArrayList<String>());
				doc.setDoelgroep(rs.getString("Doelgroep"));
				doc.setLocatieDocument(rs.getString("Locatie"));
				doc.setID(rs.getInt("DocumentID"));
				doc.setType(SoortOnderdeel.DOCUMENT);
				oLijst.add(doc);
			}
		    pstmt.close();
		    rs.close();
		    
		    //Stellingspellen
		    sqlQuery = "SELECT * FROM tblStellingspel";
			con = dbCon.getConn();
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Stellingspel spel = new Stellingspel();
				spel.setTitel(rs.getString("Titel"));
				spel.setOmschrijving(rs.getString("Omschrijving"));
			    //TODO Kernwoorden
				spel.setKernwoorden(new ArrayList<String>());
				spel.setDoelgroep(rs.getString("Doelgroep"));
				//TODO Stellingen
				spel.setStellingen(new HashMap<String,List<String>>());
				spel.setID(rs.getInt("StellingspelID"));
				spel.setType(SoortOnderdeel.STELLINGSPEL);
				oLijst.add(spel);
			}
		    pstmt.close();
		    rs.close();
		    
		    //Casussen
			sqlQuery = "SELECT * FROM tblCasus";
			con = dbCon.getConn();
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Casus cas = new Casus();
				cas.setTitel(rs.getString("Titel"));
				cas.setOmschrijving(rs.getString("Omschrijving"));
			    //TODO Kernwoorden
				cas.setKernwoorden(new ArrayList<String>());
				cas.setDoelgroep(rs.getString("Doelgroep"));
				cas.setSituatieschets(rs.getString("Situatieschets"));
				cas.setLocatieIntro(rs.getString("LocatieFilm"));
				//TODO CasusRoot
				cas.setCasusRoot(new CasusVraag(rs.getString("EersteVraag")));
				cas.setID(rs.getInt("CasusID"));
				cas.setType(SoortOnderdeel.CASUS);
				oLijst.add(cas);
			}
		    pstmt.close();
		    rs.close();
			
			//Dozen
			sqlQuery = "SELECT * FROM tblDoos";
			con = dbCon.getConn();
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Doos doos = new Doos();
				doos.setTitel(rs.getString("Titel"));
				doos.setOmschrijving(rs.getString("Omschrijving"));
			    //TODO Kernwoorden
				doos.setKernwoorden(new ArrayList<String>());
				doos.setDoelgroep(rs.getString("Doelgroep"));
				//TODO Vragen
				doos.setVragen(new ArrayList<String>());
				doos.setID(rs.getInt("DoosID"));
				doos.setType(SoortOnderdeel.DOOS);
				oLijst.add(doos);
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
	
	@Override
	public List<Onderdeel> geefLijstPerItem(String ltCode) {
		oLijst = new ArrayList<>();
		dbCon.openConnection();
		try {
			String sqlQuery = "SELECT * FROM tblTrajectOnderdelen WHERE LeertrajectCode = '" + ltCode + "'";
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String type = rs.getString("Type");
				
				switch(type){
				
				case "Document": sqlQuery = "SELECT * FROM tblDocument WHERE DocumentID ='"+rs.getInt("OnderdeelID")+"'";
				pstmt = con.prepareStatement(sqlQuery);
				ResultSet rs2 = pstmt.executeQuery();
				
				if(rs2.next()){
					Document doc = new Document();
					doc.setTitel(rs2.getString("Titel"));
					doc.setOmschrijving(rs2.getString("Omschrijving"));
					// TODO Kernwoorden
					doc.setKernwoorden(new ArrayList<String>());
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
				break;
				
				case "Stellingspel": sqlQuery = "SELECT * FROM tblStellingspel WHERE StellingspelID ='"+rs.getInt("OnderdeelID")+"'";
            	pstmt = con.prepareStatement(sqlQuery);
            	ResultSet rs3 = pstmt.executeQuery();
            	
            	if(rs3.next()){
					Stellingspel spel = new Stellingspel();
					spel.setTitel(rs3.getString("Titel"));
					spel.setOmschrijving(rs3.getString("Omschrijving"));
					//TODO Kernwoorden
					spel.setKernwoorden(new ArrayList<String>());
					spel.setDoelgroep(rs3.getString("Doelgroep"));
					//TODO Stellingen
					spel.setStellingen(new HashMap<String,List<String>>());
					spel.setDisplayNaam(rs.getString("displayNaam"));
					spel.setID(rs.getInt("OnderdeelID"));
					spel.setDisplayOmschrijving(rs.getString("DisplayOmschrijving"));
					spel.setDisplayNaam(rs.getString("DisplayNaam"));
					spel.setBeschikbaarVan(LocalDate.fromDateFields(rs.getDate("BeschikbaarVan")));
					spel.setBeschikbaarTot(LocalDate.fromDateFields(rs.getDate("BeschikbaarTot")));
					oLijst.add(spel);}
				rs3.close(); 
				break; 
				
				case "Casus": sqlQuery = "SELECT * FROM tblCasus WHERE CasusID ='"+rs.getInt("OnderdeelID")+"'";
            	pstmt = con.prepareStatement(sqlQuery);
            	ResultSet rs4 = pstmt.executeQuery();
            	
            	if(rs4.next()){
					Casus cas = new Casus();
					cas.setTitel(rs4.getString("Titel"));
					cas.setOmschrijving(rs4.getString("Omschrijving"));
					//TODO Kernwoorden
					cas.setKernwoorden(new ArrayList<String>());
					cas.setDoelgroep(rs4.getString("Doelgroep"));
					cas.setSituatieschets(rs4.getString("Situatieschets"));
					cas.setLocatieIntro(rs4.getString("LocatieFilm"));
					//TODO CasusRoot
					cas.setCasusRoot(new CasusVraag("EersteVraag"));
					cas.setDisplayNaam(rs.getString("displayNaam"));
					cas.setID(rs.getInt("OnderdeelID"));
					cas.setDisplayOmschrijving(rs.getString("DisplayOmschrijving"));
					cas.setDisplayNaam(rs.getString("DisplayNaam"));
					cas.setBeschikbaarVan(LocalDate.fromDateFields(rs.getDate("BeschikbaarVan")));
					cas.setBeschikbaarTot(LocalDate.fromDateFields(rs.getDate("BeschikbaarTot")));
					oLijst.add(cas);}
				rs4.close();
				break; 
				
				case "Doos": sqlQuery = "SELECT * FROM tblDoos WHERE DoosID ='"+rs.getInt("OnderdeelID")+"'";
            	pstmt = con.prepareStatement(sqlQuery);
            	ResultSet rs5 = pstmt.executeQuery();
            	
            	if(rs5.next()){
					Doos doos = new Doos();
					doos.setTitel(rs5.getString("Titel"));
					doos.setOmschrijving(rs5.getString("Omschrijving"));
					//TODO Kernwoorden
					doos.setKernwoorden(new ArrayList<String>());
					doos.setDoelgroep(rs5.getString("Doelgroep"));
					//TODO Vragen
					doos.setVragen(new ArrayList<String>());
					doos.setDisplayNaam(rs.getString("displayNaam"));
					doos.setID(rs.getInt("OnderdeelID"));
					doos.setDisplayOmschrijving(rs.getString("DisplayOmschrijving"));
					doos.setDisplayNaam(rs.getString("DisplayNaam"));
					doos.setBeschikbaarVan(LocalDate.fromDateFields(rs.getDate("BeschikbaarVan")));
					doos.setBeschikbaarTot(LocalDate.fromDateFields(rs.getDate("BeschikbaarTot")));
					oLijst.add(doos);}
				rs5.close();
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
			case "Document" : Document doc = (Document) onderdeel;
							  String sqlQuery = "INSERT INTO tblDocument VALUES ('"+doc.getTitel()+"','"+doc.getOmschrijving()+"','"+doc.getLocatie()+"','"+doc.getDoelgroep()+"')";
							  Connection con = dbCon.getConn();
							  PreparedStatement pstmt = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
							  pstmt.executeUpdate();
							  ResultSet rs = pstmt.getGeneratedKeys();
							  if(rs.next())
								  onderdeel.setID(rs.getInt(1));
							  //TODO Kernwoorden
							  rs.close();
							  pstmt.close();
							  break;
			case "Stellingspel" : Stellingspel spel = (Stellingspel) onderdeel;
			  					String sqlQuery2 = "INSERT INTO tblStellingspel VALUES ('"+spel.getTitel()+"','"+spel.getOmschrijving()+"','"+spel.getDoelgroep()+"')";
			  					Connection con2 = dbCon.getConn();
			  					PreparedStatement pstmt2 = con2.prepareStatement(sqlQuery2, Statement.RETURN_GENERATED_KEYS);
			  					pstmt2.executeUpdate();
			  					ResultSet rs2 = pstmt2.getGeneratedKeys();
			  					if(rs2.next())
			  						onderdeel.setID(rs2.getInt(1));
			  					//TODO Kernwoorden
			  					//TODO Stellingen
			  					rs2.close();
			  					pstmt2.close();
			  					break;
			case "Casus":   Casus cas = (Casus) onderdeel;
							String sqlQuery3 = "INSERT INTO tblCasus VALUES ('"+cas.getTitel()+"','"+cas.getOmschrijving()+"','"+cas.getLocatieIntro()+"','"+cas.getDoelgroep()+"','"+cas.getSituatieschets()+"')";
							Connection con3 = dbCon.getConn();
							PreparedStatement pstmt3 = con3.prepareStatement(sqlQuery3, Statement.RETURN_GENERATED_KEYS);
							pstmt3.executeUpdate();
							ResultSet rs3 = pstmt3.getGeneratedKeys();
							if(rs3.next())
								onderdeel.setID(rs3.getInt(1));
							//TODO Kernwoorden
							//TODO CasusRoot
							rs3.close();
							pstmt3.close();
							break;
			case "Doos": Doos doos = (Doos) onderdeel;
						 String sqlQuery4 = "INSERT INTO tblDoos VALUES ('"+doos.getTitel()+"','"+doos.getOmschrijving()+"','"+doos.getDoelgroep()+"')";
						 Connection con4 = dbCon.getConn();
						 PreparedStatement pstmt4 = con4.prepareStatement(sqlQuery4, Statement.RETURN_GENERATED_KEYS);
						 pstmt4.executeUpdate();
						 ResultSet rs4 = pstmt4.getGeneratedKeys();
						 if(rs4.next())
							 onderdeel.setID(rs4.getInt(1));
						 //TODO Kernwoorden
						 //TODO Vragen
						 rs4.close();
						 pstmt4.close();
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
			String sqlQuery = "INSERT INTO tblTrajectOnderdelen VALUES ('"+leertrajectCode+"','"+onderdeel.getID()+"','"+onderdeel.getType().toString()+"','"+onderdeel.getDisplayNaam()
					        + "','"+onderdeel.getDisplayOmschrijving()+"','"+onderdeel.getBeschikbaarVan()+"','"+onderdeel.getBeschikbaarTot()+"')";
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
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
			String sqlQuery = "DELETE FROM tblTrajectOnderdelen WHERE LeertrajectCode = '"+ltCode+"' AND OnderdeelID = '"+onderdeel.getID()
					  +"' AND Type = '"+onderdeel.getType().toString()+"'";
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			pstmt.executeUpdate();			
			pstmt.close();
		} catch (SQLException e) {
            e.printStackTrace(); 
		} finally{
			dbCon.closeConnection();
		}
	}
}
