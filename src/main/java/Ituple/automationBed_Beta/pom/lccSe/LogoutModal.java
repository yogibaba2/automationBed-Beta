package Ituple.automationBed_Beta.pom.lccSe;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Constant;
import Ituple.automationBed_Beta.utility.base.Utils;

public class LogoutModal {

	/*logout modal OR*/
	@FindBy(xpath=".//a[contains(@ng-click,'logout')]")
	WebElement logoutLink;
	
	@FindBy(xpath="//button[text()='Logout']")
	WebElement logoutBtn;
	
	
	@FindBy(xpath=".//img[contains(@src,'avatar')][1]")
	WebElement avatar;

	@FindBy(xpath=".//strong[contains(@ng-bind,'profileData')]")
	WebElement userName;
	
	@FindBy(xpath=".//span[contains(@ng-bind,'USER_ROLES')]")
	WebElement role;
	
	
	public void logout(String userid) throws Exception{
		try {
			Utils.uBase.untilAngularFinishHttpCalls();
			Utils.uBase.clickWebelement(logoutLink);
			Utils.uBase.untilAngularFinishHttpCalls();
			Utils.uBase.clickWebelement(logoutBtn);
			AdvanceReporting.addLogs("pass", userid+" is logged out.");
		} catch (Exception e) {
			AdvanceReporting.addLogs("fail", userid+" is failed to log out.");
			e.printStackTrace();
		}
	}
	
	public LogoutModal openLogoutModal() throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		Utils.uBase.clickWebelement(avatar);
		return this;
	}
	
	public LogoutModal getUserDetail() throws Exception{
		String[] userDetails=new String[2];
		Utils.uBase.untilAngularFinishHttpCalls();
		userDetails[0]=userName.getText();
		userDetails[1]=role.getText();
		AdvanceReporting.addLogs("info", userDetails[0]+" Logged in as "+userDetails[1]);
		AdvanceReporting.addLogs("info", userDetails[0]+" Logged in as "+userDetails[1],"userRoles");
		return this;
	}
}
