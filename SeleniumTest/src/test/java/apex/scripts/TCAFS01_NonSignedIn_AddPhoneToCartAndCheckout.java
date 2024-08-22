package apex.scripts;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apex.basepage.BaseClass;
import apex.functions.OrderManagement;
import apex.functions.PaymentManagement;
import apex.utils.ConfigManager;
import apex.utils.ExtentReportManager;
import apex.utils.PropertiesDataFile;

public class TCAFS01_NonSignedIn_AddPhoneToCartAndCheckout extends BaseClass {
    private PropertiesDataFile testData;

    @BeforeTest
    public void setUpTestData() {
        String propertiesFilePath = "resources/data/TCAFS01/TCAFS01.properties";
        testData = new PropertiesDataFile(propertiesFilePath);
    }

    @Test(groups = {"non-signedin"})
    @Parameters({"browser"})
    public void TCAFS01_NonSignedIn_AddPhoneToCartAndCheckout() {
//        ExtentReportManager.createTest("TCAFS01_NonSignedIn_AddPhoneToCartAndCheckout");

        try {
            String baseUrl = ConfigManager.getProperty("baseUrl");
            assertThat(driver.getCurrentUrl()).describedAs("Browser not matched!").isEqualTo(baseUrl);
            log.info("Navigated to: " + driver.getCurrentUrl());

            OrderManagement orderManagement = new OrderManagement(driver);
            orderManagement.addPhoneToCart();
            orderManagement.proceedToCheckout();

            PaymentManagement paymentManagement = new PaymentManagement(driver);
            paymentManagement.fillInDetails(
                testData.get("firstName"),
                testData.get("lastName"),
                testData.get("address"),
                testData.get("province"),
                testData.get("postalCode")
            );
            
            paymentManagement.continueShopping();
            log.info("Successfully completed the checkout process.");
        } catch (Exception e) {
            handleTestFailure("TCAFS01_Failure");
        }
    }
}
