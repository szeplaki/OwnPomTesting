package Model.EditIssue;

import Model.Login.LoginPageModel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditIssueModel extends LoginPageModel {
    public EditIssueModel() {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//*[@id='key-val']")
    WebElement issueID;
    @FindBy(id = "edit-issue")
    WebElement editBtn;
    @FindBy(xpath = "//*[@id='edit-issue-dialog']/header/h2")
    WebElement editModalTitle;
    @FindBy(xpath = "//*[@id='summary']")
    WebElement modalSummaryField;
    @FindBy(id = "edit-issue-submit")
    WebElement updateBtn;
    @FindBy(id = "summary-val")
    WebElement summaryTitle;
    @FindBy(xpath = "//*[@id='aui-flag-container']/div/div/button")
    WebElement closeModalBtn;


    public String getIssueID(){
        return issueID.getText();
    }

    public void clickEditBtn(){
        editBtn.click();
    }

    public String getEditModelTitle(){
        return editModalTitle.getText();
    }

    public WebElement getEditBtn() {
        return editBtn;
    }

    public void setModalSummaryField(String strSummary){
        modalSummaryField.clear();
        modalSummaryField.sendKeys(strSummary);
    }

    public void clickUpdateBtn(){
        updateBtn.click();
    }

    public String getSummaryTitle() {
        return summaryTitle.getText();
    }

    public void clickModalBtn(){
        closeModalBtn.click();
    }
}
