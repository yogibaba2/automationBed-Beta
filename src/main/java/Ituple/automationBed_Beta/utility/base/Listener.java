package Ituple.automationBed_Beta.utility.base;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Listener implements ITestListener,ISuiteListener,IInvokedMethodListener {

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ISuite suite) {
		Reporter.log(suite.getName()+"suite started",true);
		
	}

	public void onFinish(ISuite suite) {
		Reporter.log(suite.getName()+"suite Finished",true);
		
	}

	public void onTestStart(ITestResult result) {
		Reporter.log(result.getName()+"test started",true);
		
	}

	public void onTestSuccess(ITestResult result) {
		Reporter.log(result.getName()+"passed",true);
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
}
