package automation.tests.browsegroup;

import automation.tests.utils.loaders.EnvironmentConfigLoader;
import automation.tests.utils.loaders.UserConfigLoader;
import automation.tests.utils.pages.BrowsePage;
import automation.tests.utils.pages.HomePage;
import automation.tests.utils.pages.LoginPage;
import automation.tests.utils.pages.NewGroupPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagingGroupTest {

    private static LoginPage loginPage;
    private NewGroupPage newGroupPage;
    private  static HomePage homePage;
    private BrowsePage browsePage;
    private static WebDriver driver;
    private static UserConfigLoader userConfLoader = new UserConfigLoader("user");
    private static EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");

    //TC02 - Existing group can be edited
    //TC03 - Existing group can be removed
    //TC04 - Existing group can be removed permanently

    //TC01 - New group can be added
    @Test
    void checkIfNewGroupCanBeCreated() {
        //given:
        driver.get(envConfLoader.getNewGroup());

        //when:
        newGroupPage = new NewGroupPage(driver);
        //todo: make it more generic - send this to config file
        newGroupPage.typeIdentifier("Group");
        newGroupPage.typeDisplayName("SomeGroup");
        browsePage = newGroupPage.submitCreateGroup();

        //then

    }

    @BeforeEach
    void beforeEach() {
        driver.get(envConfLoader.getBrowsePanel());
    }

    @BeforeAll
    public static void beforeAll() {
        driver = envConfLoader.getDriver();

        driver.get(envConfLoader.getURL());

        loginPage = new LoginPage(driver);
        loginPage.typeUsername(userConfLoader.getUserLogin());
        loginPage.typePassword(userConfLoader.getUserPassword());

        homePage = loginPage.submitLogin();
    }

    @AfterAll
    static void afterAll() {
       //driver.quit();
    }
}
