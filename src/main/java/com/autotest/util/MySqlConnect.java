package com.autotest.util;

import java.sql.*;
import java.util.Properties;


// Sequel Pro - for Database
// Driver Download - https://dev.mysql.com/downloads/connector/odbc/
public class MySqlConnect {
	// Connection object
	private static Connection conn = null;
	// Statement object
	private static Statement stmt = null;
	// Result Set
	private static ResultSet results = null;
	// Constant for Database URL
	private static String DB_URL = "jdbc:mysql://localhost:3306/mydb"; // Oracle "jdbc:oracle:thin:@localhost:1521/sid"

	// Constant for Database Username
	private static String DB_USER = "root";
	// Constant for Database Password
	private static String DB_PASSWORD = "Sifra123456789";
	// Driver
	private static String driver = "com.mysql.jdbc.Driver"; // "oracle.jdbc.driver.OracleDriver"
	
	// WebDriver
	// public static WebDriver dv;


	public static Statement getStmt() {
		return stmt;
	}

	public Connection openDB() {
		// Intialize WebDriver
		// dv = new FirefoxDriver();
		
		// Properties for creating connection to database
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "Sifra123456789");
	    
		try {
			// STEP 1: Register JDBC driver
			Class.forName(driver).newInstance();
			
			// STEP 2: Get connection to DB
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// conn = DriverManager.getConnection(DB_URL, props);		
			System.out.println("Connected database successfully...");
			
			// STEP 3: Statement object to send the SQL statement to the Database
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeDb() {
		try {
			if (results != null)
				results.close();
			if (stmt != null)
				conn.close();
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}