package com.autotest.driver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Author: askeledzija
 * It's a generic WebDriver manager, it works with local and remote instances of WebDriver
 */

public class DriverManager {

    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();



    public static WebDriver getDriver() {
        logger.debug("Getting instance of remote driver");
        return webDriver.get();
    }

    static void setWebDriver(WebDriver driver) {
        logger.debug("Setting the driver");
        webDriver.set(driver);
    }

    /**
     * Returns a string containing current browser name, its version and OS name. This method is used in the the
     * *WebDriverListeners to change the test name.
     */
    public static String getBrowserInfo() {
        logger.debug("Getting browser info");
        Capabilities cap = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        String b = cap.getBrowserName();
        String os = cap.getPlatform().toString();
        String v = cap.getVersion();
        return String.format("%s v:%s %s", b, v, os);
    }
}