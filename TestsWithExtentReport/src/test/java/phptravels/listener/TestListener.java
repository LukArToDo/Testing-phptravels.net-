package phptravels.listener;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import phptravels.extentReport.Report;
import phptravels.extentReport.ReportManager;
import phptravels.testProcess.BaseTest;


public class TestListener extends BaseTest implements ITestListener{
	
public static String suiteName;

	public void onTestStart(ITestResult result) {
		System.out.println("Test "+result.getName()+" is onTestStart");
		Object currentClass = result.getInstance();
		String browser = ((BaseTest) currentClass).getBrowser();
		String className = result.getInstance().getClass().getSimpleName();
		String methodName=result.getMethod().getMethodName();
		String uLine="_";
		Report.startTest(browser+uLine+className+uLine+methodName);
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test "+result.getName()+" is onTestSuccess");
		Report.getTest().log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
		String methodName=result.getName().toString().trim();
		System.out.println("Test "+ methodName +" is FAILED");
		
		Object currentClass = result.getInstance();
	    WebDriver driver = ((BaseTest) currentClass).getDriver();
		
	    String uLine="_";
	    String fileSeperator = System.getProperty("file.separator");
	    String browser = ((BaseTest) currentClass).getBrowser();
	    String className = result.getInstanceName().trim();
	    String timeStamp = getDateAndTime();
	    
	    String screenshotName= browser+uLine+methodName+uLine+timeStamp+".png";
		String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestExtentReports" + fileSeperator
				+ "screenshots" + fileSeperator + className;
		File file = new File(reportsPath); // Set
		// screenshots
		// folder
		if (!file.exists()) {
			if (file.mkdirs()) {
				System.out.println("Directory: " + file.getAbsolutePath() + " is created!");
			} else {
				System.out.println("Failed to create directory: " + file.getAbsolutePath());
			}

		}
		
		// take screenshot and put to the Test-Automation-Report.html
		takeScreenshot(screenshotName, driver, reportsPath+fileSeperator);
		Report.getTest().log(Status.FAIL, "Test Failed.");
		Report.getTest().log(Status.FAIL, "Cause: "+result.getThrowable().getMessage());
	}

	private String getDateAndTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now); 
	}

	public void takeScreenshot(String screenshotName, WebDriver driver, String reportsPath) {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String targetPath=reportsPath+screenshotName;
        //	The below method will save the screen shot in local drive with test method name 
           try {
				FileUtils.copyFile(scrFile, new File(targetPath));
				System.out.println("***Placed screen shot in "+targetPath+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
           
           try {
   			Report.getTest().fail("Screenshot",
   					MediaEntityBuilder.createScreenCaptureFromPath(targetPath).build());
   		} catch (IOException e) {
   			System.out.println("An exception occured while taking screenshot " + e.getCause());
   		}
           
	}
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test "+result.getName()+" is SKIPPED");
		Report.getTest().log(Status.SKIP, "Test Skipped: "
				+result.getInstanceName().trim()+"_"
				+result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test "+result.getName()+" is failedButWithSucccessPrecentage");
	}

	public void onStart(ITestContext context) {
		suiteName=context.getSuite().getName();
		System.out.println("Test "+context.getName()+" is onStart");
	}

	public void onFinish(ITestContext context) {
		System.out.println("Test "+context.getName()+" is onFinish");
		Report.endTest();
		ReportManager.getInstance().flush();
	}

}
