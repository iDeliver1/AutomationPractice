package com.AP.qa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.AP.qa.base.TestBase;


public class TestUtil extends TestBase{
	

static Extent_Report objRep = new Extent_Report();
public static long PAGE_LOAD_TIMEOUT = 40;
public static long IMPLICIT_WAIT = 40;
public static String Report_Folder_path = "C:\\Reporting\\Report"+fTimestamp();



	//------------------Function for Current Date -------------------
	public static String fGetCurrentDate()
	{
		Date date = new Date();  
	    SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");  
	    String strDate = dateformat.format(date); 
	    return strDate;
	}

	
//-------------------------------------------TimeStamp Function----------------------------------	
		public static String fTimestamp()
		{
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss");
			String time = dateFormat.format(now);
			return time.replace("-", "");
		}
		 
		//-----------------------------------------------Screenshot Function-------------------------------	
	   public static String fScreenReport() throws Throwable
		{
	    	File source_image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    	String Image_path = Report_Folder_path+"/screenshots/" + System.currentTimeMillis() + ".png";
	    	//System.out.println(Image_path);
			File Desti_image = new File(Image_path);
			FileUtils.copyFile(source_image,Desti_image);
			return ""+Desti_image;
		}
	   
	   
	 //-----------------------------------------------Screenshot Function At end of the Function-------------------------------	 
		public  void takeScreenshotAtEndOfTest() throws Throwable  {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		}
		
					
		//-------------------Function for getting Chrome Browser Version---------------------
		public static String getBrowserVersion() throws IOException {
			try {
			 
		   Runtime rt = Runtime.getRuntime();
	       Process proc =  rt.exec("cmd  /K \"dir /B/AD \"C:/Program Files (x86)/Google/Chrome/Application/\"|findstr /R /C:\"^[0-9].*\\..*[0-9]$\"");
	       InputStream stdIn = proc.getInputStream();
	       InputStreamReader isr = new InputStreamReader(stdIn);
	       BufferedReader br = new BufferedReader(isr);
	       
	       while ((brow = br.readLine()) != null) {
	            System.out.println("Chrome Browser Version is - "+brow);
	            break;
	            }
	    
	       return brow.substring(0, brow.length() - 4);
		}
		catch(Exception e)
		{
			return null;
		}
		
		}

		//------------------Function for move able object------------------- 
		public static void MoveElement(WebElement element) {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
		}
		
		
		//---------------------function for Select size --------------------
		public static void SelectQuantity(List<WebElement> element,String size) {
			System.out.println(element.size());
			for(int i=0;i<element.size();i++) {
				if(element.get(i).getAttribute("Title").contains(size)) {
					element.get(i).click();
					break;
				}
			}
		}
}
