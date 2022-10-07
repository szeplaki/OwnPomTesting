package Test.EditIssue;

import Model.EditIssue.EditIssueModel;
import org.example.FileReader;
import org.example.WebDriverService;
import org.junit.jupiter.api.*;

public class EditIssueTest {
    EditIssueModel editIssueModel;

    @BeforeAll
    public static void setProperty() {
        System.setProperty("webdriver.chrome.driver", FileReader.getValueByKey("driver.location"));
    }

    @BeforeEach
    public void openNewTab() {
        editIssueModel = new EditIssueModel();
        editIssueModel.goToUrlAndMaximizeWindow("https://jira-auto.codecool.metastage.net/login.jsp?os_destination=%2Fsecure%2FTests.jspa#/design?projectId=10101");
        editIssueModel.doLogin();
    }

    @AfterEach
    public void closeTab() {
        WebDriverService.getInstance().quitWebDriver();
    }

    @Test
    public void editIssueSuccessfully() {
        editIssueModel.goToUrlAndMaximizeWindow("https://jira-auto.codecool.metastage.net/browse/MTP-2245");
        Assertions.assertTrue(editIssueModel.getIssueID().contains("MTP-2245"));

        editIssueModel.clickEditBtn();
        editIssueModel.waitUntil("xpath", "//*[@id='edit-issue-dialog']/header/h2");
        Assertions.assertTrue(editIssueModel.getEditModelTitle().contains("Edit Issue : MTP-2245"));
        editIssueModel.setModalSummaryField("Allopenissues");
        editIssueModel.clickUpdateBtn();


        editIssueModel.clickEditBtn();
        editIssueModel.waitUntil("xpath", "//*[@id='edit-issue-dialog']/header/h2");
        Assertions.assertTrue(editIssueModel.getEditModelTitle().contains("Edit Issue : MTP-2245"));
        editIssueModel.setModalSummaryField("Jira Test Project");
        editIssueModel.clickUpdateBtn();
    }
}
