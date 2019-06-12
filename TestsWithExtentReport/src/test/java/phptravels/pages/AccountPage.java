package phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import phptravels.base.BasePage;


public class AccountPage extends BasePage{

	// Web elements:	
	@FindBy(xpath="//nav//li[@class='']/a")
	WebElement myAccount;
	
	@FindBy(xpath="//nav//ul[@class='dropdown-menu']/li[1]/a")
	WebElement account;
	
	@FindBy(xpath="//nav//ul[@class='dropdown-menu']/li[2]/a")
	WebElement logout;
	
	// constructor
	public AccountPage(WebDriver driver) {
		super(driver);
	}

	public AccountPage verifyAccountPage(String accountPageTitle) {
		verifyPageTitle(accountPageTitle);
		return this;
	}
	
	public AccountPage logToAccountPage(String email, String password, String loginPageURL) {
		return new LoginPage(driver)
					.goToLoginPageWithLink(loginPageURL)
					.enterEmail(email)
					.enterPassword(password)
					.login(); // redirect to AccountPage
	}
	
	public LoginPage logoutFromAccount() {
		click(myAccount);
		click(logout); // redirect to LoginPage
		return new LoginPage(driver);
	}
}
