package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	protected WebDriver driver;

	private WebDriver initializeWebDriver(String browser) {
		switch (browser) {
		case "chrome":
			return new ChromeDriver();
		case "firefox":
			return new FirefoxDriver();
		default:
			throw new IllegalArgumentException("No defined web browser " + browser);
		}
	}
	
	@BeforeClass
	@Parameters({"browser"})
	public void launchBrowser(@Optional ("chrome") String browser) {
		driver = initializeWebDriver(browser);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));		
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
