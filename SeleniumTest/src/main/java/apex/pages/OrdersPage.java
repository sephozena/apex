package apex.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import apex.basepage.BasePageObject;
import apex.utils.WebDriverUtils;

public class OrdersPage extends BasePageObject<OrdersPage>{

	public OrdersPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how = How.XPATH, using = "//*[@class='a-box shipment shipment-is-delivered']")
	private List<WebElement> listOfPlacedOrders;
	
	
	@FindBy(how = How.XPATH, using = "(//*[@class='a-box shipment shipment-is-delivered']//descendant-or-self::div//span[contains(.,'$')])[1]")
	private WebElement firstItemPrice;
	
	@FindBy(how = How.XPATH, using = "(//*[@class='a-box shipment shipment-is-delivered']//descendant-or-self::div[@class='a-row'])[4]")
	private WebElement firstItemDescription;
	
	
	
	
	public class OrdersActions {
		
		public  String verifyItemDetails() {
			var itemDescription = firstItemDescription.getAttribute("innerText").trim();
			var itemPrice = firstItemPrice.getAttribute("innerText").trim();
			return itemDescription +" - "+ itemPrice;
			
		}
		
	}
	
}
