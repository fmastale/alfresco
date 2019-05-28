package automation.utils.loaders;

import org.apache.commons.configuration.PropertiesConfiguration;

public class UserConfigLoader {
    private PropertiesConfiguration properties;

    public UserConfigLoader(String propName) {
        this.properties = PropertiesLoader.load(propName);
    }

    public String getUserLogin(){
        return properties.getString("login");
    }

    public String getUserPassword(){
        return properties.getString("password");
    }

    public String getUserFullName() {
        return properties.getString("full_name");
    }
}
