package com.AP.qa.TEST1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.AP.qa.base.TestBase;
import com.AP.qa.pages.Login;
import com.AP.qa.pages.Logout;
import com.AP.qa.pages.Payment;
import com.AP.qa.pages.Homepage;


public class Test1 extends TestBase {
	Login login;
	Homepage Home;
	Payment pay;
	Logout log;
	
	@Parameters("Browser")
	@BeforeTest
	public void init(String Browser) throws Throwable {
		initialization(Browser);
		
		
	}
	
	
	@BeforeClass
	public void Setup() throws Throwable {
		SetUP(this.getClass().getSimpleName(), driver.getTitle());
		
		//------Validation for Home Page is open or not -------------
		
		login =	HomePageValidation(driver.getTitle());
		
		 if(login!=null) {
			 Reporting("Pass", "URL Navigation", "Successfully navigated to Automation Practice", "User Should be able to navigate Automation Practice");
		 }else {
			 Reporting("Fail", "URL Navigation", "Unsuccessfully navigated to Automation Practice", "User Should be able to navigate Automation Practice");
			 closeBrowser();
		 }	
	}
	
	
	 //--------------Login function------------------
	@Test(priority = 1,enabled = true)
	public void Login_Test() throws Throwable {
		 Home = login.UserLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	

	@Test(priority = 2,enabled = true)
	public void Product_Selection_Test() throws Throwable {
			
		Home.SelectSingle_Product("L");
		
		pay=Home.PriceValidation();
			
	}
	@Test(priority = 3,enabled = true)
	public void Payment_Process_Test() throws Throwable {
		
		if(pay!=null) {
			Reporting("Pass", "Payment Page Validation", "User successfully navigate to Payment Page", "User should be able to navigate to Payment Page");
			pay.proceed.click(); 
		}
		else {
			Reporting("Fail", "Payment Page Validation", "User unsuccessfully navigate to Payment Page", "User should be able to navigate to Payment Page");
			closeBrowser();
		}
		
		GlobalValue =	pay.PaymentProcess();
		log = pay.FinalPrice_Validation();
		
	}
	
	@Test(priority = 4,enabled = true)
	public void Logout_Test() throws Throwable {
			log.Logout_perform();
	}
	
	
	@AfterClass
	public void tearUp() {
		closeBrowser();
	}

}
