package automation.utils.loaders;

import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static PropertiesConfiguration load(String propName) {
        PropertiesConfiguration properties = new PropertiesConfiguration();

        //todo check nulls
        try {
            String projectPath = System.getProperty("user.dir");
            String propertiesPath = String.format("/src/main/resources/%s.properties", propName);

            try (InputStream inputStream = new FileInputStream(projectPath + propertiesPath)) {
                properties.load(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return properties;
    }
}
