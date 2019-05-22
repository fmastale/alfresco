package automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewGroupPage extends PageObject {

    @FindBy(id = "page_x002e_ctool_x002e_admin-console_x0023_default-create-shortname")
    WebElement identifierLocator;

    @FindBy(id = "page_x002e_ctool_x002e_admin-console_x0023_default-create-displayname")
    WebElement displayNameLocator;

    @FindBy(id = "page_x002e_ctool_x002e_admin-console_x0023_default-creategroup-ok-button-button")
    WebElement createGroupButtonLocator;

    public NewGroupPage(WebDriver driver) {
        super(driver);
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


}
