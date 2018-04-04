package com.autotest.orange.admin;

import com.autotest.sales.pages.AdminPage;
import com.autotest.test.BaseTest;
import com.autotest.util.Config;
import com.autotest.util.MySqlRemoteConnect;
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

public class MySqlRemoteTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(MySqlRemoteTest.class);
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
        MySqlRemoteConnect.closeDb();
    }

    @Parameters
    @Test(description = "MySQL Remote Example", groups = {"checkintest"})
    public void compareTheDataRetrievedOnTheUIandDatabase() throws Exception {
        logger.info("Test class = " + this.getClass().getSimpleName());
        logger.info("Test method = " + Thread.currentThread().getStackTrace()[1].getMethodName());
        driver = invokeBrowser(Config.USER_URL);
        adminPage = salesModuleSubmit(driver);

        //Side bar
        adminPage.submitSearch("Admin");
        String sCell = adminPage.readValueFromTable("1", "2", driver);
        Assert.assertEquals(sCell, "Admin");

        //Connecting to MySQL db and retrieving the data
        conn = new MySqlRemoteConnect().connectDB();
        stmt = MySqlRemoteConnect.getStmt();

        int id;
        String name = null;
        String sql = "select * from users";

        try {

            results = stmt.executeQuery(sql);

            while (results.next()) {
                id = results.getInt("id");
                if (id==1) {
                    name = results.getString("name");
                    // Display Values
                    System.out.println("id: " + id);
                    System.out.println("name: " + name);
                } else {
                    break;
                }
            }
            results.close();

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }

        Assert.assertEquals(name, sCell);

    }
}
