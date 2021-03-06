package com.AP.qa.TEST1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.AP.qa.base.TestBase;
import com.AP.qa.pages.Login_Page;
import com.AP.qa.pages.Logout_Page;
import com.AP.qa.pages.Payment_Page;
import com.AP.qa.pages.Homepage_Page;


public class Test1 extends TestBase {
	Login_Page ClsObjLogin;
	Homepage_Page ClsObjHome;
	Payment_Page ClsObjPayment;
	Logout_Page ClsObjLogout;
	
	@Parameters("Browser")
	@BeforeTest
	public void init(String Browser) throws Throwable {
		initialization(Browser);
		
		
	}
	
	
	@BeforeClass
	public void Setup() throws Throwable {
		SetUP(this.getClass().getSimpleName(), driver.getTitle());
		
		//------Validation for Home Page is open or not -------------
		
		ClsObjLogin =	HomePageValidation(driver.getTitle());
		
		 if(ClsObjLogin!=null) {
			 Reporting("Pass", "URL Navigation", "Successfully navigated to Automation Practice", "User Should be able to navigate Automation Practice");
		 }else {
			 Reporting("Fail", "URL Navigation", "Unsuccessfully navigated to Automation Practice", "User Should be able to navigate Automation Practice");
			 closeBrowser();
		 }	
	}
	
	
	 //--------------Login function------------------
	@Test(priority = 1,enabled = true)
	public void Login_Test() throws Throwable {
		 ClsObjHome = ClsObjLogin.UserLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	

	@Test(priority = 2,enabled = true)
	public void Product_Selection_Test() throws Throwable {
			
		ClsObjHome.SelectSingle_Product("L");
		
		ClsObjPayment=ClsObjHome.PriceValidation();
			
	}
	
	@Test(priority = 3,enabled = true)
	public void Payment_Process_Test() throws Throwable {
		
		if(ClsObjPayment!=null) {
			Reporting("Pass", "Payment Page Validation", "User successfully navigate to Payment Page", "User should be able to navigate to Payment Page");
			ClsObjPayment.proceed.click(); 
		}
		else {
			Reporting("Fail", "Payment Page Validation", "User unsuccessfully navigate to Payment Page", "User should be able to navigate to Payment Page");
			closeBrowser();
		}
		
		ClsObjPayment.PaymentProcess();
		ClsObjLogout = ClsObjPayment.FinalPrice_Validation();
		
	}
	
	@Test(priority = 4,enabled = true)
	public void Logout_Test() throws Throwable {
		ClsObjLogout.Logout_perform();
	}
	
	
	@AfterClass
	public void tearUp() {
		closeBrowser();
	}

}
