package automation.tests;

import automation.utils.loaders.EnvironmentConfigLoader;
import automation.utils.loaders.Go;
import automation.utils.loaders.Pages;
import automation.utils.loaders.UserConfigLoader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import automation.pages.HomePage;
import automation.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginPageTest {
    private static WebDriver driver;
    private static Go go;
    private LoginPage loginPage;

    @Test
    void checkIfLoginDefaultUser() {
        UserConfigLoader userConfLoader = new UserConfigLoader("user");

        String login = userConfLoader.getUserLogin();
        String password = userConfLoader.getUserPassword();

        loginPage.logUser(login, password);

        HomePage homePage = new HomePage(driver);
        String userFullName = userConfLoader.getUserFullName();
        String spanWithUsername = homePage.nameFromSpan();
        String errorMessage = String.format("Span should contain: %s", userConfLoader.getUserFullName());

        assertEquals(userFullName, spanWithUsername, errorMessage);
    }

    @BeforeEach
    void beforeEach(){
        loginPage = new LoginPage(driver);
    }

    @BeforeAll
    static void beforeAll(){
        EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");
        driver = envConfLoader.getDriver();
        go = new Go(driver);

        go.to(Pages.LOGIN_PAGE);
    }

    @AfterAll
    static void afterAll(){
        driver.quit();
    }
}
