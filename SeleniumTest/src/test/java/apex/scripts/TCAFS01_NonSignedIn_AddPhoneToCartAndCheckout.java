package apex.scripts;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import apex.basepage.BaseClass;
import apex.functions.OrderManagement;
import apex.functions.PaymentManagement;
import apex.utils.ConfigManager;
import apex.utils.PropertiesDataFile;
import apex.utils.ScreenshotUtils;

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
            String baseUrl = ConfigManager.getProperty("baseUrl");
            assertThat(driver.getCurrentUrl()).describedAs("Browser not matched!").isEqualTo(baseUrl);
            log.info("Navigated to: " + driver.getCurrentUrl());

            OrderManagement orderManagement = new OrderManagement(driver);
            orderManagement.addPhoneToCart();
            ScreenshotUtils.logWithScreenshot(driver, "phone successfully added to cart", Status.INFO);
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
    }
}
