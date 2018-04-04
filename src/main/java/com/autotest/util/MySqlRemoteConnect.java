package com.autotest.util;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.*;

public class MySqlRemoteConnect {


    private static Statement stmt = null;
    private static Connection conn = null;
    private static ResultSet results = null;

    public static Statement getStmt() {
        return stmt;
    }

    public static void portForwarding() {

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(Config.SSH_USER, Config.SSH_HOST, 22);
            session.setPassword(Config.SSH_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            int assinged_port = session.setPortForwardingL(1234, "localhost", 3306);
            System.out.println("localhost:" + assinged_port + " -> " + 1234 + ":" + 3306);
        } catch (Exception e) {
            System.err.print(e);
        }
    }

    public Connection connectDB() {
        try {

            portForwarding();

            System.out.println("Retrieving data from Mysql Database ....");

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