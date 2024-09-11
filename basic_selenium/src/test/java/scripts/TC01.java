package scripts;

import org.testng.annotations.Test;

import base.BaseClass;
import functions.LoginManagement;

/**
 * Unit test for simple App.
 */
public class TC01 extends BaseClass {

	
	@Test
	public  void TC01_login() {
		LoginManagement loginManagement = new LoginManagement(driver);
		driver.get("https://www.saucedemo.com/");
		loginManagement.Login("standard_user","secret_sauce");
	}
}
