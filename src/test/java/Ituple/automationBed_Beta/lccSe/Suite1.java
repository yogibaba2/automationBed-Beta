package Ituple.automationBed_Beta.lccSe;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ituple.automationBed_Beta.pom.lccSe.CaseDetailsPage;
import Ituple.automationBed_Beta.pom.lccSe.CaseList;
import Ituple.automationBed_Beta.pom.lccSe.CaseWizard;
import Ituple.automationBed_Beta.pom.lccSe.LoginPage;
import Ituple.automationBed_Beta.pom.lccSe.ProgramDashboard;
import Ituple.automationBed_Beta.pom.lccSe.ProjectDashboard;
import Ituple.automationBed_Beta.pom.lccSe.UserManagementPage;
import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.SuiteBase;
import Ituple.automationBed_Beta.utility.base.Utils;
import Ituple.automationBed_Beta.utility.excelManager.ExcelUtility;
import Ituple.automationBed_Beta.utility.ngwebdriver.NgWebDriver;

public class Suite1 extends SuiteBase {

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
		// Utils.uBase.driver.quit();

	}

	// Data Provider
	@DataProvider(name = "LccSeDataProvider")
	public Object[][] LccSeDataProvider() {
		return new ExcelUtility().getData(TestSuite, suiteName);

	}

	/************* LCC test ************/
	@Test(dataProvider = "LccSeDataProvider")
	public void LoginTest(String testCase, String UserId, String Password, String role, String programName,
			String projectName, String data) throws Exception {
		String status = "pass";
		Utils.uBase.StartTest(testCase);

		try {

			if (new ExcelUtility().getToRunFlag(TestSuite, suiteName, testCase)) {
				try {
					String methodName = Utils.uBase.lowerCaseFirst(testCase.replaceAll("\\s+", ""));

					Method method = this.getClass().getMethod(methodName, String.class, String.class, String.class,
							String.class, String.class, String.class);
					method.invoke(this, UserId, Password, role, programName, projectName, data);
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

	public void verifyLogin(String UserId, String Password, String role, String programName, String projectName,
			String data) throws Exception {

		Utils.uBase.getDriverInstance();
		// Utils.uBase.setUrl("https://lcc-qa.garretsongroup.com/lcc-ui/law.html");
		// Utils.uBase.openUrl();
		Utils.uBase.setUrl("https://lcc-qa.garretsongroup.com/lcc-ui/law.html");
		Utils.uBase.openUrl();

		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		if (login.login(UserId, Password).verifyLogin()) {
			try {
				ProgramDashboard programDashboard = PageFactory.initElements(Utils.uBase.driver, ProgramDashboard.class);
				Utils.uBase.checkPendingRequests();
				ProjectDashboard projectDashboard = programDashboard.selectProgram(programName);
				CaseList caseList = projectDashboard.selectProject(projectName);
				Utils.uBase.checkPendingRequests();
				String str[] = data.split(Pattern.quote("|"));
				CaseDetailsPage caseDetailsPage = caseList.openAddAClientPopUp().AddAClient(str[0], str[1], str[2], str[3],
						str[4], str[5], str[6], str[7], str[8], str[9], str[10]);
				Utils.uBase.checkPendingRequests();
				Thread.sleep(3000);
				caseDetailsPage.verifyCaseInfo("Case Status", caseDetailsPage.caseStatus, "Action Needed");
				caseDetailsPage.verifyCaseInfo("Action Item", caseDetailsPage.actionItem, "Select and activate case service(s).");
				CaseWizard caseWizard = caseDetailsPage.activateCase();
				Utils.uBase.checkPendingRequests();
				Thread.sleep(3000);
				/*
				 * WebElement activateCaseBtn =
				 * Utils.uBase.driver.findElement(By.xpath(
				 * ".//*[@ng-click='activateLienRes()']"));
				 * Utils.uBase.clickWebelement(activateCaseBtn);
				 */
				caseDetailsPage = caseWizard.fillOption_1().saveAndContinue().fillOption_2().saveAndContinue()
						.fillClientDetails_1("abcd", "abcd", "INDIA", "ALASKA", "Gurgaon", "123456").saveAndContinue()
						.fillClientDetails_2().saveAndContinue().fillClientDetails_3().saveAndContinue()
						.fillClientDetails_4().saveAndContinue().fillInjury_1().saveAndContinue()
						.fillInjury_3("Pedestrian Injured In Transport Accident", "Pedestrian conveyance accident",
								"Certain infectious and parasitic diseases", "Intestinal Infectious Diseases", "Cholera",
								"09/15/2017")
						.saveAndContinue().fillSettlement("09/30/2017").saveAndContinue().fillDocument(str[11])
						.payment().exitCaseWizard();
				caseDetailsPage.verifyCaseInfo("Case Status", caseDetailsPage.caseStatus, "Pending Review");
				caseDetailsPage.verifyCaseInfo("Action Item", caseDetailsPage.actionItem, "Pending case review");
			} catch (Exception e) {
				AdvanceReporting.addLogs("error", "error: "+e.toString());
				AdvanceReporting.addLogs("error", "error: "+e.toString(),"errorSnap");
				throw e;
			}
		}
		Utils.uBase.driver.close();

	}

	public void verifyUserManagement(String UserId, String Password, String role, String programName,
			String projectName, String data) throws Exception {

		Utils.uBase.getDriverInstance();
		Utils.uBase.setUrl("https://lcc-qa.garretsongroup.com/lcc-ui/law.html");
		Utils.uBase.openUrl();
		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		if (login.login(UserId, Password).verifyLogin()) {
			ProgramDashboard programDashboard = PageFactory.initElements(Utils.uBase.driver, ProgramDashboard.class);
			Utils.uBase.checkPendingRequests();
			UserManagementPage usermanagement = programDashboard.openUsermanagement();
			Utils.uBase.checkPendingRequests();
			usermanagement.openadduser();
			Utils.uBase.checkPendingRequests();
			String userEmailId = usermanagement.AddUser(role, data);
			System.out.println("Add User");
			Utils.uBase.checkPendingRequests();
			usermanagement.verifyAddedUser(userEmailId);
			System.out.println("verifyAddedUser");
			Utils.uBase.checkPendingRequests();
			usermanagement.editUser(userEmailId);
			System.out.println("editUser");
			Utils.uBase.checkPendingRequests();
			usermanagement.VerifyeditUser(userEmailId);
			System.out.println("VerifyeditUser");
			Utils.uBase.checkPendingRequests();
			usermanagement.deActivateUser(userEmailId);
			System.out.println("deActivateUser");
			Utils.uBase.checkPendingRequests();

		}
		Utils.uBase.driver.close();
	}
	

}
