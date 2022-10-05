package Model.BrowseProject;

import Model.Login.LoginPageModel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrowseProjectModel extends LoginPageModel {

    public BrowseProjectModel() {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//*[@id=\"sidebar\"]/div/div[1]/div/div/div[2]/h1/div/div/a")
    private WebElement projectKey;
    @FindBy(xpath = "//*[@id=\"main\"]/h1")
    private WebElement errorMessage;


    public String getProjectKey() {
        return projectKey.getAttribute("href");
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
