package Ituple.automationBed_Beta.pom.mrr;

import java.awt.datatransfer.StringSelection;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Constant;
import Ituple.automationBed_Beta.utility.base.Utils;

public class CaseUploadPage {
	
	@FindBy(xpath="//select[contains(@ng-model,'program')]")
	WebElement selectProgram;
	
	@FindBy(xpath="//select[contains(@ng-model,'project')]")
	WebElement selectProject;
	
	@FindBy(xpath=".//span[text()='Browse Files']/..")
	WebElement browseFiles;
	
	@FindBy(xpath=".//button[contains(@ng-click,'downloadTemplate()')]")
	WebElement downloadTemplate;
	
	private String programeName;
	private String projectName;
	
	public CaseUploadPage selectProgram(String programeName) throws Exception {
		Utils.uBase.untilAngularFinishHttpCalls();
		try {
			Utils.uBase.selectElementByName(selectProgram, programeName);
			AdvanceReporting.addLogs("pass", programeName +" selected","1");
		} catch (Exception e) {
			AdvanceReporting.addLogs("fail", programeName +" not selected","1");
			e.printStackTrace();
		}
		return this;
		
	}
	
	public CaseUploadPage selectProject(String programeName, String projectName) throws Exception {
		this.programeName=programeName;
		this.projectName=projectName;
		try {
			Utils.uBase.selectElementByName(selectProject, (programeName+" - "+projectName).toUpperCase());
			AdvanceReporting.addLogs("pass", projectName +" selected","2");
		} catch (Exception e) {
			AdvanceReporting.addLogs("fail", projectName +" not selected","2");
			e.printStackTrace();
		}
		return this;
		
	}
	
	public CaseUploadPage downloadTemplate() throws Exception{
		try {
			
			
			Utils.uBase.downloadFileWithRobot(downloadTemplate);
			
			//Utils.uBase.downloadFile("https://onesource-qa.garretsongroup.com/onesource-core/rest/client/mrr/5/case/downloadTemplate.rest", "PRADAXA - MICHAEL BRADY LYNCH_case_upload_template.xlsx");;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return this;
	}

	public CaseUploadPage uploadTemplate(String excelPath){
		try {
			String tempDirPath=System.getProperty("user.dir")+"\\src\\main\\resources\\datafiles\\MRR_case_upload_template\\";
			
			StringSelection templatePath = new StringSelection(tempDirPath+excelPath);
			
			Utils.uBase.uploadFileWithRobot(browseFiles,templatePath);
			
			Thread.sleep(5000);
			
			AdvanceReporting.addLogs("pass", "uploadTemplate passed","3");
			
			//Utils.uBase.downloadFile("https://onesource-qa.garretsongroup.com/onesource-core/rest/client/mrr/5/case/downloadTemplate.rest", "PRADAXA - MICHAEL BRADY LYNCH_case_upload_template.xlsx");;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	} 
	
	public CaseUploadPage openCaseUpload() {
		
		Utils.uBase.driver.get("https://onesource-development.garretsongroup.com/mrr-ui/grg.html#/case-upload/upload");
		return this;
	}
	
}
