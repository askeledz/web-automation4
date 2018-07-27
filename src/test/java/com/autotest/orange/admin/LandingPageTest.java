package com.autotest.orange.admin;

import com.autotest.sales.pages.IndexPage;
import com.autotest.sales.pages.LandingPage;
import com.autotest.sales.pages.LoginPage;
import com.autotest.test.BaseTest;
import com.autotest.util.Config;
import com.autotest.util.MySeleniumMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LandingPageTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LandingPageTest.class);
    private static WebDriver driver = null;

    IndexPage indexPage;
    LoginPage loginPage;
    LandingPage landingPage;

    @BeforeTest
    public void doBeforeTest() {

    }

    @Parameters
    @Test(description = "Load Landing Page", groups = {"checkintest"})
    public void pageIsLoaded() throws Exception {
        logger.info("Test class = " + this.getClass().getSimpleName());
        logger.info("Test method = " + Thread.currentThread().getStackTrace()[1].getMethodName());
        driver = invokeBrowser(Config.USER_URL);

        loginPage = signInSubmit(driver);

        landingPage = loginPage.signIn();

        //Debug screenshot
        MySeleniumMethods.takeScreenshot(driver,getClass().getSimpleName().toString());

    }

}
