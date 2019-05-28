package automation.utils.loaders;

import org.openqa.selenium.WebDriver;

public class Go {
    private WebDriver driver;

    public Go(WebDriver driver) {
        this.driver = driver;
    }

    public void to(Pages page) {
        driver.get(page.getUrl());
    }

    public void toConcreteURL(Pages page, String address) {
        driver.get(page.getConcreteUrl(address));
    }
}
