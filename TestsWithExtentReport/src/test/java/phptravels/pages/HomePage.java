package phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import phptravels.base.BasePage;


public class HomePage extends BasePage{

	// Web elements	
	@FindBy(xpath="//nav//li[@id='li_myaccount']/a")
	WebElement myAccount_dropdownMenu;
	
	@FindBy(xpath="//nav//a[@href='https://www.phptravels.net/login']")
	WebElement login_dropdownMenu;
	
	@FindBy(xpath="//nav//a[@href='https://www.phptravels.net/register']")
	WebElement signUp_dropdownMenu;

	
	// constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	// Home page methods:	
	
	public HomePage goToHomePage(String homePageURL) {
		driver.get(homePageURL);
		return this;
	}
	
	public void verifyHomePage(String homeTitle) {
		verifyPageTitle(homeTitle);
	}
	
	public LoginPage goToLoginPage() {
		click(myAccount_dropdownMenu);
		click(login_dropdownMenu);
		return new LoginPage(driver);
	}
	
	public RegisterPage goToRegisterPage() {
		click(myAccount_dropdownMenu);
		click(signUp_dropdownMenu);
		return new RegisterPage(driver);
	}
	
}
