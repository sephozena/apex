package apex.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDataFile {
    private Properties prop;

    public PropertiesDataFile(String filePath) {
        prop = new Properties();
        loadProperties(filePath);
    }

    private void loadProperties(String filePath) {
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return prop.getProperty(key);
    }
}