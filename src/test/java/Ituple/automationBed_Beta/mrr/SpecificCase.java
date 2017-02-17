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
import Ituple.automationBed_Beta.pom.mrr.LogoutModal;
import Ituple.automationBed_Beta.pom.mrr.ProgramPage;
import Ituple.automationBed_Beta.pom.mrr.ProjectDashboardPage;
import Ituple.automationBed_Beta.pom.mrr.ProjectPage;
import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Constant;
import Ituple.automationBed_Beta.utility.base.SuiteBase;
import Ituple.automationBed_Beta.utility.base.Utils;
import Ituple.automationBed_Beta.utility.excelManager.ExcelUtility;

public class SpecificCase extends SuiteBase{
	private String suiteName = this.getClass().getSimpleName();

	@BeforeSuite
	public void beforeClass() throws Exception {
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
	public void afterClass() {
		// Utils.uBase.driver.quit();
	}

	/************* MRR test ************/
	@Test(dataProvider = "MRRdataProvider")
	public void specificCaseTest(String testCase, String UserId, String Password, String role, String programName, String client,String caseType,
			String caseName, String data) throws Exception {
		String status = "pass";
		Utils.uBase.StartTest(testCase);

		try {

			if (new ExcelUtility().getToRunFlag(TestSuite, suiteName, testCase)) {
				try {
					String methodName=Utils.uBase.lowerCaseFirst(testCase.replaceAll("\\s+",""));
					
					Method method = this.getClass().getMethod(methodName, String.class, String.class, String.class,
							String.class, String.class, String.class, String.class, String.class);
					method.invoke(this, UserId, Password, role, programName, client,caseType, caseName, data);
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

	// Data Provider
	@DataProvider(name = "MRRdataProvider")
	public Object[][] MRRdataProvider() {
		return new ExcelUtility().getData(TestSuite, suiteName);
	}

	
	/**
	 * Test Methods that works as Script
	 * 
	 * @throws Exception
	 **/
	
	public void verifyEscalateFunctionality(String UserId, String Password, String role, String programName, String client,String caseType,
			String caseName, String data) throws Exception{
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
		if(projectDashboard.selectCaseType(caseType).verifyCase(caseName)){
			projectDashboard.selectCase(caseName);
			
			CasePage cPage= PageFactory.initElements(Utils.uBase.driver, CasePage.class);
			cPage.escalate(role,data);
			
			LogoutModal logout= PageFactory.initElements(Utils.uBase.driver, LogoutModal.class);
			logout.logout(UserId);
			
		}else{
			AdvanceReporting.addLogs("fail", "case not found");
			AdvanceReporting.addLogs("fail", "case not found",Constant.TestCase.getConstant()+"\\caseNotFound");
		}
		
		Utils.uBase.driver.close();
			
	}
	
	public void verifyMarkAsDescripancyFunctionality(String UserId, String Password, String role, String programName, String client,String caseType,
			String caseName, String data) throws Exception{
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
		if(projectDashboard.selectCaseType(caseType).verifyCase(caseName)){
			projectDashboard.selectCase(caseName);
			
			CasePage cPage= PageFactory.initElements(Utils.uBase.driver, CasePage.class);
			cPage.markAsDiscrepancy(role, data);
			
			LogoutModal logout= PageFactory.initElements(Utils.uBase.driver, LogoutModal.class);
			logout.logout(UserId);
			
		}else{
			AdvanceReporting.addLogs("fail", "case not found");
			AdvanceReporting.addLogs("fail", "case not found",Constant.TestCase.getConstant()+"\\caseNotFound");
		}
		
		Utils.uBase.driver.close();
			
	}
	
}
