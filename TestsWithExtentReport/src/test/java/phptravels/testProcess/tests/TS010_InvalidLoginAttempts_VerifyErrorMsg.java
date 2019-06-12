package phptravels.testProcess.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import phptravels.extentReport.Report;
import phptravels.pages.LoginPage;
import phptravels.testProcess.BaseTest;

public class TS010_InvalidLoginAttempts_VerifyErrorMsg extends BaseTest {

	LoginPage loginPage;
	String expectedErrorMsg;
	
	@BeforeClass
	public void setErrorMsg() {
		expectedErrorMsg=data.getProperty("LOGIN.ERROR");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		loginPage=new LoginPage(driver).goToLoginPageWithLink(loginURL);
		  
		// Delete all cookies
	    driver.manage().deleteAllCookies();
	}
	
	@Test
	public void TC01_loginWithAllEmptyFields_VerifyErrorMsg() {
		Report.getTest().log(Status.INFO, "Open LoginPage used link: "+loginURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying login with empty 'email' and 'password' fields");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+expectedErrorMsg+"' is displayed");
		 
		loginPage.enterEmail("")
				.enterPassword("")
				.login();
		
		loginPage.verifyLoginErrorMsg(expectedErrorMsg);
	}
	
	@Test
	public void TC02_loginWithFakeCredentials_VerifyErrorMsg() {
		Report.getTest().log(Status.INFO, "Open LoginPage used link: "+loginURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying login with fake credentials");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+expectedErrorMsg+"' is displayed");
		Report.getTest().log(Status.INFO, "Fake credentials=["+fakeEmail+", "+fakePassword+"]");
		 
		loginPage.enterEmail(fakeEmail)
				.enterPassword(fakePassword)
				.login();

		loginPage.verifyLoginErrorMsg(expectedErrorMsg);
	}
	
	@Test
	public void TC03_loginWithValidEmailAndEmptyPassword_VerifyErrorMsg() {
		Report.getTest().log(Status.INFO, "Open LoginPage used link: "+loginURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying login with valid email and empty 'password' field");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+expectedErrorMsg+"' is displayed");
		Report.getTest().log(Status.INFO, "Credentials=["+validEmail+", ]");
		 
		loginPage.enterEmail(validEmail)
				.enterPassword("")
				.login();

		loginPage.verifyLoginErrorMsg(expectedErrorMsg);
	}
	
	@Test
	public void TC04_loginWithEmptyEmailAndValidPassword_VerifyErrorMsg() {
		Report.getTest().log(Status.INFO, "Open LoginPage used link: "+loginURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying login with empty 'email' field and  valid password");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+expectedErrorMsg+"' is displayed");
		Report.getTest().log(Status.INFO, "Credentials=[ , "+validPassword+"]");
		 
		loginPage.enterEmail("")
				.enterPassword(validPassword)
				.login();

		loginPage.verifyLoginErrorMsg(expectedErrorMsg);
	}
	
	@Test
	public void TC05_loginWithFakeEmailAndValidPassword_VerifyErrorMsg() {
		Report.getTest().log(Status.INFO, "Open LoginPage used link: "+loginURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying login with fake email and valid password");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+expectedErrorMsg+"' is displayed");
		Report.getTest().log(Status.INFO, "Credentials=["+fakeEmail+", "+validPassword+"]");
		 
		loginPage.enterEmail(fakeEmail)
				.enterPassword(validPassword)
				.login();

		loginPage.verifyLoginErrorMsg(expectedErrorMsg);
	}
}
