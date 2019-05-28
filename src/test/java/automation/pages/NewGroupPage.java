package automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewGroupPage extends PageObject {

    @FindBy(id = "page_x002e_ctool_x002e_admin-console_x0023_default-create-shortname")
    private WebElement identifierLocator;

    @FindBy(id = "page_x002e_ctool_x002e_admin-console_x0023_default-create-displayname")
    private WebElement displayNameLocator;

    @FindBy(id = "page_x002e_ctool_x002e_admin-console_x0023_default-creategroup-ok-button-button")
    private WebElement createGroupButtonLocator;

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

    public NewGroupPage createGroup(String displayName, String identifier) {
        typeDisplayName(displayName);
        typeIdentifier(identifier);
        submitCreateGroupButton();
        return this;
    }

}
