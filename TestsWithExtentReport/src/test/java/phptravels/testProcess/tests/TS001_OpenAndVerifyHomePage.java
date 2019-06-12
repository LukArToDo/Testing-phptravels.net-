package phptravels.testProcess.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import phptravels.extentReport.Report;
import phptravels.pages.HomePage;
import phptravels.testProcess.BaseTest;

public class TS001_OpenAndVerifyHomePage extends BaseTest {
  @Test
  public void TC01_OpenAndVerifyHomePage() {
	  Report.getTest().log(Status.INFO, "Opaen and Verify HomePage, link: "+homeURL );
	  Report.getTest().log(Status.INFO, "Expected result: HomePage is verified.");
	  
	  new HomePage(driver)
	  .goToHomePage(homeURL)
	  .verifyHomePage(homeTitle);
  }
}
