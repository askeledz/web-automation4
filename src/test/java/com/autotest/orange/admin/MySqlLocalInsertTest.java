package com.autotest.orange.admin;

import com.autotest.sales.pages.LandingPage;
import com.autotest.sales.pages.LoginPage;
import com.autotest.test.BaseTest;
import com.autotest.util.Config;
import com.autotest.util.MySqlConnect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySqlLocalInsertTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(MySqlLocalInsertTest.class);
    private static WebDriver driver = null;
    private static ResultSet results = null;

    Connection conn;
    Statement stmt;

    LoginPage loginPage;
    LandingPage landingPage;

    @BeforeTest
    public void doBeforeTest() {

    }

    @AfterClass
    public void afterClass() {
        //Close DB connection if any
        MySqlConnect.closeDb();
    }

    @Parameters
    @Test(description = "MySQL Local INSERT Example", groups = {"checkintest"})
    public void insertDataToDatabase() throws Exception {
        logger.info("Test class = " + this.getClass().getSimpleName());
        logger.info("Test method = " + Thread.currentThread().getStackTrace()[1].getMethodName());
        driver = invokeBrowser(Config.USER_URL);

        loginPage = signInSubmit(driver);

        landingPage = loginPage.signIn();


        try {
            //Connecting MySQL Local and retrieving the data
            conn = new MySqlConnect().openDB();
            //stmt = MySqlConnect.getStmt();

            /// the mysql insert statement
            String query = "INSERT INTO Persons (PersonID, FirstName, LastName, Address, City ) VALUES (?,?,?,?,?);";


            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, "1");
            preparedStmt.setString(2, "Andrej");
            preparedStmt.setString(3, "Skeledzija");
            preparedStmt.setString(4, "I.G. 61B");
            preparedStmt.setString(5, "Osijek");


            // execute the preparedstatement
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
            System.out.println("Got an exception!");
            System.out.println(e.getMessage());
        }
    }
}

