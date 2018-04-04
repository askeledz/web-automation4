package com.autotest.orange.admin;

import com.autotest.sales.pages.AdminPage;
import com.autotest.test.BaseTest;
import com.autotest.util.Config;
import com.autotest.util.MySeleniumMethods;
import com.autotest.util.MySqlConnect;
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

    AdminPage adminPage;

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
        adminPage = salesModuleSubmit(driver);

        //Side bar
        adminPage.submitSearch("Admin");
        String sCell = adminPage.readValueFromTable("1", "2", driver);
        Assert.assertEquals(sCell, "Admin");

        //Connecting MySQL Local and retrieving the data
        conn = new MySqlConnect().openDB();
        stmt = MySqlConnect.getStmt();

        String query =  "SELECT " +
                        "* " +
                        "FROM " +
                        "pet";

        System.out.println(query + ";");

        String name = null;

        try {

            results = stmt.executeQuery(query);

            while (results.next()) {
                //int id = results.getInt("user_id");
                name = results.getString("name");
                String owner = results.getString("owner");
                String species = results.getString("species");
                String birth = results.getString("birth");
                //String death = results.getString("death");

                // Display Values
                System.out.println("name: " + name);
                System.out.println("owner: " + owner);
                System.out.println("species: " + species);
                System.out.println("date: " + birth);

            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }

        //Assert Data from DB and GUI
        Assert.assertEquals(name, sCell);

        //Debug screenshot
        MySeleniumMethods.takeScreenshot(driver, getClass().getSimpleName().toString());

    }

}