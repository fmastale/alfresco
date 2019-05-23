package automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdateGroupPage extends PageObject {
    @FindBy(id = "page_x002e_ctool_x002e_admin-console_x0023_default-update-displayname")
    private WebElement editGroupInputField;

    @FindBy(id = "page_x002e_ctool_x002e_admin-console_x0023_default-updategroup-save-button-button")
    private WebElement saveChangesButton;

    @FindBy(id = "//span[contains(text(),'SomeGroup with updated name')]")
    private WebElement updatedGroupNameElement;

    public UpdateGroupPage(WebDriver driver) {
        super(driver);
    }

    public UpdateGroupPage editGroupName(String editText) {
        editGroupInputField.sendKeys(editText);
        return this;
    }

    public WebElement getEditGroupInputField() {
        return editGroupInputField;
    }

    public void clickUpdateButton() {
        saveChangesButton.click();
    }

}
