package Model.CreateIssue;

import Model.Login.LoginPageModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateIssueModel extends LoginPageModel {
    public CreateIssueModel() {
        PageFactory.initElements(webDriver, this);
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

    public void waitForPresenceOfSummaryField() {
        for (int i = 0; i <= 2; i++) {
            try {
                webDriver.findElement(By.id("summary")).click();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
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
}
