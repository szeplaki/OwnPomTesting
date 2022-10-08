package Test.CreateIssue;

import Model.CreateIssue.CreateIssueModel;
import org.example.FileReader;
import org.example.WebDriverService;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.util.UUID;

public class CreateIssueTest {

    CreateIssueModel createIssueModel;

    @BeforeAll
    public static void setProperty() {
        System.setProperty("webdriver.chrome.driver", FileReader.getValueByKey("driver.location"));
    }

    @BeforeEach
    public void openNewTab() {
        createIssueModel = new CreateIssueModel();
        createIssueModel.goToUrlAndMaximizeWindow("https://jira-auto.codecool.metastage.net/login.jsp?os_destination=%2Fsecure%2FTests.jspa#/design?projectId=10101");
        createIssueModel.doLogin();
    }

    @AfterEach
    public void closeTab() {
        WebDriverService.getInstance().quitWebDriver();
    }

    @Test
    public void createIssueTest() {
        createIssueModel.clickCreateButton();
        createIssueModel.waitUntil("xpath", "//h2[text() = 'Create Issue']");
        Assertions.assertEquals("Create Issue", createIssueModel.getModalHeader().getText());

        createIssueModel.getProjectField().click();
        createIssueModel.getProjectField().sendKeys(Keys.BACK_SPACE);
        createIssueModel.getProjectField().sendKeys("Main Testing Project (MTP)");
        createIssueModel.getProjectField().sendKeys(Keys.ENTER);

        createIssueModel.waitUntil("id", "issuetype-field");
        createIssueModel.getIssueField().click();
        createIssueModel.getIssueField().sendKeys(Keys.BACK_SPACE);
        createIssueModel.getIssueField().sendKeys("Bug");
        createIssueModel.getIssueField().sendKeys(Keys.ENTER);

        createIssueModel.waitForPresenceOfSummaryField();

        createIssueModel.getSummaryField().click();
        String expectedSummaryText = UUID.randomUUID().toString();
        createIssueModel.getSummaryField().sendKeys(expectedSummaryText);

        createIssueModel.getSubmitButton().click();

        createIssueModel.waitUntil("xpath", "//*[@id='aui-flag-container']//a");
        createIssueModel.getLinkOfNewIssue().click();

        Assertions.assertEquals("Main Testing Project", createIssueModel.getProjectNameOfCreatedIssue().getText());
        Assertions.assertEquals(expectedSummaryText, createIssueModel.getSummaryOfCreatedIssue().getText());
        Assertions.assertEquals("Bug", createIssueModel.getIssueTypeOfCreatedIssue().getText());

        createIssueModel.waitUntil("id", "opsbar-operations_more");
        createIssueModel.getButtonWithNameMoreToDelete().click();

        createIssueModel.waitUntil("id", "delete-issue");
        createIssueModel.getDeleteButton().click();

        createIssueModel.waitUntil("id", "delete-issue-submit");
        createIssueModel.getSubmitDelete().click();
    }

}
