package main;

import java.sql.ResultSet;
import java.sql.SQLException;


import dbconnection.DBConnection;

public class Main {
	
	static String mySqlQuery=null;
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DBConnection dbCon = new DBConnection();
		mySqlQuery = "SELECT emp_first_name FROM employees WHERE emp_last_name ='Mondal'";
		ResultSet queryResult = dbCon.executeSelectQuery(mySqlQuery);
		
		while(queryResult.next()) {
			System.out.println(queryResult.getString(1));
		}
	
	}
}
