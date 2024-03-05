package CommonUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListnerImplimentation implements ITestListener {
	
	
	ExtentReports report;
	public static void main(String [] args) {
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		Reporter.log(methodname+" TestScript Execution started" , true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		Reporter.log(methodname+" TestScript Execution passed" , true);
//		test
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String message = result.getThrowable().toString();
		String methodname = result.getMethod().getMethodName();
		Reporter.log(methodname+" TestScript Execution failed "+message , true);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();

		Reporter.log(methodname+" TestScript Execution skipeed" , true);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
//		String methodname = result.getMethod().getMethodName();
//		Reporter.log(" Execution started" , true);
		
		// use ExtentSparkReporter class to just configure extentreport
		JavaUtil jutil = new JavaUtil();
		ExtentSparkReporter reporter = new ExtentSparkReporter("./extentreport/report"+jutil.getRandomNumber()+".html");
		reporter.config().setDocumentTitle("Vtigercrm");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setReportName("organization");
		
		// use ExtentReports to generate ExtentReport
		 report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("OS", "Window");
		report.setSystemInfo("Browser", "Chome");
		report.setSystemInfo("Author", "Sid");
		
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
//		String methodname = result.getMethod().getMethodName();
		Reporter.log(" Execution passed" , true);

		report.flush();
	}

}
