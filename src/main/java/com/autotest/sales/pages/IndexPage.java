package com.autotest.sales.pages;

import com.autotest.test.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author askeledzija
 */


public class IndexPage extends BasePage {

    BasePage basePage;

    @FindBy(how = How.CSS, using = ".login")
    private WebElement signIn;



    public IndexPage(WebDriver driver) {
        super(driver);
    }

    // go to Login Page from Index page
    public LoginPage goToLoginPage() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".login")));
        signIn.click();
        return new LoginPage(driver);
    }
}
