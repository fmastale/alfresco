package automation.tests.utils.loaders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;
import java.util.Properties;

public class EnvironmentConfigLoader {
    private Properties properties;

    public EnvironmentConfigLoader(String propName) {
        this.properties = PropertiesLoader.load(propName);
    }

    public WebDriver getDriver() {
        if (Objects.equals(properties.getProperty("browser"), "chrome")){

            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver" );
            return new ChromeDriver();
        }
        return new FirefoxDriver();
    }

    public String getURL() {
        // fixme check nulls
        String hostname = properties.getProperty("hostname");
        String port = properties.getProperty("port");
        String pageURL = properties.getProperty("pageURL");
        return String.format("http://%s:%s%s", hostname, port, pageURL );
    }

    public  String getBrowsePanel() {
        //todo: move this to env. prop.
        return "http://127.0.0.1:8080/share/page/console/admin-console/groups#state=panel%3Dsearch%26refresh%3Dfalse";
    }

    public String getNewGroup() {
        //todo: same as above
        return "http://127.0.0.1:8080/share/page/console/admin-console/groups#state=panel%3Dcreate";
    }
}
