package Test.CreateIssue;

import Model.CreateIssue.CreateIssueModel;
import org.example.WebDriverService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.UUID;

public class CreateIssueTest {

    CreateIssueModel createIssueModel;

    @BeforeEach
    public void openNewTab() {
        createIssueModel = new CreateIssueModel();
        createIssueModel.goToUrlAndMaximizeWindow("/login.jsp?os_destination=%2Fsecure%2FTests.jspa#/design?projectId=10101");
        createIssueModel.doLogin();
    }

    @AfterEach
    public void closeTab() {
        WebDriverService.getInstance().quitWebDriver();
    }

    @Test
    public void createIssueTest() {
        createIssueModel.clickCreateButton();
        createIssueModel.waitUntilElementIsVisible("xpath", "//h2[text() = 'Create Issue']");
        Assertions.assertEquals("Create Issue", createIssueModel.getModalHeader().getText());

        createIssueModel.selectProjectField("Main Testing Project (MTP)");
        createIssueModel.selectIssueTypeField("Bug");

        createIssueModel.catchAndClickElement("summary");
        String expectedSummaryText = UUID.randomUUID().toString();
        createIssueModel.getSummaryField().sendKeys(expectedSummaryText);

        createIssueModel.getSubmitButton().click();

        createIssueModel.waitUntilElementIsVisible("xpath", "//*[@id='aui-flag-container']//a");
        createIssueModel.getLinkOfNewIssue().click();

        Assertions.assertEquals("Main Testing Project", createIssueModel.getProjectNameOfCreatedIssue().getText());
        Assertions.assertEquals(expectedSummaryText, createIssueModel.getSummaryOfCreatedIssue().getText());
        Assertions.assertEquals("Bug", createIssueModel.getIssueTypeOfCreatedIssue().getText());

        createIssueModel.waitUntilElementIsVisible("id", "opsbar-operations_more");
        createIssueModel.getButtonWithNameMoreToDelete().click();

        createIssueModel.waitUntilElementIsVisible("id", "delete-issue");
        createIssueModel.getDeleteButton().click();

        createIssueModel.waitUntilElementIsVisible("id", "delete-issue-submit");
        createIssueModel.getSubmitDelete().click();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createTests.csv", numLinesToSkip = 1)
    public void createIssueWithSpecificData(String expectedProjectKey, String expectedIssueType) {
        createIssueModel.clickCreateButton();

        createIssueModel.selectProjectField(expectedProjectKey);
        createIssueModel.copyValueFromProjectField();
        createIssueModel.pasteValueToSummaryField();
        String actualProjectName = createIssueModel.saveValueOfSummaryIntoVariable();
        createIssueModel.clearSummaryField();

        createIssueModel.selectIssueTypeField(expectedIssueType);
        createIssueModel.copyValueFromIssueTypeField();
        createIssueModel.pasteValueToSummaryField();
        String actualIssueType = createIssueModel.saveValueOfSummaryIntoVariable();
        createIssueModel.clearSummaryField();

        Assertions.assertTrue(actualProjectName.contains(expectedProjectKey));
        Assertions.assertEquals(expectedIssueType, actualIssueType);
    }

    @Test
    public void createIssueWithEmptySummary() {
        createIssueModel.clickCreateButton();
        createIssueModel.waitUntilElementIsVisible("id", "create-issue-submit");
        createIssueModel.getSubmitButton().click();
        createIssueModel.waitUntilElementIsVisible("xpath", "//*[@id='dialog-form']//div[text() = 'You must specify a summary of the issue.']");
        Assertions.assertEquals("You must specify a summary of the issue.", createIssueModel.getErrorMessage());
    }
}
