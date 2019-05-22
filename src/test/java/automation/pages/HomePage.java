package automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject{

    @FindBy(id="HEADER_USER_MENU_POPUP_text")
    private WebElement nameSpanLocator;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String nameFromSpan() {
        return nameSpanLocator.getText();
    }
}
