package com.autotest.orange.admin;

import com.autotest.sales.pages.LandingPage;
import com.autotest.sales.pages.LoginPage;
import com.autotest.test.BaseTest;
import com.autotest.util.Config;
import com.autotest.util.MySeleniumMethods;
import com.autotest.util.MySqlConnect;
import com.autotest.util.Queries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlLocalTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(MySqlLocalTest.class);
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
    @Test(description = "MySQL Local Example", groups = {"checkintest"})
    public void compareTheDataRetrievedOnTheUIandDatabase() throws Exception {
        logger.info("Test class = " + this.getClass().getSimpleName());
        logger.info("Test method = " + Thread.currentThread().getStackTrace()[1].getMethodName());
        driver = invokeBrowser(Config.USER_URL);

        loginPage = signInSubmit(driver);

        landingPage = loginPage.signIn();

        //Connecting MySQL Local and retrieving the data
        conn = new MySqlConnect().openDB();
        stmt = MySqlConnect.getStmt();

        //PersonID, FirstName, LastName, Address, City
        int personID;
        String firstName = null;
        String lastName = null;
        String address = null;
        String city = null;

        try {


            System.out.println(Queries.getQuery("ltest_query"));
            results = stmt.executeQuery(Queries.getQuery("ltest_query"));

            while (results.next()) {
                //int id = results.getInt("user_id");
                personID = results.getInt("PersonID");
                firstName = results.getString("FirstName");
                lastName = results.getString("LastName");
                address = results.getString("Address");
                city = results.getString("City");

                // Display Values
                System.out.println("PersonID: " + personID);
                System.out.println("FirstName: " + firstName);
                System.out.println("LastName: " + lastName);
                System.out.println("Address: " + address);
                System.out.println("City: " + city);

            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }

        //Assert Data from DB and GUI

        Assert.assertEquals(landingPage.extractingUserTitle(), firstName+ " " +lastName);

        //Debug screenshot
        MySeleniumMethods.takeScreenshot(driver, getClass().getSimpleName().toString());

    }

}
