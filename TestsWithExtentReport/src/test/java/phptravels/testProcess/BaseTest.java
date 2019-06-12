package phptravels.testProcess;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import phptravels.util.DataGenerator;
import phptravels.util.PropertiesFile;

public class BaseTest {
	protected WebDriver driver;
	public static Properties data;
	
	protected String resourcesPath=System.getProperty("user.dir")+"/src/test/java/resources/";
	protected String browserName;
	
	public static String homeURL,loginURL, registerURL, 
					homeTitle, loginTitle, registerTitle, accountTitle,
					validEmail, validPassword, fakeEmail, fakePassword, randomPassword,
					firstName, lastName, phoneNumber, emailLocal, emailDomain;

	public static String enivronmentInfo;
	
		@Parameters("environment")
		@BeforeTest
		public void setupData(String environment) {
			enivronmentInfo = environment;
			data=PropertiesFile.getProperties(resourcesPath+"properties/", environment);
			// set some major and common data (get from properties file)
			homeURL=data.getProperty("HOME.URL");
			loginURL=data.getProperty("LOGIN.URL");
			registerURL=data.getProperty("REGISTER.URL");
			homeTitle=data.getProperty("HOME.TITLE");
			loginTitle=data.getProperty("LOGIN.TITLE");
			registerTitle=data.getProperty("REGISTER.TITLE");
			accountTitle=data.getProperty("ACCOUNT.TITLE");
			validEmail=data.getProperty("LOGIN.VALID.EMAIL");
			validPassword=data.getProperty("LOGIN.VALID.PASSWORD");
			fakeEmail=data.getProperty("LOGIN.FAKE.EMAIL");
			fakePassword=data.getProperty("LOGIN.FAKE.PASSWORD");
			firstName=data.getProperty("REGISTER.FIRSTNAME");
			lastName=data.getProperty("REGISTER.LASTNAME");
			randomPassword=DataGenerator.getPassword(8);
			phoneNumber=data.getProperty("REGISTER.MOBILE.NUMBER");
			emailLocal=data.getProperty("REGISTER.EMAIL.LOCALPART");
			emailDomain=data.getProperty("REGISTER.EMAIL.DOMAIN");
		}
		
		@Parameters("browser")
		@BeforeClass
		public synchronized void setupDriver(String browser) {
			browserName=browser;
			
			// will handle 2 major browsers: FireFox & Chrome
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", resourcesPath + data.getProperty("CHROMEDRIVER.PATH"));
			    ChromeOptions optionsChrome = new ChromeOptions();
			    optionsChrome.addArguments("--incognito");
			    optionsChrome.addArguments("--start-maximized"); // set maximum window
				driver = new ChromeDriver(optionsChrome);
				
			} else if (browser.equalsIgnoreCase("firefox")) {
				//resourcesPath + data.getProperty("GECKODRIVER.PATH"));System.setProperty("webdriver.firefox.marionette", resourcesPath + data.getProperty("GECKODRIVER.PATH"));
				System.setProperty("webdriver.gecko.driver", resourcesPath + data.getProperty("GECKODRIVER.PATH"));
//				
				driver=new FirefoxDriver();
				driver.manage().window().maximize();
			}
			else {
				System.out.println("Browser is undefifnied");
			}
			
		}
		
		@AfterClass
		public void quitDriver() {
			driver.manage().deleteAllCookies();
			driver.quit();
		}
		
		public WebDriver getDriver() {
			return driver;
		}

		public String getBrowser() {
			return browserName;
		}
	}