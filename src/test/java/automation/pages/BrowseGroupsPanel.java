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

        driver.navigate().refresh();

        wait.until(ExpectedConditions.visibilityOfElementLocated(groupsNamesSpansLocator));
        List<WebElement> groups = driver.findElements(groupsNamesSpansLocator);

        return groups.stream().anyMatch(group -> group.getText().equals(groupFullName));
    }

    public void removeGroup(String displayName, String identifier) {
        findGroupSpan(displayName, identifier);
        findGroupRemoveButton(displayName, identifier);

        scrollToGroupSpan();
        clickRemoveButton();
        clickConfirmDeleteButton();
    }

    public void addUserToGroup(String displayName, String identifier, String username) {
        //todo: 1.check for tableWithUsers, 2.'mark it', 3.proceed adding user,
        // 4.after check that table can't be found, 5.find it again, 6.search for user

        // click on group to which user will be added
        findGroupSpan(displayName, identifier);
        clickOnGroupSpan();

        // wait for add user locator

        By addUserButtonLocator = By.className("groups-adduser-button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addUserButtonLocator));

        // wait and click add user button
        WebElement addGroupButton = driver.findElement(addUserButtonLocator);
        wait.until(ExpectedConditions.visibilityOf(addGroupButton));
        jsExecutor.executeScript("arguments[0].click()", addGroupButton);

        //enter username into search field
        By searchUserInputFieldLocator = By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-peoplefinder-search-text']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchUserInputFieldLocator));
        driver.findElement(searchUserInputFieldLocator).sendKeys(username);

        //click search button
        By searchButtonLocator = By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-peoplefinder-search-button-button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchButtonLocator));
        driver.findElement(searchButtonLocator).click();

        //click add button
        By addButtonUILocator = By.xpath("//span[@class='yui-button yui-button-button']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addButtonUILocator));
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(addButtonUILocator));


        // getting table with users
        By tableBodyLocator = By.className("yui-columnbrowser-column-body");
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableBodyLocator));

        By spanWithUserLocator = By.xpath("//a[@class='yui-columnbrowser-item groups-item-user']");


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> users = driver.findElements(spanWithUserLocator);
        System.out.println(users.size());
        users.stream().forEach(user -> System.out.println(user.getText()));

    }

    private void clickOnGroupSpan() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(exactGroupSpanLocator));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(exactGroupSpanLocator));
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
