package automation.tests;

import automation.pages.BrowseGroupsPanel;
import automation.pages.LogInPage;
import automation.utils.loaders.EnvironmentConfigLoader;
import automation.utils.loaders.Go;
import automation.utils.loaders.Pages;
import automation.utils.loaders.UserConfigLoader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Stream;

@DisplayName("TS02 - Verify adding to group")
public class AddingToGroupTest {
    private static Go go;
    private static WebDriver driver;
    private static WebDriverWait wait;
    private BrowseGroupsPanel browseGroupsPanel;

    @DisplayName("TC01 - User can be added to group")
    @ParameterizedTest
    @MethodSource("groupNamesAndIdentifierProvider")
    public void checkIfNewUserCanBeAddedToGroup(String displayName, String identifier, String username) {

        browseGroupsPanel.addUserToGroup(displayName, identifier, username);

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

        new LogInPage(driver, wait).logUser(userConfLoader.getUsername(), userConfLoader.getUserPassword());

    }

    @BeforeEach
    public void beforeEach() {
        go.to(Pages.BROWSE_GROUPS_PANEL);
        browseGroupsPanel = new BrowseGroupsPanel(driver, wait);
    }

    @AfterAll
    public static void afterAll() {
        //driver.quit();
    }

    private static Stream<Arguments> groupNamesAndIdentifierProvider() {
        return Stream.of(
                Arguments.of("Heroes", "Marvel", "Iron Man")
        );
    }
}

