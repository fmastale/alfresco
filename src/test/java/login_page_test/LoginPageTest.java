package login_page_test;

import login_page_test.loaders.EnvironmentConfigLoader;
import login_page_test.loaders.UserConfigLoader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import login_page_test.pages.HomePage;
import login_page_test.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {
    private LoginPage loginPage;
    private static WebDriver driver;
    private UserConfigLoader userConfLoader = new UserConfigLoader("user");
    private static EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");

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
    public void beforeEach(){
        driver.get(envConfLoader.getURL());
        loginPage = new LoginPage(driver);
    }

    @BeforeAll
    public static void beforeAll(){
        driver = envConfLoader.getDriver();
    }

    @AfterAll
    public static void afterAll(){
        driver.quit();
    }
}
