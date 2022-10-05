package Test;

import Model.DashPageModel;
import Model.ProfilePageModel;
import org.example.FileReader;
import org.example.RandomHelper;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTestDashPage {
    static WebDriver webDriver;

    @BeforeAll
    public static void setProperty() {
        System.setProperty("webdriver.chrome.driver", FileReader.getValueByKey("driver.location"));
    }

    @BeforeEach
    public void openTab() {
        webDriver = new ChromeDriver();
        webDriver.get("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
        webDriver.manage().window().maximize();
    }

    @AfterEach
    public void closeTab() {
        webDriver.close();
    }

    @Test
    public void successfulLoginOnDashPage(){
        DashPageModel dashPageModel = new DashPageModel(webDriver);
        ProfilePageModel profilePageModel = new ProfilePageModel(webDriver);

        Assertions.assertTrue(dashPageModel.getDashPageTitle().contains("System Dashboard"));

        dashPageModel.loginOnDashPage (FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));
        RandomHelper.Wait(webDriver);

        webDriver.get("https://jira-auto.codecool.metastage.net/secure/ViewProfile.jspa");
        RandomHelper.Wait(webDriver);

        Assertions.assertTrue(profilePageModel.getFullName().contains(FileReader.getValueByKey("jira.displayname")));
    }

    @Test
    public void loginWithInvalidUserName() {
        DashPageModel dashPageModel = new DashPageModel(webDriver);

        Assertions.assertTrue(dashPageModel.getDashPageTitle().contains("System Dashboard"));

        dashPageModel.loginOnDashPage("whatever", FileReader.getValueByKey("jira.password"));
        RandomHelper.Wait(webDriver);

        Assertions.assertTrue(dashPageModel.getErrorMessage().contains("Sorry, your username and password are incorrect - please try again."));
    }

    @Test
    public void loginWithInvalidPassword() {
        DashPageModel dashPageModel = new DashPageModel(webDriver);

        Assertions.assertTrue(dashPageModel.getDashPageTitle().contains("System Dashboard"));

        dashPageModel.loginOnDashPage(FileReader.getValueByKey("jira.username"), "whatever");
        RandomHelper.Wait(webDriver);

        Assertions.assertTrue(dashPageModel.getErrorMessage().contains("Sorry, your username and password are incorrect - please try again."));

        dashPageModel.loginOnDashPage(FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));
    }


}
