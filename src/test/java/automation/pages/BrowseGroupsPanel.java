package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BrowseGroupsPanel extends PageObject{
    private WebElement newGroupSpan;

    @FindBy(xpath = "//a[@class='yui-columnbrowser-item groups-item-group']")
    private List<WebElement> spansWithGroupsNames;

    @FindBy(xpath = "//div[@class='yui-columnbrowser-column-body']")
    private WebElement groupsTable;


    public BrowseGroupsPanel(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void setNewGroupSpan(String displayName, String identifier) {
        this.newGroupSpan = driver.findElement(By.xpath(String.format("//span[contains(text(),'%s (%s)')]", displayName, identifier)));
    }

    public String getNewGroupName() {
        String name = newGroupSpan.getText();
        return name;
    }

    public boolean isInGroups(String groupName) {
        System.out.println(spansWithGroupsNames.size());
        spansWithGroupsNames.stream().forEach(element -> System.out.println(element.getText()));
        boolean isFound = spansWithGroupsNames.stream().anyMatch(element -> element.getText().equals(groupName));
        return isFound;
    }

    public WebElement getGroupsTable() {
        return groupsTable;
    }


}
