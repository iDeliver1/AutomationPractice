package com.AP.qa.util;


import com.AP.qa.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Extent_Report {
	 static ExtentTest logger,child_logger,parent_logger;
	  private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	  private static ThreadLocal<ExtentReports>  extent1 =new ThreadLocal<ExtentReports>();
	  ExtentHtmlReporter htmlReporter;
	  static ExtentReports extent;
	  static String Report_Folder_path = "C:\\Reporting\\Report"+TestUtil.fTimestamp();
	  static int Stepnumber=1,i=1,functioncall=1;
	  
	  
	  //------------Creating Report for HTML Reporting-----------------
	    static   {
		  ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Report_Folder_path + "\\testReport.html");
	    extent = new ExtentReports();
	    extent.attachReporter(htmlReporter);
	    extent.setSystemInfo("OS", "OS");
	    extent.setSystemInfo("Browser", "browser");
	    htmlReporter.config().setDocumentTitle("Extent Report Demo");
	    htmlReporter.config().setReportName("Test Report");
	    htmlReporter.config().setTheme(Theme.STANDARD);
	    
	    
	  
	}

//--------------Getting Instance of Extent-Report--------------
	  public synchronized ExtentReports getInstance() {	
		  	return extent1.get();
	  }


	//Getting TestName
	  public  ExtentTest CreateRoportname(String Step_details){
		  logger =extent.createTest(Step_details);
		  functioncall=1;
		  extentTest.set(logger);
		  return extentTest.get();
			
	  }
	  
	  
	//--------------Getting Instance of Extent-Test--------------
	  public synchronized  ExtentTest getTest() {
	      return extentTest.get();
	  }
	  
	  
	  
	  //--------------------Creating Report----------------------
	  public  void Report(String Status1,String Description,String ActualStep,String ExpectedStep) throws Throwable{

		  String ReportStatus = "<b>Step Number "+functioncall+"<br>Description :</b> "+Description+"<br><b>Expected :</b> "+ExpectedStep+"<br><b>Actual :</b> "+ActualStep;
		  try{
			  
			Excel_Libraries.fExcelReporter(Description, ActualStep, ExpectedStep, Status1, TestUtil.fGetCurrentDate());
			
			if(Status1.equalsIgnoreCase("PASS")){	
				
				extentTest.get().log(Status.PASS, ReportStatus);
				extentTest.get().addScreenCaptureFromPath(TestUtil.fScreenReport());
				flush();
			}
			else{
				
				extentTest.get().log(Status.FAIL, ReportStatus);
				extentTest.get().addScreenCaptureFromPath(TestUtil.fScreenReport());
				TestBase.closeBrowser();
	
			}
			
			}catch(Exception e){
				System.out.println(e);
			}
			functioncall=functioncall+1;
			
		}
	  

	  //-----------------Flushing Report and Save --------------------
	public void flush(){
		extent.flush();
	}
	
	
}
