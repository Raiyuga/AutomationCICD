package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	//In below method defining all the configuration belong to extent report
	public static ExtentReports getReportObject() { // by making this method as static, I can access this method from another class without making its object
		
//		ExtentReports, ExtentSparkReporter (This 2 classes are important for out extent report to work)
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); //This object is responsible for doing config of our report
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports(); //This object is responsible for, to derive our extent reports execution
        extent.attachReporter(reporter); // This line gives the knowledge of all the config done in reporter class to main (ExtentReports) class
        extent.setSystemInfo("Tester", "Rishabh Shukla");
        return extent;
	}
}
