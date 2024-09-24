package scripts;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import functions.LoginManagement;

public class TC01_Login extends BaseClass {

	@Test
	public void TC01() {
		String url = "https://practicetestautomation.com/practice-test-login/";
		driver.get(url);
		LoginManagement loginManagement = new LoginManagement(driver);
		loginManagement.Login("student", "Password123");
		
		assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/logged-in-successfully/");
	      int[] numbers = {1, 2,3,4,5};
	        int sum = 0;
	        
	        for (int num : numbers) {
	            if (num % 2 == 0) {
	                System.out.println(num);
	            	sum += num;
	            }
	        }
	        
	        System.out.println("Sum using enhanced for loop: " + sum);
	        
	        int sum2 = 0;
	        int sum3 = 0;
	        for (int i = 0; i < numbers.length; i++) {
	        	sum3 = sum3 + numbers[i];
	        	System.out.println(sum3);
	        	if(numbers[i] % 2 == 0) {
	        		sum2 = sum2 + numbers[i];
	        		System.out.println(sum2);
	        	}
	        	
	        }
	        System.out.println("Sum using enhanced for loop: " + sum3);
	        System.out.println("Sum using enhanced for loop: " + sum2);
	}
	
}
