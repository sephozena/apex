/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesDataFile {
    private Properties prop;

    public PropertiesDataFile(String filePath) {
        prop = new Properties();
        loadProperties(filePath);
    }

    private void loadProperties(String filePath) {
    	
    	/*
    	 * load properties from class path using File Input Stream
    	 */
//        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
//            prop.load(fis);
    	/*
    	 * load properties from class path using InputStream
    	 */
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IOException("Property file '" + filePath + "' not found in the classpath");
            }
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return prop.getProperty(key);
    }
    
    public String set(String key, String value) {
    	return  (String) prop.setProperty(key, value);
    }
}