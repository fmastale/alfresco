package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupManagementPage {
    private WebDriver driver;
    private By groupIdentifier;

    public GroupManagementPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setGroupIdentifier(String displayName, String identifier) {
        this.groupIdentifier = By.xpath(String.format("//span[contains(text(),'%s (%s)')]", displayName, identifier));
    }

    public String getGroupName() {
        return driver.findElement(groupIdentifier).getText();
    }

    public By getGroupIdentifier() {
        return this.groupIdentifier;
    }
}
