package com.autotest.test;

import com.autotest.driver.DriverManager;
import com.autotest.sales.pages.AdminPage;
import com.autotest.sales.pages.LandingPage;
import com.autotest.sales.pages.LoginPage;
import com.autotest.util.Config;
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
    AdminPage adminPage;

    protected WebDriver invokeBrowser(String url) {
        WebDriver driver = DriverManager.getDriver();
         logger.info("START: " + DriverManager.getBrowserInfo());
         logger.info("Thread id = " + Thread.currentThread().getId());
         logger.info("Driver instance = " + driver.hashCode());
        driver.get(url);
        return driver;
    }

    protected AdminPage salesModuleSubmit(WebDriver driver) throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.inputEmail(Config.USER_NAME);
        loginPage.inputPassword(Config.USER_PASSWORD);
        landingPage = loginPage.signIn();
        adminPage = landingPage.goToAdminPage();
        return adminPage;
    }
}
