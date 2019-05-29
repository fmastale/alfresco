package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends PageObject{

    private By nameSpanLocator = By.id("HEADER_USER_MENU_POPUP_text");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean checkLoggedUser(String userFullName) {
        return getUserNameFromSpan().equals(userFullName);
    }

    private String getUserNameFromSpan() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameSpanLocator));
        return driver.findElement(nameSpanLocator).getText();
    }
}
