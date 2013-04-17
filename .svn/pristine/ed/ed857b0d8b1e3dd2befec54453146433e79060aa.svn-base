package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import domein.Leertraject;
import domein.Medewerker;
import domein.Onderdeel;
import domein.builders.LeertrajectBuilder;

public class LeertrajectMapper extends Mapper<Leertraject> {
	
	private List<Leertraject> leertrajectLijst;
	private DbConnectie dbCon;
	private Mapper<Onderdeel> om;
	
	public LeertrajectMapper(){
		dbCon = new DbConnectie();
		om = MapperFactory.geefMapper(SoortMapper.ONDERDEEL);
	}
	
	public List<Leertraject> geefLijst(Map<String, Medewerker> medewerkerMap) {
		leertrajectLijst = new ArrayList<>();
		dbCon.openConnection();
		try {
			String sqlQuery = "SELECT * FROM tblLeertraject";
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
		    ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				LeertrajectBuilder ltBuilder = new LeertrajectBuilder();
				ltBuilder.createNewLeertraject();
				ltBuilder.buildLeertrajectCode(rs.getString("LeertrajectCode"));
				ltBuilder.buildTitel(rs.getString("Titel"));
				ltBuilder.buildOmschrijving(rs.getString("Omschrijving"));
				ltBuilder.buildStartdatum(LocalDate.fromDateFields(rs.getDate("startDatum")));
				ltBuilder.buildDoelgroep(rs.getString("Doelgroep"));
				ltBuilder.buildBeschikbaarVan(LocalDate.fromDateFields(rs.getDate("BeschikbaarheidVan")));
				ltBuilder.buildBeschikbaarTot(LocalDate.fromDateFields(rs.getDate("BeschikbaarheidTot")));
				ltBuilder.buildMedewerkerMap(geefMedewerkersTraject(rs.getString("LeertrajectCode"),medewerkerMap));
				ltBuilder.buildOnderdelenLijst(om.geefLijstPerItem(rs.getString("LeertrajectCode")));
				leertrajectLijst.add(ltBuilder.getLeertraject());
			}
		    rs.close();
		    pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbCon.closeConnection();
		}
		return leertrajectLijst;
	}
	
	public List<Leertraject> geefLijstPerItem(String actorID, Map<String, Medewerker> medewerkerMap) {
		leertrajectLijst = new ArrayList<>();
		dbCon.openConnection();
		try {
			String sqlQuery = "SELECT * FROM tblLeertraject WHERE GebruikerID = '"+actorID+"'";
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
		    ResultSet rs = pstmt.executeQuery();
		    
			while(rs.next()){
				LeertrajectBuilder ltBuilder = new LeertrajectBuilder();
				ltBuilder.createNewLeertraject();
				ltBuilder.buildLeertrajectCode(rs.getString("LeertrajectCode"));
				ltBuilder.buildTitel(rs.getString("Titel"));
				ltBuilder.buildOmschrijving(rs.getString("Omschrijving"));
				ltBuilder.buildStartdatum(LocalDate.fromDateFields(rs.getDate("startDatum")));
				ltBuilder.buildDoelgroep(rs.getString("Doelgroep"));
				ltBuilder.buildBeschikbaarVan(LocalDate.fromDateFields(rs.getDate("BeschikbaarheidVan")));
				ltBuilder.buildBeschikbaarTot(LocalDate.fromDateFields(rs.getDate("BeschikbaarheidTot")));
				ltBuilder.buildMedewerkerMap(geefMedewerkersTraject(rs.getString("LeertrajectCode"),medewerkerMap));
				ltBuilder.buildOnderdelenLijst(om.geefLijstPerItem(rs.getString("LeertrajectCode")));
				leertrajectLijst.add(ltBuilder.getLeertraject());
			}
		    rs.close();
		    pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbCon.closeConnection();
		}
		return leertrajectLijst;
	}

	@Override
	public void voegItemToe(Leertraject lt){
		dbCon.openConnection();
		try { 
			String sqlQuery = "INSERT INTO tblLeertraject VALUES ('"
							  +lt.getLeertrajectCode()+"','"
		                      +lt.getTitel()+"','"
		                      +lt.getOmschrijving()+"','"
		                      +lt.getStartdatum()+"','"
		                      +lt.getDoelgroep()+"','"
		                      +lt.getBeschikbaarVan()+"','"
		                      +lt.getBeschikbaarTot()+"')";
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
		    pstmt.executeUpdate();
		    
		    linkMedewerkersTraject(lt.getLeertrajectCode(), lt.getMedewerkerMap());
		    
			Iterator<Onderdeel> it = lt.getOnderdelenLijst().iterator();
			while(it.hasNext()) {
		    	om.voegItemToe(lt.getLeertrajectCode(), it.next());
		    } 
		    pstmt.close();
		} catch (SQLException e) {
			if(e.getSQLState().equals("23000")){
				throw new IllegalArgumentException("Deze leertrajectcode bestaat al.");
			}
		}
		finally{
			dbCon.closeConnection();
		}
	}
	
	private void linkMedewerkersTraject(String LeertrajectCode, Map<String, Medewerker> medewerkerMap){
		try {
			for(Medewerker m : medewerkerMap.values()) {
				String sqlQuery = "INSERT INTO tblLeertrajectGebruiker VALUES ('"
						          +LeertrajectCode+"','"+m.getLogin()+"')";
				Connection con = dbCon.getConn();
				PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			    pstmt.executeUpdate();
					          
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, Medewerker> geefMedewerkersTraject(String LeertrajectCode, Map<String, Medewerker> medewerkerMap){
		Map<String, Medewerker> medewerkerMapTraject = new HashMap<>();
		try {
			String sqlQuery = "SELECT * FROM tblGebruiker tg JOIN tblLeertrajectGebruiker tlg ON tlg.GebruikerID = tg.gebruikerID WHERE tlg.LeertrajectCode ='"+LeertrajectCode+"'";
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String gebruikerID = rs.getString("gebruikerID");
				medewerkerMapTraject.put(gebruikerID, medewerkerMap.get(gebruikerID));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return medewerkerMapTraject;
	}
}
