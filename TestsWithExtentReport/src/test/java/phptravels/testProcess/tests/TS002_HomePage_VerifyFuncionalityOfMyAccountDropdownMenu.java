package phptravels.testProcess.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import phptravels.extentReport.Report;
import phptravels.pages.HomePage;
import phptravels.testProcess.BaseTest;

public class TS002_HomePage_VerifyFuncionalityOfMyAccountDropdownMenu extends BaseTest {
  
	HomePage homePage;
	@BeforeMethod
	public void beforeMethod() {
		homePage= new HomePage(driver).goToHomePage(homeURL);
	}
	
	@Test 
	public void TC01_goToLoginPageWithLoginBtnOnDropdownMenu() {
		Report.getTest().log(Status.INFO, "Open Hompepage, link :"+homeURL);
		Report.getTest().log(Status.INFO, "Navigate to LoginPage with Login button on dropdown menu.");
		Report.getTest().log(Status.INFO, "Expected result: LoginPage is verified.");
		  
		homePage.goToLoginPage()
				.verifyLoginPage(loginTitle);
	}
	  
	@Test 
	public void TC02_goToRegisterPageWithSignUpBtnOnDropdownMenu() {
		Report.getTest().log(Status.INFO, "Open Hompepage, link :"+homeURL);
		Report.getTest().log(Status.INFO, "Navigate to LoginPage with SignUp button on dropdown menu.");
		Report.getTest().log(Status.INFO, "Expected result: RegisterPage is verified.");
		  
		homePage.goToRegisterPage()
				.verifyRegisterPage(registerTitle);
	  }
}
