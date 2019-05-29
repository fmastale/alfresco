package automation.tests;

import automation.pages.BrowseGroupsPanel;
import automation.pages.LoginPage;
import automation.utils.WaitForElement;
import automation.utils.loaders.EnvironmentConfigLoader;
import automation.utils.loaders.Go;
import automation.utils.loaders.Pages;
import automation.utils.loaders.UserConfigLoader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Stream;

@DisplayName("TS01 - Managing groups")
public class ManagingGroupTest {

    private static Go go;
    private static WebDriver driver;
    private BrowseGroupsPanel browseGroupsPanel;
    private WaitForElement waitForElement;
    private static int timeOut = 5;
    private static JavascriptExecutor jsExecutor;
    private static WebDriverWait wait;

    @DisplayName("TC01 - New group can be created")
    @ParameterizedTest
    @MethodSource("groupCredentialsProvider")
    void checkIfNewGroupCanBeCreated(final String identifier, final String displayName) {
        go.to(Pages.NEW_GROUP_PAGE);

        //create group - new group page
        New

        //check if created - browse panel


    }

    @DisplayName("TC02 - Existing group can be edited")
    @ParameterizedTest
    @MethodSource("groupNamesAndIdentifierProvider")
    void checkIfGroupCanBeEdited(final String displayName, final String identifier, final String newDisplayName) {

    }

    @DisplayName("TC03 - Existing group can be removed")
    @ParameterizedTest
    @MethodSource("groupToRemoveProvider")
    void checkIfGroupCanBeRemoved(String groupToRemove) {

    }

    @DisplayName("TC04 - Existing group can be removed permanently")
    @ParameterizedTest
    @MethodSource("permanentRemoveGroupProvider")
    void checkIfGroupCanBeRemovedPermanently(String secondToRemove) {

    }

    @BeforeEach
    void beforeEach() {
        go.to(Pages.ADMIN_TOOLS_GROUPS_PAGE);
        browseGroupsPanel = new BrowseGroupsPanel(driver, wait);
        waitForElement = new WaitForElement(driver);
    }

    @BeforeAll
    public static void beforeAll() {
        EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");
        driver = envConfLoader.getDriver();
        wait = new WebDriverWait(driver, timeOut);
        go = new Go(driver);
        go.to(Pages.LOGIN_PAGE);

        UserConfigLoader userConfLoader = new UserConfigLoader("user");
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.loginUser(userConfLoader.getUserLogin(), userConfLoader.getUserPassword());

        //do I need it here?
        jsExecutor = ((JavascriptExecutor)driver);
    }

    @AfterAll
    static void afterAll() {
       driver.quit();
    }

    private static Stream<Arguments> groupCredentialsProvider() {
        return Stream.of(
                Arguments.of("Jay&SilentBob", "Jay & Silent Bob")
        );
    }

    private static Stream<Arguments> groupNamesAndIdentifierProvider() {
        return Stream.of(
                Arguments.of("SomeGroup", "Some", "SomeGroup with updated name")
        );
    }

    private static Stream<Arguments> groupToRemoveProvider() {
        return Stream.of(
                Arguments.of("RemovableGroup (Removable)")
        );
    }

    private static Stream<Arguments> permanentRemoveGroupProvider() {
        return Stream.of(
                Arguments.of("Second")
        );
    }
}
