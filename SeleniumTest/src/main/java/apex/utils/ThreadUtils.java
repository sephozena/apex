package apex.utils;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
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
    
    public static synchronized Logger getLogger() {
        Logger loggerRef = logger.get();
        if (loggerRef == null) {
        	loggerRef = LogManager.getLogger("DefaultLogger");
            logger.set(loggerRef);
            System.out.println("Logger initialized: " + loggerRef.getName()); // Debug output

        }
        return logger.get();
      }
    
    public static synchronized void setLogger(Logger loggerParam) {
        logger.set(loggerParam);
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
