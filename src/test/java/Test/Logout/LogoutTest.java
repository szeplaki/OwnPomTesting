package Test.Logout;

import Model.Logout.LogoutPageModel;
import org.example.FileReader;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LogoutTest {
    static WebDriver webDriver;
    LogoutPageModel logoutPageModel;

    @BeforeAll
    public static void setProperty() {
        System.setProperty("webdriver.chrome.driver", FileReader.getValueByKey("driver.location"));
    }

    @BeforeEach
    public void openTab() {
        webDriver = new ChromeDriver();
        webDriver.get("https://jira-auto.codecool.metastage.net/login.jsp?os_destination=%2Fsecure%2FTests.jspa#/design?projectId=10101");
        webDriver.manage().window().maximize();
        logoutPageModel = new LogoutPageModel(webDriver);
        logoutPageModel.doLogin();
    }

    @AfterEach
    public void closeTab() {
        webDriver.close();
    }

    @Test
    public void successfulLogout(){
        logoutPageModel.logout();
        Assertions.assertTrue(logoutPageModel.getLogoutMsg().contains("You are now logged out. Any automatic login has also been stopped."));
    }

}
