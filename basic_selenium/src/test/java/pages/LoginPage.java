package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import base.BasePage;

public class LoginPage extends BasePage<LoginPage>{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(how = How.ID, using = "user-name")
	private WebElement inputUsername;

	@FindBy(how = How.ID, using = "password")
	private WebElement inputPassword;
	
	@FindBy(how = How.ID, using = "login-button")
	private WebElement btnLogin;
	
	
	public class LoginActions {
		
		public LoginActions enterUsername(String username) {
			inputUsername.sendKeys(username);
			return this;
		}
		
		public LoginActions enterPassword(String password) {
			inputPassword.sendKeys(password);
			return this;
		}
		
		public WebElement clickLogin() {
			return btnLogin;
		}
	}
	
}
