package Ituple.automationBed_Beta.pom.lccSe;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Utils;

public class ProgramDashboard {

	/**
	 * Object Identifier for Program Dashboard page
	 * @throws Exception 
	 */
	@FindBy(xpath="//div[@ng-if[contains(.,'SEProgramsList')]]//a[@ng-click='usermanagement()']")
	WebElement btn_usermanagement;
	
	public ProjectDashboard selectProgram(String programName) throws Exception {
		WebElement programe = Utils.uBase.driver.findElement(
				By.xpath(".//*[text()[contains(.,'"+programName+"')]]/../a"));
		//Utils.uBase.clickWebelement(programe);
		Utils.uBase.clickWebelement(programe);
		Utils.uBase.checkPendingRequests();
		return PageFactory.initElements(Utils.uBase.driver, ProjectDashboard.class);
	}
	
	public UserManagementPage openUsermanagement() throws Exception{			
		Thread.sleep(5000);	
		if (Utils.uBase.isElementPresent_webelement(btn_usermanagement)){
			AdvanceReporting.addLogs("info", "UserManagement button exists");
			AdvanceReporting.addLogs("info", "UserManagement button exists", "UserManagement button");
			Utils.uBase.clickWebelement(btn_usermanagement);
		}
		else {
			AdvanceReporting.addLogs("fail", "UserManagement button does not exists");
			AdvanceReporting.addLogs("info", "UserManagement button does not exists", "UserManagement button");
			Utils.uBase.clickWebelement(btn_usermanagement);
		}
		
		return PageFactory.initElements(Utils.uBase.driver, UserManagementPage.class);
		
	}
}
