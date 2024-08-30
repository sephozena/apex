/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.functions;
import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import apex.basepage.BaseClass;
import apex.pages.MainPage;
import apex.pages.MainPage.MainActions;
import apex.pages.SignInPage;
import apex.pages.SignInPage.SignInActions;
import apex.utils.ConfigManager;
import apex.utils.WebDriverUtils;

public class SignInManagement extends BaseClass {
	private WebDriverUtils driverUtils;

	private SignInPage signInPage;
	private SignInActions signInActions;
	
	private MainPage mainPage;
	private MainActions mainActions;


    public SignInManagement(WebDriver driver) {
    	this.driverUtils = new WebDriverUtils(driver);
    	this.signInPage = new SignInPage(driver);
    	this.mainPage = new MainPage(driver);
    	
    	
    	this.signInActions = signInPage.new SignInActions();
    	this.mainActions = mainPage.new MainActions();
    }
    
    public void enterValidInfoAndLogin() {
    	
        String username = ConfigManager.getProperty("username",0);
        String password = ConfigManager.getProperty("password",0);
        signInActions.signIn(username, password);
        pause(3);
        assertThat(mainActions.menuHeaderUsername().getAttribute("textContent")).describedAs("Username not found! ").isEqualTo(username);
        log.info(mainActions.menuHeaderUsername().getAttribute("textContent") + " successfully signed-in");
        
        

        
        
        
        
    }
}
