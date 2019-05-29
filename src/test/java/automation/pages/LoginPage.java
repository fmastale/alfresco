package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends PageObject{

    private By usernameLocator = By.id("page_x002e_components_x002e_slingshot-login_x0023_default-username");

    private By passwordLocator  = By.id("page_x002e_components_x002e_slingshot-login_x0023_default-password");

    private By loginButtonLocator = By.id("page_x002e_components_x002e_slingshot-login_x0023_default-submit-button");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void loginUser(String username, String password) {
        typeUsername(username);
        typePassword(password);
        clickLoginButton();
    }

    private void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButtonLocator));
        driver.findElement(loginButtonLocator).click();
    }

    private void typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLocator));
        driver.findElement(passwordLocator).sendKeys(password);
    }

    private void typeUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLocator));
        driver.findElement(usernameLocator).sendKeys(username);
    }
}
