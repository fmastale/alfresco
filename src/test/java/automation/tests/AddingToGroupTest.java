package automation.tests;

import automation.pages.BrowseGroupsPanel;
import automation.pages.LogInPage;
import automation.utils.loaders.EnvironmentConfigLoader;
import automation.utils.loaders.Go;
import automation.utils.loaders.Pages;
import automation.utils.loaders.UserConfigLoader;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@DisplayName("TS02 - Verify adding to group")
public class AddingToGroupTest {
    private static Go go;
    private static WebDriver driver;
    private  static WebDriverWait wait;
    private BrowseGroupsPanel browseGroupsPanel;

    @DisplayName("TC01 - User can be added to group")
    @Test
    public void checkIfNewUserCanBeAddedToGroup() {
        //todo: implement this

        // be on browse panel page
        // click on group to which user will be added
        // click add user button
        // sign in credentials into form
        // click create button
        // check if created
    }

    @DisplayName("TC02 - Subgroup can be added to current group")
    @Test
    public void checkIfSubgroupCanBeAddedToCurrentGroup() {
        //todo: implement this

        // be on browse panel page
        // click on group to which group will be added
        // click add group button
        // search for group
        // click add button
        // check if added
    }

    @DisplayName("TC03 - Newly created subgroup can be added to group")
    @Test
    //where is the boundary between being too long and adequate name?
    public void checkIfNewlyCreatedSubgroupCanBeAddedToCurrentGroup() {
        //todo: implement this

        // be on browse panel page
        // click on group to which group will be added
        // click add group button
        // sign in credentials into form
        // click create button
        // check if created
    }

    @BeforeAll
    public static void beforeAll() {
        // todo: Dry base config
        EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");

        driver = envConfLoader.getDriver();
        wait = new WebDriverWait(driver, envConfLoader.getTimeOut());

        go = new Go(driver);
        go.to(Pages.LOGIN_PAGE);

        UserConfigLoader userConfLoader = new UserConfigLoader("user");

        LogInPage logInPage = new LogInPage(driver, wait);
        logInPage.logUser(userConfLoader.getUsername(), userConfLoader.getUserPassword());
    }

    @BeforeEach
    public void beforeEach() {
        go.to(Pages.BROWSE_GROUPS_PANEL);
    }

    @AfterAll
    public void afterAll() {
        driver.quit();
    }
}

