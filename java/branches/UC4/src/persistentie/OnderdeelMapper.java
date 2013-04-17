package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import domein.Document;
import domein.Onderdeel;
import domein.SoortOnderdeel;

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
			String sqlQuery = "SELECT * FROM tblDocument";
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Document doc = new Document();
				doc.setTitel(rs.getString("Titel"));
				doc.setOmschrijving(rs.getString("Omschrijving"));
				// TODO kernwoorden uit DB halen
				doc.setKernwoorden(new ArrayList<String>());
				doc.setDoelgroep(rs.getString("Doelgroep"));
				doc.setLocatie(rs.getString("Locatie"));
				doc.setID(rs.getInt("DocumentID"));
				doc.setType(SoortOnderdeel.DOCUMENT);
				oLijst.add(doc);
			}
		    pstmt.close();
		    rs.close();
		    
			// TODO andere onderdelen uit DB halen
		    
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
									// TODO kernwoorden uit DB halen
									doc.setKernwoorden(new ArrayList<String>());
									doc.setDoelgroep(rs2.getString("Doelgroep"));
									doc.setLocatie(rs2.getString("Locatie"));
									doc.setDisplayNaam(rs.getString("displayNaam"));
									doc.setID(rs.getInt("OnderdeelID"));
									doc.setDisplayOmschrijving(rs.getString("DisplayOmschrijving"));
									doc.setDisplayNaam(rs.getString("DisplayNaam"));
									doc.setBeschikbaarVan(LocalDate.fromDateFields(rs.getDate("BeschikbaarVan")));
									doc.setBeschikbaarTot(LocalDate.fromDateFields(rs.getDate("BeschikbaarTot")));
									oLijst.add(doc);}
								rs2.close();
								break;
				case "Casus": break;
				case "Stelling": break;
				case "Doos": break;
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
							  // TODO insert kernwoorden
							  rs.close();
							  pstmt.close();
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
			String sqlQuery = "INSERT INTO tblTrajectOnderdelen VALUES ('"+leertrajectCode+"','"+onderdeel.getID()+"','"+onderdeel.getType()+"','"+onderdeel.getDisplayNaam()
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
					  +"' AND Type = '"+onderdeel.getType()+"'";
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
