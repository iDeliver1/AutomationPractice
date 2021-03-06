 package com.AP.qa.base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import com.AP.qa.pages.Login_Page;
import com.AP.qa.pages.Homepage_Page;
import com.AP.qa.util.Excel_Libraries;
import com.AP.qa.util.Extent_Report;
import com.AP.qa.util.TestUtil;
import com.AP.qa.util.WebEventListener;
import com.google.common.base.Function;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static Extent_Report objExtent = new Extent_Report();
	private static final Duration DEFAULT_WAIT_POLLING = Duration.ofSeconds(1);
	private static final Duration DEFAULT_WAIT_DURATION = Duration.ofSeconds(20);
	public static String Report_Folder_path = "C:\\Reporting\\Report"+TestUtil.fTimestamp();
	public WebElement GlobalElement;
	public static String GlobalValue,FinalPriceValue;
	public static int counter = 0;
	public static String brow;
	public static String BrowserVersion ;
	public static Boolean GlobalArgumrnt;
	
	
		public TestBase(){
		
			try {
				prop = new Properties();
				FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/AP/"
						+ "qa/config/config.properties");
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	

	//------------------Function for initialization Browser ---------------------
		public  void initialization(String browserName) throws Throwable{
		
			try {
				if(counter == 0) {
					if(TestUtil.getBrowserVersion()!=null) {
					 BrowserVersion = TestUtil.getBrowserVersion();	
					}
					else {
						System.out.println("Unable to get Chrome eVersion Please check the Chrome installtion path");
						System.exit(0);
					}
				}
				
				System.out.println(BrowserVersion);
				if(browserName.equals("chrome")){
					
					WebDriverManager.chromedriver().version(BrowserVersion).setup();
					driver = new ChromeDriver(); 
				}
				else if(browserName.equals("FF")){
					
					System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/java/com/AP/qa/driver/geckodriver.exe");	
					driver = new FirefoxDriver(); 
				}
				
				e_driver = new EventFiringWebDriver(driver);
			
				// Now create object of EventListerHandler to register it with EventFiringWebDriver
				eventListener = new WebEventListener();
				e_driver.register(eventListener);
				driver = e_driver;	
				}
			catch(Exception e) {
				System.out.println(e);
			}
			counter = counter+1;
}
	
	
	//--------------Function for Initate URL--------------------
		public void initateURL() throws Throwable{
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);		
			driver.get(prop.getProperty("url"));
	}
	
	
	//----------------Setup for Create Reports -------------------
	    public void SetUP(String Reportname,String Title) throws Throwable{		
			objExtent.CreateRoportname(Reportname);
			Excel_Libraries.createExcel(Reportname);
			initateURL();
		
	}
	
	//Function for Getting Project Name ---------------
		public  String GetProjectName(){
		
			String userDir = System.getProperty("user.dir");
			Path path = Paths.get(userDir);
			String project = path.getFileName().toString();
			return project;
		
	}

	//-------------Logging method so that the same log is added in logger as well as syso---------------
		public static void log(String data) {
			
			log.info(data);
			Reporter.log(data);
		}
		
			//--------------Function For reporting For Both Excel and Extent Reports
		public static   void Reporting(String Status,String StepName,String ActualStep,String ExpectedStep) throws Throwable{
			objExtent.Report(Status, StepName, ActualStep, ExpectedStep);
			
		}
		
		//--------------Explicit Wait---------------
		public   void waitforElement(long timeoutseconds, WebElement element) {
			WebDriverWait wait = new WebDriverWait(driver, timeoutseconds);
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		//------------PageLoadTimeout Function----------------
		public  void waitfunction(){
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);	
		}
		


		//----------------Fluent Wait for an Element for event for clicking or checking--------------------
		public  void WaitForObject(  final WebElement element,String EventName) throws InterruptedException{
			Wait<WebDriver> wait = new FluentWait<WebDriver> (driver)
			   .withTimeout(DEFAULT_WAIT_DURATION)
			   .pollingEvery(DEFAULT_WAIT_POLLING)
			   .ignoring(NoSuchElementException.class);
		
			 WebElement Element_ToBe_Checked = wait.until(new Function<WebDriver, WebElement>() { 
			     public WebElement apply(WebDriver driver) {
			      return element;
			     }
			   });
			 
			 try{ 
				 Element_ToBe_Checked.isDisplayed();
			if(EventName.contains("Click")){
				Element_ToBe_Checked.click();
			}
			 }	
				catch(Exception f){
					System.out.println(f);
				}
				
			}
		
		
		//------------Function for Validating Home page is open or not and then return Login Page Constructor
		public static Login_Page HomePageValidation(String Actual) {
					try {
						Assert.assertEquals(Actual, "My Store");
						return new Login_Page();
					}catch(Exception e) {
						
						return null;
					}
		}
				
				//------------Function for Validating Home page is open or not and then return Home Page Constructor 
		public static Homepage_Page HomePageVvalidation(String Actual) {
					try {
						Assert.assertEquals(Actual, "My Store");
						return new Homepage_Page();
					}catch(Exception e) {
						
						return null;
					}
		}
		
		
		//----------Closing Browser And Saving Report --------------
		public static   void closeBrowser() {
			driver.quit();
			objExtent.flush();
			
		}

		
		
}
