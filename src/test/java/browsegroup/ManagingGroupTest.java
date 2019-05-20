package browsegroup;

import loginpage.loaders.EnvironmentConfigLoader;
import loginpage.loaders.UserConfigLoader;
import loginpage.pages.HomePage;
import loginpage.pages.LoginPage;
import loginpage.pages.NewGroupPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagingGroupTest {

    private static LoginPage loginPage;
    private NewGroupPage newGroupPage;
    private static WebDriver driver;
    private static UserConfigLoader userConfLoader = new UserConfigLoader("user");
    private static EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");

    //TC02 - Existing group can be edited
    //TC03 - Existing group can be removed
    //TC04 - Existing group can be removed permanently

    //TC01 - New group can be added
    @Test
    void checkIfNewGroupCanBeCreated() throws InterruptedException {
        driver.get("http://127.0.0.1:8080/share/page/console/admin-console/groups#state=panel%3Dcreate");
        newGroupPage = new NewGroupPage(driver);
        newGroupPage.typeIdentifier("New User Group");
        newGroupPage.typeDisplayName("Some new user group");
        newGroupPage.submitCreateGroup();
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
        HomePage homePage = loginPage.submitLogin();
    }

    @AfterAll
    static void afterAll() {
       // driver.quit();
    }
}
