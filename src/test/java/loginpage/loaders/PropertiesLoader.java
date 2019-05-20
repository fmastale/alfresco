package loginpage.loaders;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties load(String propName) {
        Properties properties = new Properties();

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
