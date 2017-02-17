package Ituple.automationBed_Beta.mrrSmokeTest;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ituple.automationBed_Beta.utility.base.SuiteBase;
import Ituple.automationBed_Beta.utility.base.Utils;
import Ituple.automationBed_Beta.utility.excelManager.ExcelUtility;

public class SmokeTest extends SuiteBase{
	
	private String suiteName = this.getClass().getSimpleName();
	public static Thread mgr_Thread;
	public static Thread revwr_Thread;
	public static Thread qa_Thread;
	
	@BeforeTest
	public void beforeTest() throws Exception {
		Utils.uBase.setupDriver();
		Utils.uBase.intializeReport(suiteName);
	}

	@AfterTest
	public void afterTest() {
		//Utils.uBase.finalizeReport();
		//Utils.uBase.driver.quit();
	}

	// Data Provider
	/*@DataProvider(name = "SchdataProvider")
	public Object[][] SchdataProvider() {
		return new ExcelUtility().getData(TestSuite, suiteName);
	}*/

	/************* Scheduler Test test ************/
	@Test
	public void MRRSmoke(){
		mgr_Thread = new Thread(new ManagerThread("ManagerThread:"));
		revwr_Thread = new Thread(new ReviewerThread("ReviewerThread:"));
		qa_Thread = new Thread(new QAThread("QAThread:"));
		
		mgr_Thread.start();
		revwr_Thread.start();
		qa_Thread.start();
	}
	
	
}
