/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.utils;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;

public final class ThreadUtils {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<Logger> logger = new ThreadLocal<>();
    private static final ThreadLocal<String> dataFolder = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ThreadLocal<String> browserName = new ThreadLocal<>();

    
    // Private constructor to prevent instantiation
    private ThreadUtils() {}

    // WebDriver methods
    public static WebDriver getDriverRef() {
        WebDriver driverRef = driver.get();
        if (driverRef == null) {
            throw new IllegalStateException("WebDriver instance has not been set for this thread.");
        }
        return driverRef;
    }

    public static void setDriverRef(WebDriver driver) {
        ThreadUtils.driver.set(driver);
    }
    
    // Logger methods
    public static synchronized Logger getLogger() {
        Logger loggerRef = logger.get();
        if (loggerRef == null) {
            loggerRef = LogManager.getLogger("Logger_"+ Thread.currentThread().getId());
            logger.set(loggerRef);
            System.out.println("Logger initialized: " + loggerRef.getName()); // Debug output
        }
        return logger.get();
    }
    
    public static synchronized void setLogger(Logger loggerParam) {
        logger.set(loggerParam);
    }
    
    // Data folder methods
    public static String getDataFolder() {
        String dataFolderRef = dataFolder.get();
        if (dataFolderRef == null) {
            throw new IllegalStateException("Data folder has not been set for this thread.");
        }
        return dataFolderRef;
    }

    public static void setDataFolder(String dataFolderParm) {
        ThreadUtils.dataFolder.set(dataFolderParm);
    }

    // ExtentTest methods
    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    public static void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }
    
    public static void setBrowserName(String browser) {
        browserName.set(browser);
    }

    public static String getBrowserName() {
        return browserName.get();
    }
}
