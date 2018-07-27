package com.autotest.sales.pages;

import com.autotest.test.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author askeledzija
 */
public class LandingPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")
    private WebElement userTitle;


    public LandingPage(WebDriver driver){
        super(driver);
    }


    /**
     * Read value Total Customers info
     */
    public String extractingUserTitle () {
        String strTitle = null;
        strTitle = userTitle.getText();
        System.out.println("User title: " + strTitle);
        return strTitle;
    }


}

