package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class DbConnectie {
	private static String USERNAME = "admin";
	private static String PASSWORD = "admin";
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String URL = "jdbc:sqlserver://localhost;databaseName=p2g7;user=admin;password=admin";
	private Connection conn;
	
	public Connection getConn() {
		return conn;
	}

	public void openConnection(){
		try {	
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL);
		}
		catch(SQLException e){
			System.out.println("Errorcode: " + e.getErrorCode() + "\nSQLState :" + e.getSQLState());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getStackTrace());
		}
		
	}
	
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Errorcode: " + e.getErrorCode() + "\nSQLState :" + e.getSQLState());
		}
	}
}