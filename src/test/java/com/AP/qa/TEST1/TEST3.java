package com.AP.qa.TEST1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.AP.qa.base.TestBase;
import com.AP.qa.pages.Homepage_Page;
import com.AP.qa.pages.Logout;
import com.AP.qa.pages.Payment;

public class TEST3 extends TestBase{

	Homepage_Page OHome;
	Payment Opayment;
	Logout Ologout;
	
	@Parameters("Browser")
	@BeforeTest
	public void init(String Browser) throws Throwable {
		initialization(Browser);
		
	}
	
	@BeforeClass
	public void Setup() throws Throwable {
		SetUP(this.getClass().getSimpleName(), driver.getTitle());
		
		//------Validation for Home Page is open or not -------------
		
		OHome =	HomePageVvalidation(driver.getTitle());
		
		 if(OHome!=null) {
			 Reporting("Pass", "URL Navigation", "Successfully navigated to Automation Practice", "User Should be able to navigate Automation Practice");
		 }else {
			 Reporting("Fail", "URL Navigation", "Unsuccessfully navigated to Automation Practice", "User Should be able to navigate Automation Practice");
			 closeBrowser();
		 }	
	}
	
	@Test(priority = 1,enabled = true)
	public void Multiple_Product_Selection_Test() throws Throwable {
			
		OHome.Select_Multiple_Product();
		
		Opayment=OHome.PriceValidation();
			
	}
	
	@Test(priority = 2,enabled = true)
	public void Login_And_Payment_Test() throws Throwable {
		if(Opayment!=null) {
			Reporting("Pass", "Payment Page Validation", "User successfully navigate to Payment Page", "User should be able to navigate to Payment Page");
			Opayment.proceed.click(); 
		}
		else {
			Reporting("Fail", "Payment Page Validation", "User unsuccessfully navigate to Payment Page", "User should be able to navigate to Payment Page");
			closeBrowser();
		}
		
		Opayment.PaymentProcess();
		Ologout = Opayment.FinalPrice_Validation();
	}
	
	@Test(priority = 3,enabled = true)
	public void Logout_Test() throws Throwable {
		Ologout.Logout_perform();
	}
	
	@AfterClass
	public void tearUp() {
		closeBrowser();
	}
}
