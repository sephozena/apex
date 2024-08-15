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
    	System.out.println(mainPage.lblCloseCartQty.getAttribute("textContent"));
    	String oldCartQty = mainPage.lblCloseCartQty.getAttribute("textContent");
    	mainActions.clickDynamicAddButton(1);
    	
    	driverUtils.waitForElementToBeVisible(mainPage.lblOpenCartQty, 5);
//    	waitForElementToBeVisible(mainPage.lblOpenCartQty, 5);
    	String newCartQty = mainPage.lblOpenCartQty.getAttribute("textContent");
    	
    	assertThat(Integer.parseInt(newCartQty)).describedAs("Qty did not increase").isEqualTo((Integer.parseInt(oldCartQty)+1));
    	System.out.println(mainPage.lblOpenCartQty.getAttribute("textContent") + " item has been added to the cart");
    }
    
    /*
     * proceed to checkout
     */
    public void proceedToCheckout() {
    	mainActions.proceedToCheckout();
    	driverUtils.pause(3);
    	assertThat(checkoutPage.frmCheckout.isDisplayed()).describedAs("Checkout form is NOT displayed in a not signed-in user!").isTrue();
    	System.out.println("Checkout form is displayed in a not signed-in user!");
    }
    
    /*
     * verify if user is not signed-in
     */
    public void verifySignedOutUser() {
    	assertThat(mainPage.btnSignIn.isDisplayed()).describedAs("Signin button is missing").isTrue();
    	System.out.println("User is currently logged out");
    }
    
    /*
     * click signin button
     */
    public void verifySignInPage() {
    	mainActions.clickSignInButton();
    	driverUtils.pause(1);
    	assertThat(signInPage.inputUsername.isDisplayed()).describedAs("The Log-in button is not displayed").isTrue();
    	assertThat(signInPage.inputPassword.isDisplayed()).describedAs("The Log-in button is not displayed").isTrue();
    	assertThat(signInPage.btnLogin.isDisplayed()).describedAs("The Log-in button is not displayed").isTrue();
    	System.out.println("The login fields and button is displayed");
    }
    
    
    public void addMultiplePhoneToCartWithQuantities(int newItemQty) {
    	System.out.println(mainPage.lblCloseCartQty.getAttribute("textContent"));
    	String oldCartQty = mainPage.lblCloseCartQty.getAttribute("textContent");
    	mainActions.clickDynamicAddButton(1);
    	mainActions.clickDynamicAddButton(2);
    	mainActions.clickDynamicAddButton(3);
    	
    	int itemCount = mainPage.openCartItems.size();
    	System.out.println("item list count inside the cart: "+ itemCount);
    	
    	for(int i=0; i<mainPage.openCartItems.size(); i++) {
    		mainPage.btnOpenCartIncreaseQty.get(i).click();
    	}
    	
    	driverUtils.waitForElementToBeVisible(mainPage.lblOpenCartQty, 3);
//    	waitForElementToBeVisible(mainPage.lblOpenCartQty, 3);
    	String newCartQty = mainPage.lblOpenCartQty.getAttribute("textContent");
    	
    	assertThat(Integer.parseInt(newCartQty)).describedAs("Qty did not increase").isEqualTo((Integer.parseInt(oldCartQty)+newItemQty));
    	System.out.println(mainPage.lblOpenCartQty.getAttribute("textContent") + " items has been added to the cart");
    }    
    
    
    public void verifyCartSubTotal(String subTotal) {
    	assertThat(mainPage.lblOpenCartSubTotalPrice.getAttribute("innerText")).describedAs("Subtotal doesnt match").isEqualTo(subTotal);
    	System.out.println("the sub total is: " + mainPage.lblOpenCartSubTotalPrice.getAttribute("innerText"));
    }
    
    public void navigateToOrdersPage() {
    	mainActions.clickOrders();
    }
    
    
    public void verifyOrderDetails(String itemPrice, String itemDescription) {
    	 String itemDetails = ordersActions.verifyItemDetails();
    	 assertThat(itemDetails).describedAs("Item not match").contains(itemPrice);
    	 assertThat(itemDetails).describedAs("Item not match").contains(itemDescription);
    	 System.out.println("This is the item Details " +itemDetails);
    	 
    }
    
    public void addItemToFavourites() {
    	mainActions.addItemToFavourites();
    	assertThat(mainPage.shelfItemFavourite.getAttribute("class").contains("clicked")).describedAs("The item is not added on favourites!").isTrue();
    	System.out.println("The Item has been added to favourites!");
    	
    }
    
    public void navigateToFavouritesPage() {
    	mainActions.clickFavourites();
     	assertThat(mainPage.shelfItemFavourite.getAttribute("class").contains("clicked")).describedAs("No item in the favourites!").isTrue();
     	System.out.println("This item is on your favourites!");
    }
    
    public void verifyDynamicItemName(String itemName) {
    	WebElement dynamicItemName = favouritesActions.getDynamicItemName(1);
    	assertThat(dynamicItemName.getAttribute("innerText")).describedAs("Incorrect Item!").contains(itemName);
    	System.out.println("Correct Item on the favourite! "+dynamicItemName.getAttribute("innerText"));
    	
    }
    

}