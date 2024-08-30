/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.functions;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import apex.basepage.BaseClass;
import apex.pages.CheckoutPage;
import apex.pages.CheckoutPage.CheckoutActions;
import apex.pages.FavouritesPage;
import apex.pages.FavouritesPage.FavouritesActions;
import apex.pages.MainPage;
import apex.pages.MainPage.MainActions;
import apex.pages.OffersPage;
import apex.pages.OrdersPage;
import apex.pages.OrdersPage.OrdersActions;
import apex.pages.SignInPage;
import apex.pages.SignInPage.SignInActions;
import apex.utils.WebDriverUtils;


public class OrderManagement extends BaseClass{
	private WebDriverUtils driverUtils;
    private MainPage mainPage;
    private MainActions mainActions;
    
    private SignInPage signInPage;
    private SignInActions signInActions;
    
    private CheckoutPage checkoutPage;
    private CheckoutActions checkoutActions;
    
    private OrdersPage ordersPage;
    private OrdersActions ordersActions;
    
    private FavouritesPage favouritesPage;
    private FavouritesActions favouritesActions;

    public OrderManagement(WebDriver driver) {
    	this.driverUtils = new WebDriverUtils(driver);
    	
        this.mainPage = new MainPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
        this.signInPage = new SignInPage(driver);
        this.ordersPage = new OrdersPage(driver);
        this.favouritesPage = new FavouritesPage(driver);
        
        this.mainActions = mainPage.new MainActions();
        this.checkoutActions = checkoutPage.new CheckoutActions();
        this.signInActions = signInPage.new SignInActions();
        this.ordersActions = ordersPage.new OrdersActions();
        this.favouritesActions = favouritesPage.new FavouritesActions();
    }

    /*
     * adds dynamic item to cart
     */
    public void addPhoneToCart() {
    	log.info(mainActions.closeCartQty().getAttribute("textContent"));
    	String oldCartQty = mainActions.closeCartQty().getAttribute("textContent");
    	mainActions.clickDynamicAddButton(1);
    	
    	driverUtils.waitForElementToBeVisible(mainActions.openCartQty(), 5);
//    	waitForElementToBeVisible(mainPage.lblOpenCartQty, 5);
    	String newCartQty = mainActions.openCartQty().getAttribute("textContent");
    	
    	assertThat(Integer.parseInt(newCartQty)).describedAs("Qty did not increase").isEqualTo((Integer.parseInt(oldCartQty)+1));
    	log.info(mainActions.openCartQty().getAttribute("textContent") + " item has been added to the cart");
    }
    
    /*
     * proceed to checkout
     */
    public void proceedToCheckout() {
    	mainActions.proceedToCheckout();
    	pause(3);
    	assertThat(checkoutActions.frmCheckout().isDisplayed()).describedAs("Checkout form is NOT displayed in a not signed-in user!").isTrue();
    	log.info("Checkout form is displayed in a not signed-in user!");
    }
    
    /*
     * verify if user is not signed-in
     */
    public void verifySignedOutUser() {
    	assertThat(mainActions.signIn().isDisplayed()).describedAs("Signin button is missing").isTrue();
    	log.info("User is currently logged out");
    }
    
    /*
     * click signin button
     */
    public void verifySignInPage() {
    	mainActions.clickSignInButton();
    	pause(1);
    	assertThat(signInActions.inputUsername().isDisplayed()).describedAs("The Log-in button is not displayed").isTrue();
    	assertThat(signInActions.inputPassword().isDisplayed()).describedAs("The Log-in button is not displayed").isTrue();
    	assertThat(signInActions.btnLogin().isDisplayed()).describedAs("The Log-in button is not displayed").isTrue();
    	log.info("The login fields and button is displayed");
    }
    
    
    public void addMultiplePhoneToCartWithQuantities(int newItemQty) {
    	log.info(mainActions.closeCartQty().getAttribute("textContent"));
    	String oldCartQty = mainActions.closeCartQty().getAttribute("textContent");
    	mainActions.clickDynamicAddButton(1);
    	mainActions.clickDynamicAddButton(2);
    	mainActions.clickDynamicAddButton(3);
    	
    	int itemCount = mainActions.openCartItems().size();
    	log.info("item list count inside the cart: "+ itemCount);
    	
    	for(int i=0; i<mainActions.openCartItems().size(); i++) {
    		mainActions.openCartIncreaseQty().get(i).click();
    	}
    	
    	driverUtils.waitForElementToBeVisible(mainActions.openCartQty(), 3);
//    	waitForElementToBeVisible(mainPage.lblOpenCartQty, 3);
    	String newCartQty = mainActions.openCartQty().getAttribute("textContent");
    	
    	assertThat(Integer.parseInt(newCartQty)).describedAs("Qty did not increase").isEqualTo((Integer.parseInt(oldCartQty)+newItemQty));
    	log.info(mainActions.openCartQty().getAttribute("textContent") + " items has been added to the cart");
    }    
    
    
    public void verifyCartSubTotal(String subTotal) {
    	assertThat(mainActions.openCartSubtotalPrice().getAttribute("innerText")).describedAs("Subtotal doesnt match" + subTotal).isEqualTo(subTotal);
    	log.info("the sub total is: " + mainActions.openCartSubtotalPrice().getAttribute("innerText"));
    }
    
    public void navigateToOrdersPage() {
    	mainActions.clickOrders();
    }
    
    
    public void verifyOrderDetails(String itemPrice, String itemDescription) {
    	 String itemDetails = ordersActions.verifyItemDetails();
    	 assertThat(itemDetails).describedAs("Item not match").contains(itemPrice);
    	 assertThat(itemDetails).describedAs("Item not match").contains(itemDescription);
    	 log.info("This is the item Details " +itemDetails);
    	 
    }
    
    public void addItemToFavourites() {
    	mainActions.addItemToFavourites();
    	assertThat(mainActions.shelfItemFavourite().getAttribute("class").contains("clicked")).describedAs("The item is not added on favourites!").isTrue();
    	log.info("The Item has been added to favourites!");
    	
    }
    
    public void navigateToFavouritesPage() {
    	mainActions.clickFavourites();
     	assertThat(mainActions.shelfItemFavourite().getAttribute("class").contains("clicked")).describedAs("No item in the favourites!").isTrue();
     	log.info("This item is on your favourites!");
    }
    
    public void verifyDynamicItemName(String itemName) {
    	WebElement dynamicItemName = favouritesActions.getDynamicItemName(1);
    	assertThat(dynamicItemName.getAttribute("innerText")).describedAs("Incorrect Item!").contains(itemName);
    	log.info("Correct Item on the favourite! "+dynamicItemName.getAttribute("innerText"));
    	
    }
    

}
