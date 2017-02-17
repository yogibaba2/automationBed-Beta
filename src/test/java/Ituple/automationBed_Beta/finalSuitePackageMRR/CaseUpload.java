package Ituple.automationBed_Beta.finalSuitePackageMRR;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ituple.automationBed_Beta.pom.mrr.CasePage;
import Ituple.automationBed_Beta.pom.mrr.CaseUploadPage;
import Ituple.automationBed_Beta.pom.mrr.LoginPage;
import Ituple.automationBed_Beta.pom.mrr.LogoutModal;
import Ituple.automationBed_Beta.pom.mrr.ProgramPage;
import Ituple.automationBed_Beta.pom.mrr.ProjectDashboardPage;
import Ituple.automationBed_Beta.pom.mrr.ProjectPage;
import Ituple.automationBed_Beta.pom.wap.ProjectDetailsPage;
import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Constant;
import Ituple.automationBed_Beta.utility.base.SuiteBase;
import Ituple.automationBed_Beta.utility.base.Utils;
import Ituple.automationBed_Beta.utility.excelManager.ExcelUtility;
import Ituple.automationBed_Beta.utility.excelManager.Read_XLS;

public class CaseUpload extends SuiteBase {
	
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
	@DataProvider(name = "MRRdataProvider")
	public Object[][] MRRdataProvider() {
		return new ExcelUtility().getData(TestSuite, suiteName);
	}
	
	/************* MRR test ************/
	@Test(dataProvider = "MRRdataProvider")
	public void CaseUploadTest(String testCase, String UserId, String Password, String role, String programName, String client,
			String excelPath) throws Exception {
		String status = "pass";
		Utils.uBase.StartTest(testCase);

		try {

			if (new ExcelUtility().getToRunFlag(TestSuite, suiteName, testCase)) {
				try {
					String methodName=Utils.uBase.lowerCaseFirst(testCase.replaceAll("\\s+",""));
					
					Method method = this.getClass().getMethod(methodName, String.class, String.class, String.class,
							String.class, String.class, String.class);
					method.invoke(this, UserId, Password, role, programName, client, excelPath);
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
	
	public void verifyCaseUploaded(String UserId, String Password, String role, String programName, String client,
			String ExcelPath) throws Exception {

		Utils.uBase.getDriverInstance();

		Utils.uBase.setUrl("https://onesource-development.garretsongroup.com/mrr-ui/grg.html");
		Utils.uBase.openUrl();

		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		login.login(UserId, Password).verifyLogin();

		CaseUploadPage caseupload = PageFactory.initElements(Utils.uBase.driver, CaseUploadPage.class);
		caseupload.openCaseUpload().selectProgram(programName).selectProject(programName, client)
		.uploadTemplate(ExcelPath);

		Utils.uBase.driver.close();
	}

	public void verifyTemplateDownload(String UserId, String Password, String role, String programName, String client,
			String ExcelPath) throws Exception {

		Utils.uBase.getDriverInstance();

		Utils.uBase.setUrl("https://onesource-development.garretsongroup.com/mrr-ui/grg.html");
		Utils.uBase.openUrl();

		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		login.login(UserId, Password).verifyLogin();

		CaseUploadPage caseupload = PageFactory.initElements(Utils.uBase.driver, CaseUploadPage.class);
		caseupload.openCaseUpload().selectProgram(programName).selectProject(programName, client).downloadTemplate();

		Utils.uBase.driver.close();
	}

	public void verifyUploadedCasesDetails(String UserId, String Password, String role, String programName, String client,
			String ExcelPath) throws Exception {
		Utils.uBase.getDriverInstance();
		Utils.uBase.setUrl("https://onesource-development.garretsongroup.com/mrr-ui/grg.html");
		Utils.uBase.openUrl();

		Read_XLS sortedCaseDetails = new Read_XLS(
				System.getProperty("user.dir") + "//src//main//resources//datafiles//MRR_case_upload_template//"+ExcelPath);
		Read_XLS loginDetails = new Read_XLS(System.getProperty("user.dir")
				+ "//src//main//resources//datafiles//MRRJsonResources//LoginDetails.xlsx");

		// login for each reviewer   
		ArrayList<ArrayList<Object>> reviewerLogin = new ExcelUtility().getDataAsArrayList(loginDetails, "Reviewer");

		// uploaded caseDetails
		

		for (ArrayList<Object> reviewer : reviewerLogin) {
			String worksheet=(String) reviewer.get(1);
			if (sortedCaseDetails.isWorksheetExist(worksheet)) {
				try {
					ArrayList<ArrayList<Object>> CaseDetails = new ExcelUtility().getDataAsArrayList(sortedCaseDetails,worksheet );
					ArrayList<Object> columnName = sortedCaseDetails.getRowAt(worksheet, 0);
					LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
					if(login.login((String) reviewer.get(1), (String) reviewer.get(2)).verifyLogin()){
						AdvanceReporting.addLogs("info", "Logged in as "+(String) reviewer.get(0));
						//for dev env
						Utils.uBase.driver.get("https://onesource-development.garretsongroup.com/mrr-ui/grg.html");
						
						ProgramPage program = PageFactory.initElements(Utils.uBase.driver, ProgramPage.class);
						program.chooseProgram(programName);
						
						ProjectPage project= PageFactory.initElements(Utils.uBase.driver, ProjectPage.class);
						project.selectClient(client);
						
						ProjectDashboardPage projectDashboardPage= PageFactory.initElements(Utils.uBase.driver, ProjectDashboardPage.class);
						
						CasePage cPage= PageFactory.initElements(Utils.uBase.driver, CasePage.class);
						cPage.setCaseDetails(programName, client, "");
						
						for (ArrayList<Object> casedetails : CaseDetails) {
							String caseName=(String) casedetails.get(0);
							
							if(projectDashboardPage.verifyCase(caseName)){
								projectDashboardPage.selectCase(caseName);
								cPage.verifyCase(casedetails,columnName);
								Utils.uBase.untilAngularFinishHttpCalls();
								Utils.uBase.navigate_back();
							}else{
								continue;
							}
						}
						LogoutModal logout = PageFactory.initElements(Utils.uBase.driver, LogoutModal.class);
						logout.openLogoutModal().logout((String) reviewer.get(0));
					}else{
						AdvanceReporting.addLogs("fail", "Unable to Login as "+(String) reviewer.get(0));
					}
					
				} catch (Exception e) {
					AdvanceReporting.addLogs("fail", "failed", "failed");
					e.printStackTrace();
				}
				
				
			} else {
				continue;
			}
			
		}

		Utils.uBase.driver.close();
	}
	
	public void verifyUploadedCases(String UserId, String Password, String role, String programName, String client,String ExcelPath) throws Exception{
		
		System.out.println("start method");
		Utils.uBase.getDriverInstance();
		
		Utils.uBase.setUrl("http://wap-development.garretsongroup.com/wap-ui/index.html");
		Utils.uBase.openUrl();

		LoginPage login = PageFactory.initElements(Utils.uBase.driver, LoginPage.class);
		login.login(UserId, Password);
		
		Ituple.automationBed_Beta.pom.wap.ProgramPage programPage= PageFactory.initElements(Utils.uBase.driver, Ituple.automationBed_Beta.pom.wap.ProgramPage.class);
		programPage.chooseProgram(programName).selectProject(client);
		
		ProjectDetailsPage pdp = PageFactory.initElements(Utils.uBase.driver, ProjectDetailsPage.class);
		pdp.openAllTaskModal().verifyBulkCases(programName, client, ExcelPath);
		
		
		
		Utils.uBase.driver.close();
		
	}
	
}
