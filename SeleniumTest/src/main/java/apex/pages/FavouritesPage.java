/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import apex.basepage.BasePageObject;
import apex.utils.WebDriverUtils;

public class FavouritesPage extends BasePageObject<FavouritesPage>{

	public FavouritesPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(how = How.XPATH, using = "//div[@class='shelf-item']")
	private List<WebElement> shelfItem;
	
	@FindBy(how = How.XPATH, using = "//p[@class='shelf-item__title']")
	private WebElement lblFavouriteName;
	
	@FindBy(how = How.XPATH, using = "//div[@class='shelf-item__price']//div[@class='val']")
	private WebElement lblFavouritePrice;
	
    // Utility method to get the "Add to Cart" button for a specific index
	private WebElement dynamicShelfItemName(int index) {
        String dynamicXpath = String.format("(//p[@class='shelf-item__title'])['%s']", index);
        return driver.findElement(By.xpath(dynamicXpath));
    }
    
    public class FavouritesActions {
    	public   WebElement getDynamicItemName(int index) {
    		WebElement dynamicItemName = dynamicShelfItemName(index);
    		return dynamicItemName;
    	}
    }
    
	
}
