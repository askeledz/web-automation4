package com.autotest.orange.admin;

import com.autotest.sales.pages.AdminPage;
import com.autotest.sales.pages.LandingPage;
import com.autotest.test.BaseTest;
import com.autotest.util.Config;
import com.autotest.util.MySeleniumMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AdminPageTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(AdminPageTest.class);
    private static WebDriver driver = null;

    LandingPage landingPage;
    AdminPage adminPage;

    @BeforeTest
    public void doBeforeTest() {

    }

    @Parameters
    @Test(description = "Load Admin Page", groups = {"checkintest"})
    public void pageIsLoaded() throws Exception {
        logger.info("Test class = " + this.getClass().getSimpleName());
        logger.info("Test method = " + Thread.currentThread().getStackTrace()[1].getMethodName());
        driver = invokeBrowser(Config.USER_URL);
        adminPage = salesModuleSubmit(driver);

        //Side bar
        adminPage.submitSearch("Admin");
        Assert.assertEquals(adminPage.readValueFromTable("1", "2", driver), "Admin");

        //Debug screenshot
        MySeleniumMethods.takeScreenshot(driver,getClass().getSimpleName().toString());

    }

}
