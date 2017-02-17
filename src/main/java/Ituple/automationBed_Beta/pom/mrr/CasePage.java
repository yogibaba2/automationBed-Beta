package Ituple.automationBed_Beta.pom.mrr;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.AngularCop;
import Ituple.automationBed_Beta.utility.base.Constant;
import Ituple.automationBed_Beta.utility.base.JSONReader;
import Ituple.automationBed_Beta.utility.base.Utils;

public class CasePage {

	@FindBy(xpath = ".//*[@id='page-wrapper']/div[2]/div/div[2]/div[1]/span/strong[text()='Status:']/..")
	WebElement caseSatusPageHeading;

	/* case dashboard controlller */

	@FindBy(xpath = ".//button[text()[contains(.,'Back to dashboard')]]")
	WebElement backToDashBoardBtn;

	@FindBy(xpath = ".//button[text()[contains(.,'Escalate')]]")
	WebElement escalateBtn;

	@FindBy(xpath = ".//button[text()[contains(.,'Injury Done')]]")
	WebElement injuryBtn;

	@FindBy(xpath = ".//button[text()[contains(.,'Discrepancy')]]")
	WebElement discrepancyBtn;

	@FindBy(xpath = ".//button[text()[contains(.,'Case Documents')]]")
	WebElement caseDocumentsBtn;

	@FindBy(xpath = ".//button[text()[contains(.,'More')]]")
	WebElement moreBtn;

	// Escalation modal
	@FindBy(xpath = ".//button[text()[contains(.,'New Escalation')]]")
	private WebElement newEscalationBtn;

	@FindBy(xpath = ".//textarea[contains(@ng-model,'escalation')]")
	private WebElement newEscalationQuestion;

	@FindBy(className = "btn-cancel-escalate")
	private WebElement btnCancelEscalate;
	
	@FindBy(xpath = ".//div[contains(@ng-repeat,'escalations')]")
	private WebElement escalationPanelQA;
	
	@FindBy(xpath = "//button[contains(@data-ng-click,'saveNote()')]")
	private WebElement escalationPanelQAReplyBtn;
	

	// @FindBy(xpath =
	// "//button[contains(@class,'btn-save')][text()[contains(.,'Escalate')]]")
	@FindBy(xpath = "//button[contains(@ng-click,'escalate?')]")
	private WebElement escalateQueSaveBtn;

	@FindBy(xpath = "//button[contains(@ng-click,'close?')]")
	private WebElement escalateQueCloseBtn;

	/* pop up common buttons */
	@FindBy(xpath = "//button[@ng-click='savePressed($event)']")
	private WebElement commonModalSaveBtn;

	@FindBy(xpath = "//button[@ng-click='closePopupNoDialogue()']")
	private WebElement commonModalCloseBtn;

	/* modal final yes / no buttons */
	@FindBy(xpath = "//button[@data-ng-click='cancel()']")
	private WebElement commonModalNo;

	@FindBy(xpath = "//button[@data-ng-click='confirmClicked()']")
	private WebElement commonModalYes;

	// Mark Discrepancy modal

	@FindBy(xpath = ".//ng-form[contains(@name,'discrepancy')]/select[@name='field_name']")
	private WebElement discrepancyFieldSelect;

	@FindBy(xpath = ".//form[@name='forms.discrepancyForm']//tbody/tr/td[3]//input")
	private WebElement discrepancyFieldNewValue;

	@FindBy(xpath = ".//form[@name='forms.discrepancyForm']//tbody/tr/td[4]//input")
	private WebElement discrepancyFieldSource;

	@FindBy(xpath = ".//ul[contains(@class,'ui-select-choices')]/li/div[contains(@id,'ui-select')][1]")
	private WebElement discrepancyFieldSourceList;

	@FindBy(xpath = ".//form[@name='forms.discrepancyForm']//tbody/tr/td[5]//input")
	private WebElement discrepancyFieldDocPageNo;

	@FindBy(xpath = ".//form[@name='forms.discrepancyForm']//tbody/tr/td[6]/button")
	private WebElement discrepancyFieldAddCmnt;

	@FindBy(xpath = "//button[@data-ng-click='saveNote()']")
	private WebElement discrepancyFieldAddCmntSave;

