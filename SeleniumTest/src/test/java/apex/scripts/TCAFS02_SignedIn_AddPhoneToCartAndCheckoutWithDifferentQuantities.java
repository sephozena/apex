package apex.scripts;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import apex.basepage.BaseClass;
import apex.functions.OrderManagement;
import apex.functions.PaymentManagement;
import apex.functions.SignInManagement;
import apex.utils.ConfigManager;
import apex.utils.PropertiesDataFile;

public class TCAFS02_SignedIn_AddPhoneToCartAndCheckoutWithDifferentQuantities extends BaseClass {
    private PropertiesDataFile testData;

	
	@BeforeTest
    public void setUpTestData() {
    	
    	/*
    	 * load Test Data 
    	 */
    	String dataFolder = System.getProperty("user.dir") + "/src/test/java/apex/data/TCAFS02/";
        testData = new PropertiesDataFile(dataFolder + "TCAFS02.properties");
    }
	
	@Test
	public void TCAFS02_SignedIn_AddPhoneToCartAndCheckoutWithDifferentQuantities() {
    	String baseUrl = ConfigManager.getProperty("baseUrl");
    	
        /*
         * Verify that navigated to correct environment url
         */
    	assertThat(driver.getCurrentUrl()).describedAs("Browser not matched!").isEqualTo(baseUrl);
    	System.out.println("Navigated to: " + driver.getCurrentUrl());
    	
        OrderManagement orderManagement = new OrderManagement(driver);
        orderManagement.verifySignedOutUser();
        orderManagement.verifySignInPage();
        
        SignInManagement signInManagement = new SignInManagement(driver);
        signInManagement.enterValidInfoAndLogin();

        orderManagement.addMultiplePhoneToCartWithQuantities(Integer.parseInt(testData.get("verifyNewItemQty")));
        orderManagement.verifyCartSubTotal(testData.get("subTotal"));
        orderManagement.proceedToCheckout();
        
        PaymentManagement paymentManagement = new PaymentManagement(driver);
        paymentManagement.getTotalAmount();
	}
	
}
