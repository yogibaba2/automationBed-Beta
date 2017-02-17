package Ituple.automationBed_Beta.utility.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import Ituple.automationBed_Beta.utility.excelManager.Read_XLS;
import Ituple.automationBed_Beta.utility.logsManager.LogsManager;
import Ituple.automationBed_Beta.utility.mailManager.MailManager;

public class SuiteBase {

	public static Read_XLS TestSuite= null;
	
	
	@BeforeSuite
	public void beforeSuite() throws Exception {
		init();
	}

	@AfterSuite
	public void afterSuite() {
		//MailManager.sendMail();
	}
	
	public void init(){
		TestSuite = new Read_XLS(System.getProperty("user.dir")+"//src//main//resources//datafiles//TestSuite.xlsx");
		Utils.uBase=new UtilityBase();
		Utils.addLog=LogsManager.createLogger("application");
	}
	 
}
