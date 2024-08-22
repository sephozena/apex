package apex.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class ScreenshotUtils {


    private static final String SCREENSHOTS_DIR = System.getProperty("user.dir") + "/Screenshots/";

    // Method to capture a screenshot and return the file path
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String dateName = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = SCREENSHOTS_DIR + screenshotName + "_" + dateName + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }

    // Method to capture and attach a screenshot to the Extent Report
    public static void captureAndAttachScreenshot(WebDriver driver, String screenshotName) {
        String screenshotPath = captureScreenshot(driver, screenshotName);
        try {
            ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to log a message and attach a screenshot on pass/fail
    public static void logWithScreenshot(WebDriver driver, String message, Status status) {
        String screenshotPath = captureScreenshot(driver, "screenshot");
        try {
            ExtentReportManager.getTest().log(status, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
