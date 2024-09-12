package scripts;

import org.testng.annotations.Test;

import base.BaseClass;
import functions.LoginManagement;
import functions.OrderManagement;

/**
 * Unit test for simple App.
 */
public class TC01 extends BaseClass {

	
	@Test
	public  void TC01_login() {
		driver.get("https://www.saucedemo.com/");		

		LoginManagement loginManagement = new LoginManagement(driver);
		OrderManagement orderManagement = new OrderManagement(driver);

		loginManagement.Login("standard_user", "secret_sauce");
//		orderManagement.manageOrders();

	}
}
