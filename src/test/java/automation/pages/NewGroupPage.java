package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewGroupPage {
    private WebDriver driver;

    By identifierLocator = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-create-shortname");
    By displayNameLocator = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-create-displayname");
    By createGroupButtonLocator = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-creategroup-ok-button-button");

    public NewGroupPage(WebDriver driver) {
        this.driver = driver;
    }

    public NewGroupPage typeIdentifier(String identifier) {
        driver.findElement(identifierLocator).sendKeys(identifier);
        return this;
    }

    public NewGroupPage typeDisplayName(String displayName) {
        driver.findElement(displayNameLocator).sendKeys(displayName);
        return this;
    }

    public BrowsePage submitCreateGroup() {
        driver.findElement(createGroupButtonLocator).click();
        return new BrowsePage(driver);
    }


}
