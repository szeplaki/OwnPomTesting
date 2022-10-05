package Model.Login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashPageModel extends LoginPageModel {

    public DashPageModel() {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "login-form-username")
    private WebElement usernameField;
    @FindBy(id = "login-form-password")
    private WebElement passwordField;
    @FindBy(id = "login")
    private WebElement loginButton;
    @FindBy(xpath = "//*[@id=\"dashboard-content\"]/div[1]/div/div[1]")
    private WebElement dashPageTitle;
    @FindBy(xpath = "//*[@id=\"usernameerror\"]/p")
    private WebElement errorMessage;


    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getDashPageTitle() {
        return dashPageTitle.getText();
    }

    public void setUsername(String username) {
        this.usernameField.sendKeys(username);
    }

    public void setPassword(String password) {
        this.passwordField.sendKeys(password);
    }

    private void clickLoginButtonOnDash() {
        loginButton.click();
    }

    public void loginOnDashPage(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButtonOnDash();
    }
}
