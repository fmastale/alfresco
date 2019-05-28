package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowseGroupsPanel {
    private WebDriver driver;
    private By groupCredentials;

    public BrowseGroupsPanel(WebDriver driver) {
        this.driver = driver;
    }

    public BrowseGroupsPanel setGroupCredentialsPath(String displayName, String identifier) {
        this.groupCredentials = By.xpath(String.format("//span[contains(text(),'%s (%s)')]", displayName, identifier));
        return this;
    }

    public String getGroupName() {
        return driver.findElement(groupCredentials).getText();
    }

    public By getGroupCredentialsPath() {
        return groupCredentials;
    }

    public WebElement getGroup() {
        WebElement group;
        return group = driver.findElement(groupCredentials);
    }
}
