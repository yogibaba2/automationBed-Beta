package Ituple.automationBed_Beta.wap;

import java.lang.reflect.Method;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ituple.automationBed_Beta.pom.mrr.LoginPage;
import Ituple.automationBed_Beta.pom.wap.ProgramPage;
import Ituple.automationBed_Beta.pom.wap.ProjectDetailsPage;
import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Utils;
import Ituple.automationBed_Beta.utility.excelManager.ExcelUtility;
import Ituple.automationBed_Beta.utility.excelManager.Read_XLS;
import Ituple.automationBed_Beta.utility.base.SuiteBase;


public class WapTestSuite extends SuiteBase {

	private String suiteName = this.getClass().getSimpleName();
	
	@BeforeSuite
	public void beforeSuite() throws Exception {
		System.out.println("before suite");
		init();
		Utils.uBase.setupDriver();
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("before test");
		Utils.uBase.intializeReport(suiteName);
	}

	@AfterTest
	public void afterTest() {
		System.out.println("after test");
		Utils.uBase.finalizeReport();

	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("after suite");
		//Utils.uBase.driver.quit();
	}

	/************* MRR test ************/
	@Test(dataProvider = "WAPdataProvider")
	public void WAPTest(String testCase, String UserId, String Password, String role, String programName, String client,String ExcelPath) throws Exception {
		System.out.println("test start");
		String status = "pass";
		Utils.uBase.StartTest(testCase);

		try {

			if (new ExcelUtility().getToRunFlag(TestSuite, suiteName, testCase)) {
				try {
					System.out.println("before method");
					Method method = this.getClass().getMethod(testCase, String.class, String.class, String.class,
							String.class, String.class, String.class);
					method.invoke(this, UserId, Password, role, programName, client, ExcelPath);
					System.out.println("after method");
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
					status = "skip";
				}
			} else {
				status = "skip";
			}

		} catch (Exception e) {
			status = "fail";
			e.printStackTrace();
		}

		AdvanceReporting.addLogs(status, testCase + " " + status);
		Utils.uBase.writeResult(suiteName, status);

		Utils.uBase.EndTest();

	}

	// Data Provider
	@DataProvider(name = "WAPdataProvider")
	public Object[][] MRRdataProvider() {
		System.out.println(new ExcelUtility().getData(TestSuite, suiteName));
		return new ExcelUtility().getData(TestSuite, suiteName);
	}

	
	
	
	
	/**
	 * Test Methods that works as Script
	 * 
	 * @throws Exception
	 **/

	public void caseUploadVerify(String UserId, String Password, String role, String programName, String client,String ExcelPath) throws Exception{
		
		System.out.println("start method");
		Utils.uBase.getDriverInstance();
		
		Utils.uBase.setUrl("http://wap-qa.garretsongroup.com/wap-ui/");
		Utils.uBase.openUrl();

		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		login.login(UserId, Password);
		
		ProgramPage programPage= PageFactory.initElements(Utils.uBase.driver, ProgramPage.class);
		programPage.chooseProgram(programName).selectProject(client);
		
		ProjectDetailsPage pdp = PageFactory.initElements(Utils.uBase.driver, ProjectDetailsPage.class);
		pdp.openAllTaskModal().verifyBulkCases(programName, client, ExcelPath);
		
		
		
		Utils.uBase.driver.close();
		
	}
	
}
