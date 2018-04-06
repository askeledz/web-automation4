package com.autotest.driver;


import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Author: askeledzija
 * It's a generic WebDriver manager, it works with local and remote instances of WebDriver
 */

public class RemoteDriver {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(RemoteDriver.class);

    static RemoteWebDriver createInstance(String browserName) {
        URL hubUrl = null;
        try {
            hubUrl = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return RemoteDriver.createInstance(hubUrl, browserName);
    }

    static RemoteWebDriver createInstance(URL hubUrl, String browserName) {
        RemoteWebDriver driver = null;

        if (browserName.equalsIgnoreCase("firefox")) {
            DesiredCapabilities capability = DesiredCapabilities.firefox();
            driver = new RemoteWebDriver(hubUrl, capability);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return driver;
        }
        if (browserName.equalsIgnoreCase("chrome")) {
            DesiredCapabilities capability = DesiredCapabilities.chrome();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            // options.addArguments("--start-maximized");
            // options.addArguments("--kiosk");

            capability.setCapability(ChromeOptions.CAPABILITY, options);
            driver = new RemoteWebDriver(hubUrl, capability);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return driver;
        }
        if (browserName.equalsIgnoreCase("ie")) {
            DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
            driver = new RemoteWebDriver(hubUrl, capability);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return driver;
        }
        if (browserName.equalsIgnoreCase("safari")) {
            DesiredCapabilities capability = DesiredCapabilities.safari();
            driver = new RemoteWebDriver(hubUrl, capability);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return driver;
        }

        if (browserName.equalsIgnoreCase("edge")) {
            DesiredCapabilities capability = DesiredCapabilities.edge();
//
//            EdgeOptions options = new EdgeOptions();
//
//            capability.setCapability(EdgeOptions.CAPABILITY, options);
//            capability.setVersion("ANY");
            driver = new RemoteWebDriver(hubUrl, capability);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return driver;
        }

        //logger.info("Browser name: " + browserName);
        return driver;
    }
}
