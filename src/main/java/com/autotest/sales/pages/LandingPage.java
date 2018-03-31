package com.autotest.sales.pages;

import com.autotest.test.BasePage;
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


public class LandingPage extends BasePage {

    BasePage basePage;

    @FindBy(how = How.ID, using = "menu_admin_viewAdminModule")
    private WebElement adminTab;

    @FindBy(how = How.ID, using = "menu_pim_viewPimModule")
    private WebElement pimTab;

    @FindBy(how = How.ID, using = "menu_leave_viewLeaveModule")
    private WebElement leaveTab;

    @FindBy(how = How.ID, using = "menu_time_viewTimeModule")
    private WebElement timeTab;

    @FindBy(how = How.ID, using = "menu_recruitment_viewRecruitmentModule")
    private WebElement recruitmentTab;

    @FindBy(how = How.ID, using = "menu__Performance")
    private WebElement performanceTab;

    @FindBy(how = How.ID, using = "menu_dashboard_index")
    private WebElement dashboardTab;

    @FindBy(how = How.ID, using = "menu_directory_viewDirectory")
    private WebElement directoryTab;

    @FindBy(how = How.ID, using = "welcome")
    private WebElement userDropDownBtn;

    @FindBy(how = How.ID, using = "welcome-menu")
    private WebElement userDropDownOptions;


    public LandingPage(WebDriver driver) {
        super(driver);
    }

    // go to Admin Page from NavBar
    public AdminPage goToAdminPage() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"menu_admin_viewAdminModule\"]")));
        adminTab.click();
        return new AdminPage(driver);
    }


    /** Select Settngs Drop Down option
     * @param searchText  search text option
     */
    public void selectSettingsDropDownByText(String searchText, WebDriver driver){
        List<WebElement> options = null;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"navbar-content\"]/ul/li[12]")));
        userDropDownBtn.click();
        options = userDropDownOptions.findElements(By.tagName("li"));
        // manage users; faq; my account; logout;
        for (WebElement option : options)
        {
            if (option.getText().equals(searchText))
            {
                option.click(); // click the desired option
                break;
            }
        }
    }

    // Use Generics

//    public <T> T selectSettingsDropDownAndReturnPageObject(String searchText, WebDriver driver) throws InterruptedException {
//
//        T t = null;
//
//        List<WebElement> options = null;
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"navbar-content\"]/ul/li[12]")));
//        settingsDropDownBtn.click();
//        options = settingsDropDownOptions.findElements(By.tagName("li"));
//        if ("manage users".equals(searchText)) {
//            for (WebElement option : options) {
//                if (option.getText().equals(searchText)) {
//                    option.click(); // click the desired option
//                    t = (T) new ManageUsersPage(driver);
//                    break;
//                }
//            }
//        } else if ("faq".equals(searchText)) {
//            for (WebElement option : options) {
//                if (option.getText().equals(searchText)) {
//                    option.click(); // click the desired option
//                    t = (T) new FaqPage(driver);
//                    break;
//                }
//            }
//        } else if ("my account".equals(searchText)) {
//            for (WebElement option : options) {
//                if (option.getText().equals(searchText)) {
//                    option.click(); // click the desired option
//                    t = (T) new MyAccountPage(driver);
//                    break;
//                }
//            }
//        } else if ("logout".equals(searchText)) {
//            for (WebElement option : options) {
//                if (option.getText().equals(searchText)) {
//                    Thread.sleep(5000);
//                    option.click(); // click the desired option
//                    t = (T) new LoginPage(driver);
//                    break;
//                }
//            }
//        }
//        return t;
//    }

  //  Drop Downs wich returns Pages

//    public BasePage selectSettingsDropDownAndReturnPageObject(String searchText, WebDriver driver) throws InterruptedException {
//
//        List<WebElement> options = null;
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"navbar-content\"]/ul/li[12]")));
//        settingsDropDownBtn.click();
//        options = settingsDropDownOptions.findElements(By.tagName("li"));
//        if ("manage users".equals(searchText)) {
//            for (WebElement option : options) {
//                if (option.getText().equals(searchText)) {
//                    option.click(); // click the desired option
//                    basePage = new ManageUsersPage(driver);
//                    break;
//                }
//            }
//        } else if ("faq".equals(searchText)) {
//            for (WebElement option : options) {
//                if (option.getText().equals(searchText)) {
//                    option.click(); // click the desired option
//                    basePage = new FaqPage(driver);
//                    break;
//                }
//            }
//        } else if ("my account".equals(searchText)) {
//            for (WebElement option : options) {
//                if (option.getText().equals(searchText)) {
//                    option.click(); // click the desired option
//                    basePage = new MyAccountPage(driver);
//                    break;
//                }
//            }
//        } else if ("logout".equals(searchText)) {
//            for (WebElement option : options) {
//                if (option.getText().equals(searchText)) {
//                    Thread.sleep(5000);
//                    option.click(); // click the desired option
//                    basePage = new LoginPage(driver);
//                    break;
//                }
//            }
//        }
//        return basePage;
//    }


}
