package Ituple.automationBed_Beta.utility.ReportManager;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Ituple.automationBed_Beta.utility.base.Constant;
import Ituple.automationBed_Beta.utility.base.Utils;

public class AdvanceReporting {

	private static ExtentReports report;
	private static ExtentTest logger;
	public static String reportPath;
	
	public static void intializeReport(String suiteName){
		Utils.addLog.debug("setting path for report : {}",Constant.ReportPath.getConstant()+suiteName+"\\AdvanceReport.html");
		Constant.SuiteName.setConstant(suiteName);
		reportPath=Constant.ReportPath.getConstant()+Constant.SuiteName.getConstant()+"\\AdvanceReport.html";
		Utils.addLog.debug("Initializing report for : {}",Constant.SuiteName.getConstant());
		report= new ExtentReports(reportPath);
		
	}
	
	public static void intializeLoger(String testCase){
		 Utils.addLog.debug("Initializing Testcase instance for {} in report",testCase);
		logger=report.startTest(testCase);
	}
	
	public static void addLogs(String status, String value){
		
		switch (status.toLowerCase()) {
		case "info":
			logger.log(LogStatus.INFO, value);
			break;
		case "pass":
			logger.log(LogStatus.PASS, value);
			break;
		case "fail":
			logger.log(LogStatus.FAIL, value);
			break;
		case "skip":
			logger.log(LogStatus.SKIP, value);
			break;
		case "error":
			logger.log(LogStatus.ERROR, value);
			break;
			
		default:
			break;
		}
		
		
	}
	
	public static void addLogs(String status, String value, String snapshotName) throws Exception{
		
		//capturing Snaps and getting image String
		String image =logger.addScreenCapture(Utils.uBase.takeScreenshot(snapshotName));
		
		switch (status.toLowerCase()) {
		case "info":
			logger.log(LogStatus.INFO, value, image);
			break;
		case "pass":
			logger.log(LogStatus.PASS, value, image);
			break;
		case "fail":
			logger.log(LogStatus.FAIL, value, image);
			break;
		case "skip":
			logger.log(LogStatus.SKIP, value, image);
			break;
		case "error":
			logger.log(LogStatus.ERROR, value, image);
			break;
			
		default:
			break;
		}
		
		
	}

	public static void endTest(){
		Utils.addLog.debug("Ending current Testcase instance in report");
		report.endTest(logger);
	}
	
	public static void endReport() {
		Utils.addLog.debug("Ending report for current Suite");
			report.flush();
	}
	
	public static void getReport(){
		//Utils.driver.get(reportPath);
	}
}
