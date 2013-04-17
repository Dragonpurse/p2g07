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

public class OnderdeelMapper extends Mapper<Onderdeel>{

	private List<Onderdeel> oLijst;
	private DbConnectie dbCon;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private ResultSet rs2;
	private String sqlQuery;
	
	public OnderdeelMapper(){
		dbCon = new DbConnectie();
	}
	
	@Override
	public List<Onderdeel> geefLijstPerItem(String ltCode) {
		oLijst = new ArrayList<>();
		sqlQuery = "SELECT Type, OnderdeelID, BeschikbaarheidVan, BeschikbaarheidTot FROM tblTrajectOnderdelen tt JOIN tblLeertraject tl "
				  +"ON tt.LeertrajectCode = tl.LeertrajectCode WHERE tt.LeertrajectCode = '" + ltCode + "'";
		try {
			dbCon.openConnection();
			Connection con = dbCon.getConn();
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String onderdeelType = rs.getString("Type");
				switch(onderdeelType){
				case "DOC": sqlQuery = "SELECT * FROM tblDocument WHERE DocumentID ='"+rs.getString("OnderdeelID")+"'";
				            pstmt = con.prepareStatement(sqlQuery);
						    rs2 = pstmt.executeQuery();
							if(rs2.next()){
							Document doc = new Document(rs2.getString("Titel"), rs2.getString("Omschrijving"), new ArrayList<String>(),
														rs2.getString("Doelgroep"), rs2.getString("Locatie"), 
														LocalDate.fromDateFields(rs.getDate("BeschikbaarheidVan")), LocalDate.fromDateFields(rs.getDate("BeschikbaarheidTot")));
							oLijst.add(doc);
							}
							break;
				case "CASUS": break;
				case "STELLING": break;
				case "DOOS": break;
				default : return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally{
			dbCon.closeConnection();
		}
		return oLijst;
	}

	@Override
	public void voegItemToe(String ltCode, Onderdeel onderdeel) {
		
		String tableType = onderdeel.getClass().getSimpleName();
		String onderdeelType = null;
		int key = 0;
		switch(tableType){
		case "Document" : Document d = (Document) onderdeel;
						  onderdeelType = "DOC";
			              sqlQuery = "INSERT INTO tblDocument VALUES ('"+d.getTitel()+"','"+d.getOmschrijving()+"','"+d.getLocatie()+"','"+d.getDoelgroep()+"')";
			              break;
		default: break;
		}
		try {
			dbCon.openConnection();
			Connection con = dbCon.getConn();
			pstmt = con.prepareStatement(sqlQuery,Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			rs.next();
			key = rs.getInt(1);
			sqlQuery = "INSERT INTO tblTrajectOnderdelen VALUES ('"+ltCode+"','"+key+"','"+onderdeelType+"','"+onderdeel.getDisplayNaam()+"','"+onderdeel.getDisplayOmschrijving()+"')";
			pstmt = con.prepareStatement(sqlQuery);
			pstmt.executeUpdate();
			
			rs.close();
			pstmt.close();
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			dbCon.closeConnection();
		}
	}
	
	private void voegBestaandItemToe(String ltCode, Onderdeel onderdeel){
		
	}

	@Override
	public void verwijderItem(String ltCode, Onderdeel onderdeel) {		
		String tableType = onderdeel.getClass().getSimpleName();
		String locatie = null;
		String onderdeelType = null;
		switch(tableType){
		case "Document": Document d = (Document) onderdeel;
					     locatie = d.getLocatie();
			             onderdeelType = "DOC";
			             break;
		default : break;
		}
		sqlQuery = "SELECT "+tableType+"ID FROM tbl"+tableType+" WHERE Locatie = '"+locatie+"'";
		try {
			dbCon.openConnection();
			Connection con = dbCon.getConn();
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			rs.next();
			sqlQuery = "DELETE FROM tblTrajectOnderdelen WHERE LeertrajectCode = '"+ltCode+"' AND OnderdeelID = '"+rs.getString(1)
					  +"' AND Type = '"+onderdeelType+"'";
			pstmt = con.prepareStatement(sqlQuery);
			pstmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace(); 
		} finally{
			dbCon.closeConnection();
		}
	}	
}
