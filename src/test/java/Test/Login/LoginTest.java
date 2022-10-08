package Test.Login;

import Model.Login.LoginPageModel;
import Model.Login.ProfilePageModel;
import org.example.FileReader;
import org.example.WebDriverService;
import org.junit.jupiter.api.*;

public class LoginTest {
    LoginPageModel loginPageModel;
    ProfilePageModel profilePageModel;

    @BeforeEach
    public void openTab() {
        loginPageModel = new LoginPageModel();
        loginPageModel.goToUrlAndMaximizeWindow("https://jira-auto.codecool.metastage.net/login.jsp?os_destination=%2Fsecure%2FTests.jspa#/design?projectId=10101");
    }

    @AfterEach
    public void closeTab() {
        WebDriverService.getInstance().quitWebDriver();
    }

    @Test
    public void successfulLogin() {

        profilePageModel = new ProfilePageModel();

        Assertions.assertTrue(loginPageModel.getTitle().contains("Welcome to Jira Auto"));

        loginPageModel.login(FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));

        loginPageModel.goToUrlAndMaximizeWindow("https://jira-auto.codecool.metastage.net/secure/ViewProfile.jspa");

        Assertions.assertTrue(profilePageModel.getFullName().contains(FileReader.getValueByKey("jira.displayname")));
    }

    @Test
    public void loginWithInvalidUserName() {

        Assertions.assertTrue(loginPageModel.getTitle().contains("Welcome to Jira Auto"));

        loginPageModel.login("whatever", FileReader.getValueByKey("jira.password"));

        Assertions.assertTrue(loginPageModel.getErrorMsg().contains("Sorry, your username and password are incorrect - please try again."));

        loginPageModel.login(FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));
    }

    @Test
    public void loginWithInvalidPassword() {

        Assertions.assertTrue(loginPageModel.getTitle().contains("Welcome to Jira Auto"));

        loginPageModel.login(FileReader.getValueByKey("jira.username"), "whatever");

        Assertions.assertTrue(loginPageModel.getErrorMsg().contains("Sorry, your username and password are incorrect - please try again."));

        loginPageModel.login(FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));
    }
}
