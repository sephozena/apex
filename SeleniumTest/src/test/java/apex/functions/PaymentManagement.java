/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.functions;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;

import apex.basepage.BaseClass;
import apex.pages.CheckoutPage;
import apex.pages.CheckoutPage.CheckoutActions;
import apex.pages.ConfirmationPage;
import apex.pages.ConfirmationPage.ConfirmationActions;

public class PaymentManagement extends BaseClass {
	
	private CheckoutPage checkoutPage;
	private CheckoutActions checkoutActions;
	
	private ConfirmationPage confirmationPage;
	private ConfirmationActions confirmationActions;

    public PaymentManagement(WebDriver driver) {
        this.checkoutPage = new CheckoutPage(driver);
        this.checkoutActions = checkoutPage.new CheckoutActions();
        
        this.confirmationPage = new ConfirmationPage(driver);
        this.confirmationActions = confirmationPage.new ConfirmationActions();
    }
    
    
    public void fillInDetails(String firstName, String lastName, String address, String province, String postalCode) {
    	checkoutActions.fillInCheckoutDetails(firstName, lastName, address, province, postalCode);
    	log.info("Checkout form has been filled with the following details: " + firstName +" "+ lastName +" "+  address+" "+ province +" "+ postalCode);
    	checkoutActions.submitCheckout();
    }
    
    
    public void getTotalAmount() {
    	int totalAmount = checkoutActions.getTotalAmount();    	
    	
    	String orderSummaryTotalAmountString = checkoutActions.orderSummaryTotalPrice().getAttribute("innerText").replaceAll("[^\\d.]", "").trim();
    	int orderSummaryTotalAmount = (int)Double.parseDouble(orderSummaryTotalAmountString);
	
    	assertThat(orderSummaryTotalAmount).describedAs("Total amount doesnt match!").isEqualTo(totalAmount);
    	log.info("The total amount is correct! "+ orderSummaryTotalAmount);
    }
    
    public void continueShopping() {
    	confirmationActions.proceedToMainPage();
    }
    
}
