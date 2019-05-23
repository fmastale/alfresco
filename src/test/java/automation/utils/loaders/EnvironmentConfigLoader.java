package automation.utils.loaders;

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

    public String urlBeginning() {
        // fixme check nulls
        // fixme write buider for making URL?! do I still need this if I move urls to Pages enum?
        String hostname = properties.getProperty("hostname");
        String port = properties.getProperty("port");
        String pageURL = properties.getProperty("pageURL");
        return String.format("http://%s:%s%s", hostname, port, pageURL );
    }

    public String getGroupManagementPage() {
        String adminBrowsePanel = properties.getProperty("adminBrowsePanel");
        String adminToolsPanel = properties.getProperty("adminToolsPanel");
        return String.format("%s%s%s", urlBeginning(), adminToolsPanel, adminBrowsePanel);
    }

    public String getNewGroupPanel() {
        String adminNewGroupPanel = properties.getProperty("adminNewGroupPanel");
        String adminToolsPanel = properties.getProperty("adminToolsPanel");
        return String.format("%s%s%s", urlBeginning(), adminToolsPanel, adminNewGroupPanel);
    }

}
