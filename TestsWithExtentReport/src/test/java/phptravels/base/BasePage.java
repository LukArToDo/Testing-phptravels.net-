package phptravels.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;


public class BasePage {

	public WebDriver driver;
	public WebDriverWait wait;
	
	// constructor
	public BasePage(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,10);
		PageFactory.initElements(driver, this);
	}
	
	// wait wrapper method
	public void waitVisibility (WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	// wait for page loading method
	public void waitForLoadPage(final WebDriver driver, int TIMEOUT) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver arg0) {
				return ((JavascriptExecutor)driver)
						.executeScript("return document.readyState")
						.equals("complete");
			}			
		};
		WebDriverWait waitPage = new WebDriverWait(driver,TIMEOUT);
		waitPage.until(pageLoadCondition);
	}
	
	// method to verify page title
	public void verifyPageTitle(String title) {
		wait.until(ExpectedConditions.titleContains(title));
		assertTrue(driver.getTitle().equals(title));
	}
	
	// write (send) text method
	public void writeText(WebElement element, String text) {
		waitVisibility(element);
		element.clear();
		element.sendKeys(text);
	}
	
	// read (get) text method - get name or label of WebElement
	public String readText (WebElement element) {
		waitVisibility(element);
		return element.getText().trim();
	}
	
	// read (get) text data method - get (entered) data on WebElement (email, password, etc.)
	public String readTextData (WebElement element) {
		waitVisibility(element);
		return element.getAttribute("value").trim();
	}
	
	// click methods:
	
	// Click functionality with chromedriver does not work consistently. Until now, below 'click' method is best solution  
	public void clickChrome (WebElement element) {
		waitVisibility(element);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	// click method for others browser-drivers
	public void click (WebElement element) {
		waitVisibility(element);
		element.click();
	}
	
	
	// assert methods:
	
	// assert Equals method comparing name/label of WebElement with expected Text 
	public void assertEqual(WebElement elementBy, String expectedText) {
		waitVisibility(elementBy);
		Assert.assertEquals(expectedText, readText(elementBy));
	}
	
	// assert Equals method comparing actual Text with expected Text
	public void assertEqual(String actualText, String expectedText) {
		Assert.assertEquals(expectedText, actualText);
	}
	
	// assert True method
	public void assertTrue(Boolean condition) {
		Assert.assertTrue(condition);
	}
}