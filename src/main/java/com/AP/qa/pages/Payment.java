package com.AP.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.AP.qa.base.TestBase;
import com.AP.qa.util.Extent_Report;


public class Payment extends TestBase{
	
	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	public static WebElement proceed;
	
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
	public static WebElement price;
	
	@FindBy(xpath = "//strong[contains(text(),'Your order on My Store is complete.')]")
	WebElement order_msg;
	
	@FindBy(xpath = "//span[@id='total_price']")
	WebElement amount;
	
	@FindBy(xpath = "//span[contains(@id,'total_product_price')]")
	public static  List<WebElement> Price;
	
	@FindBy(xpath = "//td[@id='total_shipping']")
	public static  WebElement tax;
	
	@FindBy(xpath = "//span[@id='total_price']")
	public static WebElement TotalPrice;
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement user;

	
	Extent_Report objExp = new Extent_Report();
	public Payment() {
		PageFactory.initElements(driver, this);
	}
	
	public String  paymentpage() throws Throwable {	
		processAddress.click();
		checkbox.click();
		processCarrier.click();
		String FPrice = amount.getText().replace("$", "");
		pay_method.click();
		confirm.click();
		return FPrice;
		}
}
