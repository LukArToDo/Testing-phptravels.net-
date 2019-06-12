package phptravels.testProcess.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import phptravels.extentReport.Report;
import phptravels.pages.AccountPage;
import phptravels.pages.LoginPage;
import phptravels.testProcess.BaseTest;

public class TS008_LoginValidUser_VerifyAccountPage_AndLogout extends BaseTest {

	AccountPage accountPage;
	
	@Test
	 public void TC01_testLoginWithValidCredentials() {
		 Report.getTest().log(Status.INFO, "Open LoginPage used link: "+loginURL);
		 Report.getTest().log(Status.INFO, "Login with valid credentials and redirect to account page");
		 Report.getTest().log(Status.INFO, "Expected result: AccountPage is verified");
		 Report.getTest().log(Status.INFO, "credentials=[ "+validEmail+", "+validPassword+" ]");
		
		 LoginPage loginPage=new LoginPage(driver).goToLoginPageWithLink(loginURL);
		 
		 accountPage=loginPage
				 		.enterEmail(validEmail)
				 		.enterPassword(validPassword)
				 		.login()	// redirect to AccountPage
				 		.verifyAccountPage(accountTitle);
	 }


	 @Test(dependsOnMethods="TC01_testLoginWithValidCredentials")
	 public void TC02_logoutToLoginPage() {
		 Report.getTest().log(Status.INFO, "Logout from account page and redirect to Login page");
		 Report.getTest().log(Status.INFO, "LoginPage is verified");
		 
		 accountPage.logoutFromAccount() // redirect to LoginPage
				 	.verifyLoginPage(loginTitle);
	 }
	 
}
