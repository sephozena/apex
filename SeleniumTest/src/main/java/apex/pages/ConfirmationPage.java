package apex.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import apex.basepage.BasePageObject;

public class ConfirmationPage extends BasePageObject<ConfirmationPage>{

	public ConfirmationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how = How.XPATH, using = "//*[@id='checkout-app']//button[contains(.,'Continue Shopping')]")
	private WebElement proceedToShopping;

	
	
	public class ConfirmationActions{
		
		public void proceedToMainPage() {
			webDriverUtils.click(proceedToShopping);
		}
		
	}
}
