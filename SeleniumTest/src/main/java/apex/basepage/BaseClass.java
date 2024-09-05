/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.basepage;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
import apex.utils.WebDriverUtils;

@Listeners(apex.utils.TestListener.class)
public class BaseClass {
	protected WebDriver driver;
	protected static final Logger log = LogManager.getLogger(BaseClass.class);

	private WebDriver initializeDriver(String browser, boolean isHeadless) {
		switch (browser.toLowerCase()) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			if (isHeadless) {
				chromeOptions.addArguments("--headless");
			}
			log.info("Initializing WebDriver for: " + browser);
			return new ChromeDriver(chromeOptions);

		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (isHeadless) {
				firefoxOptions.addArguments("--headless");
			}
			log.info("Initializing WebDriver for: " + browser);
			return new FirefoxDriver(firefoxOptions);

		default:
			throw new IllegalArgumentException("Invalid browser name: " + browser);
		}
	}



	@BeforeClass(alwaysRun = true)
	@Parameters({ "browser", "headless" })
	public void launchBrowser(ITestContext context, @Optional("chrome") String browser,@Optional("false") boolean isHeadless) {
		String testName = context.getCurrentXmlTest().getName();
		String testId = testName.split("_")[0]; // Extract prefix before underscore
		ThreadContext.put("testId", testId); // Set testId in logging context

		ThreadUtils.setBrowserName(browser); // Set the browser name in ThreadUtils
		driver = initializeDriver(browser, isHeadless);
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
			ThreadUtils.setDriverRef(null); // Clean up thread-local reference
		}
		ThreadContext.clearAll();
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

	protected static Logger log() {
		return ThreadUtils.getLogger();
	}

	protected void captureAndAttachScreenshot(String screenshotName) {
		ScreenshotUtils.captureAndAttachScreenshot(driver, screenshotName);
	}

	protected void logWithScreenshot(String message, Status status) {
		ScreenshotUtils.logWithScreenshot(driver, message, status);
	}

	protected void pause(int seconds) {
		WebDriverUtils.pause(seconds);
	}
}
