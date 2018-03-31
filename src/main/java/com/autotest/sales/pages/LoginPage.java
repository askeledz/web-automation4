package com.autotest.sales.pages;

import com.autotest.test.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author askeledzija
 */
public class LoginPage extends BasePage {

    @FindBy(how = How.ID, using = "txtUsername")
    private WebElement input_username_box;

    @FindBy(how = How.ID, using = "txtPassword")
    private WebElement input_password_box;


    @FindBy(how = How.ID, using = "btnLogin")
    private WebElement loginInButton;


    public LoginPage(WebDriver driver){
        super(driver);
    }


    public boolean isEmailBoxVisible() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(input_username_box));
        return input_username_box.isDisplayed();
    }

    public boolean isPasswordBoxVisible() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(input_password_box));
        return input_password_box.isDisplayed();
    }

    public void inputEmail(String email) {
        isEmailBoxVisible();
        input_username_box.sendKeys(email);
    }

    public void inputPassword(String pass) {
        isPasswordBoxVisible();
        input_password_box.sendKeys(pass);
    }

    public LandingPage signIn() throws InterruptedException {
        loginInButton.click();
        Thread.sleep(3000);
        return new LandingPage(driver);
    }


}

