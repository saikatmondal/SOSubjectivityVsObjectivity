package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	
	private static final String CONNECTIONSTRING = "jdbc:mysql://localhost:3306/socodequalitydb?useSSL=false";
	private static final String USERNAME = "root";
	private static final String KEY = "AlokitoBangla@2018";
	private static Connection myCon=null;
	private static Statement myStmt=null;
	
	public Statement getDBConnection(){
		
		try {
			myCon = DriverManager.getConnection(CONNECTIONSTRING,USERNAME,KEY);
			myStmt = myCon.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return myStmt;
		
	}
	
	public ResultSet executeSelectQuery (String query) {
		//getDBConnection();
		ResultSet myResult=null;
		try {
			
			myResult = myStmt.executeQuery(query);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return myResult;
	}
	
	public void executeInsertDeleteQuery (String query) {
		//getDBConnection();
		try {
			
			myStmt.executeUpdate(query);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
}
