package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditGroupPage extends PageObject {

    private By editGroupInputField = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-update-displayname");
    private By saveChangesButton = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-updategroup-save-button-button");

    public EditGroupPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public void editGroup(String newDisplayName) {
        typeNewDispalyName(newDisplayName);
        clickSaveChangesButton();
    }

    private void clickSaveChangesButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveChangesButton));
        driver.findElement(saveChangesButton).click();
    }

    private void typeNewDispalyName(String newDisplayName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(editGroupInputField));
        driver.findElement(editGroupInputField).clear();
        driver.findElement(editGroupInputField).sendKeys(newDisplayName);
    }
}
