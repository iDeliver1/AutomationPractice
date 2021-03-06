package com.AP.qa.test;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.AP.qa.base.TestBase;
import com.AP.qa.pages.Login_Page;
import com.AP.qa.pages.Logout;
import com.AP.qa.pages.Payment;
import com.AP.qa.pages.Homepage_Page;
import com.AP.qa.util.Excel_Libraries;
import com.AP.qa.util.Genral_Function;
import com.AP.qa.util.TestUtil;

public class TC002_Multiple_Products_Checkout_2 extends TestBase{

	Login_Page login;

	
/*
	
	@Parameters("Browser")
	@BeforeClass
	public void init(String Browser) throws Throwable {
		initialization(Browser);
		SetUP(this.getClass().getSimpleName(), driver.getTitle());
		//Validation for Given URL is opened or not
		 if(HomePageValidation(driver.getTitle())!=null) {
			 Reporting("Pass", "URL Navigation", "Successfully navigated to Automation Practice", "User Should be able to navigate Automation Practice");
		 }else {
			 Reporting("Fail", "URL Navigation", "Unsuccessfully navigated to Automation Practice", "User Should be able to navigate Automation Practice");
		 }
		
	}
	
	@Test//(dataProvider = "Run")
	public void LoginTest() throws Throwable{
		
		Login.signInbtn.click();
		Login.user.sendKeys(prop.getProperty("username"));
		Login.password.sendKeys(prop.getProperty("password"));
		Login.signIn.click();
		Login.home.click();
		
		
		if(Login.Beforeloginvalidation()!=null) {
			 Reporting("Pass", "Login Page Validation", "User successfull naviagted to homepage with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"), "User should be able to  naviagted to homepage with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"));	 
		}
		else {
			 Reporting("Fail", "Login Page Validation", "User unsuccessfull naviagted to homepage with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"), "User should be able to  naviagted to homepage with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"));
			 closeBrowser();
		}
	}
	
	@Parameters("Product")
	@Test(priority = 2, enabled = true)
	public void MultiplePro(String Product)throws Throwable {
		
		//Selection for Multiple products
		
		int counter = 0;
		try {
			int multiproducts  = Integer.parseInt(prop.getProperty("MultiProduct"));
			
			for(int j = 0;j<multiproducts;j++ ) {
					
				System.out.println(homepage.MultiProducts.size());
					for(int i=0;i<=homepage.MultiProducts.size();i++)
					{
						try {
						if(Excel_Libraries.getdata(j).isEmpty()==false) {
							
							if(homepage.MultiProducts.get(i).getText().contains(Excel_Libraries.getdata(j)))
							{
								Reporting("Pass", j+1+" Validation for Input Value", "There must be an Input value", " Input Value"+Excel_Libraries.getdata(j));
								TestUtil.MoveElement(homepage.MultiProducts.get(i));
								
								WaitForObject(homepage.Addtocart.get(i), "Click");
								counter=counter+1;
								break;
							}
						}
					}
					catch(Exception f){
						f.printStackTrace();
						Reporting("Fail", j+1+" Validation for Input Value", "There must be an Input value", " Input Value"+Excel_Libraries.getdata(j));
					}
					}
					
					if(counter==multiproducts) {
						homepage.ProccedCheckout.click();
					}
					else
						WaitForObject(homepage.ContinueShop, "Click");		
			}
		
			
			}
			catch(Exception e) {
				String Casue = e.toString();
				Reporting("Fail", "Home  Page  Validation", "Home Page should displayed ", "Home Page is unable to show due to"+Casue.substring(1, 88));
				closeBrowser();
			}
		
		
	}
	
	@Test(priority = 3, enabled = true)
	public void PaymentTest() throws Throwable{
		GlobalValue = Genral_Function.getMultiProductValue(homepage.Price, homepage.tax);
		GlobalArgumrnt = Genral_Function.Argvalidation("CheckOut Price ", GlobalValue,homepage.TotalPrice.getText().replace("$", ""));
		
		try{
		if(homepage.Price_Validation(GlobalArgumrnt)!=null) {
			Reporting("Pass", "Payment Page Validation", "User successfully navigate to Payment Page", "User should be able to navigate to Payment Page");
			Payment.proceed.click(); 
		}
		}
		catch(Exception e)
		{
			Reporting("Fail", "Payment Page Validation", "User unsuccessfully navigate to Payment Page", "User should be able to navigate to Payment Page");
			closeBrowser();
		}
		
		Payment.processAddress.click();
		Payment.checkbox.click();
		Payment.processCarrier.click();
		GlobalValue = Payment.amount.getText().replace("$", "");
		Payment.pay_method.click();
		Payment.confirm.click();
		
		if(Genral_Function.Argvalidation("Final Price Validation", GlobalValue,Payment.price.getText().replace("$", ""))==true) {
			Payment.logoutvalidation();
		}
		
	}
	
	@Test(priority = 4, enabled = true)
	public void LogoutTest() throws Throwable {
		Logout.signOut.click();
		Genral_Function.logoutvalidation(Logout.signIn.getText());
	}
	
	
	@AfterClass
	public void Flush() throws Throwable
	{
		closeBrowser();
	}
	
	*/
}
