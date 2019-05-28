package automation.tests;

import automation.pages.BrowseGroupsPanel;
import automation.pages.EditGroupPage;
import automation.pages.LoginPage;
import automation.pages.NewGroupPage;
import automation.utils.WaitForElement;
import automation.utils.loaders.EnvironmentConfigLoader;
import automation.utils.loaders.Go;
import automation.utils.loaders.Pages;
import automation.utils.loaders.UserConfigLoader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("TS01 - Managing groups")
public class ManagingGroupTest {

    private static Go go;
    private static WebDriver driver;
    private BrowseGroupsPanel browseGroupsPanel;
    private WaitForElement waitForElement;
    private static int timeOut;
    private static JavascriptExecutor jsExecutor;

    @DisplayName("TC01 - New group can be created")
    @ParameterizedTest
    @MethodSource("groupCredentialsProvider")
    void checkIfNewGroupCanBeCreated(final String identifier, final String displayName) {
        //given:
        go.to(Pages.NEW_GROUP_PAGE);

        //when:
        new NewGroupPage(driver).createGroup(displayName, identifier);
        browseGroupsPanel.setGroupCredentialsPath(displayName, identifier);
        waitForElement.wait(browseGroupsPanel.getGroupCredentialsPath(), timeOut);

        //then
        assertEquals(String.format("%s (%s)", displayName, identifier), browseGroupsPanel.getGroupName(),
                "Group 'display name' and/or 'identifier' are not the same");
    }

    @DisplayName("TC02 - Existing group can be edited")
    @ParameterizedTest
    @MethodSource("groupNamesAndIdentifierProvider")
    void checkIfGroupCanBeEdited(final String displayName, final String identifier, final String newDisplayName) {
        //given:
        //todo: better name for getting to <some default url + postfix> ?!
        go.toConcretePage(Pages.GROUP_EDIT_PAGE, identifier);

        //when:
        EditGroupPage editGroupPage = new EditGroupPage(driver);
        waitForElement.wait(editGroupPage.getEditGroupInputField(), timeOut);
        editGroupPage.editDisplayName(newDisplayName);
        browseGroupsPanel.setGroupCredentialsPath(newDisplayName, identifier);
        waitForElement.wait(browseGroupsPanel.getGroupCredentialsPath(), timeOut);

        //then:
        assertEquals(String.format("%s (%s)",newDisplayName, identifier), browseGroupsPanel.getGroupName(),
                "Group names don't match");
    }

    @DisplayName("TC03 - Existing group can be removed")
    @ParameterizedTest
    @MethodSource("groupToRemoveProvider")
    void checkIfGroupCanBeRemoved(String groupToRemove) {
        //given:
        //todo: get rid off driver.manage()...
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        go.to(Pages.BROWSE_GROUPS_PANEL);

        //when:
        browseGroupsPanel.removeGroup(jsExecutor, groupToRemove);
        go.to(Pages.BROWSE_GROUPS_PANEL);
        waitForElement.wait(By.xpath("//div[@class='yui-columnbrowser-column-body']"), timeOut);

        //then:
        assertFalse(browseGroupsPanel.isGroupOnList(groupToRemove), "Group was found on a list containing all of groups");
    }

    @DisplayName("TC04 - Existing group can be removed permanently")
    @Test
    void checkIfGroupCanBeRemovedPermanently() {
        // todo: implement this
        //given:
        //when:
        //then:
    }

    @BeforeEach
    void beforeEach() {
        go.to(Pages.ADMIN_TOOLS_GROUPS_PAGE);
        browseGroupsPanel = new BrowseGroupsPanel(driver);
        waitForElement = new WaitForElement(driver);
    }

    @BeforeAll
    public static void beforeAll() {
        EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");
        driver = envConfLoader.getDriver();

        go = new Go(driver);
        go.to(Pages.LOGIN_PAGE);

        UserConfigLoader userConfLoader = new UserConfigLoader("user");
        new LoginPage(driver).logUser(userConfLoader.getUserLogin(), userConfLoader.getUserPassword());
        timeOut = envConfLoader.getTimeOut();

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
}
