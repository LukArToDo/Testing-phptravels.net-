package phptravels.testProcess.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import phptravels.extentReport.Report;
import phptravels.pages.LoginPage;
import phptravels.testProcess.BaseTest;


public class TS007_OpenAndVerifyLoginPage_UsingLink extends BaseTest{
	
	@Test
	 public void TC01_verifyLoginPage() {
		 Report.getTest().log(Status.INFO, "Open and Verify LoginPage used link: "+loginURL);
		 Report.getTest().log(Status.INFO, "Expected result: Login page is verified.");
			
		 new LoginPage(driver)
		 	.goToLoginPageWithLink(loginURL)
		 	.verifyLoginPage(loginTitle);
	 }
}
