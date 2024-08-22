package apex.basepage;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import apex.utils.ConfigManager;
import apex.utils.ThreadUtils;

public class BaseClass {
    protected WebDriver driver;
    protected static final Logger log = LogManager.getLogger(BaseClass.class);

       
    
    private WebDriver initializeDriver(String browser) {
        if ("chrome".equals(browser)) {
        	System.out.println("Initializing WebDriver for: " + browser);
        	log.info("Initializing WebDriver for browser: " + browser);
        	return new ChromeDriver();
            
        } else if ("firefox".equals(browser)) {
        	log.info("Initializing WebDriver for: " + browser);
            return new FirefoxDriver();
        }
        throw new IllegalArgumentException("Invalid browser name: " + browser);
    }
    	
    @BeforeClass(alwaysRun = true)
    @Parameters({"browser"})
    public void launchBrowser(@Optional("chrome") String browserName) {
    	driver = initializeDriver(browserName);
        

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
        }
    }
    
    
    public static Logger log() {
      return ThreadUtils.getLogger();
    }
    
    
}
