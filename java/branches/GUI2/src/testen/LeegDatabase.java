package testen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import persistentie.DbConnectie;

public class LeegDatabase {
	
	private LeegDatabase(){	}
	
	
	
	public static void leegDatabase() {
		DbConnectie dbCon = new DbConnectie();
		dbCon.openConnection();
		try {
				String sqlQuery = "EXEC sp_MSForEachTable 'ALTER TABLE ? NOCHECK CONSTRAINT ALL'";
				Connection con = dbCon.getConn();
				PreparedStatement pstmt = con.prepareStatement(sqlQuery);
				pstmt.execute();
				
				sqlQuery = "EXEC sp_MSForEachTable 'DELETE FROM ?'";
				pstmt = con.prepareStatement(sqlQuery);
				pstmt.execute();
				
				sqlQuery = "EXEC sp_MSForEachTable 'ALTER TABLE ? CHECK CONSTRAINT ALL'";
				pstmt = con.prepareStatement(sqlQuery);
				pstmt.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			dbCon.closeConnection();
		}
	}
}
