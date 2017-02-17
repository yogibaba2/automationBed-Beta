package Ituple.automationBed_Beta.mrr;

//Libraries import
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ituple.automationBed_Beta.pom.mrr.CasePage;
//AutomationBed import
import Ituple.automationBed_Beta.pom.mrr.LoginPage;
import Ituple.automationBed_Beta.pom.mrr.ProgramPage;
import Ituple.automationBed_Beta.pom.mrr.ProjectDashboardPage;
import Ituple.automationBed_Beta.pom.mrr.ProjectPage;
import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Utils;
import Ituple.automationBed_Beta.utility.excelManager.ExcelUtility;
import Ituple.automationBed_Beta.utility.base.SuiteBase;


public class MRRTest1 extends SuiteBase {

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
		String status = "Passed";
		Utils.uBase.StartTest(testCase);

		// intialize test
		try {
			if (new ExcelUtility().getToRunFlag(TestSuite, "LoginTest", testCase)) {

				Utils.uBase.getDriverInstance();
				Utils.uBase.setUrl("https://onesource-qa.garretsongroup.com/mrr-ui/grg.html");
				Utils.uBase.openUrl();

				LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
				login.login(UserId, Password).verifyLogin();

				ProgramPage program = PageFactory.initElements(Utils.uBase.driver, ProgramPage.class);
				program.chooseProgram(programName);
				
				ProjectPage project = PageFactory.initElements(Utils.uBase.driver, ProjectPage.class);
				project.selectClient(client);
				
				ProjectDashboardPage projectDashboardPage= PageFactory.initElements(Utils.uBase.driver, ProjectDashboardPage.class);
				projectDashboardPage.selectCaseType(caseType).selectCase(Casename);

				CasePage casePage = PageFactory.initElements(Utils.uBase.driver, CasePage.class);
				casePage.editCaseDetails(programName, client, Casename).openSection("Injury").editSection();

				// end test

			} else {
				status="Skipped";
				AdvanceReporting.addLogs("skip", testCase + " Skipped");
				
			}
		} catch (Exception e) {
			status="Failed";
			AdvanceReporting.addLogs("fail", testCase + " Failed");
			e.printStackTrace();
		}

		AdvanceReporting.addLogs("pass", testCase + " Passed");
		new ExcelUtility().writeResult(TestSuite, "LoginTest", "status", testCase, status);

		Utils.uBase.EndTest();
		Utils.uBase.driver.close();
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
}
