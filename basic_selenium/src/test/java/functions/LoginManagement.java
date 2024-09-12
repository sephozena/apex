package functions;

import org.openqa.selenium.WebDriver;

import base.BaseClass;
import functions.OrderManagement.OrderActions;
import pages.LoginPage;
import pages.LoginPage.LoginActions;

public class LoginManagement extends BaseClass{
	
	private OrderManagement orderManagement;
	private OrderActions orderActions;

	
	private LoginPage loginPage;
	private LoginActions loginActions;

	public LoginManagement(WebDriver driver){
		this.orderManagement = new OrderManagement(driver);
		this.orderActions = orderManagement.new OrderActions();
		
		this.loginPage = new LoginPage(driver);
		this.loginActions = loginPage.new LoginActions();
	}
	
	public void Login(String username, String password) {
		loginActions.enterUsername(username).enterPassword(password);
		loginActions.clickLogin();
		
		orderManagement.manageOrders();
	}
}
