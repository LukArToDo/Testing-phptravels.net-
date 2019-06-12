package phptravels.testProcess.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import phptravels.extentReport.Report;
import phptravels.pages.RegisterPage;
import phptravels.testProcess.BaseTest;
import phptravels.util.DataGenerator;

public class TS006_IncompleteRegistartion_VerifyErrorMsgs extends BaseTest {
	RegisterPage registerPage;
	
	// all error messages on register page
	private String 	errEmailEmpty, errEmailExist, errEmailNotValidForm,
					errPasswordEmpty, errPasswordToShort,
					errConfPasswordEmpty, errConfPasswordNotMatch,
					errFirstNameEmpty, errLastNameEmpty;
  
	@BeforeClass
	public void setUpErrorMessages() {
		errEmailEmpty=data.getProperty("REGISTER.ERROR.EMAIL.EMPTY");
		errEmailExist=data.getProperty("REGISTER.ERROR.EMAIL.EXIST");
		errEmailNotValidForm=data.getProperty("REGISTER.ERROR.EMAIL.INVALIDFORM");
		errPasswordEmpty=data.getProperty("REGISTER.ERROR.PASSWORD.EMPTY");
		errPasswordToShort=data.getProperty("REGISTER.ERROR.PASSWORD.LENGTH");
		errConfPasswordEmpty = data.getProperty("REGISTER.ERROR.CONFIRMPASSWORD.EMPTY");
		errConfPasswordNotMatch = data.getProperty("REGISTER.ERROR.CONFIRMPASSWORD.NOTMATCH");
		errFirstNameEmpty = data.getProperty("REGISTER.ERROR.FIRSTNAME.EMPTY");
		errLastNameEmpty = data.getProperty("REGISTER.ERROR.LASTNAME.EMPTY");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		registerPage= new RegisterPage(driver).goToRegisterPageDirectlyWithLink(registerURL);
	}
	
