package com.AP.qa.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.AP.qa.base.TestBase;
import com.AP.qa.util.Genral_Function;


public class Payment_Page extends TestBase{
	
	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	public
	  WebElement proceed;
	
	@FindBy(xpath = "//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]")
	
	WebElement processAddress;
	
	@FindBy(xpath = "//input[@id='cgv']")
	
	WebElement checkbox;
	
	@FindBy(xpath = "//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]")
	
	WebElement processCarrier; 
	
	@FindBy(xpath = "//a[@class='bankwire']")

	WebElement pay_method;
	
	@FindBy(xpath = "//span[contains(text(),'I confirm my order')]")
	
	WebElement confirm;
	
	@FindBy(xpath = "//span[@class='price']")
	 WebElement price;
	
	@FindBy(xpath = "//strong[contains(text(),'Your order on My Store is complete.')]")
	WebElement order_msg;
	
	@FindBy(xpath = "//span[@id='total_price']")
	
	WebElement amount;
	
	@FindBy(xpath = "//span[contains(@id,'total_product_price')]")
	  List<WebElement> Price;
	
	@FindBy(xpath = "//td[@id='total_shipping']")
	  WebElement tax;
	
	@FindBy(xpath = "//span[@id='total_price']")
	 WebElement TotalPrice;
	
	
	

	
	public Payment_Page() {
		PageFactory.initElements(driver, this);
	}

	public void PaymentProcess() throws Throwable {
		
		
		if(driver.getTitle().contains("Login - My Store"))
		{
			Login_Page Ologin = new Login_Page();
			Ologin.PaymentPageLogin(prop.getProperty("username"),prop.getProperty("password"));
		}
		processAddress.click();
		checkbox.click();
		processCarrier.click(	);
		GlobalValue = amount.getText().replace("$", "");
		pay_method.click();
		confirm.click();
	}
	
	
	
	public  Logout_Page FinalPrice_Validation() throws Throwable {
		if(Genral_Function.Argvalidation("Final Price Validation", GlobalValue,price.getText().replace("$", ""))==true) {
			return new Logout_Page();
			}
		else return null;
	}
}
