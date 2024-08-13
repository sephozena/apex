package apex.scripts;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.BeforeTest;
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
    	String dataFolder = System.getProperty("user.dir") + "/src/test/java/apex/data/TCAFS03/";
        testData = new PropertiesDataFile(dataFolder + "TCAFS03.properties");
    }
    @Test
    public void TCAFS03_SignedIn_ViewOrderHistory() {
    	String baseUrl = ConfigManager.getProperty("baseUrl");
    	
        /*
         * Verify that navigated to correct environment url
         */
    	assertThat(driver.getCurrentUrl()).describedAs("Browser not matched!").isEqualTo(baseUrl);
    	System.out.println("Navigated to: " + driver.getCurrentUrl());
    	
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