	@Test 
	public void TC01_registerWithEmptyFirstnameField_showErrorMsg() {
		String randomEmail=DataGenerator.getValidEmail(emailLocal, 6 , emailDomain); 
		
		Report.getTest().log(Status.INFO, "Open register page using link: "+registerURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying register with empty 'First Name' field");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+errFirstNameEmpty+"'  is displayed.");
		
		registerPage.fillAllRegistartionFields("" , /* empty field 'First Name'*/ 
						lastName, 
						randomEmail,
						randomPassword,
						randomPassword,
						phoneNumber)
					.signUp(browserName);
		
		registerPage.verifyErrorMessage(errFirstNameEmpty);
	}
	
	@Test 
	public void TC02_registerWithEmptyLastnameField_showErrorMsg() {
		String randomEmail=DataGenerator.getValidEmail(emailLocal, 6 , emailDomain); 
		
		Report.getTest().log(Status.INFO, "Open register page using link: "+registerURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying register with empty 'Last Name' field");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+errLastNameEmpty+"'  is displayed.");
		
		registerPage.fillAllRegistartionFields(firstName, 
						"" , /* empty field 'Last Name'*/  
						randomEmail,
						randomPassword,
						randomPassword,
						phoneNumber)
					.signUp(browserName);
		
		registerPage.verifyErrorMessage(errLastNameEmpty);
	}
	
	@Test 
	public void TC03_registerWithEmptyEmailField_showErrorMsg() {
		Report.getTest().log(Status.INFO, "Open register page using link: "+registerURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying register with empty 'Email' field");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+errEmailEmpty+"'  is displayed.");
		
		registerPage.fillAllRegistartionFields(
						firstName, 
						lastName, 
						"" , /* empty field 'Email'*/ 
						randomPassword,
						randomPassword,
						phoneNumber)
					.signUp(browserName);
		
		registerPage.verifyErrorMessage(errEmailEmpty);
	}
	
	@Test 
	public void TC04_registerWithEmptyPasswordField_showErrorMsg() {
		String randomEmail=DataGenerator.getValidEmail(emailLocal, 6 , emailDomain); 
		
		Report.getTest().log(Status.INFO, "Open register page using link: "+registerURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying register with empty 'Password' field");
		Report.getTest().log(Status.INFO, "Expected result: error messages '"+errPasswordEmpty
											+ "' and '"+errConfPasswordNotMatch+"' are displayed.");
		
		registerPage.fillAllRegistartionFields(
						firstName, 
						lastName, 
						randomEmail  ,
						"",  /* empty field 'Password'*/ 
						randomPassword,
						phoneNumber)
					.signUp(browserName);
		
		registerPage.verifyErrorMessage(errPasswordEmpty+"\n"
										+errConfPasswordNotMatch);
	}
	
	@Test 
	public void TC05_registerWithEmptyConfirmPasswordField_showErrorMsg() {
		String randomEmail=DataGenerator.getValidEmail(emailLocal, 6 , emailDomain); 
		
		Report.getTest().log(Status.INFO, "Open register page using link: "+registerURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying register with empty 'Confirm Password' field");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+errConfPasswordEmpty+"'  is displayed.");
		
		registerPage.fillAllRegistartionFields(
						firstName, 
						lastName, 
						randomEmail  ,
						randomPassword,
						"" , /* empty field 'Confirm Password'*/ 
						phoneNumber)
					.signUp(browserName);
		
		registerPage.verifyErrorMessage(errConfPasswordEmpty);
	}
	
	@Test 
	public void TC06_registerWithAllEmptyField_showErrorMsg() {
		Report.getTest().log(Status.INFO, "Open register page using link: "+registerURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying register with all empty fields");
		Report.getTest().log(Status.INFO, "Expected result: error messages: '"
											+ errEmailEmpty + "', '"
											+ errPasswordEmpty + "', '"
											+ errConfPasswordEmpty + "', '"
											+ errFirstNameEmpty + "' and '"
											+ errLastNameEmpty +"'  are displayed.");
		
		registerPage.fillAllRegistartionFields("", /* empty field 'First Name'*/
							"", /* empty field 'Last Name'*/
							"", /* empty field 'Email'*/
							"", /* empty field 'Password'*/
							"", /* empty field 'Confirm Password'*/ 
							"") /* empty field 'Mobile Number'*/
					.signUp(browserName);
		
		registerPage.verifyErrorMessage(errEmailEmpty + "\n"
										+ errPasswordEmpty + "\n"
										+ errConfPasswordEmpty + "\n"
										+ errFirstNameEmpty + "\n"
										+ errLastNameEmpty );
	}
	
	@Test 
	public void TC07_registerWithWrongConfirmpaswordField_showErrorMsg() {
		String randomEmail=DataGenerator.getValidEmail(emailLocal, 6 , emailDomain); 
		
		Report.getTest().log(Status.INFO, "Open register page using link: "+registerURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying register with not matching 'Confirm Password' field");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+errConfPasswordNotMatch+"'  is displayed.");
		
		registerPage.fillAllRegistartionFields(
						firstName, 
						lastName, 
						randomEmail  ,
						randomPassword,
						randomPassword+"NoNo", /* field 'Confirm Password' not match to 'Password' filed */ 
						phoneNumber)
					.signUp(browserName);
		
		registerPage.verifyErrorMessage(errConfPasswordNotMatch);
	}
	
	
	@Test
	public void TC08_registerWithWrongFormOfEmail() {
		Report.getTest().log(Status.INFO, "Open register page using link: "+registerURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying register new user with not valid email, email without '@' and domain part");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+errEmailNotValidForm+"'  is displayed.");
		Report.getTest().log(Status.INFO, "Credentials: [ "+emailLocal +", "+ randomPassword+" ]");
		
		registerPage.fillAllRegistartionFields(firstName, 
						lastName, 
						emailLocal , /* not valid email form, without @ and domain*/ 
						randomPassword,
						randomPassword,
						phoneNumber)
					.signUp(browserName);

		registerPage.verifyErrorMessage(errEmailNotValidForm);
	}
	
	@Test
	public void TC09_registerWithToLongEmail_localPartIsGreaterThan64Char() {
		int maxLengthOfEmailLocalPart=Integer.parseInt(data.getProperty("REGISTER.EMAIL.LOCALPART.MAXIMUM.LENGTH"));
		String toLongEmail=DataGenerator
							.getValidEmail(	emailLocal, maxLengthOfEmailLocalPart,emailDomain); 
		
		Report.getTest().log(Status.INFO, "Open register page using link: "+registerURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying register new user with too long email, where is local part graeter than 64 characters");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+errEmailNotValidForm+"'  is displayed.");
		Report.getTest().log(Status.INFO, "Credentials: [ "+toLongEmail +", "+ randomPassword+" ]");
		
		registerPage.fillAllRegistartionFields(
						firstName, 
						lastName, 
						toLongEmail , /* not valid email, local part is greater than 64 characters*/ 
						randomPassword,
						randomPassword,
						phoneNumber)
					.signUp(browserName);

		registerPage.verifyErrorMessage(errEmailNotValidForm);
	}
	
	@Test 
	public void TC10_registerWithTooShortPassword_lessThanSixChar() {
		int minLengthOfPassword=Integer.parseInt(data.getProperty("REGISTER.PASSWORD.MINIMUM.LENGTH"));
		String toShortPassword= DataGenerator.getPassword(minLengthOfPassword-1);
		String randomEmail=DataGenerator.getValidEmail(emailLocal, 6 , emailDomain); 
		
		Report.getTest().log(Status.INFO, "Open register page using link: "+registerURL);
		Report.getTest().log(Status.INFO, "Verify message error when we trying register new user with too short password, less than 6 characters");
		Report.getTest().log(Status.INFO, "Expected result: error message '"+errPasswordToShort+"'  is displayed.");
		Report.getTest().log(Status.INFO, "Credentials: [ "+randomEmail +", "+ toShortPassword+" ]");
		
		registerPage.fillAllRegistartionFields(
						firstName, 
						lastName, 
						randomEmail, 
						toShortPassword, /* too short password , less than 6 characters*/ 
						toShortPassword,
						phoneNumber)
					.signUp(browserName);

		registerPage.verifyErrorMessage(errPasswordToShort);
	}
	
}
