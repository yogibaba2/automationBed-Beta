package Ituple.automationBed_Beta.pom.nfl.scheduling;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Utils;

public class LoginPage {
	
	@FindBy(xpath="//input[@id='UserName']")
	WebElement username;
	
	@FindBy(xpath="//input[@id='Password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='LoginButton']")
	WebElement loginBtn;
	
	public LoginPage login(String username, String password) throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		
		Utils.uBase.setText(this.username, username);
		AdvanceReporting.addLogs("info", "Email is set to "+username);
		
		Utils.uBase.setPassword(this.password, password);
		AdvanceReporting.addLogs("info", "Password is set to "+Utils.uBase.encryptTxtTo(password, "BoBMarley"));
		
		Utils.uBase.clickWebelement(loginBtn);
		System.out.println("login btn clicked.");
		AdvanceReporting.addLogs("info", "Login credentioal filled ", "Login");
		Utils.uBase.untilAngularFinishHttpCalls();
		return this;
	}
	
	public boolean verifyLogin() throws Exception{
		Utils.uBase.untilAngularFinishHttpCalls();
		if(Utils.uBase.driver.getTitle().contains("Record Review")){
			AdvanceReporting.addLogs("pass", "Login done ", "loginPassed");
			AdvanceReporting.addLogs("pass", "Login is successfull ");
			Utils.addLog.info("Login Successfull");
			return true;
		}else{
			AdvanceReporting.addLogs("fail", "Login done ", "loginFailed");
			AdvanceReporting.addLogs("fail", "Login is unsuccessfull ");
			Utils.addLog.info("Login Unsuccessfull");
			return false;
		}
		
	}
}
