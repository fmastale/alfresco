package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewGroupPage extends PageObject {

    private By identifierLocator = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-create-shortname");
    private By displayNameLocator = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-create-displayname");
    private By createGroupButtonLocator = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-creategroup-ok-button-button");

    public NewGroupPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public NewGroupPage createGroup(String displayName, String identifier) {
        typeDisplayName(displayName);
        typeIdentifier(identifier);
        clickCreateGroupButton();
        return this;
    }

    private void clickCreateGroupButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createGroupButtonLocator));
        driver.findElement(createGroupButtonLocator).click();
    }

    private void typeIdentifier(String identifier) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(identifierLocator));
        driver.findElement(identifierLocator).sendKeys(identifier);
    }

    private void typeDisplayName(String displayName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(displayNameLocator));
        driver.findElement(displayNameLocator).sendKeys(displayName);
    }

}
