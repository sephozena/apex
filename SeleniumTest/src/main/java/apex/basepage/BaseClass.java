/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.basepage;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;

import apex.utils.ConfigManager;
import apex.utils.ExtentReportManager;
import apex.utils.ScreenshotUtils;
import apex.utils.ThreadUtils;

@Listeners(apex.utils.TestListener.class)
public class BaseClass {
	protected WebDriver driver;
	protected static final Logger log = LogManager.getLogger(BaseClass.class);

	private WebDriver initializeDriver(String browser) {

		switch (browser.toLowerCase()) {

		case "chrome":
			log.info("Initializing WebDriver for: " + browser);
			return new ChromeDriver();
		case "firefox":
			log.info("Initializing WebDriver for: " + browser);
			return new FirefoxDriver();
		default:
			throw new IllegalArgumentException("Invalid browser name: " + browser);
		}
	}

	@BeforeClass(alwaysRun = true)
	@Parameters({ "browser" })
	public void launchBrowser(@Optional("chrome") String browser) {
        ThreadUtils.setBrowserName(browser);  // Set the browser name in ThreadUtils
		driver = initializeDriver(browser);
		ThreadUtils.setDriverRef(driver);
		ThreadUtils.setLogger(log);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		String baseUrl = ConfigManager.getProperty("baseUrl");
		driver.get(baseUrl);
		log.info("Setting up the base url: " + baseUrl);

	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
			log.info("Browser closed.");
		}
	}

	@BeforeSuite
	public void setUpReport() {
		// Initialize the Extent Report before any test suite runs
		ExtentReportManager.initializeReport("test-output/extent-report.html");
	}

	@AfterSuite
	public void tearDownReport() {
		// Flush the Extent Report after all tests in the suite have run
		ExtentReportManager.flushReport();
	}

	public static Logger log() {
		return ThreadUtils.getLogger();
	}

	public void captureAndAttachScreenshot(String screenshotName) {
		ScreenshotUtils.captureAndAttachScreenshot(driver, screenshotName);
	}
//
//    public void handleTestFailure(String screenshotName) {
//        log.error("Test failed: " + screenshotName);
//        captureAndAttachScreenshot(screenshotName);
//        ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + screenshotName);
//        // Explicitly fail the test to ensure it is recorded as failed in the TestListener
//        throw new AssertionError("Test failed: " + screenshotName);
//    }
	
    // New method to access ScreenshotUtils.logWithScreenshot
    public void logWithScreenshot(String message, Status status) {
         ScreenshotUtils.logWithScreenshot(driver, message, status);
    }
}
