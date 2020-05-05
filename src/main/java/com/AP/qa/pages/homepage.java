package com.AP.qa.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.AP.qa.base.TestBase;



public class homepage extends TestBase{

	//-----------------WebElement for Re-Order----------------------------
	@FindBy(xpath = "//span[contains(text(),'Order history and details')]")public static
	  WebElement orderDetails;	

	@FindBy(xpath = "//tr[contains(@class,'first_item')]")public
	  WebElement orderTable;
		
	@FindBy(xpath = "//tr[contains(@class,'first_item')]//a[2]")public static
	  WebElement selectFirstOrder;
	
	@FindBy(xpath = "//span[contains(text(),'sunil jaiswal')]")public static
	  WebElement profile;
	
	
	//-------------------WebElement for Single Product---------------------------------
	
	@FindBy(xpath = "//li[@class='ajax_block_product col-xs-12 col-sm-4 col-md-3 first-in-line first-item-of-tablet-line first-item-of-mobile-line hovered']//a[@class='product-name'][contains(text(),'Faded Short Sleeve T-shirts')]")
	public static
	  WebElement Tshirt;
	
	@FindBy(xpath = "//ul[@id='homefeatured']//li[@class='ajax_block_product col-xs-12 col-sm-4 col-md-3 first-in-line first-item-of-tablet-line first-item-of-mobile-line']//div[@class='product-image-container']")
	public static
	  WebElement Target;
	
	@FindBy(xpath = "//input[@id='quantity_wanted']") public static
	  WebElement qty;
	
	@FindBy(xpath = "//div[@id='uniform-group_1']//following-sibling::option")
	public static
	 List < WebElement> size;
	 
	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
	public static
	  WebElement cart;

	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	public static
	  WebElement checkout;
	
	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	public static WebElement proceed;
	
	
	
	//-----------------WebElement for Multiple Products----------------------------
	
	
	@FindBy(xpath = "//ul[@id='homefeatured']//following-sibling::li")public static
	  List<WebElement> MultiProducts;
	
	@FindBy(xpath = "//li[contains(@class,'ajax_block_product col-xs-12 col-sm-4 col-md-3')]//span[contains(text(),'Add to cart')]")public static
	  List<WebElement> Addtocart;
	
	@FindBy(xpath = "//span[@class='continue btn btn-default button exclusive-medium']")public static
	  WebElement ContinueShop;
	
	
	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")public static
	  WebElement ProccedCheckout;
	
	
	
	//-----------------WebElement for Price,Tax & Final Price(Price + Tax)-------------------
	
	@FindBy(xpath = "//span[contains(@id,'total_product_price')]")
	public static  List<WebElement> Price;
	
	@FindBy(xpath = "//span[@id='total_price']")public static
	  WebElement TotalPrice;
	
	@FindBy(xpath = "//td[@id='total_shipping']")
	public static  WebElement tax;
	
	@FindBy(xpath = "//td[@id='total_shipping']")public
	  WebElement FinalProced;
	
	
	
	
	public homepage() {
		PageFactory.initElements(driver, this);
	}
	
	
	
	//------------Validation for Final Price is equal to Price+Tax or not and then navigate to Payment Section----------------
	public static Payment Price_Validation(Boolean Validationpoint) {
		try {
		
		if(Validationpoint==true) {
			return new Payment();
		}
		}catch(Exception e) {
			
		}
		return  null;
	}
	
	
	
	
	
	
}
	
		
		
