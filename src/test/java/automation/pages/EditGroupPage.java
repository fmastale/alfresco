package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditGroupPage extends PageObject {

    private By editGroupInputField = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-update-displayname");

    private By saveChangesButton = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-updategroup-save-button-button");

    public EditGroupPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    /*public void addNewDisplayName(String newDisplayName) {
        waitForElement.wait(editGroupInputField, 5);
        editGroupInputField.sendKeys(newDisplayName);
    }

    public void editDisplayName(String newDisplayName) {
        getEditGroupInputField().clear();
        addNewDisplayName(newDisplayName);
        clickUpdateButton();
    }

    public WebElement getEditGroupInputField() {
        waitForElement.wait(editGroupInputField, timeOut);
        return editGroupInputField;
    }

    public void clickUpdateButton() {
        saveChangesButton.click();
    }*/

}
