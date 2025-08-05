package ListenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseTest.BaseClass;

public class ListenersImplementation implements ISuiteListener,ITestListener{
    
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		
		Reporter.log("Report Configuration",true);
		Date d=new Date();
		String newdate = d.toString().replace(" ", "_").replace(":","_");
		
	    spark=new ExtentSparkReporter("./AdvanceReport/report_"+newdate+".html");
		spark.config().setDocumentTitle("NinzaCRM Test Suite Results");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 12");
		report.setSystemInfo("Browser", "Edge");
	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
		Reporter.log("Report backup",true);
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		 test = report.createTest(result.getMethod().getMethodName());
		 test.log(Status.INFO,"==="+result.getMethod().getMethodName()+"Execution STARTED====");
		
		//Reporter.log("==="+result.getMethod().getMethodName()+"Execution STARTED====",true);	
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		test.log(Status.PASS,"==="+result.getMethod().getMethodName()+"SUCCESS====");
		//Reporter.log("==="+result.getMethod().getMethodName()+"SUCCESS====",true);		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		Reporter.log("===="+testName+"FAILURE=====",true);
		Date d=new Date();
		String newdate = d.toString().replace(" ", "_").replace(":","_");

		
		TakesScreenshot shot=(TakesScreenshot) BaseClass.sdriver;
		 String temp = shot.getScreenshotAs(OutputType.BASE64);
		 test.addScreenCaptureFromBase64String(temp,testName+newdate);
		 
		 test.log(Status.FAIL,"===="+testName+"FAILED=====");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		test.log(Status.SKIP,"==="+result.getMethod().getMethodName()+"SKIPPED====");
		//Reporter.log("==="+result.getMethod().getMethodName()+"SKIPPED====",true);
		
	}

	
	

}
