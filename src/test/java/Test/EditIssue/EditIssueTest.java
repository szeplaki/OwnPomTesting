package Test.EditIssue;

import Model.EditIssue.EditIssueModel;
import org.example.WebDriverService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class EditIssueTest {
    EditIssueModel editIssueModel;

    @BeforeEach
    public void openNewTab() {
        editIssueModel = new EditIssueModel();
        editIssueModel.goToUrlAndMaximizeWindow("/login.jsp?os_destination=%2Fsecure%2FTests.jspa#/design?projectId=10101");
        editIssueModel.doLogin();
    }

    @AfterEach
    public void closeTab() {
        WebDriverService.getInstance().quitWebDriver();
    }

    @Test
    public void editIssueSuccessfully() {
        editIssueModel.goToUrlAndMaximizeWindow("/browse/MTP-2245");
        Assertions.assertTrue(editIssueModel.getIssueID().contains("MTP-2245"));

        editIssueModel.clickEditBtn();
        editIssueModel.waitUntilElementIsVisible("xpath", "//*[@id='edit-issue-dialog']/header/h2");
        Assertions.assertTrue(editIssueModel.getEditModelTitle().contains("Edit Issue : MTP-2245"));
        editIssueModel.setModalSummaryField("Allopenissues");
        editIssueModel.clickUpdateBtn();


        editIssueModel.clickEditBtn();
        editIssueModel.waitUntilElementIsVisible("xpath", "//*[@id='edit-issue-dialog']/header/h2");
        Assertions.assertTrue(editIssueModel.getEditModelTitle().contains("Edit Issue : MTP-2245"));
        editIssueModel.setModalSummaryField("Jira Test Project");
        editIssueModel.clickUpdateBtn();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/issueIds.csv")
    public void editIssueWithSpecificId(String issueId){
        editIssueModel.goToUrlAndMaximizeWindow(String.format("/browse/%s", issueId));
        Assertions.assertDoesNotThrow(() -> editIssueModel.clickEditBtn());
    }
}