	@FindBy(xpath = ".//button[@data-ng-click='closeNotePopup()']")
	private WebElement discrepancyFieldAddCmntClose;

	@FindBy(xpath = ".//textarea[@placeholder='Enter discrepancy note']")
	private WebElement discrepancyFieldAddCmntArea;

	@FindBy(xpath = ".//form[@name='forms.discrepancyForm']//tbody/tr/td[7]/button")
	private WebElement discrepancyFieldDlt;

	// Header Case Details
	@FindBy(name = "GRG_ID")
	private WebElement GRG_ID;

	@FindBy(name = "FIRM_NAME")
	private WebElement FIRM_NAME;

	@FindBy(name = "CLAIMANT_NAME")
	private WebElement CLAIMANT_NAME;

	@FindBy(name = "DOB")
	private WebElement DOB;

	@FindBy(name = "DOD")
	private WebElement DOD;

	@FindBy(name = "CAUSE_OF_DEATH")
	private WebElement CAUSE_OF_DEATH;

	@FindBy(name = "DEATH_CERTIFICATE_FLAG")
	private WebElement DEATH_CERTIFICATE_FLAG;

	@FindBy(name = "AGE_AT_INJURY")
	private WebElement AGE_AT_INJURY;

	@FindBy(xpath = "//button[contains(@ng-click,'editCaseDetails')]")
	private WebElement caseEditBtn;

	private String programName;
	private String client;
	private String caseName;

	private WebElement caseSave;
	private WebElement caseCancel;
	private WebElement caseSectionEditBtn;

	private WebElement caseSection;
	private String section;

	/**
	 * @param
	 * 
	 * @return this
	 * 
	 * @Description :open passed sub section
	 */
	public CasePage openSection(String section) throws Exception {

		this.section = section;
		Utils.uBase.untilAngularFinishHttpCalls();

		caseSection = Utils.uBase.driver.findElement(By.xpath("//span[contains(text(),'" + section + "')]"));
		Utils.uBase.clickWebelement(caseSection);

		AdvanceReporting.addLogs("info", section + " is opened.");
		AdvanceReporting.addLogs("info", section + " is opened.", "section_opened");

		return this;
	}

	public CasePage editSection() throws Exception {

		Utils.uBase.untilAngularFinishHttpCalls();

		caseSectionEditBtn = Utils.uBase.driver.findElement(
				By.xpath("//span[contains(text(),'" + this.section + "')]/following::i/following::button[1]"));
		Utils.uBase.clickWebelement(caseSectionEditBtn);

		JSONReader caseObject = new JSONReader(Constant.ResourcePath.getConstant() + "MRRJsonResources//"
				+ programName.toUpperCase() + "_" + setNameFormate(client) + "_" + section + ".json");
		IterateOverElements(caseObject.getArray("elements"));

		AdvanceReporting.addLogs("info", section + " is edited.");
		AdvanceReporting.addLogs("info", section + " is edited.", "section_edited");
		return this;
	}

	public CasePage saveSection() throws Exception {

		Utils.uBase.untilAngularFinishHttpCalls();

		caseSectionEditBtn = Utils.uBase.driver.findElement(
				By.xpath("//span[contains(text(),'" + this.section + "')]/following::i/following::button[1]"));
		Utils.uBase.clickWebelement(caseSectionEditBtn);

		AdvanceReporting.addLogs("info", section + " is saved.");
		AdvanceReporting.addLogs("info", section + " is saved.", "section_saved");

		return this;
	}

