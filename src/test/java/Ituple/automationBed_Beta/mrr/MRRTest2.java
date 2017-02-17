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
import Ituple.automationBed_Beta.pom.wap.ProjectDetailsPage;
import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Utils;
import Ituple.automationBed_Beta.utility.excelManager.ExcelUtility;
import Ituple.automationBed_Beta.utility.base.Constant;
import Ituple.automationBed_Beta.utility.base.SuiteBase;

public class MRRTest2 extends SuiteBase {

	@BeforeSuite
	public void beforeSuite() throws Exception {
		init();
		Utils.uBase.setupDriver();
	}

	@BeforeTest
	public void beforeTest() {

		Utils.uBase.intializeReport("MRRTest");
	}

	/************* MRR test ************/
	@Test(dataProvider = "MRRdataProvider")
	public void MrrTest(String testCase, String UserId, String Password, String role, String programName, String client,
			String caseType, String Casename) throws Exception {
		String status = "pass";
		Utils.uBase.StartTest(testCase);

		try {

			if (new ExcelUtility().getToRunFlag(TestSuite, "LoginTest", testCase)) {
				try {
					Method method = this.getClass().getMethod(testCase, String.class, String.class, String.class,
							String.class, String.class, String.class, String.class);
					method.invoke(this, UserId, Password, role, programName, client, caseType, Casename);
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

		AdvanceReporting.addLogs(status, testCase + status);
		Utils.uBase.writeResult("LoginTest", status);

		Utils.uBase.EndTest();

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
	@DataProvider(name = "MRRdataProvider")
	public Object[][] MRRdataProvider() {
		return new ExcelUtility().getData(TestSuite, "LoginTest");
	}

	/**
	 * Test Methods that works as Script
	 * 
	 * @throws Exception
	 **/

	public void Pradaxa2(String UserId, String Password, String role, String programName, String client,
			String caseType, String Casename) throws Exception {

		Utils.uBase.getDriverInstance();

		Utils.uBase.setUrl("https://onesource-qa.garretsongroup.com/mrr-ui/grg.html");
		Utils.uBase.openUrl();

		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		login.login(UserId, Password).verifyLogin();

		ProgramPage program = PageFactory.initElements(Utils.uBase.driver, ProgramPage.class);
		program.chooseProgram(programName);
		
		ProjectPage project = PageFactory.initElements(Utils.uBase.driver, ProjectPage.class);
		project.selectClient(client);
		
		ProjectDashboardPage projectDashboard=PageFactory.initElements(Utils.uBase.driver, ProjectDashboardPage.class);
		projectDashboard.selectCaseType(caseType).searchCase(Casename).selectCase(Casename);

		CasePage casePage = PageFactory.initElements(Utils.uBase.driver, CasePage.class);
		casePage.editCaseDetails(programName, client, Casename).openSection("Injury").editSection();

		Utils.uBase.driver.close();
	}

	/**
	 * verify that shown completed case count is matching 
	 * */
	public void verifyCasesCountProgram(String UserId, String Password, String role, String programName, String client,
			String caseType, String Casename) throws Exception {

		int programCaseCount;
		int allProjectsCaseCount;
		Utils.uBase.getDriverInstance();

		Utils.uBase.setUrl("https://onesource-qa.garretsongroup.com/mrr-ui/grg.html");
		Utils.uBase.openUrl();

		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		login.login(UserId, Password).verifyLogin();

		ProgramPage program = PageFactory.initElements(Utils.uBase.driver, ProgramPage.class);
		programCaseCount = program.getProgramsCompletedCaseCount(programName);
		program.chooseProgram(programName);
		
		ProjectPage project = PageFactory.initElements(Utils.uBase.driver, ProjectPage.class);
		allProjectsCaseCount = project.getAllProjectCompletedCaseCount();
		System.out.println(programCaseCount + " : " + allProjectsCaseCount);

		if (programCaseCount == allProjectsCaseCount) {
			AdvanceReporting.addLogs("pass",
					"Program : " + programName
							+ " completed case count matches to all its lawfirms completed case count",
					Constant.TestCase.getConstant() + "//match passed");
		} else {
			AdvanceReporting.addLogs("fail",
					"Program : " + programName
							+ " completed case count matches does not to all its lawfirms completed case count",
					Constant.TestCase.getConstant() + "//match failed");
		}

		Utils.uBase.driver.close();
	}
	
	/**
	 * verify that shown completed case count is matching 
	 * */
	public void verifyCasesCountProject(String UserId, String Password, String role, String programName, String client,
			String caseType, String Casename) throws Exception {

		int projectCaseCount;
		int allCaseCount;
		Utils.uBase.getDriverInstance();

		Utils.uBase.setUrl("https://onesource-qa.garretsongroup.com/mrr-ui/grg.html");
		Utils.uBase.openUrl();

		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		login.login(UserId, Password).verifyLogin();

		ProgramPage program = PageFactory.initElements(Utils.uBase.driver, ProgramPage.class);
		program.chooseProgram(programName);
		
		ProjectPage project = PageFactory.initElements(Utils.uBase.driver, ProjectPage.class);
		projectCaseCount = project.getProjectCompletedCaseCount(client);;
		
		project.selectClient(client);
		
		ProjectDashboardPage projectDashboard= PageFactory.initElements(Utils.uBase.driver, ProjectDashboardPage.class);
		allCaseCount = projectDashboard.selectCaseType(caseType).getCountOnCaseHeader(caseType);
		
		System.out.println(projectCaseCount + " : " + allCaseCount);

		if (projectCaseCount == allCaseCount) {
			AdvanceReporting.addLogs("pass",
					"Project : " + client
							+ " completed case count matches to all its completed case count",
					Constant.TestCase.getConstant() + "//match passed");
		} else {
			AdvanceReporting.addLogs("fail",
					"Project : " + client
							+ " completed case count matches does not to all its completed case count",
					Constant.TestCase.getConstant() + "//match failed");
		}

		Utils.uBase.driver.close();
	}
	
	public void verifyCasesStatus(String UserId, String Password, String role, String programName, String client,
			String caseType, String Casename) throws Exception {

		int tableCaseCount;
		int headerCaseCount;
		Utils.uBase.getDriverInstance();

		Utils.uBase.setUrl("https://onesource-qa.garretsongroup.com/mrr-ui/grg.html");
		Utils.uBase.openUrl();

		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		login.login(UserId, Password).verifyLogin();

		ProgramPage program = PageFactory.initElements(Utils.uBase.driver, ProgramPage.class);
		program.chooseProgram(programName);
		
		ProjectPage project = PageFactory.initElements(Utils.uBase.driver, ProjectPage.class);
		project.selectClient(client);		
		
		ProjectDashboardPage projectDashboard= PageFactory.initElements(Utils.uBase.driver, ProjectDashboardPage.class);
		headerCaseCount = projectDashboard.selectCaseType(caseType).getCountOnCaseHeader(caseType);
		
		tableCaseCount=projectDashboard.getTableCaseCount(caseType);
		
		System.out.println(tableCaseCount + " : " + headerCaseCount);

		if (tableCaseCount == headerCaseCount) {
			AdvanceReporting.addLogs("pass",
					"Status : " + caseType
					+ "  case count matches to case count of table",
					Constant.TestCase.getConstant() + "//match passed");
		} else {
			AdvanceReporting.addLogs("fail",
					"Status : " + caseType
					+ "  case count matches to case count of table",
					Constant.TestCase.getConstant() + "//match failed");
		}

		Utils.uBase.driver.close();
	}
	

	public void VerifyRecentlyViewedCaseProgram(String UserId, String Password, String role, String programName, String client,
			String caseType, String Casename) throws Exception {

		String viewedCase=Casename;
		String actualCase;
		Utils.uBase.getDriverInstance();

		Utils.uBase.setUrl("https://onesource-qa.garretsongroup.com/mrr-ui/grg.html");
		Utils.uBase.openUrl();

		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		login.login(UserId, Password).verifyLogin();

		ProgramPage program = PageFactory.initElements(Utils.uBase.driver, ProgramPage.class);
		program.chooseProgram(programName);
		
		ProjectPage project = PageFactory.initElements(Utils.uBase.driver, ProjectPage.class);
		project.selectClient(client);		
		
		ProjectDashboardPage projectDashboard= PageFactory.initElements(Utils.uBase.driver, ProjectDashboardPage.class);
		projectDashboard.selectCaseType(caseType).selectCase(Casename);
		
		Utils.uBase.navigate_back();
		Utils.uBase.navigate_back();
		
		actualCase=project.getRecentelyViewedCase(1);
	
		
		System.out.println(viewedCase + " : " + actualCase);

		if (actualCase.equalsIgnoreCase(viewedCase)) {
			AdvanceReporting.addLogs("pass",
					"Recently Viewed Case : " + viewedCase
					+ "  matches to actual displayed case on Project page",
					Constant.TestCase.getConstant() + "//match passed");
		} else {
			AdvanceReporting.addLogs("fail",
					"Recently Viewed Case : " + viewedCase
					+ " does not matches to actual displayed case on Project page",
					Constant.TestCase.getConstant() + "//match failed");
		}

		Utils.uBase.driver.close();
	}
	
	public void VerifyRecentlyViewedCaseProject(String UserId, String Password, String role, String programName, String client,
			String caseType, String Casename) throws Exception {

		String viewedCase=Casename;
		String actualCase;
		Utils.uBase.getDriverInstance();

		Utils.uBase.setUrl("https://onesource-qa.garretsongroup.com/mrr-ui/grg.html");
		Utils.uBase.openUrl();

		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		login.login(UserId, Password).verifyLogin();

		ProgramPage program = PageFactory.initElements(Utils.uBase.driver, ProgramPage.class);
		program.chooseProgram(programName);
		
		ProjectPage project = PageFactory.initElements(Utils.uBase.driver, ProjectPage.class);
		project.selectClient(client);		
		
		ProjectDashboardPage projectDashboard= PageFactory.initElements(Utils.uBase.driver, ProjectDashboardPage.class);
		projectDashboard.selectCaseType(caseType).selectCase(Casename);
		
		Utils.uBase.navigate_back();
		Utils.uBase.navigate_back();
		Utils.uBase.navigate_back();
		
		actualCase=project.getRecentelyViewedCase(1);
	
		
		System.out.println(viewedCase + " : " + actualCase);

		if (actualCase.equalsIgnoreCase(viewedCase)) {
			AdvanceReporting.addLogs("pass",
					"Recently Viewed Case : " + viewedCase
					+ "  matches to actual displayed case on Program page",
					Constant.TestCase.getConstant() + "//match passed");
		} else {
			AdvanceReporting.addLogs("fail",
					"Recently Viewed Case : " + viewedCase
					+ " does not matches to actual displayed case on Program page",
					Constant.TestCase.getConstant() + "//match failed");
		}

		Utils.uBase.driver.close();
	}
}
