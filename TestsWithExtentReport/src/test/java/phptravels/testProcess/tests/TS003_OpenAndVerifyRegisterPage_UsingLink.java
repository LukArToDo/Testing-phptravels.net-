package phptravels.testProcess.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import phptravels.extentReport.Report;
import phptravels.pages.RegisterPage;
import phptravels.testProcess.BaseTest;

public class TS003_OpenAndVerifyRegisterPage_UsingLink extends BaseTest {
  
	@Test
	public void TC01_OpenAndVerifyLoginPage_UsingLink() {
		Report.getTest().log(Status.INFO, "Open and verify RegisterPage used link: "+registerURL);
		Report.getTest().log(Status.INFO, "Expected result: Register page is verified.");
				
		new RegisterPage(driver)
			.goToRegisterPageDirectlyWithLink(registerURL)
			.verifyRegisterPage(registerTitle);  
	}
}
