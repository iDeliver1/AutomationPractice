package com.AP.qa.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.AP.qa.base.TestBase;
import com.AP.qa.util.Excel_Libraries;
import com.AP.qa.util.Genral_Function;
import com.AP.qa.util.TestUtil;



public class Homepage_Page extends TestBase{

	//-----------------WebElement for Re-Order----------------------------
	@FindBy(xpath = "//span[contains(text(),'Order history and details')]")
	  WebElement orderDetails;	

	@FindBy(xpath = "//tr[contains(@class,'first_item')]")public
	  WebElement orderTable;
		
	@FindBy(xpath = "//tr[contains(@class,'first_item')]//a[2]")
	  WebElement selectFirstOrder;
	
	@FindBy(xpath = "//span[contains(text(),'sunil jaiswal')]")
	  WebElement profile;
	
	
	//-------------------WebElement for Single Product---------------------------------
	
	@FindBy(xpath = "//li[@class='ajax_block_product col-xs-12 col-sm-4 col-md-3 first-in-line first-item-of-tablet-line first-item-of-mobile-line hovered']//a[@class='product-name'][contains(text(),'Faded Short Sleeve T-shirts')]")
	
	  WebElement Tshirt;
	
	@FindBy(xpath = "//ul[@id='homefeatured']//li[@class='ajax_block_product col-xs-12 col-sm-4 col-md-3 first-in-line first-item-of-tablet-line first-item-of-mobile-line']//div[@class='product-image-container']")
	
	  WebElement Target;
	
	@FindBy(xpath = "//input[@id='quantity_wanted']") 
	  WebElement qty;
	
	@FindBy(xpath = "//div[@id='uniform-group_1']//following-sibling::option")
	
	 List < WebElement> size;
	 
	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
	
	  WebElement cart;

	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	
	  WebElement checkout;
	
	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	 WebElement proceed;
	
	
	
	//-----------------WebElement for Multiple Products----------------------------
	
	
	@FindBy(xpath = "//ul[@id='homefeatured']//following-sibling::li")
	  List<WebElement> MultiProducts;
	
	@FindBy(xpath = "//li[contains(@class,'ajax_block_product col-xs-12 col-sm-4 col-md-3')]//span[contains(text(),'Add to cart')]")
	  List<WebElement> Addtocart;
	
	@FindBy(xpath = "//span[@class='continue btn btn-default button exclusive-medium']")
	  WebElement ContinueShop;
	
	
	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	  WebElement ProccedCheckout;
	
	@FindBy(xpath = "//img[@class='logo img-responsive']")
	public
	WebElement homebtn;
	
	//-----------------WebElement for Price,Tax & Final Price(Price + Tax)-------------------
	
	@FindBy(xpath = "//span[contains(@id,'total_product_price')]")
	  List<WebElement> Price;
	
	@FindBy(xpath = "//span[@id='total_price']")
	  WebElement TotalPrice;
	
	@FindBy(xpath = "//td[@id='total_shipping']")
	  WebElement tax;
	
	@FindBy(xpath = "//td[@id='total_shipping']")public
	  WebElement FinalProced;
	
	
	
	
	public Homepage_Page() {
		PageFactory.initElements(driver, this);
	}
	
	
	
	//------------Validation for Final Price is equal to Price+Tax or not and then navigate to Payment Section----------------
	public static Payment_Page Price_Validation(Boolean Validationpoint) {
		try {
		
		if(Validationpoint==true) {
			return new Payment_Page();
		}
		}catch(Exception e) {
			
		}
		return  null;
	}
	
	
	
	public void SelectSingle_Product(String Size) throws InterruptedException {
		TestUtil.MoveElement(Target);	//Moving object to desired postion 
		
		Tshirt.click();
		
		WaitForObject(qty, "Check");
		
		qty.clear();
		
		qty.sendKeys(prop.getProperty("Qty"));	//Can give number of quantity 
		
		TestUtil.SelectQuantity(size, Size);	//Select Size -S / M /L  prop.getProperty("size")
		
		cart.click();
		
		WaitForObject(checkout, "Click");
		
		
	}
	
	
	public Payment_Page PriceValidation() throws Throwable {
		
		GlobalValue = Genral_Function.getMultiProductValue(Price, tax);
		if(Genral_Function.Argvalidation("CheckOut Price ", GlobalValue,TotalPrice.getText().replace("$", "")))
			return new Payment_Page();
		else
			return null;
	
	}
	
	
	public void Select_Multiple_Product() throws Throwable {
		int counter = 0;
		try {
			int multiproducts  = Integer.parseInt(prop.getProperty("MultiProduct"));
			
			for(int j = 0;j<multiproducts;j++ ) {
					
				System.out.println(MultiProducts.size());
					for(int i=0;i<=MultiProducts.size();i++)
					{
						try {
						if(Excel_Libraries.getdata(j).isEmpty()==false) {
							
							if(MultiProducts.get(i).getText().contains(Excel_Libraries.getdata(j)))
							{
								Reporting("Pass", j+1+" Validation for Input Value", "There must be an Input value", " Input Value"+Excel_Libraries.getdata(j));
								TestUtil.MoveElement(MultiProducts.get(i));
								
								WaitForObject(Addtocart.get(i), "Click");
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
						ProccedCheckout.click();
					}
					else
						WaitForObject(ContinueShop, "Click");		
			}
		
			
			}
			catch(Exception e) {
				String Casue = e.toString();
				Reporting("Fail", "Home  Page  Validation", "Home Page should displayed ", "Home Page is unable to show due to"+Casue.substring(1, 88));
				closeBrowser();
			}
		
	}
}
	
		
		
