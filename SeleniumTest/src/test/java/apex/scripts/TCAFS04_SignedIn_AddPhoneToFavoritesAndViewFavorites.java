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
    public void TCAFS04() {    	    	
        /*
         * proceed to login
         */
        OrderManagement orderManagement = new OrderManagement();
        orderManagement.verifySignedOutUser();
        orderManagement.verifySignInPage();
        
        SignInManagement signInManagement = new SignInManagement();
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
