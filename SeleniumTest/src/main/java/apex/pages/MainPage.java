package apex.pages;
import apex.utils.WebDriverUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import apex.basepage.BasePageObject;

public class MainPage extends BasePageObject<MainPage>{
	
	public MainPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.XPATH, using = "//a[@id='offers']")
	public WebElement btnMenuHeaderOffers;

	@FindBy(how = How.XPATH, using = "//a[@id='orders']")
	public WebElement btnMenuHeaderOrders;

	@FindBy(how = How.XPATH, using = "//a[@id='favourites']")
	public WebElement btnMenuHeaderFavourites;
	
	@FindBy(how = How.XPATH, using = "//span[@class='username']")
	public WebElement btnMenuHeaderUsername;
	
	@FindBy(how = How.XPATH, using = "//span[@id='logout']")
	public WebElement btnMenuHeaderLogout;
	
	@FindBy(how = How.XPATH, using = "//span[@id='logout']")
	public WebElement btnOpenCart;
	
	@FindBy(how = How.XPATH, using = "//div[@class='float-cart__close-btn']")
	public WebElement btnCloseCart;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='float-cart']//span[contains(@class,'quantity')])[1]")
	public WebElement lblCloseCartQty;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'float-cart--open')]//span[@class='bag__quantity']")
	public WebElement lblOpenCartQty;	
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'float-cart--open')]//div[@class='shelf-item']")
	public List<WebElement> openCartItems;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'float-cart--open')]//div[@class='shelf-item__del']")
	public WebElement btnOpenCartDeleteItem;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'float-cart--open')]//div[@class='shelf-item']//div[@class='shelf-item__price']")
	public WebElement lblOpenCartItemPrice;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'float-cart--open')]//div[@class='shelf-item']//button[@class='change-product-button'][1]")
	public WebElement btnOpenCartDecreaseQty;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'float-cart--open')]//div[@class='shelf-item']//button[@class='change-product-button'][2]")
	public List<WebElement> btnOpenCartIncreaseQty;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'float-cart--open')]//p[@class='sub-price__val']")
	public WebElement lblOpenCartSubTotalPrice;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'float-cart--open')]//div[@class='buy-btn']")
	public WebElement btnOpenCartCheckout;
	
	@FindBy(how = How.XPATH, using = "//*[@class='shelf-container-header']")
	public WebElement itemHeader;
	
	@FindBy(how = How.XPATH, using = "//*[@class='shelf-container-header']//div[@class='sort']//select//option[@value='%s']")
	public WebElement orderByFilter;
	
	@FindBy(how = How.XPATH, using = "//div[@class='filters']")
	public WebElement vendorFilter;

	@FindBy(how = How.XPATH, using = "//input[1]//following-sibling::span[contains(text(),'%s')")
	public WebElement availableVendor;
	
	@FindBy(how = How.XPATH, using = "//div[@class='shelf-container']//div[@class='shelf-item']")
	public WebElement availablePhones;
	
	@FindBy(how = How.XPATH, using = "	//div[@class='shelf-container']//div[@class='shelf-item']//div[@class='shelf-item__buy-btn']")
	public WebElement availablePhonesAddToCart;
	
	@FindBy(how = How.XPATH, using = "	//div[@class='shelf-container']//div[@class='shelf-item']//div[@class='shelf-item__buy-btn']['%s']")
	public WebElement availablePhonesAddToCartDynamic;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='shelf-container']//div[@class='shelf-item']//button)[1]")
	public WebElement shelfItemFavourite;
	
	@FindBy(how = How.ID, using = "signin")
	public WebElement btnSignIn;
	
	
	
	
	/**
	 * Start of Dynamic Elements
	 * @author Seph
	 *
	 */
	
    // Utility method to get the "Add to Cart" button for a specific index
    public WebElement getAddToCartButton(int index) {
        String dynamicXpath = String.format("//div[@class='shelf-container']//div[@class='shelf-item'][%d]//div[@class='shelf-item__buy-btn']", index);
        return driver.findElement(By.xpath(dynamicXpath));
    }
    
    
    /**
     * Start of Menu Actions
     * @author Seph
     *
     */

	public class MainActions{
	    public void clickOffers() {
	        webDriverUtils.click(btnMenuHeaderOffers);
	    }

	    public void clickOrders() {
	        webDriverUtils.click(btnMenuHeaderOrders);
	    }
	    
        public void clickDynamicAddButton(int index) {
            WebElement addToCartButton = getAddToCartButton(index);
            webDriverUtils.click(addToCartButton);
        }
        
        public void proceedToCheckout() {
        	webDriverUtils.clickUsingJS(btnOpenCartCheckout);
        }
        
        public void clickSignInButton() {
        	webDriverUtils.click(btnSignIn);
        	
        }
        public void addItemToFavourites() {
        	webDriverUtils.click(shelfItemFavourite);
        }
        public void clickFavourites() {
        	webDriverUtils.click(btnMenuHeaderFavourites);
        }

	}
}