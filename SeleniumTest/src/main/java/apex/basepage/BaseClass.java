package apex.basepage;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import apex.utils.ConfigManager;
import apex.utils.ThreadUtils;

public class BaseClass {
    protected WebDriver driver;
    protected static final Logger logger = (Logger) LogManager.getLogger(BaseClass.class);
    public Logger log() {
        return logger;
    }

    
    
    private WebDriver initializeDriver(String browserName) {
        if ("chrome".equals(browserName)) {
            return new ChromeDriver();
        } else if ("firefox".equals(browserName)) {
            return new FirefoxDriver();
        }
        throw new IllegalArgumentException("Invalid browser name: " + browserName);
    }
    
    // Explicit wait method
    protected void waitForElementToBeVisible(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Pause method
    protected void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log().error("Thread was interrupted during sleep", e);
        }
    }
    @BeforeClass(alwaysRun = true)
    @Parameters({"browserName"})
    public void launchBrowser(@Optional("chrome") String browserName) {
        driver = initializeDriver(browserName);
        ThreadUtils.setDriverRef(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        String baseUrl = ConfigManager.getProperty("baseUrl");
        driver.get(baseUrl);
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    
}
