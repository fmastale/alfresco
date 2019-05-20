package automation.tests.utils.loaders;

import java.util.Properties;

public class UserConfigLoader {
    private Properties properties;

    public UserConfigLoader(String propName) {
        this.properties = PropertiesLoader.load(propName);
    }

    public String getUserLogin(){
        return properties.getProperty("login");
    }

    public String getUserPassword(){
        return properties.getProperty("password");
    }

    public String getUserFullName() {
        return properties.getProperty("full_name");
    }
}
