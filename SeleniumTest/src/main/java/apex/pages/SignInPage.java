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
		private WebElement inputUsername2;
		
		@FindBy(how = How.XPATH, using = "//div[@id='username']")
		private WebElement inputUsername;
		
		@FindBy(how = How.XPATH, using = "//div[@id='password']//input[@id='react-select-3-input']")
		private WebElement inputPassword2;
		
		@FindBy(how = How.XPATH, using = "//div[@id='password']")
		private WebElement inputPassword;
		
		@FindBy(how = How.ID, using = "login-btn")
		private WebElement btnLogin;
		
		@FindBy(how = How.XPATH, using = "//*[@id='login-btn']//following-sibling::h3[contains(.,'%s')]")
		private WebElement lblErrorMessage;

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
		    
		    public String getErrorMessage() {
		    	return lblErrorMessage.getText();
		    }
		    
		    public WebElement inputUsername() {
		    	return inputUsername;
		    }
		    
		    public WebElement inputPassword() {
		    	return inputPassword;
		    }
		    
		    public WebElement btnLogin() {
		    	return btnLogin;
		    }
		    
		}
}
