package phptravels.testProcess.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import phptravels.extentReport.Report;
import phptravels.pages.LoginPage;
import phptravels.testProcess.BaseTest;

public class TS009_LoginValidUserWithInvalidPassword_VerifyErrorMsg extends BaseTest {

	@Test
	public void TC01_testLoginWithBadPassword_showErrorMsg() {	
		Report.getTest().log(Status.INFO, "Open LoginPage used link: "+loginURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying login with valid email, but fake password");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+data.getProperty("LOGIN.ERROR")+"' is displayed");
		Report.getTest().log(Status.INFO, "Credentials=["+validEmail+", "+fakePassword+"]");
		 
		LoginPage loginPage=new LoginPage(driver).goToLoginPageWithLink(loginURL);
		
		loginPage.enterEmail(validEmail)
			.enterPassword(fakePassword)
			.login();// stayed on LoginPage
			
		loginPage.verifyLoginErrorMsg(data.getProperty("LOGIN.ERROR"));
	 }
}
