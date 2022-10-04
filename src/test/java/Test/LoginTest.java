package Test;

import Model.LoginPageModel;
import Model.ProfilePageModel;
import org.example.FileReader;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    static WebDriver webDriver;
    @BeforeAll
    public static void setProperty(){
        System.setProperty("webdriver.chrome.driver", FileReader.getValueByKey("driver.location"));
    }

    @BeforeEach
    public void openTab() {
        webDriver = new ChromeDriver();
        webDriver.get("https://jira-auto.codecool.metastage.net/login.jsp?os_destination=%2Fsecure%2FTests.jspa#/design?projectId=10101");
        webDriver.manage().window().maximize();
    }

    @AfterEach
    public void closeTab(){ webDriver.close(); }

    @Test
    public void successfulLogin(){
        LoginPageModel loginPageModel = new LoginPageModel(webDriver);
        ProfilePageModel profilePageModel = new ProfilePageModel(webDriver);

        Assertions.assertTrue(loginPageModel.getTitle().contains("Welcome to Jira Auto"));

        loginPageModel.login(FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));

        webDriver.get("https://jira-auto.codecool.metastage.net/secure/ViewProfile.jspa");

        Assertions.assertTrue(profilePageModel.getFullName().contains(FileReader.getValueByKey("jira.displayname")));
    }

    @Test
    public void loginWithInvalidUserName(){
        LoginPageModel loginPageModel = new LoginPageModel(webDriver);

        Assertions.assertTrue(loginPageModel.getTitle().contains("Welcome to Jira Auto"));

        loginPageModel.login("whatever", FileReader.getValueByKey("jira.password"));

        Assertions.assertTrue(loginPageModel.getErrorMsg().contains("Sorry, your username and password are incorrect - please try again."));
    }
}
