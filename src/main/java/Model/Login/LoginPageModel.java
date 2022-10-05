package Model.Login;

import org.example.FileReader;
import org.example.WebDriverService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageModel {
    protected final WebDriver webDriver;
    protected final WebDriverWait webDriverWait;

    public LoginPageModel() {
        this.webDriver = WebDriverService.getInstance().getWebDriver();
        PageFactory.initElements(webDriver, this);
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    @FindBy(id = "login-form-username")
    private WebElement usernameField;
    @FindBy(id = "login-form-password")
    private WebElement passwordField;
    @FindBy(id = "login-form-submit")
    private WebElement loginButton;
    @FindBy(className = "aui-page-header-main")
    private WebElement title;
    @FindBy(xpath = "//*[@id='login-form']//p")
    private WebElement invalidLoginMsg;


    public String getTitle() {
        return title.getText();
    }

    public String getErrorMsg() {
        return invalidLoginMsg.getText();
    }

    public void setUsername(String username) {
        this.usernameField.sendKeys(username);
    }

    public void setPassword(String password) {
        this.passwordField.sendKeys(password);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickOnLoginButton();
    }

    public void doLogin() {
        webDriver.navigate().to("https://jira-auto.codecool.metastage.net/login.jsp?os_destination=%2Fsecure%2FMyJiraHome.jspa");
        login(FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));
    }

    public void goToUrlAndMaximizeWindow(String url) {
        webDriver.get(url);
        webDriver.manage().window().maximize();
    }

    public void waitUntil(String type, String id){
        switch (type){
            case "id":
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
                break;
            case "xpath":
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id)));
                break;
        }
    }
}
