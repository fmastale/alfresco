package automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject{

    @FindBy(id = "page_x002e_components_x002e_slingshot-login_x0023_default-username")
    private WebElement usernameLocator;

    @FindBy(id = "page_x002e_components_x002e_slingshot-login_x0023_default-password")
    private WebElement passwordLocator;

    @FindBy(id = "page_x002e_components_x002e_slingshot-login_x0023_default-submit-button")
    private WebElement loginButtonLocator;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage typeUsername(String username) {
        usernameLocator.sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        passwordLocator.sendKeys(password);
        return this;
    }

    public HomePage submitLogin() {
        loginButtonLocator.submit();
        return new HomePage(driver);
    }
}
