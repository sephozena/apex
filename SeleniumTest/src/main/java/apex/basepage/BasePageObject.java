/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.basepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import apex.utils.WebDriverUtils;



public class BasePageObject<T extends  BasePageObject <T>> {
	 protected WebDriver driver;
	 protected WebDriverUtils webDriverUtils;

	 
	    public BasePageObject(WebDriver driver) {
	        this.driver = driver;
	        this.webDriverUtils = new WebDriverUtils(driver);
	        PageFactory.initElements(driver, this);
	    }
	    
	}

