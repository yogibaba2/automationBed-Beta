package Ituple.automationBed_Beta.pom.lccSe;
/*package Ituple.automationBed_Beta.pom.lccSe;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Utils;

public class CaseList {
	
	@FindBy(xpath=".//div[@ng-if='authoriseToAddCase']/button[text()[contains(.,'Add a Client')]]")
	WebElement addClientBtn;
	
	@FindBy(id="fname")
	WebElement fname;
	
	@FindBy(id = "lname")
	WebElement lname;
	
	@FindBy(id = "mname")
	WebElement mname;
	
	@FindBy(id = "maname")
	WebElement maname; 
	
	@FindBy(id = "dob")
	WebElement dob;
	
	@FindBy(id="SSN")
	WebElement ssn;
	
	@FindBy(xpath="//label[@class='radio-inline'][text()[contains(.,'Male')]]")
	WebElement gender;
	
	@FindBy(xpath="//label[@data-ng-init='setDefaultFields(case_Info.EXTERNAL_INFORMATION.HAVE_UNIQUE_CASEPII_IDENTIFIER)'][text()[contains(.,'No')]]")
	WebElement clntUniqIden;
	
	@FindBy(xpath="//label[@data-ng-init='setDefaultFields(case_Info.EXTERNAL_INFORMATION.HAVE_UNIQUE_CASE_IDENTIFIER)'][text()[contains(.,'Yes')]]")
	WebElement caseUniqIden;
	
	@FindBy(xpath="//input[@id='otheruid']")
	WebElement caseUniqIdenTxt;
	
	@FindBy(xpath="//select[@ng-model='case_Info.LAW_SUIT']")
	WebElement lawSuit;
	
	@FindBy(xpath="//button[@id='continueClient'][text()[contains(.,'Continue')]]")
	WebElement continueBtn;
	
	@FindBy(xpath="//span[@ng-if='!config.noMoreItems']")
	WebElement showMore;
	
	@FindBy(xpath="//table[@class='table table-bordered']/tbody")
	WebElement claimantTable;
	
	@FindBy(xpath="//ul[@class='nav metismenu']/li/a[text()[contains(.,'Clients')]]")
	WebElement client;
	
	
	
	public CaseList addClient() throws Exception{
		Utils.uBase.clickWebelement(addClientBtn);
		Utils.uBase.untilAngularFinishHttpCalls();
		return this;
	}
	
	public String addClientdata() throws Exception{
		
		Utils.uBase.untilAngularFinishHttpCalls();
		Thread.sleep(5000);
		Utils.uBase.setText(fname, "Josh");
		Utils.uBase.setText(mname, "J");
		Utils.uBase.setText(lname, "Judd");
		String varSSsn = "2" + Utils.uBase.generateRandomNumber(3) + Utils.uBase.generateRandomNumber(5);
		Utils.uBase.setText(ssn, varSSsn);
		Utils.uBase.clickWebelement(gender);
		Utils.uBase.setText(dob, "08/31/1990");
		Utils.uBase.setText(maname, "Judd");
		Utils.uBase.clickWebelement(clntUniqIden);
		Utils.uBase.selectElementByName(lawSuit, "Jones Act");
		Utils.uBase.clickWebelement(caseUniqIden);
		Utils.uBase.untilAngularFinishHttpCalls();
		Thread.sleep(1000);
		Utils.uBase.setText(caseUniqIdenTxt, "987654321");
		Utils.uBase.untilAngularFinishHttpCalls();
		Thread.sleep(5000);
		try {
			Utils.uBase.clickWebelement(continueBtn);
			System.out.println("Continue button not clicked");
		} catch (Exception e) {
			System.out.println("Continue button not clicked");
		}
		System.out.println("ClaimantC Creation Done");
		Utils.uBase.untilAngularFinishHttpCalls();
		Thread.sleep(5000);
		return varSSsn;
	}
	public void showMore() throws Exception{
		
		while(Utils.uBase.isElementPresent_webelement(showMore)){
			Utils.uBase.clickWebelement(showMore);
			Utils.uBase.untilAngularFinishHttpCalls();
			Thread.sleep(2000);
		}
		System.out.println("ShowMore");
	}
	
	public void getClaimantdetail(String varSSsn){
		
		List<WebElement> claimantTblRows = claimantTable.findElements(By.tagName("tr"));
		List<WebElement> claimantTblCols = claimantTable.findElement(By.tagName("tr")).findElements(By.tagName("td"));
		
		for(int i=1; i<claimantTblRows.size(); i++){
			for(int j=1; j<claimantTblCols.size();j++){
				String claimantVal = Utils.uBase.driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[" + i + "]/td[" + j +"]")).getText();
				System.out.println(claimantVal);
				if(claimantVal.contentEquals(varSSsn)){
					System.out.println("Contains the created SSN Claimant");
				}
			}
		}
	
	}
	
	public void sltMenuClient() throws Exception{
		
		Utils.uBase.clickWebelement(client);
		Utils.uBase.untilAngularFinishHttpCalls();
		Thread.sleep(2000);
	}
	
}

*/