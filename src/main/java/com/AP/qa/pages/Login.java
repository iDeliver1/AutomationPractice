package com.AP.qa.pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.AP.qa.base.TestBase;



public class Login extends TestBase{
	
	@FindBy(xpath = "//a[@class='login']")
	  WebElement signInbtn;
	
	@FindBy(xpath = "//input[@id='email']")
	  WebElement user;
	
	@FindBy(xpath = "//input[@id='passwd']")
	 WebElement password;
	
	@FindBy(xpath = "//button[@id='SubmitLogin']")
	  WebElement Loginbtn;
	
	@FindBy(xpath = "//img[@class='logo img-responsive']")
	  WebElement home;
	
	@FindBy(xpath = "//span[contains(text(),'sunil jaiswal')]")
	static	  WebElement validate_user;
	
	
	public Login() {
		PageFactory.initElements(driver, this);
	}
	
	public static boolean validateAPLogo(){
		return validate_user.isDisplayed();
	}
	
	
	
		public Homepage UserLogin(String username,String pass) throws Throwable {
			signInbtn.click();
			user.sendKeys(username);
			password.sendKeys(pass);
			Loginbtn.click();
			
			if(Logoinvalidation()==true) {
				home.click();
				Reporting("Pass", "Login Page Validation", "User successfull naviagted to homepage with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"), "User should be able to  naviagted to homepage with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"));
				return new Homepage();
			}
			else {
				Reporting("Fail", "Login Page Validation", "User unsuccessfull naviagted to homepage with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"), "User should be able to  naviagted to homepage with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"));
				 closeBrowser();
				return null;
			}
	}
	
		
	public boolean  Logoinvalidation() {
		try {
			Assert.assertEquals(true,validateAPLogo());
			return true;
		}catch(Exception e) {
			return false;
		}
		
		
	}
	

	

	
		
	}


