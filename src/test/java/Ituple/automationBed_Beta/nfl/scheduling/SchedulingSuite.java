package Ituple.automationBed_Beta.nfl.scheduling;

import java.lang.reflect.Method;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ituple.automationBed_Beta.pom.nfl.scheduling.HomePage;
import Ituple.automationBed_Beta.pom.nfl.scheduling.LoginPage;
import Ituple.automationBed_Beta.pom.nfl.scheduling.NflMemberTab;
import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.SuiteBase;
import Ituple.automationBed_Beta.utility.base.Utils;
import Ituple.automationBed_Beta.utility.excelManager.ExcelUtility;

public class SchedulingSuite extends SuiteBase {

	private String suiteName = this.getClass().getSimpleName();

	@BeforeTest
	public void beforeTest() throws Exception {
		init();
		Utils.uBase.setupDriver();
		Utils.uBase.intializeReport(suiteName);
	}

	@AfterTest
	public void afterTest() {
		Utils.uBase.finalizeReport();
		Utils.uBase.driver.quit();
	}

	// Data Provider
	@DataProvider(name = "SchdataProvider")
	public Object[][] SchdataProvider() {
		return new ExcelUtility().getData(TestSuite, suiteName);
	}

	/************* Scheduler Test test ************/
	@Test(dataProvider = "SchdataProvider")
	public void schedulerTest(String testCase, String userId, String password, String GRGID) throws Exception {
		String status = "pass";
		Utils.uBase.StartTest(testCase);

		try {

			if (new ExcelUtility().getToRunFlag(TestSuite, suiteName, testCase)) {
				try {
					String methodName = Utils.uBase.lowerCaseFirst(testCase.replaceAll("\\s", ""));

					Method method = this.getClass().getMethod(methodName, String.class, String.class, String.class);
					method.invoke(this, userId, password, GRGID);
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
	 **/

	/**
	 * This method verify Scheduling visit flow in NFL
	 * 
	 * @throws Exception
	 */
	public void verifyScheduleVisit(String userId, String password, String GRGID) throws Exception {
		Utils.uBase.getDriverInstance();

		Utils.uBase.setUrl("https://appnetqa2.garretsongroup.com/MedMon/default.aspx");
		Utils.uBase.openUrl();

		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		login.login(userId, password);

		HomePage home = PageFactory.initElements(Utils.uBase.driver, HomePage.class);
		NflMemberTab memberTab = home.selectMemebrTab().searchBy("State", "GA").selectRandomClassMember().openScheduleAppointment()
				.selectHlthOrg().scheduleVisit("08/04/2017|9:30 AM").verifyAppointmentDetails();
		
		Utils.uBase.driver.close();
	}

}
