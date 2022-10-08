package Test.BrowseIssue;

import Model.BrowseIssue.BrowseIssueModel;
import org.example.WebDriverService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BrowseIssueTest {
    BrowseIssueModel browseIssueModel;

    @BeforeEach
    public void openNewTab() {
        browseIssueModel = new BrowseIssueModel();
        browseIssueModel.goToUrlAndMaximizeWindow("/login.jsp?os_destination=%2Fsecure%2FTests.jspa#/design?projectId=10101");
        browseIssueModel.doLogin();
    }

    @AfterEach
    public void closeTab() {
        WebDriverService.getInstance().quitWebDriver();
    }

    @Test
    public void browseExistingIssue() {
        browseIssueModel.goToUrlAndMaximizeWindow("/browse/MTP-2253");
        Assertions.assertEquals("MTP-2253",browseIssueModel.getIssueId());
    }

    @Test
    public void checkPossibilityOfBrowsing(){
        String expectedKey = "MTP-2245";
        browseIssueModel.goToUrlAndMaximizeWindow("/issues/?jql=");
        browseIssueModel.getSearchField().click();
        browseIssueModel.getSearchField().sendKeys("Jira Test Project");
        browseIssueModel.getSearchButton().click();
        Assertions.assertTrue(browseIssueModel.waitUntilKeyIsVisible(expectedKey));
    }

    @ParameterizedTest
    @ValueSource(strings = {"MTP-99999999999"})
    public void browseNonExistingIssue(String projectType) {
        browseIssueModel.goToUrlAndMaximizeWindow(String.format("/browse/%s", projectType));
        Assertions.assertEquals("You can't view this issue", browseIssueModel.getErrorMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/issueIds.csv")
    public void browseIssueWithSpecificId(String issueId) {
        browseIssueModel.goToUrlAndMaximizeWindow(String.format("/browse/%s", issueId));
        Assertions.assertDoesNotThrow(() -> browseIssueModel.getIssueId());
    }
}
