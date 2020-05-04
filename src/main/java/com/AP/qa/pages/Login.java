package com.AP.qa.pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.AP.qa.base.TestBase;
import com.AP.qa.util.TestUtil;


public class Login extends TestBase{
	
	@FindBy(xpath = "//a[@class='login']")
	public static WebElement signInbtn;
	
	@FindBy(xpath = "//input[@id='email']")
	public static WebElement user;
	
	@FindBy(xpath = "//input[@id='passwd']")
	public static WebElement password;
	
	@FindBy(xpath = "//button[@id='SubmitLogin']")
	public static WebElement signIn;
	
	@FindBy(xpath = "//img[@class='logo img-responsive']")
	public static WebElement home;
	
	@FindBy(xpath = "//span[contains(text(),'sunil jaiswal')]")
	public static WebElement validate_user;
	
	
	public Login() {
		PageFactory.initElements(driver, this);
	}
	
	public static boolean validateAPLogo(){
		return validate_user.isDisplayed();
	}
	

	
	public static homepage Beforeloginvalidation() {
		try {
			Assert.assertEquals(true,validateAPLogo());
			return new homepage();
		}catch(Exception e) {
			
		}
		
		return null;
	}
	

	
	public static Payment Afterloginvalidation() {
		try {
			Assert.assertEquals(true, validateAPLogo());
			return new Payment();
		}catch(Exception e) {
			
		}
		
		return null;
	}
	
		
	}


