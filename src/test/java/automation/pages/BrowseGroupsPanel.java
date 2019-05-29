package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BrowseGroupsPanel extends PageObject{
    private WebElement newGroupSpan;

    private By spansWithGroupsNames = By.xpath("//a[@class='yui-columnbrowser-item groups-item-group']");

    private By tableWithGroupsName = By.xpath("//div[@class='yui-columnbrowser-column-body']");

    private By exactGroupSpan;

    public BrowseGroupsPanel(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void setExactGroupSpan(String displayName, String identifier) {
        exactGroupSpan = By.xpath(String.format("//span[contains(text(),'%s (%s)')]", displayName, identifier));
    }

    public boolean checkIfGroupOnList(String displayName, String identifier) {
        String groupFullName = String.format("%s (%s)", displayName, identifier);

        wait.until(ExpectedConditions.visibilityOfElementLocated(tableWithGroupsName));
        List<WebElement> groups = driver.findElements(spansWithGroupsNames);

        return groups.stream().anyMatch(group -> group.getText().equals(groupFullName));
    }
}
