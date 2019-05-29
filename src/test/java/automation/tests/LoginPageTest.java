package automation.tests;

import automation.pages.HomePage;
import automation.pages.LoginPage;
import automation.utils.loaders.EnvironmentConfigLoader;
import automation.utils.loaders.Go;
import automation.utils.loaders.Pages;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginPageTest {
    private static WebDriver driver;
    private static Go go;
    private LoginPage loginPage;
    private  static WebDriverWait wait;
    private static int timeOut = 5;

    @ParameterizedTest
    @MethodSource("credentialsProvider")
    void checkIfLoginDefaultUser(String username, String password, String userFullName) {

        loginPage.loginUser(username, password);
        HomePage homePage = new HomePage(driver, wait);

        assertTrue(homePage.checkLoggedUser(userFullName), "Usernames doesn't match");
    }

    @BeforeEach
    void beforeEach(){
        loginPage = new LoginPage(driver, wait);
    }

    @BeforeAll
    static void beforeAll(){
        EnvironmentConfigLoader envConfLoader = new EnvironmentConfigLoader("environment");
        driver = envConfLoader.getDriver();
        wait = new WebDriverWait(driver, timeOut);
        go = new Go(driver);
        go.to(Pages.LOGIN_PAGE);
    }

    @AfterAll
    static void afterAll(){
        driver.quit();
    }

    private static Stream<Arguments> credentialsProvider() {
        return Stream.of(
                Arguments.of("admin", "admin123", "Administrator")
        );
    }
}
