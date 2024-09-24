package scripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Quick_N_Dirty {
	
	@Test
	public void TC02() {
		// Initialize WebDriver
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
		
		
		final String expectedTitle = "Test login";
		final String expectedSuccessfulLogin = "Logged In Successfully";
		
		// Verify user navigation
		driver.get("https://practicetestautomation.com/practice-test-login/");
		
		// Web elements
		WebElement txtLogin = driver.findElement(By.xpath("//section//h2[contains(.,'Test login')]"));
		WebElement inputUsername = driver.findElement(By.id("username"));
		WebElement inputPassword = driver.findElement(By.id("password"));
		WebElement btnLogin = driver.findElement(By.id("submit"));

		

		// Assert landing page
		Assert.assertEquals(txtLogin.getText(), expectedTitle , "Failed to navigate to the login page!");
		System.out.println(txtLogin.getText());
		
		
		// Web actions
		inputUsername.sendKeys("student");
		inputPassword.sendKeys("Password123");
		btnLogin.click();
		
		//Assert successful login page	
        WebElement txtLandingPage = driver.findElement(By.xpath("//h1[contains(.,'Logged In')]"));
        		
        wait.until(ExpectedConditions.visibilityOf(txtLandingPage));
        
        Assert.assertEquals(txtLandingPage.getText(), expectedSuccessfulLogin, "Failed to login");
		System.out.println(txtLandingPage.getText());
		
		//quit browser
		driver.quit();

	}

}
