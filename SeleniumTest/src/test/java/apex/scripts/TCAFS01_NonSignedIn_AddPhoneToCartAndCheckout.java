package apex.scripts;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import apex.basepage.BaseClass;
import apex.functions.OrderManagement;
import apex.functions.PaymentManagement;
import apex.utils.ConfigManager;
import apex.utils.PropertiesDataFile;

import static org.assertj.core.api.Assertions.assertThat;

public class TCAFS01_NonSignedIn_AddPhoneToCartAndCheckout extends BaseClass {
    private PropertiesDataFile testData;

    @BeforeTest
    public void setUpTestData() {
    	
    	/*
    	 * load Test Data 
    	 */
    	String dataFolder = System.getProperty("user.dir") + "/src/test/java/apex/data/TCAFS01/";
        testData = new PropertiesDataFile(dataFolder + "TCAFS01.properties");
        
    }

    @Test
    public void TCAFS01_NonSignedIn_AddPhoneToCartAndCheckout() {
    	String baseUrl = ConfigManager.getProperty("baseUrl");
    	
        /*
         * Verify that navigated to correct environment url
         */
    	assertThat(driver.getCurrentUrl()).describedAs("Browser not matched!").isEqualTo(baseUrl);
    	System.out.println("Navigated to: " + driver.getCurrentUrl());
    	
        /*
         * Add item to cart and proceed to checkout
         */
        OrderManagement orderManagement = new OrderManagement(driver);
        orderManagement.addPhoneToCart();
        orderManagement.proceedToCheckout();

        /*
         *  Retrieve data from TCAFS01.properties and fill in the details
         */
        PaymentManagement paymentManagement = new PaymentManagement(driver);
        paymentManagement.fillInDetails(
            testData.get("firstName"), 
            testData.get("lastName"), 
            testData.get("address"), 
            testData.get("province"), 
            testData.get("postalCode")
        );
        
        paymentManagement.continueShopping();
    }
}
