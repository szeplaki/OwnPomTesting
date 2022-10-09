package Model.CreateIssue;

import Model.Login.LoginPageModel;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateIssueModel extends LoginPageModel {

    WebDriverWait webDriverWait;
    public CreateIssueModel() {
        PageFactory.initElements(webDriver, this);
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(1));
    }

    @FindBy(id = "create_link")
    private WebElement createButton;
    @FindBy(xpath = "//h2[text() = 'Create Issue']")
    private WebElement modalHeader;
    @FindBy(id = "project-field")
    private WebElement projectField;
    @FindBy(id = "issuetype-field")
    private WebElement issueField;
    @FindBy(id = "summary")
    private WebElement summaryField;
    @FindBy(id = "create-issue-submit")
    private WebElement submitButton;
    @FindBy(className = "issue-created-key")
    private WebElement linkOfNewIssue;
    @FindBy(id = "project-name-val")
    private WebElement projectNameOfCreatedIssue;
    @FindBy(id = "summary-val")
    private WebElement summaryOfCreatedIssue;
    @FindBy(id = "type-val")
    private WebElement issueTypeOfCreatedIssue;
    @FindBy(id = "opsbar-operations_more")
    private WebElement buttonWithNameMoreToDelete;
    @FindBy(id = "delete-issue")
    private WebElement deleteButton;
    @FindBy(id = "delete-issue-submit")
    private WebElement submitDelete;

    public void clickCreateButton() {
        createButton.click();
    }

    public WebElement getModalHeader() {
        return modalHeader;
    }

    public WebElement getProjectField() {
        return projectField;
    }

    public WebElement getIssueField() {
        return issueField;
    }

    public void catchAndClickElement(String id) {
        try {
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
        } catch (TimeoutException ignored) {
        } finally {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id))).click();
        }
    }

    public WebElement getSummaryField() {
        return summaryField;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public WebElement getLinkOfNewIssue() {
        return linkOfNewIssue;
    }

    public WebElement getProjectNameOfCreatedIssue() {
        return projectNameOfCreatedIssue;
    }

    public WebElement getSummaryOfCreatedIssue() {
        return summaryOfCreatedIssue;
    }

    public WebElement getIssueTypeOfCreatedIssue() {
        return issueTypeOfCreatedIssue;
    }

    public WebElement getButtonWithNameMoreToDelete() {
        return buttonWithNameMoreToDelete;
    }

    public WebElement getDeleteButton() {
        return deleteButton;
    }

    public WebElement getSubmitDelete() {
        return submitDelete;
    }
    public void selectProjectField(String expectedProjectKey) {
        catchElement("project-field");
        getProjectField().click();
        getProjectField().sendKeys(Keys.BACK_SPACE);
        getProjectField().sendKeys(expectedProjectKey);
        getModalHeader().click();
    }

    private void catchElement(String id) {
        try {
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
        } catch (TimeoutException ignored) {
        } finally {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        }
    }

    public void copyValueFromProjectField() {
        catchElement("project-field");
        getProjectField().click();
        getProjectField().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        getProjectField().sendKeys(Keys.chord(Keys.CONTROL, "c"));
    }

    public void selectIssueTypeField(String expectedIssueType) {
        catchElement("issuetype-field");
        getIssueField().click();
        getIssueField().sendKeys(Keys.BACK_SPACE);
        getIssueField().sendKeys(expectedIssueType);
        getModalHeader().click();
    }

    public void copyValueFromIssueTypeField() {
        catchElement("issuetype-field");
        getIssueField().click();
        getIssueField().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        getIssueField().sendKeys(Keys.chord(Keys.CONTROL, "c"));
    }

    public void pasteValueToSummaryField() {
        catchElement("summary");
        getSummaryField().click();
        getSummaryField().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        getSummaryField().sendKeys(Keys.chord(Keys.CONTROL, "v"));
    }

    public String saveValueOfSummaryIntoVariable() {
        return getSummaryField().getAttribute("value");
    }
    public void clearSummaryField() {
        getSummaryField().clear();
    }
}
