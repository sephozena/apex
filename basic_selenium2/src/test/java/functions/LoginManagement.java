package functions;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import base.BaseClass;
import pages.LoginPage;
import pages.LoginPage.LoginActions;

public class LoginManagement extends BaseClass {
	
	protected LoginPage loginPage;
	protected LoginActions loginActions;

	public LoginManagement(WebDriver driver) {
		this.loginPage = new LoginPage(driver);
		this.loginActions = loginPage.new LoginActions();
	}
	
	public void Login(String username, String password) {
		loginActions.enterUsername(username)
					.enterPassword(password)
					.clickLogin();
//					.validateLoggedIn();
		assertTrue(loginPage.txtLoggedin.isDisplayed());
	}
	
}