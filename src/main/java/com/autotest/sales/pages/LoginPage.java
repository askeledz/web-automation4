package com.autotest.sales.pages;

import com.autotest.test.BasePage;
import com.autotest.util.Config;
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

    @FindBy(how = How.ID, using = "email")
    private WebElement input_email_box;

    @FindBy(how = How.ID, using = "passwd")
    private WebElement input_password_box;


    @FindBy(how = How.ID, using = "SubmitLogin")
    private WebElement loginInButton;


    public LoginPage(WebDriver driver){
        super(driver);
    }


    public boolean isEmailBoxVisible() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(input_email_box));
        return input_email_box.isDisplayed();
    }

    public boolean isPasswordBoxVisible() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(input_password_box));
        return input_password_box.isDisplayed();
    }

    public void inputEmail(String email) {
        isEmailBoxVisible();
        input_email_box.sendKeys(email);
    }

    public void inputPassword(String pass) {
        isPasswordBoxVisible();
        input_password_box.sendKeys(pass);
    }

    public LandingPage signIn() throws InterruptedException {
        inputEmail(Config.USER_MAIL);
        inputPassword(Config.USER_PASSWORD);
        loginInButton.click();
        Thread.sleep(3000);
        return new LandingPage(driver);
    }


}

