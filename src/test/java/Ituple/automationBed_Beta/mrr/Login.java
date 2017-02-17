package Ituple.automationBed_Beta.mrr;

import java.lang.reflect.Method;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ituple.automationBed_Beta.pom.mrr.CasePage;
import Ituple.automationBed_Beta.pom.mrr.LoginPage;
import Ituple.automationBed_Beta.pom.mrr.ProgramPage;
import Ituple.automationBed_Beta.pom.mrr.ProjectDashboardPage;
import Ituple.automationBed_Beta.pom.mrr.ProjectPage;
import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.SuiteBase;
import Ituple.automationBed_Beta.utility.base.Utils;
import Ituple.automationBed_Beta.utility.excelManager.ExcelUtility;

public class Login extends SuiteBase {
	
	private String suiteName = this.getClass().getSimpleName();

	@BeforeSuite
	public void beforeSuite() throws Exception {
		init();
		Utils.uBase.setupDriver();
	}

	@BeforeTest
	public void beforeTest() {
		Utils.uBase.intializeReport(suiteName);
	}

	@AfterTest
	public void afterTest() {
		Utils.uBase.finalizeReport();
	}

	@AfterSuite
	public void afterSuite() {
		// Utils.driver.quit();
	}

	// Data Provider
	@DataProvider(name = "LogindataProvider")
	public Object[][] MRRdataProvider() {
		return new ExcelUtility().getData(TestSuite, suiteName);
	}
	
	/************* MRR test ************/
	@Test(dataProvider = "LogindataProvider")
	public void LoginTest(String testCase, String UserId, String Password, String role) throws Exception {
		String status = "pass";
		Utils.uBase.StartTest(testCase);

		try {

			if (new ExcelUtility().getToRunFlag(TestSuite, suiteName, testCase)) {
				try {
					String methodName=Utils.uBase.lowerCaseFirst(testCase.replaceAll("\\s+",""));
					
					Method method = this.getClass().getMethod(methodName, String.class, String.class,String.class);
					method.invoke(this, UserId, Password, role);
				} catch (NoSuchMethodException e) {
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

	

	/**
	 * Test Methods that works as Script
	 * 
	 * @throws Exception
	 **/

	public void validCredentials(String UserId, String Password, String role) throws Exception {

		Utils.uBase.getDriverInstance();

		Utils.uBase.setUrl("https://onesource-qa.garretsongroup.com/mrr-ui/grg.html");
		Utils.uBase.openUrl();

		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		login.login(UserId, Password).verifyLogin();
		
		Utils.uBase.driver.close();
	}
	
	public void invalidCredentials(String UserId, String Password, String role) throws Exception {

		Utils.uBase.getDriverInstance();

		Utils.uBase.setUrl("https://onesource-qa.garretsongroup.com/mrr-ui/grg.html");
		Utils.uBase.openUrl();

		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		login.login(UserId, Password).verifyLogin();
		
		Utils.uBase.driver.close();
	}


}
