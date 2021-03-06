package automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageObject(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        // can be removed if won't use @FindBy
        PageFactory.initElements(driver, this);
    }
}
