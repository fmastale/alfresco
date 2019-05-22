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
    private static EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");
    private static Go go;
    private LoginPage loginPage;
    private UserConfigLoader userConfLoader = new UserConfigLoader("user");

    @Test
    void checkIfLoginDefaultUser() {
        loginPage.typeUsername(userConfLoader.getUserLogin());
        loginPage.typePassword(userConfLoader.getUserPassword());
        HomePage homePage = loginPage.submitLogin();

        String userFullName = userConfLoader.getUserFullName();
        String spanWithUsername = homePage.nameFromSpan();

        String errorMsg = String.format("Span should contain: %s", userConfLoader.getUserFullName());
        assertEquals(userFullName, spanWithUsername, errorMsg);
    }

    @BeforeEach
    void beforeEach(){
        //driver.get(envConfLoader.urlBeginning());

        go.to(Pages.LOGIN_PAGE);
        loginPage = new LoginPage(driver);
    }

    @BeforeAll
    static void beforeAll(){
        driver = envConfLoader.getDriver();
        go = new Go(driver);
        go.to(Pages.LOGIN_PAGE);
    }

    @AfterAll
    static void afterAll(){
        driver.quit();
    }
}
