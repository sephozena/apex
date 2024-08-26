/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
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

	private static final String SCREENSHOTS_DIR = System.getProperty("user.dir") + "/screenshots/";
	
    private static String generateFilePath(String screenshotName) {
        String dateName = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        return SCREENSHOTS_DIR + screenshotName + "_" + dateName + ".png";
    }

	// Method to capture a screenshot and return the file path
	public static String captureAndAttachScreenshot(WebDriver driver, String screenshotName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = generateFilePath(screenshotName);
		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {
			e.printStackTrace();
			ThreadUtils.getLogger().error("Failed to capture screenshot: ", e.getMessage());
		}
		return destination;
	}

	// Method to log a message and attach a screenshot on pass/fail
	public static void logWithScreenshot(WebDriver driver, String message, Status status) {
		String screenshotPath = captureAndAttachScreenshot(driver, message);
		try {
			ExtentReportManager.getTest().log(status, message,MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} catch (Exception e) {
			e.printStackTrace();
			ThreadUtils.getLogger().error("Failed to log screenshot: " + e.getMessage());
		}
	}
}
