package apex.scripts;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apex.basepage.BaseClass;
import apex.functions.OrderManagement;
import apex.functions.PaymentManagement;
import apex.functions.SignInManagement;
import apex.utils.ConfigManager;
import apex.utils.PropertiesDataFile;
import apex.utils.ThreadUtils;

public class TCAFS02_SignedIn_AddPhoneToCartAndCheckoutWithDifferentQuantities extends BaseClass {
    private PropertiesDataFile testData;
	
	@BeforeTest
    public void setUpTestData() throws IOException {
    	/*
    	 * load Test Data 
    	 */
		
//    	String dataFolder = System.getProperty("user.dir") + "/src/test/java/apex/data/TCAFS02/";
//        testData = new PropertiesDataFile(dataFolder + "TCAFS02.properties");
//        System.out.println(dataFolder);
	    String propertiesFilePath = "resources/data/TCAFS02/TCAFS02.properties";
	    testData = new PropertiesDataFile(propertiesFilePath);
    }
	
	@Test(groups = {"signed-in"})
	@Parameters({"browser"})
	public void TCAFS02_SignedIn_AddPhoneToCartAndCheckoutWithDifferentQuantities() {
	    Logger log = log();

    	String baseUrl = ConfigManager.getProperty("baseUrl");
    	
        /*
         * Verify that navigated to correct environment url
         */
    	assertThat(driver.getCurrentUrl()).describedAs("Browser not matched!").isEqualTo(baseUrl);
    	System.out.println("Navigated to: " + driver.getCurrentUrl());
    	log.info("teest");
    	
        /*
         * Sign-in user
         */
        OrderManagement orderManagement = new OrderManagement(driver);
        orderManagement.verifySignedOutUser();
        orderManagement.verifySignInPage();
        
        SignInManagement signInManagement = new SignInManagement(driver);
        signInManagement.enterValidInfoAndLogin();

        orderManagement.addMultiplePhoneToCartWithQuantities(Integer.parseInt(testData.get("verifyNewItemQty")));
        System.out.println(testData.get("subTotal") + " this is the subtotal from test data");
        orderManagement.verifyCartSubTotal(testData.get("subTotal"));
        
        
        /*
         * set new data 
         * split the data and store in an string array
         * retrieve specific data
         */
        testData.set("newTestData", "this is my new test data1 data2 data3 data4");
        String newTestData = testData.get("newTestData");
        String[] newTestDataIndex = newTestData.split(";");
        System.out.println(" @@@@@@@@@@ sample text lorem ipsum" + " there are " +newTestDataIndex.length +" test datas available. getting data on index(2) text lorem ipsum "+  newTestDataIndex[0]);
        
        /*
         * proceed on checkout order and do calculations
         */
        orderManagement.proceedToCheckout();
        PaymentManagement paymentManagement = new PaymentManagement(driver);
        paymentManagement.getTotalAmount();
	}
	
}
