package Test.Login;

import Model.Login.DashPageModel;
import Model.Login.ProfilePageModel;
import org.example.FileReader;
import org.example.WebDriverService;
import org.junit.jupiter.api.*;

public class LoginTestDashPage {
    DashPageModel dashPageModel;
    ProfilePageModel profilePageModel;

    @BeforeEach
    public void openTab() {
        dashPageModel = new DashPageModel();
        profilePageModel = new ProfilePageModel();
        dashPageModel.goToUrlAndMaximizeWindow("/secure/Dashboard.jspa");
    }

    @AfterEach
    public void closeTab() {
        WebDriverService.getInstance().quitWebDriver();
    }

    @Test
    public void successfulLoginOnDashPage() {
        Assertions.assertTrue(dashPageModel.getDashPageTitle().contains("System Dashboard"));

        dashPageModel.loginOnDashPage(FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));

        dashPageModel.waitUntilElementIsVisible("id", "header-details-user-fullname");
        dashPageModel.goToUrlAndMaximizeWindow("/secure/ViewProfile.jspa");

        dashPageModel.waitUntilElementIsVisible("id", "up-user-title");
        Assertions.assertTrue(profilePageModel.getFullName().contains(FileReader.getValueByKey("jira.displayname")));
    }

    @Test
    public void loginWithInvalidUserName() {
        Assertions.assertTrue(dashPageModel.getDashPageTitle().contains("System Dashboard"));

        dashPageModel.loginOnDashPage("whatever", FileReader.getValueByKey("jira.password"));

        dashPageModel.waitUntilElementIsVisible("xpath", "//*[@id='usernameerror']/p");
        Assertions.assertTrue(dashPageModel.getErrorMessage().contains("Sorry, your username and password are incorrect - please try again."));
    }

    @Test
    public void loginWithInvalidPassword() {
        Assertions.assertTrue(dashPageModel.getDashPageTitle().contains("System Dashboard"));

        dashPageModel.loginOnDashPage(FileReader.getValueByKey("jira.username"), "whatever");

        dashPageModel.waitUntilElementIsVisible("xpath", "//*[@id='usernameerror']/p");
        Assertions.assertTrue(dashPageModel.getErrorMessage().contains("Sorry, your username and password are incorrect - please try again."));

        dashPageModel.loginOnDashPage(FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));
    }
}
