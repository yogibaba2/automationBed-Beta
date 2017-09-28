package Ituple.automationBed_Beta.pom.lccSe;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Utils;

public class CaseList {

	@FindBy(xpath = ".//button[text()[contains(.,'Add a Client')]]")
	WebElement addClientBtn;

	@FindBy(id = "fname")
	WebElement addClientFirstName;

	@FindBy(id = "lname")
	WebElement addClientLastName;

	@FindBy(id = "SSN")
	WebElement addClientSSN;
	
	@FindBy(xpath ="//div[text()[contains(.,'invalid format.')]]")
	WebElement addClientInvalidSSN;
	
	@FindBy(xpath = ".//input[@ng-model='claimant_Info.GENDER' and @value='M']")
	WebElement addClientGenderM;

	@FindBy(xpath = ".//input[@ng-model='claimant_Info.GENDER' and @value='F']")
	WebElement addClientGenderF;

	@FindBy(id = "dob")
	WebElement addClientDob;

	@FindBy(xpath = "//input[@name='HAVE_UNIQUE_CASEPII_IDENTIFIER' and @value='true']")
	WebElement addClientFirmIdRadioY;

	@FindBy(xpath = "//input[@name='HAVE_UNIQUE_CASEPII_IDENTIFIER' and @value='false']")
	WebElement addClientFirmIdRadioN;
	
	@FindBy(id = "uid")
	WebElement addClientFirmId;
	
	@FindBy(xpath = "//input[@name='HAVE_UNIQUE_CASE_IDENTIFIER' and @value='true']")
	WebElement addClientCaseIdRadioY;

	@FindBy(xpath = "//input[@name='HAVE_UNIQUE_CASE_IDENTIFIER' and @value='false']")
	WebElement addClientCaseIdRadioN;
	
	@FindBy(id = "otheruid")
	WebElement addClientCaseId;
	
	@FindBy(xpath = ".//select[@ng-model='case_Info.LAW_SUIT']")
	WebElement addClientLawSuitTypeSelect;
	
	@FindBy(xpath="//button[@id='continueClient'][text()[contains(.,'Continue')]]")
	WebElement addClientContinueBtn;
	
	@FindBy(xpath = ".//button[@ng-click='closePopup()']")
	WebElement addClientCancelBtn;
	
	@FindBy(xpath = ".//input[@ng-model='case_Info.addAnotherClient']")
	WebElement addClientAddAnotherCheckbox;
	
	@FindBy(id = "yes_add_case_to_exiting_client")
	WebElement addClientToExisting_Y;
	
	@FindBy(id = "no")
	WebElement addClientToExisting_N;
	
	@FindBy(xpath = ".//*[@ng-if='clientAlreadyExsistSectionView']")
	WebElement clientAlreadyExsistSection;
	
	/**
	 * method that cliick on Add a Client button and open modal
	 */
	public CaseList openAddAClientPopUp() throws Exception {
		try {
			Utils.uBase.untilAngularFinishHttpCalls();
			Utils.uBase.clickWebelement(addClientBtn);
			Utils.uBase.checkPendingRequests();
			AdvanceReporting.addLogs("pass", "Add CLient pop up opened");
			AdvanceReporting.addLogs("pass", "AddClientPop", "AddClientModal");
		} catch (Exception e) {
			AdvanceReporting.addLogs("fail", "Unable to open Add CLient pop");
			AdvanceReporting.addLogs("fail", "AddClientPop", "AddClientModal");
			throw e;
		}
		
		return this;
	}

	/**
	 * method to fill and submit add a client modal
	 * @throws Exception 
	 */
	public CaseDetailsPage AddAClient(String firstName, String lastName, String SSN, String gender, String dob,
			String firmIdRadio, String firmId,String lawSuitType, String caseIdRadio, String caseId, String addAnother) throws Exception {
		Utils.uBase.setText(addClientFirstName, firstName);
		Utils.uBase.setText(addClientLastName, lastName);
		
		
		
		String varSsn = "2" + Utils.uBase.generateRandomNumber(3) + Utils.uBase.generateRandomNumber(5);
		Thread.sleep(1000);
		Utils.uBase.setText(addClientSSN, varSsn);
		
		//addClientSSN.sendKeys("37");addClientSSN.sendKeys("5674");			
		if(gender.equalsIgnoreCase("M")){
			Utils.uBase.clickWebelement(addClientGenderM);
		}else if(gender.equalsIgnoreCase("F")){
			Utils.uBase.clickWebelement(addClientGenderF);
		}
		while(Utils.uBase.isElementPresent_webelement(addClientInvalidSSN)){
			Utils.uBase.clearTextField(addClientSSN);
			Thread.sleep(1000);
			 varSsn = "2" + Utils.uBase.generateRandomNumber(3) + Utils.uBase.generateRandomNumber(5);
			Utils.uBase.setText(addClientSSN, varSsn);	
		}
		Utils.uBase.setText(addClientDob, dob);
		if(firmIdRadio.equalsIgnoreCase("Yes")){
			Utils.uBase.clickWebelement(addClientFirmIdRadioY);
			Utils.uBase.setText(addClientFirmId, firmId);
		}else if(firmIdRadio.equalsIgnoreCase("No")){
			Utils.uBase.clickWebelement(addClientFirmIdRadioN);
		}
		
		Utils.uBase.selectElementByName(addClientLawSuitTypeSelect, lawSuitType);
		
		if(caseIdRadio.equalsIgnoreCase("Yes")){
			Utils.uBase.clickWebelement(addClientCaseIdRadioY);
			Utils.uBase.setText(addClientCaseId, firmId);
		}else if(caseIdRadio.equalsIgnoreCase("No")){
			Utils.uBase.clickWebelement(addClientCaseIdRadioN);
		}
		
		if(addAnother.equalsIgnoreCase("Yes")){
			Utils.uBase.clickWebelement(addClientAddAnotherCheckbox);
		}
		
		Thread.sleep(1000);
		if(addClientContinueBtn.isEnabled()){
			AdvanceReporting.addLogs("pass", "Add Client button gets enabled after adding all mandatory fields.");
			AdvanceReporting.addLogs("pass", "AddClient button ", "buttonEnabled");
			addClientContinueBtn.click();
			System.out.println("clicked");
		}else{
			throw new Exception("All mandatory fields are not provided.");
		}
		Utils.uBase.checkPendingRequests();
		if(Utils.uBase.isElementPresent_webelement(clientAlreadyExsistSection)){
			addClientToExisting_Y.click();
		}else{
			//TODO
		}
		Thread.sleep(1000);
		Utils.uBase.checkPendingRequests();
		return PageFactory.initElements(Utils.uBase.driver, CaseDetailsPage.class);
	}

}
