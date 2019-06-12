package phptravels.testProcess.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import phptravels.extentReport.Report;
import phptravels.pages.AccountPage;
import phptravels.pages.RegisterPage;
import phptravels.testProcess.BaseTest;
import phptravels.util.DataGenerator;

public class TS004_RegisterNewValidUser extends BaseTest {
	RegisterPage registerPage;
	AccountPage accountPage;
	boolean emailExist=false;

	@Test
	public void TC01_registerNewValidUser_VerifyAccountPage() {
		
		String randomEmail = DataGenerator.getValidEmail(emailLocal, 6 , emailDomain); 
		
		Report.getTest().log(Status.INFO, "Open register page using link: "+registerURL);
		Report.getTest().log(Status.INFO, "Registration - fill all fields on Registerpage and register new user with signUp button");
		Report.getTest().log(Status.INFO, "Expexted result: AccountPage is verified");
		Report.getTest().log(Status.INFO, "Credentials: [ "+randomEmail +", "+ randomPassword+" ]");
		
		registerPage= new RegisterPage(driver)
						.goToRegisterPageDirectlyWithLink(registerURL);
		
		accountPage=registerPage
						.fillAllRegistartionFields(
								firstName, 
								lastName, 
								randomEmail,
								randomPassword,
								randomPassword,
								phoneNumber )
						.signUp(browserName);
		
		if(registerPage.isErrorMesageDisplayed()) {
			Report.getTest().log(Status.WARNING, "Error message is displayed");
			Report.getTest().log(Status.WARNING, "Expected message: Email Already Exists.");		
			Report.getTest().log(Status.WARNING, "Try registration with new credentials");
			
			registerPage.verifyErrorMessage(data.getProperty("REGISTER.ERROR.EMAIL.EXIST"));
			emailExist=true;
			
		} else {
			accountPage.verifyAccountPage(accountTitle);
		}
					
	}

	@Test
	public void TC02_logoutFromAccountPage_AndVerifyLoginPage() {
		if(emailExist) {
			Report.getTest().log(Status.WARNING, "Registration is not finished beacuse email already exist");
			Report.getTest().log(Status.WARNING, "Execution of this method will be skipped.");		
			Report.getTest().log(Status.WARNING, "Try registration with new credentials");
			
		} else {
			Report.getTest().log(Status.INFO, "After successuful registration logout fron account page and redirect to Login page");
			Report.getTest().log(Status.INFO, "Expected result: LoginPage is verified");
		
			accountPage.logoutFromAccount()
						.verifyLoginPage(loginTitle);
		}
	}
	
}
