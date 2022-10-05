package Test;

import Model.BrowseProjectModel;
import Model.LoginPageModel;
import org.example.FileReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowseProjectTest {
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

    @ParameterizedTest
    @ValueSource(strings = {"MTP", "JETI", "TOUCAN", "COALA"})
    public void browseProject(String projectType) {
        LoginPageModel loginPageModel = new LoginPageModel(webDriver);
        loginPageModel.login(FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));
        webDriver.get(String.format("https://jira-auto.codecool.metastage.net/projects/%s/summary", projectType));
        BrowseProjectModel browseProjectModel = new BrowseProjectModel(webDriver);

        Assertions.assertTrue(browseProjectModel.getProjectKey().contains(projectType));
    }
}
