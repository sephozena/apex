/**
 * @author SephOzena
 * @param My First Test Automation Framework 
 */
package apex.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
//    private WebDriver driver;
//    private WebDriverWait wait;

//    public WebDriverUtils(WebDriver driver) {
//    	this.driver = ThreadUtils.getDriverRef();
//        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10)); // Initialize WebDriverWait
//
//    }
    
    
    public WebDriver getDriver() {
      return ThreadUtils.getDriverRef();
    }
    
    private WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10)); // Default timeout 10 seconds
    }
    

    
    public void click(WebElement element) {
        waitForElementToBeClickable(element, Duration.ofSeconds(10));
        try {
            element.click();
        } catch (Exception e) {
            // Scroll the element into view if necessary
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
        }
    }
    
    public void clickWithRetry(WebElement element, int retryCount) {
        int attempts = 0;
        while (attempts < retryCount) {
            try {
                waitForElementToBeClickable(element, Duration.ofSeconds(10));
                element.click();
                break;
            } catch (StaleElementReferenceException | TimeoutException e) {
                attempts++;
                if (attempts == retryCount) {
                    throw new RuntimeException("Element not clickable after " + retryCount + " attempts", e);
                }
                pause(2); // Add small delay between retries
            }
        }
    }
    


    public boolean isElementPresent(By locator) {
        return !getDriver().findElements(locator).isEmpty();
    }

    public boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
    
    public WebElement findDynamicElement(String xpathTemplate, int index) {
        String xpath = String.format(xpathTemplate, index);
        return getDriver().findElement(By.xpath(xpath));
    }
    
    public void waitForElementToBeVisible(WebElement element, Duration timeout) {
        getWait().withTimeout(timeout).until(ExpectedConditions.visibilityOf(element));
    }

    
    public void waitForElementToBeClickable(WebElement element, Duration timeout) {
    	getWait().withTimeout(timeout).until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public void typeUsingJS(WebElement element, String text) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value='" + text + "';", element);
    }
    
    public void clickUsingJS(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }
    
    // Explicit wait method
    public void waitForElementToBeVisible(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    protected WebElement waitForElement(By locator) {
        try {
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not found within the specified wait time: " + locator, e);
        }
    }
    
    // Pause method
    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}