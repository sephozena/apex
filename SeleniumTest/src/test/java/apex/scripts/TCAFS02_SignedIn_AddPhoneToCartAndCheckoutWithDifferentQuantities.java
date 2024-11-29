/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.scripts;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

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
		String propertiesFilePath = "resources/data/TCAFS02/TCAFS02.properties";
		testData = new PropertiesDataFile(propertiesFilePath);
	}

	@Test(groups = { "signed-in" })
	@Parameters({ "browser" })
	public void TCAFS02() {
		/*
		 * Sign-in user
		 */
		OrderManagement orderManagement = new OrderManagement(driver);
		orderManagement.verifySignedOutUser();
		orderManagement.verifySignInPage();

		SignInManagement signInManagement = new SignInManagement(driver);
		signInManagement.enterValidInfoAndLogin();

		orderManagement.addMultiplePhoneToCartWithQuantities(Integer.parseInt(testData.get("verifyNewItemQty")));
		log.info(testData.get("subTotal") + " this is the subtotal from test data");
		orderManagement.verifyCartSubTotal(testData.get("subTotal"));
		logWithScreenshot("Verify Subtotal", Status.INFO);

		/*
		 * proceed on checkout order and do calculations
		 */
		orderManagement.proceedToCheckout();
		PaymentManagement paymentManagement = new PaymentManagement(driver);
		paymentManagement.getTotalAmount();
	}

}
