/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import apex.basepage.BasePageObject;

public class CheckoutPage extends BasePageObject<CheckoutPage> {
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.ID, using = "firstNameInput")
	private WebElement inputFirstName;
	
	@FindBy(how = How.ID, using = "lastNameInput")
	private WebElement inputLastName;

	@FindBy(how = How.ID, using = "addressLine1Input")
	private WebElement inputAddress;
	
	@FindBy(how = How.ID, using = "provinceInput")
	private WebElement inputProvince;
	
	@FindBy(how = How.ID, using = "postCodeInput")
	private WebElement inputPostalCode;
	
	@FindBy(how = How.ID, using = "checkout-shipping-continue")
	private WebElement btnSubmit;
	
	@FindBy(how = How.XPATH, using = "//*[@id='checkout-app']//div[@class='layout-main']")
	private WebElement frmCheckout;
	
	@FindBy(how = How.XPATH, using = "//*[@id='checkout-app']//article[@class='cart optimizedCheckout-orderSummary']")
	private WebElement frmOrderSummary;
	
	
	@FindBy(how = How.XPATH, using = "//header/following-sibling::section//ul[@class='productList']/li//div[contains(text(),'$')]")
	public List<WebElement> orderSummaryProductListPrice;
	
	
	@FindBy(how = How.XPATH, using = "//header/following-sibling::section[2]")
	private WebElement orderSummaryTotalPrice;

	public class CheckoutActions{
		
	    // Method to fill in checkout details
	    public void fillInCheckoutDetails(String firstName, String lastName, String address, String province, String postalCode) {
	        inputFirstName.sendKeys(firstName);
	        inputLastName.sendKeys(lastName);
	        inputAddress.sendKeys(address);
	        inputProvince.sendKeys(province);
	        inputPostalCode.sendKeys(postalCode);
	    }

	    // Method to submit the checkout
	    public void submitCheckout() {
	        btnSubmit.click();
	    }
	    
	    public int getTotalAmount() {
	    	var productListPrice = new ArrayList<String>();
	    	int productListCount = orderSummaryProductListPrice.size();
	    	System.out.println(orderSummaryProductListPrice.size() +" this is the productListCount");
	    	
	    	for(int i=0; i<productListCount; i++) {
	    		orderSummaryProductListPrice.get(i).getAttribute("innerText");
	    		productListPrice.add(orderSummaryProductListPrice.get(i).getAttribute("innerText").replaceAll("[^\\d.]", ""));
	    		System.out.println(orderSummaryProductListPrice.get(i).getAttribute("innerText").replaceAll("[^\\d.]", "") + " has been added to the list");
	    	}
	    	// List to store prices as integers
	    	List<Integer> productListPriceInt = new ArrayList<Integer>();

	    	// Convert the string prices to integers and add to the list
	    	for (String priceStr : productListPrice) {
	    	    try {
	    	        // Convert string to integer
	    	        int priceInt = Integer.parseInt(priceStr);
	    	        productListPriceInt.add(priceInt);
	    	    } catch (NumberFormatException e) {
	    	        System.out.println("Error parsing price: " + priceStr);
	    	    }
	    	}

	    	// Calculate the total
	    	int total = 0;
	    	for (int price : productListPriceInt) {
	    	    total += price;
	    	}

	    	// Print the total
	    	System.out.println("Total Price: " + total);	
	    	return total;
	    }
	    
	    public WebElement orderSummaryTotalPrice() {
	    	return orderSummaryTotalPrice;
	    }
	    
	    public WebElement frmCheckout() {
	    	return frmCheckout;
	    }

	}

}
