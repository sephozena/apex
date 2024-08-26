/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.scripts;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apex.basepage.BaseClass;
import apex.functions.OrderManagement;
import apex.functions.PaymentManagement;
import apex.functions.SignInManagement;
import apex.utils.ConfigManager;
import apex.utils.PropertiesDataFile;

public class TCAFS03_SignedIn_ViewOrderHistory extends BaseClass{
    private PropertiesDataFile testData;

    @BeforeTest
    public void setUpTestData() {
    	
    	/*
    	 * load Test Data 
    	 */
	    String propertiesFilePath = "resources/data/TCAFS03/TCAFS03.properties";
	    testData = new PropertiesDataFile(propertiesFilePath);
    }
    @Test(groups = {"signed-in"})
    @Parameters({"browser"})
    public void TCAFS03() {
    	String baseUrl = ConfigManager.getProperty("baseUrl");
    	
        /*
         * Verify that navigated to correct environment url
         */
    	assertThat(driver.getCurrentUrl()).describedAs("Browser not matched!").isEqualTo(baseUrl);
    	log.info("Navigated to: " + driver.getCurrentUrl());
    	
        /*
         * proceed to login
         */
        OrderManagement orderManagement = new OrderManagement(driver);
        orderManagement.verifySignedOutUser();
        orderManagement.verifySignInPage();
        
        SignInManagement signInManagement = new SignInManagement(driver);
        signInManagement.enterValidInfoAndLogin();
        
        orderManagement.addPhoneToCart();
        orderManagement.proceedToCheckout();
        
        PaymentManagement paymentManagement = new PaymentManagement(driver);
        paymentManagement.fillInDetails(
            testData.get("firstName"), 
            testData.get("lastName"), 
            testData.get("address"), 
            testData.get("province"), 
            testData.get("postalCode"));
        
        paymentManagement.continueShopping();
        orderManagement.navigateToOrdersPage();
        orderManagement.verifyOrderDetails(
        		testData.get("itemDescription"),
        		testData.get("itemPrice"));
    }
}
