package phptravels.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import phptravels.base.BasePage;


public class RegisterPage extends BasePage {
	
	@FindBy(name="firstname")
	WebElement firstNameField;
	
	@FindBy(name="lastname")
	WebElement lastNameField;
	
	@FindBy(name="phone")
	WebElement phoneField;
	
	@FindBy(name="email")
	WebElement emailField;
	
	@FindBy(name="password")
	WebElement passwordField;
	
	@FindBy(name="confirmpassword")
	WebElement confirmPasswordField;
	
	@FindBy(xpath="//form//button")
	WebElement signUpBtn_RegisterP;
	
	@FindBy(xpath="//div[@class='alert alert-danger']")
	WebElement errorMessage;
	
	// constructor
	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	// methods for Register Page
	
	public RegisterPage goToRegisterPageDirectlyWithLink(String linkRegisterPage) {
		driver.get(linkRegisterPage);
		return this;
	}
	
	public RegisterPage goToRegisterPageTroughHomePage(String homePageURL) {
		return new HomePage(driver)
					.goToHomePage(homePageURL)
					.goToRegisterPage();
	}

	
	public RegisterPage goToRegisterPageTroughLoginPage(String loginPageURL) {
		return new LoginPage(driver)
					.goToLoginPageWithLink(loginPageURL)
					.goToRegisterPageWithSignUpButton();
	}

	public RegisterPage verifyRegisterPage(String registerPageTitle) {
		verifyPageTitle(registerPageTitle);
		return this;
	}
	
	public RegisterPage enterFirstName(String firstName) {
		writeText(firstNameField,firstName);
		return this;
	}
	
	public RegisterPage enterLastName(String lastName) {
		writeText(lastNameField, lastName);
		return this;
	}
	
	public RegisterPage enterMobileNumber(String mobileNumber) {
		writeText(phoneField, mobileNumber);
		return this;
	}
	
	public RegisterPage enterEmail(String email) {
		writeText(emailField, email);
		return this;
	}
	
	public RegisterPage enterPassword(String password) {
		writeText(passwordField, password);
		return this;
	}
	
	public RegisterPage enterConfirmPassword(String password) {
		writeText(confirmPasswordField, password);
		return this;
	}
	
	// Note: "click" seleniums method doesn't work with chromedriver 
	public AccountPage signUp(String browser) {
		if(browser.equalsIgnoreCase("chrome")){
		    clickChrome(signUpBtn_RegisterP);
		} else {
			click(signUpBtn_RegisterP);
		}
		return new AccountPage(driver);
	}
	
	public RegisterPage fillAllRegistartionFields(String firstName, String lastName, String email, String password, String confPassword, String mobileNumber) {
		return enterFirstName(firstName)
				.enterLastName(lastName)
				.enterMobileNumber(mobileNumber)
				.enterEmail(email)
				.enterPassword(password)
				.enterConfirmPassword(confPassword);
	}
	
	
	// methods for error messages verification:
	
	public RegisterPage verifyErrorMessage(String expectedText) {
		assertEqual(errorMessage, expectedText);
		return this;
	}
	
	public boolean isErrorMesageDisplayed() {
		try {
			waitVisibility(errorMessage);
			return errorMessage.isDisplayed();
		}catch(TimeoutException e) {
			System.out.println("Registration is successuful, error message is not displayed.");
			System.out.println(e.getMessage());
		}
		return false;
	}
}