package functions;

import org.openqa.selenium.WebDriver;

import base.BaseClass;
import pages.LoginPage;
import pages.LoginPage.LoginActions;

/**
 * Unit test for simple App.
 */
public class LoginManagement extends BaseClass {
	
	private LoginPage loginPage;
	private LoginActions loginActions;
	
	public LoginManagement(WebDriver driver) {
		this.loginPage = new LoginPage(driver);
		this.loginActions = loginPage.new LoginActions();
	}
	
	
	public void Login(String username, String password) {
		var test = "";
		loginActions.enterUserName(username).enterPassword(password);
		loginActions.submitLogin();
		
		

	}
}
