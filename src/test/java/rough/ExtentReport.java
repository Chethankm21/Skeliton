package rough;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReport {

	public static ExtentReports extent;
	public static ExtentSparkReporter htmlreporter;
	public static ExtentTest test;
	@BeforeTest
	public static void createInstance() {
		String fileName="./Rough/exereport.html";
		htmlreporter =new ExtentSparkReporter(fileName);
		htmlreporter.config().setTheme(Theme.DARK);
		htmlreporter.config().setDocumentTitle("Document Title");
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setReportName("Report Name");
        
        
		extent = new ExtentReports();
		
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Automation Tester", "Rahul Arora");
        extent.setSystemInfo("Organization", "Way2Automation");
        extent.setSystemInfo("Build no", "W2A-1234");
        
       // return extent;
	}
	
	@AfterTest
	public void endReport() {
		extent.flush();
	}
	
	
	@Test
	public void doLogin() {
		test=extent.createTest("doLogin Test");
		System.out.println("Executing doLogin Test");
	}
	
	@Test
	public void doLogin1() {
		
		test=extent.createTest("doLogin1 Test");
		throw new SkipException("Skipped to see result");
		//System.out.println("Executing doLogin1 Test");
	}
	@Test
	public void doLogin2() {
		//test=extent.createTest("doLogin2 Test");
		Assert.fail("Failed to see report");
		System.out.println("Executing doLogin2 Test");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			
			String methodName=result.getMethod().getMethodName();
			String text= "<b>"+ "TESTCASE :"+methodName.toUpperCase()+" FAILED"+"</b>";
			Markup m = MarkupHelper.createLabel(text, ExtentColor.RED); 
			test.fail(m);
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			
			String methodName=result.getMethod().getMethodName();
			String text= "<b>"+ "TESTCASE :"+methodName.toUpperCase()+" SKIPPED"+"</b>";
			Markup m = MarkupHelper.createLabel(text, ExtentColor.AMBER); 
			test.skip(m);
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			
			String methodName=result.getMethod().getMethodName();
			String text= "<b>"+ "TESTCASE :"+methodName.toUpperCase()+" PASSED"+"</b>";
			Markup m = MarkupHelper.createLabel(text, ExtentColor.GREEN); 
			test.pass(m);
		}
	}
	
}
