package automation.tests;

import automation.utils.loaders.EnvironmentConfigLoader;
import automation.utils.loaders.GroupPropLoader;
import automation.utils.loaders.UserConfigLoader;
import automation.pages.BrowsePage;
import automation.pages.LoginPage;
import automation.pages.NewGroupPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagingGroupTest {

    private static LoginPage loginPage;
    private NewGroupPage newGroupPage;
    private BrowsePage browsePage;
    private static WebDriver driver;
    private static UserConfigLoader userConfLoader = new UserConfigLoader("user");
    private GroupPropLoader groupPropLoader = new GroupPropLoader("group");
    private static EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");

    //TC02 - Existing group can be edited
    //TC03 - Existing group can be removed
    //TC04 - Existing group can be removed permanently

    //TC01 - New group can be added
    @Test
    void checkIfNewGroupCanBeCreated() {
        //given:
        driver.get(envConfLoader.getNewGroupPanel());

        //when:
        String identifier = groupPropLoader.getGroupIdentifier();
        String displayName = groupPropLoader.getGroupDisplayName();

        newGroupPage = new NewGroupPage(driver);
        newGroupPage.typeIdentifier(identifier);
        newGroupPage.typeDisplayName(displayName);
        newGroupPage.submitCreateGroupButton();

        browsePage = new BrowsePage(driver, displayName, identifier);

        driver.get(envConfLoader.getAdminBrowsePanel());
        driver.navigate().refresh();

        //then
        assertEquals(String.format("%s (%s)", displayName, identifier), browsePage.getGroupName());
    }

    @BeforeEach
    void beforeEach() {
        driver.get(envConfLoader.getAdminBrowsePanel());
    }

    @BeforeAll
    public static void beforeAll() {
        driver = envConfLoader.getDriver();

        driver.get(envConfLoader.getURL());

        loginPage = new LoginPage(driver);
        loginPage.typeUsername(userConfLoader.getUserLogin());
        loginPage.typePassword(userConfLoader.getUserPassword());

        loginPage.submitLogin();
    }

    @AfterAll
    static void afterAll() {
       driver.quit();
    }
}
