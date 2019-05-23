package automation.utils.loaders;

import java.util.Properties;

public class GroupPropLoader {
    private Properties properties;

    public GroupPropLoader(String propName) {
        this.properties = PropertiesLoader.load(propName);
    }

    public String getGroupIdentifier() {
        return properties.getProperty("identifier");
    }

    public String getGroupDisplayName() {
        return properties.getProperty("displayName");
    }
}
