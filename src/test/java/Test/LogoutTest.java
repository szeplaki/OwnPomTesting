package Test;

import Model.LoginPageModel;
import Model.LogoutPageModel;
import org.example.FileReader;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LogoutTest {
    static WebDriver webDriver;

    @BeforeAll
    public static void setProperty() {
        System.setProperty("webdriver.chrome.driver", FileReader.getValueByKey("driver.location"));
    }

    @BeforeEach
    public void openTab() {
        webDriver = new ChromeDriver();
        webDriver.get("https://jira-auto.codecool.metastage.net/login.jsp?os_destination=%2Fsecure%2FTests.jspa#/design?projectId=10101");
        webDriver.manage().window().maximize();
    }

    @AfterEach
    public void closeTab() {
        webDriver.close();
    }

    @Test
    public void successfulLogout(){
        LoginPageModel loginPageModel = new LoginPageModel(webDriver);
        LogoutPageModel logoutPageModel = new LogoutPageModel(webDriver);

        loginPageModel.login(FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));

        logoutPageModel.logout();
        Assertions.assertTrue(logoutPageModel.getLogoutMsg().contains("You are now logged out. Any automatic login has also been stopped."));
    }

}
