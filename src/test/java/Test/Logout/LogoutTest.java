package Test.Logout;

import Model.Logout.LogoutPageModel;
import org.example.WebDriverService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogoutTest {
    LogoutPageModel logoutPageModel;

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
