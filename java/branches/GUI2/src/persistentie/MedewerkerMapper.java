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
			String sqlQuery = "SELECT * FROM tblMedewerker";
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String email = rs.getString("Email");
				String naam = rs.getString("Naam");
				String voornaam = rs.getString("Voornaam");
				String telefoon = rs.getString("TelefoonNr");
				String organisatie = rs.getString("Organisatie");
				Boolean intern = rs.getBoolean("Intern");
				medewerkerMap.put(email, new Medewerker(email,naam,voornaam,telefoon,organisatie,intern));
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
			String sqlQuery = "SELECT * FROM tblMedewerker WHERE Email = '"+naam+"'AND wachtwoord = '"+paswoord+"'AND intern = 1";
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
	
	@Override
	public void voegItemToe(Medewerker m) {
		dbCon.openConnection();
		try { 
			String sqlQuery = "INSERT INTO tblMedewerker  (Email,Voornaam,Naam,TelefoonNr,Organisatie,Intern) VALUES ('"
							  +m.getLogin()+"','"
		                      +m.getVoornaam()+"','"
		                      +m.getNaam()+"','"
		                      +m.getTelefoon()+"','"
		                      +m.getOrganisatie()+"','"
		                      +m.getIntern()+"')";
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
		    pstmt.execute();

		    pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			dbCon.closeConnection();
		}
	}

}
