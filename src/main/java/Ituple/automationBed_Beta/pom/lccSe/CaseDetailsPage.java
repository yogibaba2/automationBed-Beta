package Ituple.automationBed_Beta.pom.lccSe;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Utils;

public class CaseDetailsPage {
	/**
	 * identifiers for web elements
	 * */
	
	@FindBy(xpath=".//*[@ng-click='activateLienRes()']")
	WebElement  activateCaseBtn;
	
	@FindBy(xpath=".//div/strong[text()[contains(.,'Case Status')]]/../following-sibling::div")
	public WebElement caseStatus;
	
	@FindBy(xpath=".//div/strong[text()[contains(.,'Primary Inj')]]/../following-sibling::div")
	public WebElement primaryInjury;
	
	@FindBy(xpath=".//div/strong[text()[contains(.,'Firm Case Id')]]/../following-sibling::div")
	public WebElement firmCaseId;
	
	@FindBy(xpath=".//div/h3[text()[contains(.,'ACTION ITEMS')]]/../following-sibling::div/span")
	public WebElement actionItem;
	
	
	/**
	 * Activate Case
	 * @throws 	 
	 * */
	public CaseWizard activateCase() throws Exception{
		Utils.uBase.checkPendingRequests();
		//Utils.uBase.clickWebelement(activateCaseBtn);
		Utils.uBase.doubleClickWebelement(activateCaseBtn);
		Utils.uBase.checkPendingRequests();
		return PageFactory.initElements(Utils.uBase.driver, CaseWizard.class);
	}
	
	public boolean verifyCaseInfo(String fieldName, WebElement ele, String ExpectedValue) throws Exception{
		Utils.uBase.checkPendingRequests();
		if(ele.getText().equalsIgnoreCase(ExpectedValue)){
			AdvanceReporting.addLogs("pass", "Value of "+fieldName+" is as expected");
			AdvanceReporting.addLogs("pass", "Value of "+fieldName+" is as expected",fieldName);
			return true;
		}else{
			AdvanceReporting.addLogs("fail", "Value of "+fieldName+" is not as expected");
			AdvanceReporting.addLogs("fail", "Value of "+fieldName+" is not as expected",fieldName);
			return false;
		}
		
	}
}
