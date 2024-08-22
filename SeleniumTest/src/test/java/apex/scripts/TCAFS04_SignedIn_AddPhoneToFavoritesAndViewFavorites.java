package apex.scripts;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apex.basepage.BaseClass;
import apex.functions.OrderManagement;
import apex.functions.SignInManagement;
import apex.utils.ConfigManager;
import apex.utils.PropertiesDataFile;

public class TCAFS04_SignedIn_AddPhoneToFavoritesAndViewFavorites extends BaseClass{
    private PropertiesDataFile testData;

    @BeforeTest
    public void setUpTestData() {
    	/*
    	 * load Test Data 
    	 */
	    String propertiesFilePath = "resources/data/TCAFS04/TCAFS04.properties";
	    testData = new PropertiesDataFile(propertiesFilePath);
    }
    
    @Test(groups = {"signed-in"})
    @Parameters({"browser"})
    public void TCAFS04_SignedIn_ViewOrderHistory() {
    	String baseUrl = ConfigManager.getProperty("baseUrl");
    	
        /*
         * Verify that navigated to correct environment url
         */
//    	assertThat(driver.getCurrentUrl()).describedAs("Browser not matched!").isEqualTo(baseUrl);
//    	log.info("Navigated to: " + driver.getCurrentUrl());
    	
        /*
         * proceed to login
         */
        OrderManagement orderManagement = new OrderManagement(driver);
        orderManagement.verifySignedOutUser();
        orderManagement.verifySignInPage();
        
        SignInManagement signInManagement = new SignInManagement(driver);
        signInManagement.enterValidInfoAndLogin();
        
        /*
         * add item to favourites and navigate to favourites
         */
        orderManagement.addItemToFavourites();
        orderManagement.navigateToFavouritesPage();
        
        /*
         * add item to favourites and navigate to favourites
         */
        orderManagement.verifyDynamicItemName(testData.get("itemDescription"));
    }
}
