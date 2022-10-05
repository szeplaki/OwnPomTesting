package Test.Logout;

import Model.Logout.LogoutPageModel;
import org.example.FileReader;
import org.example.WebDriverService;
import org.junit.jupiter.api.*;

public class LogoutTest {
    LogoutPageModel logoutPageModel;

    @BeforeAll
    public static void setProperty() {
        System.setProperty("webdriver.chrome.driver", FileReader.getValueByKey("driver.location"));
    }

    @BeforeEach
    public void openTab() {
        logoutPageModel = new LogoutPageModel();
        logoutPageModel.goToUrlAndMaximizeWindow("https://jira-auto.codecool.metastage.net/login.jsp?os_destination=%2Fsecure%2FTests.jspa#/design?projectId=10101");
        logoutPageModel.doLogin();
    }

    @AfterEach
    public void closeTab() {
        WebDriverService.getInstance().quitWebDriver();
    }

    @Test
    public void successfulLogout() {
        logoutPageModel.logout();
        Assertions.assertTrue(logoutPageModel.getLogoutMsg().contains("You are now logged out. Any automatic login has also been stopped."));
    }
}
