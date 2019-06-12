package phptravels.testProcess.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import phptravels.extentReport.Report;
import phptravels.pages.RegisterPage;
import phptravels.testProcess.BaseTest;

public class TS005_RegisterNewUserWithExistingEmail_VerifyErrorMsg extends BaseTest {
  
	@Test
	public void TC01_registerUserWithExistingEmail_showErrorMsg() {
		Report.getTest().log(Status.INFO, "Open register page using link: "+registerURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying register new user with existing email");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+data.getProperty("REGISTER.ERROR.EMAIL.EXIST")+"'  is displayed.");
		Report.getTest().log(Status.INFO, "Credentials: [ "+validEmail +", "+ randomPassword +" ]");
		
		RegisterPage registerPage=new RegisterPage(driver)
			.goToRegisterPageDirectlyWithLink(registerURL);
		
		registerPage.fillAllRegistartionFields(
					firstName, 
					lastName, 
					validEmail, // email already exist
					randomPassword,
					randomPassword,
					phoneNumber)
					.signUp(browserName);
	
		registerPage.verifyErrorMessage(data.getProperty("REGISTER.ERROR.EMAIL.EXIST"));
	}
}
