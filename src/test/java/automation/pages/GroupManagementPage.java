package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GroupManagementPage {
    private WebDriver driver;
    private By groupIdentifier;
    private WebElement group;

    public GroupManagementPage(WebDriver driver) {
        this.driver = driver;
    }

    public void groupNameXPath(String displayName, String identifier) {
        this.groupIdentifier = By.xpath(String.format("//span[contains(text(),'%s (%s)')]", displayName, identifier));

    }

    public String getGroupName() {
        return driver.findElement(groupIdentifier).getText();
    }

    public String getGroupInfo() {
        this.group = driver.findElement(groupIdentifier);
        return group.getText();
    }
}
