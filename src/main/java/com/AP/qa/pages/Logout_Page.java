package com.AP.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.AP.qa.base.TestBase;
import com.AP.qa.util.Genral_Function;

public class Logout_Page extends TestBase{
	@FindBy(xpath = "//a[@class='logout']")
	 WebElement signOut;
	
	@FindBy(xpath = "//div[@class='header_user_info']" )
	 WebElement signIn;
	
	public Logout_Page() {
		PageFactory.initElements(driver, this);
	}
	
	
	
	public void Logout_perform() throws Throwable {
	signOut.click();
	Genral_Function.logoutvalidation(signIn.getText());
	}
	
	
}
