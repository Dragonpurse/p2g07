package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import domein.Leertraject;
import domein.Medewerker;
import domein.Onderdeel;

public class LeertrajectMapper extends Mapper<Leertraject> {
	
	private List<Leertraject> ltLijst;
	private List<Medewerker> mLijst;
	private DbConnectie dbCon;
	private Mapper<Onderdeel> om;
	
	public LeertrajectMapper(){
		dbCon = new DbConnectie();
		om = MapperFactory.geefMapper(SoortMapper.ONDERDEEL);
	}
	
	@Override
	public List<Leertraject> geefLijst() {
		ltLijst = new ArrayList<>();
		try {
			String sqlQuery = "SELECT * FROM tblLeertraject";
			dbCon.openConnection();
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
		    ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				Leertraject lt = new Leertraject();
				
				lt.setLeertrajectCode(rs.getString("LeertrajectCode"));
				lt.setTitel(rs.getString("Titel"));
				lt.setOmschrijving(rs.getString("Omschrijving"));
				lt.setStartdatum(LocalDate.fromDateFields(rs.getDate("startDatum")));
				lt.setDoelgroep(rs.getString("Doelgroep"));
				lt.setBeschikbaarheidVan(LocalDate.fromDateFields(rs.getDate("BeschikbaarheidVan")));
				lt.setBeschikbaarheidTot(LocalDate.fromDateFields(rs.getDate("BeschikbaarheidTot")));
				lt.setMedewerkerLijst(geefMedewerkersTraject(rs.getString("LeertrajectCode")));
				lt.setOnderdelenLijst(om.geefLijstPerItem(rs.getString("LeertrajectCode")));
				ltLijst.add(lt);
			}
		    rs.close();
		    pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbCon.closeConnection();
		}
		return ltLijst;
	}
	
	@Override
	public List<Leertraject> geefLijstPerItem(String actorID) {
		ltLijst = new ArrayList<>();
		try {
			String sqlQuery = "SELECT * FROM tblLeertraject WHERE GebruikerID = '"+actorID+"'";
			dbCon.openConnection();
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
		    ResultSet rs = pstmt.executeQuery();
		    
			while(rs.next()){
				Leertraject lt = new Leertraject();
				lt.setLeertrajectCode(rs.getString("LeertrajectCode"));
				lt.setTitel(rs.getString("Titel"));
				lt.setOmschrijving(rs.getString("Omschrijving"));
				lt.setStartdatum(LocalDate.fromDateFields(rs.getDate("startDatum")));
				lt.setDoelgroep(rs.getString("Doelgroep"));
				lt.setBeschikbaarheidVan(LocalDate.fromDateFields(rs.getDate("BeschikbaarheidVan")));
				lt.setBeschikbaarheidTot(LocalDate.fromDateFields(rs.getDate("BeschikbaarheidTot")));
				lt.setMedewerkerLijst(geefMedewerkersTraject(rs.getString("LeertrajectCode")));
				lt.setOnderdelenLijst(om.geefLijstPerItem(rs.getString("LeertrajectCode")));
				ltLijst.add(lt);
			}
		    rs.close();
		    pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbCon.closeConnection();
		}
		return ltLijst;
	}

	@Override
	public void voegItemToe(Leertraject lt){
		try { 
			String sqlQuery = "INSERT INTO tblLeertraject VALUES ('"
							  +lt.getLeertrajectCode()+"','"
		                      +lt.getTitel()+"','"
		                      +lt.getOmschrijving()+"','"
		                      +lt.getStartdatum()+"','"
		                      +lt.getDoelgroep()+"','"
		                      +lt.getBeschikbaarheidVan()+"','"
		                      +lt.getBeschikbaarheidTot()+"')";
			dbCon.openConnection();
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
		    pstmt.executeUpdate();
		    
		    linkMedewerkersTraject(lt.getLeertrajectCode(), lt.getMedewerkerLijst());
		    
		    for(Onderdeel onderdeel : lt.getOnderdelenLijst()){
		    	om.voegItemToe(lt.getLeertrajectCode(), onderdeel);
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
	
	private void linkMedewerkersTraject(String LeertrajectCode, List<Medewerker> medewerkerLijst){
		try {
			for(Medewerker m : medewerkerLijst) {
				String sqlQuery = "INSERT INTO tblLeertrajectGebruiker VALUES ('"
						          +LeertrajectCode+"','"+m.getLogin()+"')";
				dbCon.openConnection();
				Connection con = dbCon.getConn();
				PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			    pstmt.executeUpdate();
					          
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbCon.closeConnection();
		}
	}
	
	private List<Medewerker> geefMedewerkersTraject(String LeertrajectCode){
		mLijst = new ArrayList<>();
		try {
			String sqlQuery = "SELECT * FROM tblGebruiker tg JOIN tblLeertrajectGebruiker tlg ON tlg.GebruikerID = tg.gebruikerID WHERE tlg.LeertrajectCode ='"+LeertrajectCode+"'";
			dbCon.openConnection();
			Connection con = dbCon.getConn();
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				mLijst.add(new Medewerker(rs.getString("GebruikerID"),rs.getBoolean("Intern")));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbCon.closeConnection();
		}
		
		return mLijst;
	}
}
