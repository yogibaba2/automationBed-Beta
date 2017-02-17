package Ituple.automationBed_Beta.utility.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import org.json.simple.JSONObject;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.gson.stream.JsonReader;

import Ituple.automationBed_Beta.utility.excelManager.ExcelUtility;
import Ituple.automationBed_Beta.utility.excelManager.Read_XLS;
import Ituple.automationBed_Beta.utility.webDriverManager.WdmConfig;

public class UtilityTest {
	public static void main(String[] args) {

		/*JSONReader jr = new JSONReader(
				System.getProperty("user.dir") + "\\src\\main\\resources\\datafiles\\MRRJsonResources\\PRADAXA_MICHAEL_BRADY_LYNCH.json");
		
		Object[] formFields= (jr.getJsonObject("VERTICALS").getJsonObject("MRR").getJsonObject("EXCEL_UPLOAD_TEMPLATE").getArray("FORM_FIELDS"));
		 int i=0;
		while(formFields.length>i){
			System.out.println((String)formFields[i]);
			i++;
		}*/
		
		
	
		
/*		
		System.out.println(WdmConfig.getBoolean("wdm.capabilities.*"));
		Read_XLS loginDetails = new Read_XLS(System.getProperty("user.dir")
				+ "//src//main//resources//datafiles//MRRJsonResources//LoginDetails.xlsx");

		// login for each reviewer
		ArrayList<ArrayList<Object>> reviewerLogin = new ExcelUtility().getDataAsArrayList(loginDetails, "Reviewer");

		
		Read_XLS caseTemplate = new Read_XLS(
			System.getProperty("user.dir") + "//src//main//resources//datafiles//MRR_case_upload_template//100_caseDetails.xlsx");
		
		
		
		// uploaded caseDetails
		
		for (ArrayList<Object> reviewer : reviewerLogin) {
			System.out.println(caseTemplate.isWorksheetExist((String)reviewer.get(1)));
			if (caseTemplate.isWorksheetExist((String)reviewer.get(1))) {
				Object[][] CaseDetails = new ExcelUtility().getData(caseTemplate,(String)reviewer.get(1));
	
				for (Object[] caseDetails : CaseDetails) {
					
					for (Object object : caseDetails) {
						System.out.println((String)object);
					}
					System.out.println("************************************************");
				}
			} else {
				System.out.println((String)reviewer.get(1)+" not found");
			}
			
		}
		
		
		
*/	//	ArrayList<ArrayList<Object>> CaseDetails = new ExcelUtility().getDataAsArrayList(caseTemplate, "Case Sheet");
		
		
	/*	Read_XLS caseTemplate = new Read_XLS(
				System.getProperty("user.dir") + "\\src\\main\\resources\\datafiles\\MRR_case_upload_template\\"+"100.xlsx");
		
		// uploaded caseDetails
		ArrayList<ArrayList<Object>> CaseDetails = new ExcelUtility().getDataAsArrayList(caseTemplate, "Case Sheet");
		
		Read_XLS sortedCaseDetails = new Read_XLS(
				System.getProperty("user.dir") + "\\src\\main\\resources\\datafiles\\MRR_case_upload_template\\100_caseDetails.xlsx","w");
		ArrayList<Object> sortedCaseDetailsColumn=caseTemplate.getRowAt("Case Sheet", 0);
		String assigneeName="sanchit";
		for (ArrayList<Object> casedetails : CaseDetails) {
			
			if(!sortedCaseDetails.isWorksheetExist(assigneeName)){
				sortedCaseDetails.addRowAt(assigneeName, sortedCaseDetailsColumn,0);
			}
				sortedCaseDetails.addRow(assigneeName, casedetails);
				
		}*/
		
		
		/*Read_XLS caseTemplate1 = new Read_XLS(
				System.getProperty("user.dir") + "\\src\\main\\resources\\datafiles\\MRR_case_upload_template\\cearteTest.xlsx","w");
		*/
		
		
		
		
		
		
		/*ArrayList<ArrayList<Object>> CaseDetails = new ExcelUtility().getDataAsArrayList(caseTemplate, "VerifiedCases");
		
		for (ArrayList<Object> casedetails : CaseDetails) {
			
			System.out.println(casedetails.toString());
		}*/
		
		
		/*String tempDirPath=System.getProperty("user.dir")+"//src//main//resources//datafiles//MRR_case_upload_template//";
		tempDirPath=tempDirPath.replace("//", "\\");
		System.out.println(tempDirPath);*/
		
		Read_XLS caseTemplate = new Read_XLS(
				System.getProperty("user.dir") + "\\src\\main\\resources\\datafiles\\MRR_case_upload_template\\"+"PROGRAM1-AUTOMATION_case_1.xlsx");
		
		Read_XLS sortedCaseDetails = new Read_XLS(
				System.getProperty("user.dir") + "\\src\\main\\resources\\datafiles\\MRR_case_upload_template\\"+"PROGRAM1-AUTOMATION_case_1_caseDetails.xlsx","w");
		ArrayList<Object> sortedCaseDetailsColumn=caseTemplate.getRowAt("Case Sheet", 0);
		
	}

}
