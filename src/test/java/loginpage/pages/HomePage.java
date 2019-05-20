package loginpage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    private final By nameSpanLocator = By.id("HEADER_USER_MENU_POPUP_text");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String nameFromSpan() {
        return driver.findElement(nameSpanLocator).getText();
    }

}
