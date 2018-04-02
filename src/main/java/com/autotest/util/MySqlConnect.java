package com.autotest.util;

import java.sql.*;

// Sequel Pro - for Database
// Driver Download - https://dev.mysql.com/downloads/connector/odbc/
public class MySqlConnect {
	// Connection object
	private static Connection conn = null;
	// Statement object
	private static Statement stmt = null;
	// Result Set
	private static ResultSet results = null;

	public static Statement getStmt() {
		return stmt;
	}

	public Connection openDB() {
		try {
			// STEP 1: Register JDBC driver
			Class.forName(Config.MYSQL_DRIVER).newInstance();
			
			// STEP 2: Get connection to DB
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(Config.MYSQL_DBURL, Config.MYSQL_USER, Config.MYSQL_PASSWORD);
			System.out.println("Connected database successfully...");

			// STEP 3: Statement object to send the SQL statement to the Database
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeDb() {
		try {
			if (results != null)
				results.close();
			System.out.println("results.close();");
			if (stmt != null)
				conn.close();
			System.out.println("stmt - conn.close();");
			if (conn != null)
				conn.close();
			System.out.println("conn.close();");
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}
}
