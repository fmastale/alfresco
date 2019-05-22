package automation.tests;

import automation.utils.loaders.EnvironmentConfigLoader;
import automation.utils.loaders.GroupPropLoader;
import automation.utils.loaders.UserConfigLoader;
import automation.pages.GroupManagementPage;
import automation.pages.LoginPage;
import automation.pages.NewGroupPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagingGroupTest {

    private static LoginPage loginPage;
    private static WebDriver driver;
    private static UserConfigLoader userConfLoader = new UserConfigLoader("user");
    private static EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");
    private NewGroupPage newGroupPage;
    private GroupManagementPage groupManagementPage;
    private GroupPropLoader groupPropLoader = new GroupPropLoader("group");

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

        groupManagementPage = new GroupManagementPage(driver);
        groupManagementPage.setXpath(displayName, identifier);

        driver.get(envConfLoader.getAdminBrowsePanel());
        driver.navigate().refresh();

        //then
        assertEquals(String.format("%s (%s)", displayName, identifier), groupManagementPage.getGroupName());
    }

    //TC02 - Existing group can be edited
    @Test
    void checkIfGroupCanBeEdited() {
        //todo: implement this
    }


    //TC03 - Existing group can be removed
    //TC04 - Existing group can be removed permanently

    @BeforeEach
    void beforeEach() {
        driver.get(envConfLoader.getAdminBrowsePanel());
    }

    @BeforeAll
    public static void beforeAll() {
        driver = envConfLoader.getDriver();

        driver.get(envConfLoader.urlBeginning());

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
