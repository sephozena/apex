package apex.utils;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;

public final class ThreadUtils {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<Logger> logger = new ThreadLocal<>();
    private static ThreadLocal<String> dataFolder = new ThreadLocal<>();
    
    // Private constructor to prevent instantiation
    private ThreadUtils() {}

    // Public method to get WebDriver instance
    public static WebDriver getDriverRef() {
        WebDriver driverRef = driver.get();
        if (driverRef == null) {
            throw new IllegalStateException("WebDriver instance has not been set for this thread.");
        }
        return driverRef;
    }

    // Public method to set WebDriver instance
    public static void setDriverRef(WebDriver driver) {
        ThreadUtils.driver.set(driver);
    }

    // Public method to get Logger instance
    public static Logger getLogger() {
        Logger loggerRef = logger.get();
        if (loggerRef == null) {
            throw new IllegalStateException("Logger instance has not been set for this thread.");
        }
        return loggerRef;
    }

    // Public method to set Logger instance
    public static void setLogger(Logger logger) {
        ThreadUtils.logger.set(logger);
    }
    
    // Public method to get Data Folder
    public static String getDataFolder() {
        String dataFolderRef = dataFolder.get();
        if (dataFolderRef == null) {
            throw new IllegalStateException("Data folder has not been set for this thread.");
        }
        return dataFolderRef;
    }

    // Public method to set Data Folder
    public static void setDataFolder(String dataFolderParm) {
        ThreadUtils.dataFolder.set(dataFolderParm);
    }


}
