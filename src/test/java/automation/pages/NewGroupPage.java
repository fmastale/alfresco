package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewGroupPage extends PageObject {

    private By identifierLocator = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-create-shortname");

    private By displayNameLocator = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-create-displayname");

    private By createGroupButtonLocator = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-creategroup-ok-button-button");

    public NewGroupPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }



    public NewGroupPage typeIdentifier(String identifier) {
        identifierLocator.sendKeys(identifier);
        return this;
    }

    public NewGroupPage typeDisplayName(String displayName) {
        displayNameLocator.sendKeys(displayName);
        return this;
    }

    public NewGroupPage submitCreateGroupButton() {
        createGroupButtonLocator.click();
        return this;
    }

    public NewGroupPage createGroup(String displayName, String identifier) {
        typeDisplayName(displayName);
        typeIdentifier(identifier);
        submitCreateGroupButton();
        return this;
    }

}
