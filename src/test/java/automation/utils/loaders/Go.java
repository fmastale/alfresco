package automation.utils.loaders;

import org.openqa.selenium.WebDriver;

public class Go {
    private static WebDriver driver;

    public Go(WebDriver driver) {
        this.driver = driver;
    }

    public void to(Pages page) {
        driver.get(page.getUrl());
    }
}