	/**
	 * Method to modify main Case Details
	 */
	public CasePage editCaseDetails(String programName, String client, String caseName) throws Exception {
		Utils.uBase.untilAngularFinishHttpCalls();

		// dynamically creating xpath for case details save
		//String caseSaveTemp = "_id_" + programName.toUpperCase() + "_" + setNameFormate(client) + "_FORM_11_1";
		//for devlopement env
		String caseSaveTemp = "_id_PRADAXA_MICHAEL_BRADY_LYNCH_FORM_11_1";
		
		caseSave = Utils.uBase.driver.findElement(By.id(caseSaveTemp));

		// dynamically creating xpath for case details cancel
		//String caseCancelTemp = "_id_" + programName.toUpperCase() + "_" + setNameFormate(client) + "_FORM_11_2";
		//for devlopement env
		String caseCancelTemp = "_id_PRADAXA_MICHAEL_BRADY_LYNCH_FORM_11_2";
		caseCancel = Utils.uBase.driver.findElement(By.id(caseCancelTemp));

		setCaseDetails(programName, client, caseName);

		// initializing Json object for case details elements
		JSONReader caseObject = new JSONReader(Constant.ResourcePath.getConstant() + "MRRJsonResources//"
				+ programName.toUpperCase() + "_" + setNameFormate(client) + "_CASE_DETAILS.json");

		Utils.uBase.clickWebelement(caseEditBtn);
		IterateOverElements(caseObject.getJsonObject("formData").getArray("elements"));

		AdvanceReporting.addLogs("info", "case details is edited.");
		AdvanceReporting.addLogs("info", "case details is edited.", "case_edited");

		Utils.uBase.clickWebelement(caseSave);

		AdvanceReporting.addLogs("info", "case details is saved.");
		AdvanceReporting.addLogs("info", "case details is saved.", "case_saved");

		return this;
	}

	public void setCaseDetails(String programName, String client, String caseName) {
		this.programName = programName;
		this.client = client;
		this.caseName = caseName;
	}

	private String setNameFormate(String name) {
		return name.toUpperCase().replace(" ", "_");
	}

