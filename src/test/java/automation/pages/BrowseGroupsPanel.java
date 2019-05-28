package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BrowseGroupsPanel extends PageObject{
    private By groupCredentials;

    @FindBy(id = "page_x002e_ctool_x002e_admin-console_x0023_default-remove-button-button")
    private WebElement deleteButton;

    @FindBy(xpath = "//a[@class='yui-columnbrowser-item groups-item-group']")
    private List<WebElement> groups;

    public BrowseGroupsPanel(WebDriver driver) {
        super(driver);
    }

    public BrowseGroupsPanel setGroupCredentialsPath(String displayName, String identifier) {
        this.groupCredentials = By.xpath(String.format("//span[contains(text(),'%s (%s)')]", displayName, identifier));
        return this;
    }

    public By getGroupCredentialsPath() {
        return groupCredentials;
    }

    public String getGroupName() {
        return driver.findElement(groupCredentials).getText();
    }


    public WebElement getRemoveGroupSpan(String groupToRemove) {
        String pathToSpan = String.format("//*[contains(text(), '%s')]/parent::*", groupToRemove);
        WebElement groupSpan = driver.findElement(By.xpath(pathToSpan));
        return groupSpan;
    }

    public WebElement getRemoveGroupButton(String groupToRemove) {
        String path = String.format("//a[@class='yui-columnbrowser-item groups-item-group']//span[contains(text(),'%s')]/following-sibling::span[1]/span[@class='groups-delete-button']", groupToRemove);
        WebElement removeButton = driver.findElement(By.xpath(path));
        return removeButton;
    }

    public void clickDeleteButton() {
        deleteButton.click();
    }

    public boolean isGroupOnList(String groupToRemove) {
        return groups.stream().anyMatch(element -> element.getText().equals(groupToRemove));
    }

    public void removeGroup(JavascriptExecutor jsExecutor, String groupToRemove) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getRemoveGroupSpan(groupToRemove));
        jsExecutor.executeScript("arguments[0].click();", getRemoveGroupButton(groupToRemove));
        clickDeleteButton();
    }
}
