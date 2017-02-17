package Ituple.automationBed_Beta.pom.mrr;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Constant;
import Ituple.automationBed_Beta.utility.base.Utils;

public class ProjectDashboardPage {

	WebElement Case;
	WebElement caseType;
	WebElement caseTypeCount;
	/*search functionality*/
	@FindBy(xpath="//input[@ng-model='searchText']")
	WebElement searchBox;
	
	@FindBy(xpath="//button[@ng-click='navigateToSearch()']")
	WebElement startSearchBox;
	
	
	/*pagination navigation OR*/
	@FindBy(xpath=".//a[contains(@ng-click,'selectPage')][text()='First']")
	WebElement paginationNavFirst;
	
	@FindBy(xpath=".//a[contains(@ng-click,'selectPage')][text()='Last']")
	WebElement paginationNavLast;
	
	@FindBy(xpath=".//a[contains(@ng-click,'selectPage')][text()='>']")
	WebElement paginationNavincr;
	
	@FindBy(xpath=".//a[contains(@ng-click,'selectPage')][text()='<']")
	WebElement paginationNavdecr;
	
	@FindBy(xpath=".//input[contains(@ng-change,'selectPage')]")
	WebElement paginationNavinput;
	
	
	/**
	 * select casetype
	 * */
	public ProjectDashboardPage selectCaseType(String caseType) throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();

		//defining dynamic xpath for clientSelect
		this.caseType=Utils.uBase.driver.findElement(By.xpath(".//div[@class='case-title-list']//span[text()[contains(.,'"+caseType+"')]]"));
		
		AdvanceReporting.addLogs("pass","selecting "+caseType, caseType);
		Utils.uBase.clickWebelement(this.caseType);
		return this;
	}

	/**
	 * select specific case
	 * */
	public ProjectDashboardPage selectCase(String Case) throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();

		//defining dynamic xpath for clientSelect
		try {
			this.Case=Utils.uBase.driver.findElement(By.xpath("//a[text()[contains(.,'"+Case+"')]]"));
			
			AdvanceReporting.addLogs("pass", "selecting case : "+Case, Case);
			Utils.uBase.clickWebelement(this.Case);
		} catch (NoSuchElementException e) {
			AdvanceReporting.addLogs("fail", "case not found : "+Case, Case);
			e.printStackTrace();
		}
		
		return this;
	}
	
	
	public boolean verifyCase(String casename) throws Exception {
		try {
			Utils.uBase.untilAngularFinishHttpCalls();
			this.searchCase(casename);
			Utils.uBase.untilAngularFinishHttpCalls();
			this.Case=Utils.uBase.driver.findElement(By.xpath("//a[text()[contains(.,'"+casename+"')]]"));
			if(Utils.uBase.isElementPresent_webelement(Case)){
				AdvanceReporting.addLogs("pass", casename+" case is present",casename+"_case_uploaded");
				return true;
			}else{
				AdvanceReporting.addLogs("fail", casename+" case not present to this reviewer","case_not_uploaded");
				return false;
			}
			
		} catch (Exception e) {
			AdvanceReporting.addLogs("fail", casename+" case not present to this reviewer","case_not_uploaded");
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public ProjectDashboardPage searchCase(String caseName) throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		Utils.uBase.setText(searchBox, caseName);
		AdvanceReporting.addLogs("info", caseName+" is set to search box.");
		Utils.uBase.clickWebelement(startSearchBox);
		return this;
	}
	
	public int getCountOnCaseHeader(String caseType) throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		int headerCaseCount;
		WebElement element = Utils.uBase.driver.findElement(By.xpath(".//div[@class='case-title-list']//span[text()[contains(.,'"+caseType+"')]]/../h2"));
		headerCaseCount =Integer.parseInt(element.getText());
		AdvanceReporting.addLogs("info", "Case count for :"+caseType+", on header is :"+headerCaseCount);
//		AdvanceReporting.addLogs("info", "Case count for :"+caseType+", on header is :"+headerCaseCount,"Header_Case_Count ");
		return headerCaseCount;
	}
	
	public int getTableCaseCount(String caseType) throws Exception {
		
		int count;
		
		Utils.uBase.untilAngularFinishHttpCalls();
		
		if(Utils.uBase.isElementPresent_webelement(paginationNavLast))
			paginationLast();
		
		Utils.uBase.untilAngularFinishHttpCalls();
		Utils.uBase.untilAngularFinishHttpCalls();
		List<WebElement> element=Utils.uBase.driver.findElements(By.xpath("//table[@st-table='listRecords']/tbody/tr/td[1]"));
		try {
			count=Integer.parseInt(element.get(element.size()-1).getText());
		} catch (Exception e) {
			count=0;
			e.printStackTrace();
		}
		
		AdvanceReporting.addLogs("info", "Case count for :"+caseType+", in table is :"+count);
		
		return count;
	}
	
	/*methods for page navigation */
	
	public ProjectDashboardPage paginationLast() throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		Utils.uBase.clickWebelement(paginationNavLast);
		return this;
	}
	public ProjectDashboardPage paginationFirst() throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		Utils.uBase.clickWebelement(paginationNavFirst);
		return this;
	}
	public ProjectDashboardPage paginationNext() throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		Utils.uBase.clickWebelement(paginationNavincr);
		return this;
	}
	public ProjectDashboardPage paginationPrev() throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		Utils.uBase.clickWebelement(paginationNavdecr);
		return this;
	}
	public ProjectDashboardPage paginationGoToPage(String pageNumber) throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		Utils.uBase.setText(paginationNavinput, pageNumber);
		return this;
	}
	
	
	

}
