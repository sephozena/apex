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
       
    
    private WebDriver initializeDriver(String browserName) {
        if ("chrome".equals(browserName)) {
            return new ChromeDriver();
        } else if ("firefox".equals(browserName)) {
            return new FirefoxDriver();
        }
        throw new IllegalArgumentException("Invalid browser name: " + browserName);
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
