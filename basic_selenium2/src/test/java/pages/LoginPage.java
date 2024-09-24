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
	
	@FindBy(how = How.ID, using = "username")
	private WebElement inputUsername;

	@FindBy(how = How.ID, using = "password")
	private WebElement inputPassword;
	
	@FindBy(how = How.ID, using = "submit")
	private WebElement btnLogin;
	
	
	@FindBy(how = How.XPATH, using = "//h1[contains(.,'Logged In Successfully')]")
	public WebElement txtLoggedin;
	
	
	public class LoginActions {
		
		public LoginActions enterUsername(String username) {
			inputUsername.sendKeys(username);
			return this;
		}
		
		public LoginActions enterPassword(String password) {
			inputPassword.sendKeys(password);
			return this;
		}
		
		public LoginActions clickLogin() {
			btnLogin.click();
			return this;
		}
		
//		public LoginActions validateLoggedIn() {
//			txtLoggedin.clear();
//			return this;
//		}
		
	}

}
