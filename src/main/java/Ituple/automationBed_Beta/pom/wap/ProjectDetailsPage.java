package Ituple.automationBed_Beta.pom.wap;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Utils;
import Ituple.automationBed_Beta.utility.excelManager.ExcelUtility;
import Ituple.automationBed_Beta.utility.excelManager.Read_XLS;

public class ProjectDetailsPage {

	
	@FindBy(xpath=".//div[@ng-click='showAllTasks()']")
	WebElement viewAllCaseBtn;
	
	@FindBy(xpath=".//input[contains(@aria-controls,'DataTables_Table') and @type='search']")
	WebElement modalCaseSearch;
	
	@FindBy(xpath=".//input[contains(@aria-controls,'DataTables_Table') and @type='search']")
	WebElement caseSearch;
	
	
	public ProjectDetailsPage openAllTaskModal() throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		Utils.uBase.clickWebelement(viewAllCaseBtn);
		return this;
	}
	
	public ProjectDetailsPage searchTaskModal(String taskName) throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		Utils.uBase.setText(modalCaseSearch, taskName);
		return this;
	}
	
	
	public Boolean verifyCase(String taskName) throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		try {
			WebElement element =Utils.uBase.driver.findElement(By.xpath(".//a[text()[contains(.,'"+taskName.toUpperCase()+"')]]"));
			return Utils.uBase.isElementPresent_webelement(element);
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}
	
	public String getAssignedToName(String taskName){
		String assignedTo=null;
		
		assignedTo=Utils.uBase.driver.findElement(By.xpath(".//a[text()[contains(.,'"+taskName.toUpperCase()+"')]]/../../td[4]/span[1]")).getText();
		
		return assignedTo;
	}
	
	public ProjectDetailsPage verifyBulkCases(String programName, String projectName, String ExcelPath) throws Exception{
		
		Read_XLS caseTemplate = new Read_XLS(
				System.getProperty("user.dir") + "\\src\\main\\resources\\datafiles\\MRR_case_upload_template\\"+ExcelPath);
		
		// uploaded caseDetails
		ArrayList<ArrayList<Object>> CaseDetails = new ExcelUtility().getDataAsArrayList(caseTemplate, "Case Sheet");
		
		Read_XLS sortedCaseDetails = new Read_XLS(
				System.getProperty("user.dir") + "\\src\\main\\resources\\datafiles\\MRR_case_upload_template\\"+(ExcelPath.split("[.]")[0])+"_caseDetails.xlsx","w");
		ArrayList<Object> sortedCaseDetailsColumn=caseTemplate.getRowAt("Case Sheet", 0);
		
		for (ArrayList<Object> casedetails : CaseDetails) {
			String assigneeName="";
			if(searchTaskModal((String)casedetails.get(0)).verifyCase((String)casedetails.get(0))){
			
				assigneeName=getAssignedEmail((String)casedetails.get(0));
				AdvanceReporting.addLogs("pass",(String)casedetails.get(0)+" found and assigned to :"+assigneeName);
				
				if(!sortedCaseDetails.isWorksheetExist(assigneeName)){
					sortedCaseDetails.addRowAt(assigneeName, sortedCaseDetailsColumn,0);
				}
				sortedCaseDetails.addRow(assigneeName, casedetails);
				
				
			}else{
				assigneeName="Not Found";
				AdvanceReporting.addLogs("fail",(String)casedetails.get(0)+" not found");
			}
		}
		
		
		return this;
	}
	
	public String getAssignedEmail(String taskID){
		String assigneeName="";
		
		String script="var tempscope=window.angular.element('.dataTable').scope();"+
		"var tasklist=tempscope.tasklist;"+
		"var index,result;"+
		"for (index = 0; index < tasklist.length; ++index) {"+
			"var caseID=tasklist[index]['TASK_ID'].split('-')[1];"+
			"if(caseID === '"+taskID+"') {"+
				"result = tasklist[index];"+
				 "break;"+
			"}"+
		"}"+
		"return result['ASSIGNED_TO']";
		
		try {
			assigneeName=((JavascriptExecutor)Utils.uBase.driver).executeScript(script).toString();
		} catch (Exception e) {
			e.printStackTrace();
			assigneeName="not found";
		}
		return assigneeName;
	}
	
}

