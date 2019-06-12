package phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import phptravels.base.BasePage;


public class LoginPage extends BasePage {
	
	// Web elements
	@FindBy(xpath="//nav//li[@id='li_myaccount']/a")
	@CacheLookup
	WebElement myAccount_dropdownMenu;
	
	@FindBy(xpath="//nav//a[@href='https://www.phptravels.net/login']")
	@CacheLookup
	WebElement login_dropdownMenu;
	
	@FindBy(xpath="//nav//a[@href='https://www.phptravels.net/register']")
	@CacheLookup
	WebElement signUp_dropdownMenu;
	
	@FindBy(xpath="//input[@name='username']")
	@CacheLookup
	WebElement emailField;
	
	@FindBy(xpath="//input[@name='password']")
	@CacheLookup
	WebElement passwordField;
	
	@FindBy(xpath="//input[@name='remember']")
	@CacheLookup
	WebElement rememberMeBox;
	
	@FindBy(xpath="//form[@id='loginfrm']//button[contains(@class,'loginbtn')]")
	@CacheLookup
	WebElement loginBtn ;
	
	@FindBy(xpath="//form[@id='loginfrm']//a[@href='https://www.phptravels.net/register']")
	@CacheLookup
	WebElement signUpBtn ;
	
	@FindBy(xpath="//form[@id='loginfrm']//a[@href='#ForgetPassword']")
	@CacheLookup
	WebElement forgetPasswordBtn ;
	
	@FindBy(xpath="//input[@id='resetemail']")
	@CacheLookup
	WebElement resetEmailField;
	
	@FindBy(xpath="//button[contains(@class,'resetbtn')]")
	@CacheLookup
	WebElement resetEmailBtn;
	
	@FindBy(xpath="//div[@class='alert alert-danger']")
	@CacheLookup
	WebElement errorMessageInvalidEmailOrPass ;
	
	
	// constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// Login page methods:
	
	public LoginPage goToLoginPageWithLink(String loginPageURL) {
		driver.get(loginPageURL);
		waitForLoadPage(driver,30);
		return this;
	}

	public LoginPage goToLoginPageTroughHomePage() {
		return new HomePage(driver).goToLoginPage();
	}
	
	public LoginPage verifyLoginPage(String loginPageTitle) {
		verifyPageTitle(loginPageTitle);
		return this;
	}
	
	public RegisterPage goToRegisterPageWithSignUpButton() {
		click(signUpBtn);
		waitForLoadPage(driver,30);
		return new RegisterPage(driver);
	}
	
	public RegisterPage goToRegisterPageTroughDropdownMenu() {
		click(myAccount_dropdownMenu);
		click(signUp_dropdownMenu);
		waitForLoadPage(driver,30);
		return new RegisterPage(driver);
	}
	
	public LoginPage enterEmail(String email) {
		writeText(emailField, email);
		return this;
	}
	
	public LoginPage enterPassword(String password) {
		writeText(passwordField, password);
		return this;
	}
	
	public AccountPage login() {
		click(loginBtn);
		return new AccountPage(driver);
	}
	
	public LoginPage verifyLoginErrorMsg(String expectedText) {
		assertEqual(errorMessageInvalidEmailOrPass, expectedText);
		return this;
	}
		
}