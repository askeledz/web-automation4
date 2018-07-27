package com.autotest.test;

import com.autotest.driver.DriverManager;
import com.autotest.sales.pages.IndexPage;
import com.autotest.sales.pages.LandingPage;
import com.autotest.sales.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Created by Andrej Skeledzija 2017
 */
public class BaseTest {

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    LoginPage loginPage;
    LandingPage landingPage;
    IndexPage indexPage;

    protected WebDriver invokeBrowser(String url) {
        WebDriver driver = DriverManager.getDriver();
         logger.info("START: " + DriverManager.getBrowserInfo());
         logger.info("Thread id = " + Thread.currentThread().getId());
         logger.info("Driver instance = " + driver.hashCode());
        driver.get(url);
        return driver;
    }

    protected LoginPage signInSubmit(WebDriver driver) throws InterruptedException {
        indexPage = new IndexPage(driver);
        return loginPage = indexPage.goToLoginPage();
    }
}
