/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found, check the filepath!");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    

    public static String getProperty(String key, int index) {
        String[] values = properties.getProperty(key, "").split(";");
        if (index >= 0 && index < values.length) {
            return values[index].trim();
        }
        String errorMsg = String.format("Invalid index %d for key: %s", index, key);
        ThreadUtils.getLogger().error(errorMsg); // Log the error
        throw new IndexOutOfBoundsException(errorMsg);
    }
}

