package com.autotest.sales.pages;

import com.autotest.test.BasePage;
import com.autotest.util.MySeleniumMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author askeledzija
 */

public class AdminPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(AdminPage.class);

    @FindBy(how = How.XPATH, using = "//*[@id=\"searchSystemUser_userName\"]")
    private WebElement userNameTextBox;

    @FindBy(how = How.ID, using = "searchBtn")
    private WebElement searchBtn;

    @FindBy(how = How.ID, using = "tableWrapper")
    private WebElement systemUsersTable;

    @FindBy(how = How.ID, using = "searchSystemUser_status")
    private WebElement userStatusDropDown;

    public AdminPage(WebDriver driver) {
        super(driver);
    }


    public void submitSearch(String searchTerm) throws InterruptedException {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(userNameTextBox));
        userNameTextBox.clear();
        userNameTextBox.sendKeys(searchTerm);
        searchBtn.click();
        // IN case progress bar covers the page we should use following -->
        // WebDriverWait wait = new WebDriverWait(driver, 10);
        // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"screen-load\"]")));
    }

    public String readValueFromTable( String sRow, String sCol, WebDriver driver) throws Exception {
        //Scroll to element
        MySeleniumMethods.scrollToElement(systemUsersTable, driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"screen-load\"]")));
        wait.until(ExpectedConditions.visibilityOf(systemUsersTable));
        String sCellValue = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[" + sRow + "]/td[" + sCol + "]")).getText();
        System.out.println(sCellValue);
        return sCellValue;
    }


    /** Select drop down
     * @param optionIndex  search option by index
     */
    public void selectDropDownOptionByIndex(int optionIndex, WebDriver driver){

        List<WebElement> optionList = null;

        optionList = userStatusDropDown.findElements(By.tagName("option"));

        optionList.get(optionIndex).click();

    }

}
