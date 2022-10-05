package Model.BrowseIssue;

import Model.Login.LoginPageModel;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowseIssueModel extends LoginPageModel {

    public BrowseIssueModel() {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "key-val")
    private WebElement issueId;
    @FindBy(id = "searcher-query")
    private WebElement searchField;
    @FindBy(xpath = "//*[@id='main']//form//button[text() = 'Search']")
    private WebElement searchButton;
    @FindBy(xpath = "//*[@id='key-val']")
    private WebElement issueNumber;


    public String getIssueId() {
        return issueId.getText();
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getIssueNumber() {
        return issueNumber;
    }

    public boolean waitUntilKeyIsVisible(String key) {
        try {
            WebDriverWait shortWait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//*[@id = 'key-val' and text() =  '%s']", key))));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }
}
