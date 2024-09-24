package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage <T extends BasePage <T>> {
	protected WebDriver driver;
	
	public BasePage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
