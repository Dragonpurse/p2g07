package persistentie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import domein.Leertraject;

public class LeertrajectMapper extends Mapper<Leertraject> {
	
	private List<Leertraject> ltLijst;
	private DbConnectie dbCon;
	private Statement stmt;
	private ResultSet rs;
	private String sqlQuery;
	
	public LeertrajectMapper(){
		dbCon = new DbConnectie();
		}
	
	@Override
	public List<Leertraject> geefLijst() {
		ltLijst = new ArrayList<>();
		sqlQuery = "SELECT * FROM tblLeertraject";
		dbCon.openConnection();
		Connection con = dbCon.getConn();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while(rs.next()){
				Leertraject lt = new Leertraject(rs.getString("LeertrajectCode"),
						                         rs.getString("Titel"), 
						                         rs.getString("Omschrijving"),
						                         LocalDate.fromDateFields(rs.getDate("startDatum")),
						                         rs.getString("Doelgroep"),
						                         LocalDate.fromDateFields(rs.getDate("BeschikbaarheidVan")),
						                         LocalDate.fromDateFields(rs.getDate("BeschikbaarheidTot")),
						                         rs.getString("ExterneMedewerker"));
				ltLijst.add(lt);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			dbCon.closeConnection();
		}
		return ltLijst;
	}
	
	@Override
	public List<Leertraject> geefLijstPerItem(String actorID) {
		ltLijst = new ArrayList<>();
		sqlQuery = "SELECT * FROM tblLeertraject WHERE GebruikerID = '"+actorID+"'";
		dbCon.openConnection();
		Connection con = dbCon.getConn();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while(rs.next()){
				Leertraject lt = new Leertraject(rs.getString("LeertrajectCode"),
						                         rs.getString("Titel"), 
						                         rs.getString("Omschrijving"),
						                         LocalDate.fromDateFields(rs.getDate("startDatum")),
						                         rs.getString("Doelgroep"),
						                         LocalDate.fromDateFields(rs.getDate("BeschikbaarheidVan")),
						                         LocalDate.fromDateFields(rs.getDate("BeschikbaarheidTot")),
						                         rs.getString("ExterneMedewerker"));
				ltLijst.add(lt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			dbCon.closeConnection();
		}
		return ltLijst;
	}

	@Override
	public void voegItemToe(String actorID, Leertraject lt){
		sqlQuery = "INSERT INTO tblLeertraject VALUES ('"
		          +lt.getLeertrajectCode()+"','"
		          +actorID+"','"
		          +lt.getTitel()+"','"
		          +lt.getOmschrijving()+"','"
		          +lt.getStartdatum()+"','"
		          +lt.getDoelgroep()+"','"
		          +lt.getBeschikbaarheidVan()+"','"
		          +lt.getBeschikbaarheidTot()+"','"
		          +lt.getExterneMedewerker()+"')";
		          
		dbCon.openConnection();
		Connection con = dbCon.getConn();
		try {
			stmt = con.createStatement();
			stmt.execute(sqlQuery);
		} catch (SQLException e) {
			if(e.getSQLState().equals("23000")){
				throw new IllegalArgumentException("Deze leertrajectcode bestaat al.");
			}
		}
		finally{
			dbCon.closeConnection();
		}
	}
}
