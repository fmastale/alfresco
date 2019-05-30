package automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BrowseGroupsPanel extends PageObject{
    private JavascriptExecutor jsExecutor;

    private By exactGroupSpanLocator;
    private By removeGroupButtonLocator;

    private By groupsNamesSpansLocator = By.xpath("//a[@class='yui-columnbrowser-item groups-item-group']");
    private By groupsTableLocator = By.xpath("//div[@class='yui-columnbrowser-column-body']");
    private By deleteGroupButtonLocator = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-remove-button-button");


    public BrowseGroupsPanel(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        jsExecutor = ((JavascriptExecutor)driver);
    }

    public void findGroupSpan(String displayName, String identifier) {
        exactGroupSpanLocator = By.xpath(String.format("//span[contains(text(),'%s (%s)')]", displayName, identifier));
    }

    public boolean checkIfGroupOnList(String displayName, String identifier) {
        String groupFullName = String.format("%s (%s)", displayName, identifier);

        wait.until(ExpectedConditions.visibilityOfElementLocated(groupsTableLocator));

        wait.until(ExpectedConditions.visibilityOfElementLocated(groupsNamesSpansLocator));
        List<WebElement> groups = driver.findElements(groupsNamesSpansLocator);

        //todo: stale element reference exception workaround:     - is there easiest and more elegant way to handle this?
        // it working in isolation but not when I run all tests, why?

        boolean isFound;

        try {
            isFound = groups.stream().anyMatch(group -> group.getText().equals(groupFullName));
        } catch (StaleElementReferenceException e) {
            isFound = groups.stream().anyMatch(group -> group.getText().equals(groupFullName));
            e.printStackTrace();
        }

        return isFound;
    }

    public void removeGroup(String displayName, String identifier) {
        findGroupSpan(displayName, identifier);
        findGroupRemoveButton(displayName, identifier);

        scrollToGroupSpan();
        clickRemoveButton();
        clickConfirmDeleteButton();
    }

    private void clickConfirmDeleteButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteGroupButtonLocator));
        driver.findElement(deleteGroupButtonLocator).click();
    }

    private void clickRemoveButton() {
        WebElement removeGroupButton = driver.findElement(removeGroupButtonLocator);
        jsExecutor.executeScript("arguments[0].click();", removeGroupButton);
    }

    private void scrollToGroupSpan() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(exactGroupSpanLocator));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(exactGroupSpanLocator));
    }

    private void findGroupRemoveButton(String displayName, String identifier) {
        String pathToButton = String.format(
                "//a[@class='yui-columnbrowser-item groups-item-group']//span[contains(text(),'%s (%s)')]/following-sibling::span[1]/span[@class='groups-delete-button']",
                displayName, identifier );
        removeGroupButtonLocator = By.xpath(pathToButton);
    }
}
