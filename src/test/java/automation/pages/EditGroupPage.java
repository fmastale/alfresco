package automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditGroupPage extends PageObject {
    @FindBy(id = "page_x002e_ctool_x002e_admin-console_x0023_default-update-displayname")
    private WebElement editGroupInputField;

    @FindBy(id = "page_x002e_ctool_x002e_admin-console_x0023_default-updategroup-save-button-button")
    private WebElement saveChangesButton;

    @FindBy(id = "//span[contains(text(),'SomeGroup with updated name')]")
    private WebElement updatedGroupNameElement;

    public EditGroupPage(WebDriver driver) {
        super(driver);
    }

    public EditGroupPage addNewDisplayName(String newDisplayName) {
        editGroupInputField.sendKeys(newDisplayName);
        return this;
    }

    public void editDisplayName(String newDisplayName) {
        editGroupInputField.clear();
        addNewDisplayName(newDisplayName);
        clickUpdateButton();
    }

    public WebElement getEditGroupInputField() {
        return editGroupInputField;
    }

    public void clickUpdateButton() {
        saveChangesButton.click();
    }

}
