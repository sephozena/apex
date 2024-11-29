/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.basepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import apex.utils.ThreadUtils;
import apex.utils.WebDriverUtils;

public class BasePageObject<T extends BasePageObject<T>> {
	protected WebDriver driver;
	protected WebDriverUtils webDriverUtils;

	 
	    public BasePageObject() {
	        this.driver = ThreadUtils.getDriverRef();
	        this.webDriverUtils = new WebDriverUtils();
	        PageFactory.initElements(driver, this);
	    }
	    
	}

//	public BasePageObject(WebDriver driver) {
//		// Ensure the driver is the thread-local driver
//		if (driver != ThreadUtils.getDriverRef()) {
//			System.out.println("test");
//			throw new IllegalArgumentException("The provided driver does not match the thread-local driver.");
//		}
//		this.driver = driver;
//		this.webDriverUtils = new WebDriverUtils(driver);
//		PageFactory.initElements(driver, this);
//	}
//}