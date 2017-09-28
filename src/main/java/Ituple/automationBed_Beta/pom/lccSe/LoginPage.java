package Ituple.automationBed_Beta.pom.lccSe;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Ituple.automationBed_Beta.utility.ReportManager.AdvanceReporting;
import Ituple.automationBed_Beta.utility.base.Utils;

public class LoginPage {

	@FindBy(xpath="//input[@type='email']")
	WebElement username;
	
	@FindBy(xpath="//input[@type='Password']")
	WebElement password;
	
	//@FindBy(xpath="//button[@ng-click='login()']")
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginBtn;
	
	
	public LoginPage login(String username, String password) throws Exception{
		Utils.uBase.checkPendingRequests();
		Thread.sleep(5000);
		Utils.uBase.setText(this.username, username);
		AdvanceReporting.addLogs("info", "Email is set to "+username);
		
		Utils.uBase.setPassword(this.password, password);
		AdvanceReporting.addLogs("info", "Password is set to "+Utils.uBase.encryptTxtTo(password, "BoBMarley"));
		
		Utils.uBase.clickWebelement(loginBtn);
		AdvanceReporting.addLogs("info", "Login credentioal filled ", "Login");
		Utils.uBase.checkPendingRequests();
		Thread.sleep(2000);
		return this;
	}
	
	public boolean verifyLogin() throws Exception{
		Utils.uBase.checkPendingRequests();
		Thread.sleep(10000);
		if(Utils.uBase.driver.getTitle().contains("Lien Resolution")){
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
