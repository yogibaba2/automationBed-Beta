package Ituple.automationBed_Beta.pom.mrr;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Constant;
import Ituple.automationBed_Beta.utility.base.Utils;

public class ProjectPage {

	WebElement client;
	
	/**
	 * choose lawfirm
	 * */
	public ProjectPage selectClient(String client) throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();

		//defining dynamic xpath for clientSelect
		AdvanceReporting.addLogs("info", "selecting Law firm : "+client, client);
		try {
			this.client=Utils.uBase.driver.findElement(By.xpath("//h2[contains(@uib-tooltip,'"+client+"')]"));
			Utils.uBase.clickWebelement(this.client);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			this.client=Utils.uBase.driver.findElement(By.xpath("//h2[contains(@uib-tooltip,'"+client.toUpperCase()+"')]"));
			Utils.uBase.clickWebelement(this.client);
		}
		
		AdvanceReporting.addLogs("pass", client+" is selected ");
		return this;
	}
	
	public int getProjectCompletedCaseCount(String project) throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		WebElement element;
		try {
			 element=Utils.uBase.driver.findElement(By.xpath("//h2[@uib-tooltip='"+project+"']/../span/p"));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			element=Utils.uBase.driver.findElement(By.xpath("//h2[@uib-tooltip='"+project.toUpperCase()+"']/../span/p"));
		}
		
		int completedCaseCount= Integer.parseInt((element.getText().split(" "))[0]);
		AdvanceReporting.addLogs("info", "Completed case count for "+project+" is :"+completedCaseCount);
		return completedCaseCount;
	}
	
	public int getAllProjectCompletedCaseCount() throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		int completedCaseCount=0;
		List<WebElement> element=Utils.uBase.driver.findElements(By.xpath("//div[@id='programs']//span/p"));
		
		for (WebElement webElement : element) {
			completedCaseCount+=Integer.parseInt((webElement.getText().split(" "))[0]);
			
		}
		AdvanceReporting.addLogs("info", "Completed case count for all clients is :"+completedCaseCount);
		return completedCaseCount;
	}

	
	/**
	 * @param Integer
	 * @return string
	 * 
	 * this method will reurn recentely viewed case from project page
	 * @throws Exception 
	 * */
	public String getRecentelyViewedCase(int i) throws Exception {
		Utils.uBase.untilAngularFinishHttpCalls();
		List<WebElement> recentlyViewedCases=Utils.uBase.driver.findElements(By.xpath(".//a[contains(@data-ng-click,'navigateToCasePage(case)')]/span"));
		String recentlyViewedCase=recentlyViewedCases.get(i-1).getText();
		
		AdvanceReporting.addLogs("info", i+" Recently viewed case is :"+recentlyViewedCase);
		return recentlyViewedCase.split(" ")[0];
	}
}
