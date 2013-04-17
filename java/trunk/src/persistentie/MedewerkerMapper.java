package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import domein.Medewerker;

public class MedewerkerMapper extends Mapper<Medewerker> {
	
	private DbConnectie dbCon;
	
	

	public MedewerkerMapper() {
		this.dbCon = new DbConnectie();
	}


	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Medewerker> geefMap() {
		Map<String, Medewerker> medewerkerMap = new HashMap<>();
		dbCon.openConnection();
		try {
			String sqlQuery = "SELECT * FROM tblGebruiker";
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String gebruikerID = rs.getString("GebruikerID");
				medewerkerMap.put(gebruikerID, new Medewerker(gebruikerID,rs.getBoolean("Intern")));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbCon.closeConnection();
		}
		
		return medewerkerMap;
	}
	
	public boolean logIn(String naam, String paswoord){
		dbCon.openConnection();
		Boolean ingelogd = false;
		try {
			String sqlQuery = "SELECT * FROM tblGebruiker WHERE gebruikerID = '"+naam+"'AND wachtwoord = '"+paswoord+"'AND intern = 1";
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				ingelogd = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbCon.closeConnection();
		}
		return ingelogd;
	}

}
