package Test.BrowseIssue;

import Model.BrowseIssue.BrowseIssueModel;
import org.example.FileReader;
import org.example.WebDriverService;
import org.junit.jupiter.api.*;

public class BrowseIssueTest {
    BrowseIssueModel browseIssueModel;

    @BeforeAll
    public static void setProperty() {
        System.setProperty("webdriver.chrome.driver", FileReader.getValueByKey("driver.location"));
    }

    @BeforeEach
    public void openNewTab() {
        browseIssueModel = new BrowseIssueModel();
        browseIssueModel.goToUrlAndMaximizeWindow("https://jira-auto.codecool.metastage.net/login.jsp?os_destination=%2Fsecure%2FTests.jspa#/design?projectId=10101");
        browseIssueModel.doLogin();
    }

    @AfterEach
    public void closeTab() {
        WebDriverService.getInstance().quitWebDriver();
    }

    @Test
    public void browseExistingIssue() {
        browseIssueModel.goToUrlAndMaximizeWindow("https://jira-auto.codecool.metastage.net/browse/MTP-2253");
        Assertions.assertEquals("MTP-2253",browseIssueModel.getIssueId());
    }

    @Test
    public void checkPossibilityOfBrowsing(){
        String expectedKey = "MTP-2245";
        browseIssueModel.goToUrlAndMaximizeWindow("https://jira-auto.codecool.metastage.net/issues/?jql=");
        browseIssueModel.getSearchField().click();
        browseIssueModel.getSearchField().sendKeys("Jira Test Project");
        browseIssueModel.getSearchButton().click();
        Assertions.assertTrue(browseIssueModel.waitUntilKeyIsVisible(expectedKey));
    }
}
