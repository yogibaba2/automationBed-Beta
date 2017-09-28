package Ituple.automationBed_Beta.pom.lccSe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Ituple.automationBed_Beta.utility.base.Utils;

public class ProjectDashboard {
	
	
	/**
	 * Object Identifier for Program Dashboard page
	 * @throws Exception 
	 */

	public CaseList selectProject(String projectName) throws Exception {
		Utils.uBase.checkPendingRequests();
		
		WebElement project = Utils.uBase.driver.findElement(
				By.xpath(".//*[text()[contains(.,'"+projectName+"')]]/.."));
		Utils.uBase.clickWebelement(project);
		Utils.uBase.checkPendingRequests();
		return PageFactory.initElements(Utils.uBase.driver, CaseList.class);
	}
}
