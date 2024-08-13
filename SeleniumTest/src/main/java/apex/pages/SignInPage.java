package apex.pages;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import apex.basepage.BasePageObject;
import apex.utils.WebDriverUtils;

public class SignInPage extends BasePageObject<SignInPage>{

		public SignInPage(WebDriver driver) {
			super(driver);
		}
		
		@FindBy(how = How.XPATH, using = "//div[@id='username']//input[@id='react-select-2-input']")
		public WebElement inputUsername2;
		
		@FindBy(how = How.XPATH, using = "//div[@id='username']")
		public WebElement inputUsername;
		
		@FindBy(how = How.XPATH, using = "//div[@id='password']//input[@id='react-select-3-input']")
		public WebElement inputPassword2;
		
		@FindBy(how = How.XPATH, using = "//div[@id='password']")
		public WebElement inputPassword;
		
		@FindBy(how = How.ID, using = "login-btn")
		public WebElement btnLogin;
		
		@FindBy(how = How.XPATH, using = "//*[@id='login-btn']//following-sibling::h3[contains(.,'%s')]")
		public WebElement lblErrorMessage;

	    // Method to perform sign-in
		public class SignInActions{
					
		    public void signIn(String username, String password) {
		        webDriverUtils.waitForElementToBeClickable(inputUsername, Duration.ofSeconds(5));
		        
		        webDriverUtils.clickUsingJS(inputUsername );		        
		        inputUsername2.sendKeys(username, Keys.ENTER);

		        
		        webDriverUtils.clickUsingJS(inputPassword);		        
		        inputPassword2.sendKeys(password, Keys.ENTER);

		        webDriverUtils.click(btnLogin);
		        
		    }
		    
		}
}
