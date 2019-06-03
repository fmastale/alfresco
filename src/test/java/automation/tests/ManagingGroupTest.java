package automation.tests;

import automation.pages.BrowseGroupsPanel;
import automation.pages.EditGroupPage;
import automation.pages.LogInPage;
import automation.pages.NewGroupPage;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TS01 - Managing groups")
public class ManagingGroupTest {
    private static Go go;
    private static WebDriver driver;
    private static WebDriverWait wait;
    private BrowseGroupsPanel browseGroupsPanel;

    @DisplayName("TC01 - New group can be created")
    @ParameterizedTest
    @MethodSource("groupCredentialsProvider")
    void checkIfNewGroupCanBeCreated(final String identifier, final String displayName) {
        //given:
        go.to(Pages.NEW_GROUP_PAGE);

        //when:
        NewGroupPage newGroupPage = new NewGroupPage(driver, wait);
        newGroupPage.createGroup(displayName, identifier);

        //then:
        assertTrue(browseGroupsPanel.checkIfGroupOnList(displayName, identifier),
                "New group wasn't found on the list");
    }

    @DisplayName("TC02 - Existing group can be edited")
    @ParameterizedTest
    @MethodSource("groupNamesAndIdentifierProvider")
    void checkIfGroupCanBeEdited(final String identifier, final String newDisplayName) {
        //given:
        go.toConcretePage(Pages.GROUP_EDIT_PAGE, identifier);

        //when:
        EditGroupPage editGroupPage = new EditGroupPage(driver, wait);
        editGroupPage.editGroup(newDisplayName);

        //then:
        assertTrue(browseGroupsPanel.checkIfGroupOnList(newDisplayName, identifier),
                "Edited group wasn't found on the list");
    }

    @DisplayName("TC03 - Existing group can be removed")
    @ParameterizedTest
    @MethodSource("groupToRemoveProvider")
    void checkIfGroupCanBeRemoved(final String displayName, final String identifier) {
        //given:
        go.to(Pages.BROWSE_GROUPS_PANEL);

        //when:
        browseGroupsPanel.removeGroup(displayName, identifier);

        //then:
        assertFalse(browseGroupsPanel.checkIfGroupOnList(displayName, identifier), "Group was found on the list");
    }

    @DisplayName("TC04 - Existing group can be removed permanently")
    @ParameterizedTest
    @MethodSource("permanentRemoveGroupProvider")
    void checkIfGroupCanBeRemovedPermanently(final String displayName, final String identifier) {
        // TC04 looks like TC03 but relations between groups are different
        //given:
        go.to(Pages.BROWSE_GROUPS_PANEL);

        //when:
        browseGroupsPanel.removeGroup(displayName, identifier);

        //then:
        assertFalse(browseGroupsPanel.checkIfGroupOnList(displayName, identifier), "Group was found on the list");
    }

    @BeforeEach
    void beforeEach() {
        go.to(Pages.ADMIN_TOOLS_GROUPS_PAGE);
        browseGroupsPanel = new BrowseGroupsPanel(driver, wait);
    }

    @BeforeAll
    public static void beforeAll() {
        EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");

        driver = envConfLoader.getDriver();
        wait = new WebDriverWait(driver, envConfLoader.getTimeOut());

        go = new Go(driver);
        go.to(Pages.LOGIN_PAGE);

        UserConfigLoader userConfLoader = new UserConfigLoader("user");

        new LogInPage(driver, wait).logUser(userConfLoader.getUsername(), userConfLoader.getUserPassword());
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
                Arguments.of("Some", "SomeGroup with updated name")
        );
    }

    private static Stream<Arguments> groupToRemoveProvider() {
        return Stream.of(
                Arguments.of("RemovableGroup", "Removable")
        );
    }

    private static Stream<Arguments> permanentRemoveGroupProvider() {
        return Stream.of(
                Arguments.of("SecondGroup", "Second")
        );
    }
}