	public void verifyCase(ArrayList<Object> casedetails, ArrayList<Object> columnName) {

		try {
			Utils.uBase.untilAngularFinishHttpCalls();

			JSONReader jr = new JSONReader(Constant.ResourcePath.getConstant() + "MRRJsonResources//"
					+ programName.toUpperCase() + "_" + setNameFormate(client) + ".json");

			Object[] formFields = (jr.getJsonObject("VERTICALS").getJsonObject("MRR")
					.getJsonObject("EXCEL_UPLOAD_TEMPLATE").getArray("FORM_FIELDS"));
			int i = 0;
			while (formFields.length > i) {
				if (((String) formFields[i]).equalsIgnoreCase("DEATH_CERTIFICATE_FLAG")) {
					verifyCaseDetail(columnName.get(i).toString(), casedetails.get(i).toString(),
							(String) formFields[i] + ".$viewValue.value");
					i++;
					continue;
				}

				verifyCaseDetail(columnName.get(i).toString(), casedetails.get(i).toString(),
						(String) formFields[i] + ".$viewValue");
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
			AdvanceReporting.addLogs("fail", "unable to verify case details.");
		}

	}

	/**
	 * verify case details of header
	 * 
	 * @param String
	 *            columnName,String expectedvalue, String elementName
	 * 
	 *            returns true if case verified and false when case is not
	 *            present
	 */
	public boolean verifyCaseDetail(String columnName, String expectedvalue, String elementName) {
		AngularCop cop = new AngularCop(Utils.uBase.driver);
		// String scope=programName.toUpperCase() + "_" +
		// setNameFormate(client)+"_FORM";
		// for QA purpose only using below string
		String scope = "PRADAXA_MICHAEL_BRADY_LYNCH_FORM";
		cop = cop.getScopeForElement("." + scope);

		String actualValue = cop.getVariableFromScope(scope + "." + elementName);
		if (expectedvalue.equalsIgnoreCase(actualValue)) {
			AdvanceReporting.addLogs("pass", columnName + " : " + actualValue + " is matched.");
			return true;
		} else {
			AdvanceReporting.addLogs("fail", columnName + " : " + actualValue + " is expected to be :" + expectedvalue);
			return false;
		}
	}

	/**
	 * escalate functionality
	 */
	public CasePage escalate(String role, String data) throws Exception {
		if (role.equalsIgnoreCase("reviewer")) {
			try {
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(escalateBtn);

				AdvanceReporting.addLogs("pass", caseName + " : escalate", "" + caseName + "_escalated_popup");
				AdvanceReporting.addLogs("pass", "escalate popup opened.");

				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(newEscalationBtn);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.setText(newEscalationQuestion, data);

				AdvanceReporting.addLogs("pass", caseName + " : escalate", "" + caseName + "escalated_popup_question");
				AdvanceReporting.addLogs("pass", "escalation question added.");

				Utils.uBase.clickWebelement(escalateQueSaveBtn);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(commonModalYes);
				Utils.uBase.untilAngularFinishHttpCalls();
				/*
				 * Utils.uBase.clickWebelement(escalateQueCloseBtn);
				 * Utils.uBase.untilAngularFinishHttpCalls();
				 * Utils.uBase.clickWebelement(commonModalYes);
				 * Utils.uBase.untilAngularFinishHttpCalls();
				 * Utils.uBase.clickWebelement(commonModalCloseBtn);
				 * Utils.uBase.untilAngularFinishHttpCalls();
				 */

				AdvanceReporting.addLogs("pass", caseName + " : escalate");
				AdvanceReporting.addLogs("pass", caseName + " : escalate", "" + caseName + "_escalated");
			} catch (Exception e) {
				AdvanceReporting.addLogs("fail", caseName + " : failed to escalate");
				AdvanceReporting.addLogs("pass", caseName + " : failed to escalate", "" + caseName + "_not_escalated");
				e.printStackTrace();
				return null;
			}
		}else if(role.equalsIgnoreCase("QA")){
			String[] QaData=data.split("\\|");
			try {
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(escalateBtn);

				AdvanceReporting.addLogs("pass", caseName + " : escalate", "" + caseName + "_escalated_popup");
				AdvanceReporting.addLogs("pass", "escalate popup opened.");

				Utils.uBase.untilAngularFinishHttpCalls();
				
				
				WebElement que =escalationPanelQA.findElement(By.xpath("//span[text()='"+QaData[0]+"']/preceding-sibling::i"));
				if(isescalationQueOpen(que)){
				}else{
					que.click();
				}
				que.findElement(By.xpath("../../../../following-sibling::div//textarea")).sendKeys(QaData[1]);
				Utils.uBase.clickWebelement(escalationPanelQAReplyBtn);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(commonModalCloseBtn);
				Utils.uBase.untilAngularFinishHttpCalls();
				Thread.sleep(5000);
				/*
				 * Utils.uBase.clickWebelement(escalateQueCloseBtn);
				 * Utils.uBase.untilAngularFinishHttpCalls();
				 * Utils.uBase.clickWebelement(commonModalYes);
				 * Utils.uBase.untilAngularFinishHttpCalls();
				 * Utils.uBase.clickWebelement(commonModalCloseBtn);
				 * Utils.uBase.untilAngularFinishHttpCalls();
				 */

				AdvanceReporting.addLogs("pass", caseName + " : escalate");
				AdvanceReporting.addLogs("pass", caseName + " : escalate", "" + caseName + "_escalated");
			} catch (Exception e) {
				AdvanceReporting.addLogs("fail", caseName + " : failed to escalate");
				AdvanceReporting.addLogs("pass", caseName + " : failed to escalate", "" + caseName + "_not_escalated");
				e.printStackTrace();
				return null;
			}
		}

		return this;
		
		
	}

	public boolean isescalationQueOpen(WebElement question){
		if((question.getAttribute("class")).toString().contains("glyphicon-plus-sign")) return true;
		else return false;
	}
	/**
	 * Mark as discrepancy functionality
	 */
	public CasePage markAsDiscrepancy(String role, String data) throws Exception {
		if(role.equalsIgnoreCase("reviewer")){
			try {
				String[] args = data.split("\\|");

				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(discrepancyBtn);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.selectElementByName(discrepancyFieldSelect, args[0]);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.setText(discrepancyFieldNewValue, args[1]);
				Utils.uBase.setText(discrepancyFieldSource, args[2]);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(discrepancyFieldSourceList);
				Utils.uBase.setText(discrepancyFieldDocPageNo, args[3]);
				Utils.uBase.clickWebelement(discrepancyFieldAddCmnt);
				Utils.uBase.setText(discrepancyFieldAddCmntArea, args[4]);
				Utils.uBase.clickWebelement(discrepancyFieldAddCmntSave);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(discrepancyFieldAddCmntClose);
				Utils.uBase.untilAngularFinishHttpCalls();
				AdvanceReporting.addLogs("pass", caseName + " : data filled for discrepancy");
				AdvanceReporting.addLogs("pass", caseName + " : data filled for discrepancy",
						"" + caseName + "_data_filled_for_discrepancy");
				Utils.uBase.clickWebelement(commonModalSaveBtn);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(commonModalSaveBtn);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(commonModalNo);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(commonModalCloseBtn);
				AdvanceReporting.addLogs("pass", caseName + " : marked as discrepancy");
				AdvanceReporting.addLogs("pass", caseName + " : marked as discrepancy",
						"" + caseName + "_marked_as_discrepancy");
			} catch (Exception e) {
				AdvanceReporting.addLogs("pass", caseName + " : failed to marked as discrepancy",
						"" + caseName + "failed_to_marked_as_discrepancy");
				AdvanceReporting.addLogs("fail", caseName + " : failed to marked as discrepancy");

				e.printStackTrace();
				return null;
			}
		}else if(role.equalsIgnoreCase("QA")){
			try {
				String[] args = data.split("\\|");

				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(discrepancyBtn);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.selectElementByName(discrepancyFieldSelect, args[0]);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.setText(discrepancyFieldNewValue, args[1]);
				Utils.uBase.setText(discrepancyFieldSource, args[2]);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(discrepancyFieldSourceList);
				Utils.uBase.setText(discrepancyFieldDocPageNo, args[3]);
				Utils.uBase.clickWebelement(discrepancyFieldAddCmnt);
				Utils.uBase.setText(discrepancyFieldAddCmntArea, args[4]);
				Utils.uBase.clickWebelement(discrepancyFieldAddCmntSave);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(discrepancyFieldAddCmntClose);
				Utils.uBase.untilAngularFinishHttpCalls();
				AdvanceReporting.addLogs("pass", caseName + " : data filled for discrepancy");
				AdvanceReporting.addLogs("pass", caseName + " : data filled for discrepancy",
						"" + caseName + "_data_filled_for_discrepancy");
				Utils.uBase.clickWebelement(commonModalSaveBtn);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(commonModalSaveBtn);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(commonModalNo);
				Utils.uBase.untilAngularFinishHttpCalls();
				Utils.uBase.clickWebelement(commonModalCloseBtn);
				AdvanceReporting.addLogs("pass", caseName + " : marked as discrepancy");
				AdvanceReporting.addLogs("pass", caseName + " : marked as discrepancy",
						"" + caseName + "_marked_as_discrepancy");
			} catch (Exception e) {
				AdvanceReporting.addLogs("pass", caseName + " : failed to marked as discrepancy",
						"" + caseName + "failed_to_marked_as_discrepancy");
				AdvanceReporting.addLogs("fail", caseName + " : failed to marked as discrepancy");

				e.printStackTrace();
				return null;
			}
		}
		
		

		return this;
	}

	public void injuryDone(String data) throws Exception {

		Utils.uBase.untilAngularFinishHttpCalls();
		Utils.uBase.clickWebelement(injuryBtn);
		Utils.uBase.untilAngularFinishHttpCalls();
		Utils.uBase.clickWebelement(commonModalSaveBtn);
		Utils.uBase.untilAngularFinishHttpCalls();
		AdvanceReporting.addLogs("pass", caseName + " : Injury Done");
		AdvanceReporting.addLogs("pass", caseName + " : Injury Done", "" + caseName + "_Injury_Done");
		Utils.uBase.clickWebelement(commonModalNo);
		Utils.uBase.untilAngularFinishHttpCalls();
		Utils.uBase.clickWebelement(commonModalCloseBtn);
		Utils.uBase.untilAngularFinishHttpCalls();

	}

	/**
	 * 
	 * JSON Ops
	 * 
	 * @param Object[]
	 * @throws Exception
	 * 
	 * 
	 */
	/**
	 * Iterate over elements of JSON Array
	 */
	public void IterateOverElements(Object[] elements) throws Exception {

		for (int i = 0; i < elements.length; i++) {
			JSONObject element = (JSONObject) elements[i];

			String elementValue = (String) element.get("value");
			Utils.addLog.info(elementValue);
			try {
				Utils.uBase.KeywordOperationInvoker((String) element.get("type"), "name", (String) element.get("name"),
						elementValue);
				try {
					if ((boolean) element.get("subtabFlag")) {
						IterateOverElements(new JSONReader(element).getArray("subTabs"));
					}
				} catch (NullPointerException e) {
					Utils.addLog.catching(e);
					Utils.addLog.info("No subtabFlag for :" + elementValue);
				}
			} catch (Exception e) {
				Utils.addLog.catching(e);
			}

		}
	}

}
